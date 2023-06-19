package com.example.Carrito1.services;

import com.example.Carrito1.exceptions.IdNoEncontradoException;
import com.example.Carrito1.models.Productos;
import com.example.Carrito1.repositories.IProductosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductosService {

    @Autowired
    private IProductosRepository Iprods;

    public List<Productos> getListProductos(){

        return Iprods.findAll();
    }

    public Productos getProducto(String id) throws IdNoEncontradoException {

        return Iprods.findById(id)
                .orElseThrow(() -> new IdNoEncontradoException("No existe el registro solicitado con ID: "+ id));  // hay que atajar que devuelva un null, que no sería compatible con la clase Productos
    }

    // VERSION SIN DTO
    public Productos addProducto(Productos p){

        return Iprods.save(p);
    }

    public void deleteProducto(String id){

        Iprods.deleteById(id);
    }

    public Productos updateProducto(Productos p){

        return Iprods.saveAndFlush(p);
    }

}
