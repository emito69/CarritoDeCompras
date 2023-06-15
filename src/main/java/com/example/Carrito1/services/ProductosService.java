package com.example.Carrito1.services;

import com.example.Carrito1.models.Productos;
import com.example.Carrito1.repositories.IProductosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class ProductosService {

    @Autowired
    private IProductosRepository Iprods;

    public List<Productos> getListProductos(){

        return Iprods.findAll();
    }

    public Object getProducto(String id){

        return Iprods.findById(id);
    }

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
