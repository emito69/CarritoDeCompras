package com.example.Carrito1.services;

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

}
