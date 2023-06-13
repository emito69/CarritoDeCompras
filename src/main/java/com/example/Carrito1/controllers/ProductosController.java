package com.example.Carrito1.controllers;

import com.example.Carrito1.models.Productos;
import com.example.Carrito1.services.ProductosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/carrito1/productos")
public class ProductosController {

    @Autowired
    private ProductosService productosService;

    @GetMapping("list")    // ../api/carrito1/productos/list es la url de la API
    public ResponseEntity<List<Productos>> getListProductos(){
        return ResponseEntity.ok(productosService.getListProductos());
    }



}
