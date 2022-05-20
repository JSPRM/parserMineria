package com.jsprm.parsermineria.services;

import com.jsprm.parsermineria.exceptions.*;
import com.jsprm.parsermineria.models.entities.Cliente;
import com.jsprm.parsermineria.models.entities.Factura;
import com.jsprm.parsermineria.models.entities.Producto;
import com.jsprm.parsermineria.models.entities.ProductoEnFactura;
import com.jsprm.parsermineria.services.parser.Header;
import com.jsprm.parsermineria.services.parser.Item;
import com.jsprm.parsermineria.services.parser.Parser;
import com.jsprm.parsermineria.services.parser.Trailer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;


@Service
public class ParserServiceImpl implements ParserService{

    private final ClienteService clienteService;
    private final ProductoService productoService;
    private final FacturaService facturaService;
    private final ProductoEnFacturaService productoEnFacturaService;


    @Autowired
    public ParserServiceImpl(ClienteService clienteService, ProductoService productoService, FacturaService facturaService, ProductoEnFacturaService productoEnFacturaService) {
        this.clienteService = clienteService;
        this.productoService = productoService;
        this.facturaService = facturaService;
        this.productoEnFacturaService = productoEnFacturaService;
    }


    @Override
    public Boolean parserMain(String[] direcciones) {
        for(String direccion: direcciones){
            try {
                Scanner scanner = new Scanner(new File(direccion));
                Parser parserArchivo = new Parser();
                while (scanner.hasNextLine()) {
                    String lineaDeTexto = scanner.nextLine();
                    parserArchivo.procesar(lineaDeTexto);
                }
                scanner.close();
                persistir(parserArchivo);
                return true;
            } catch (FileNotFoundException e) {
                throw new ArchivoNoValido("Archivo no encontrado");
            }
        }
        return null;
    }


    public void persistir(Parser parser){
        try{
            validarPersistir(parser);
        }
        catch(Exception e) {
            throw new ArchivoNoValido("Fail");
        }
    }

    Factura facturaActual;
    Integer itemCount;
    ArrayList<ProductoEnFactura> productosEnFacturaActual;
    Float totalNetoActual;

    public void validarPersistir(Parser parser){
        if(parser.getOrden().matches("^([hH][iI]+[tT])+$")){
            for(Object objeto: parser.getObjetos()){
                if(objeto.getClass() == Header.class){
                    Optional<Cliente> oCliente = clienteService.buscarPorNumero(((Header) objeto).getNumeroCliente());
                    if(oCliente.isPresent()){
                        facturaActual = new Factura(((Header) objeto).getNumeroFactura().toUpperCase(),
                                ((Header) objeto).getTipoMoneda(), ((Header) objeto).getFechaFactura(), oCliente.get());
                        productosEnFacturaActual = new ArrayList<>();
                        itemCount=0;
                        totalNetoActual=0f;
                    }else{
                        throw new NotFoundException(String.format("Cliente %s inexistente", ((Header) objeto).getNumeroCliente()));
                    }
                }else if(objeto.getClass() == Item.class){
                    Optional<Producto> oProducto = productoService.buscarPorNumero(((Item) objeto).getNumeroProducto());
                    if(oProducto.isPresent()){
                        productosEnFacturaActual.add(new ProductoEnFactura(
                                Integer.parseInt(((Item) objeto).getCantidadProducto().trim()),
                                facturaActual,
                                oProducto.get()
                        ));
                        totalNetoActual += Float.parseFloat(((Item) objeto).getValorNetoProducto().trim());
                        itemCount += 1;
                    }else{
                        throw new NotFoundException(String.format("Producto %s inexistente", ((Item) objeto).getNumeroProducto()));
                    }
                }else if(objeto.getClass() == Trailer.class){
                    if(itemCount == Integer.parseInt(((Trailer) objeto).getTotalItemsEnFactura().trim())){
                        if(totalNetoActual == Float.parseFloat(((Trailer) objeto).getValorNetoTotalItems().trim())){
                            facturaService.guardar(facturaActual);
                            for(ProductoEnFactura productoEnFactura: productosEnFacturaActual){
                                productoEnFacturaService.guardar(productoEnFactura);
                            }
                        }else{
                            throw new TotalNoValido(String.format("Total neto no coincide: %f != %s", totalNetoActual,((Trailer) objeto).getValorNetoTotalItems().trim()));
                        }
                    }else{
                        throw new NumeroItemsNoValido(String.format("Numero de items no coincide: %d != %s", itemCount,((Trailer) objeto).getTotalItemsEnFactura()));
                    }
                }
            }
        }else{
            throw new OrdenNoValido("Archivo con formato no valido");
        }
    }
}