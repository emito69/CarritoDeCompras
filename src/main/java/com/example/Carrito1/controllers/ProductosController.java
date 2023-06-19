package com.example.Carrito1.controllers;


import com.example.Carrito1.models.Productos;
import com.example.Carrito1.services.ProductosService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    // como en prouctosService lanzamos una exception que extiende una RuntimeExcpetion no es necesario agregar el trhows ya que igual la ataja nuestro @RestControllerAdvice
    @GetMapping("/{id}")    // ../api/carrito1/productos/list es la url de la API
    public ResponseEntity<Object> getProducto(@PathVariable String id){
        return ResponseEntity.ok(productosService.getProducto(id)); // implementar el método findById(id) en la interface productosService para poder utilizarlo
    }

    @PostMapping("add")    // ../api/carrito1/productos/add es la url de la API
    public ResponseEntity<Productos> addProducto(@RequestBody @Valid Productos p){ // agregamos la annotation de validación y usamos la neva clase DTO
        // la validacion devuelve "MethodArgumentNotValidException" y devuelve el/los argumentos[x] con error

        return new ResponseEntity<>(productosService.addProducto(p), HttpStatus.CREATED);
    }

    // como en prouctosService lanzamos una exception que extiende una RuntimeExcpetion no es necesario agregar el trhows ya que igual la ataja nuestro @RestControllerAdvice
    @DeleteMapping (value = "/{id}")    // ../api/carrito1/productos/{id} la url de la API
    public void deleteProducto(@PathVariable String id){
        productosService.deleteProducto(id); // implementar el método deleteById(id) en la interface productosService para poder utilizarlo
    }

    @PutMapping ("update")    // ../api/carrito1/productos/ la url de la API en respuesta a un POST toma el body
    public ResponseEntity<Productos> updateProducto(@RequestBody @Valid Productos p){
        return ResponseEntity.ok(productosService.updateProducto(p)); // implementar el método deleteById(id) en la interface productosService para poder utilizarlo
    }

}
