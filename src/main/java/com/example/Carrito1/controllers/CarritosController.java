package com.example.Carrito1.controllers;

import com.example.Carrito1.models.CargaDatosCarrito;
import com.example.Carrito1.models.Carrito;
import com.example.Carrito1.services.CarritosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/carrito1/carritos")
public class CarritosController {

    @Autowired
    private CarritosService carritosService;

    @GetMapping("list")    // ../api/carrito1/carritos/list es la url de la API
    public ResponseEntity<List<Carrito>> getListCarritos(){
        return ResponseEntity.ok(carritosService.getListCarritos());
    }

    @GetMapping("/{id}")    // ../api/carrito1/carritos/list es la url de la API
    public ResponseEntity<Object> getCarritoxPedido(@PathVariable Long id){
        return ResponseEntity.ok(carritosService.getCarritoxPedido(id)); // implementar el método findById(id) en la interface carritosService para poder utilizarlo
    }

    @GetMapping("total/{id}")    // ../api/carrito1/carritos/list es la url de la API
    public ResponseEntity<Double> getTotalxPedido(@PathVariable Long id){
        return ResponseEntity.ok(carritosService.getTotalxPedido(id)); // implementar el método findById(id) en la interface carritosService para poder utilizarlo
    }


    @PostMapping("add")    // ../api/carrito1/carritos/add es la url de la API
    public ResponseEntity<Object> addProductoAlCarrito(@RequestBody CargaDatosCarrito p){

        return ResponseEntity.ok(carritosService.addProductoAlCarrito(p));
    }

    @DeleteMapping (value = "/{id}")    // ../api/carrito1/carritos/{id} la url de la API
    public void deleteCarrito(@PathVariable String id){

        carritosService.deleteCarrito(id); // implementar el método deleteById(id) en la interface carritosService para poder utilizarlo
    }

    @PutMapping ("update")    // ../api/carrito1/carritos/ la url de la API en respuesta a un POST toma el body
    public ResponseEntity<Carrito> updateCarrito(@RequestBody Carrito p){
        return ResponseEntity.ok(carritosService.updateCarrito(p)); // implementar el método deleteById(id) en la interface carritosService para poder utilizarlo
    }

}


