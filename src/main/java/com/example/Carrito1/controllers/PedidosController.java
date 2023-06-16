package com.example.Carrito1.controllers;

import com.example.Carrito1.models.Pedidos;
import com.example.Carrito1.services.PedidosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/carrito1/pedidos")
public class PedidosController {

    @Autowired
    private PedidosService pedidosService;

    @GetMapping("list")    // ../api/carrito1/pedidos/list es la url de la API
    public ResponseEntity<List<Pedidos>> getListPedidos(){
        return ResponseEntity.ok(pedidosService.getListPedidos());
    }

    @GetMapping("/{id}")    // ../api/carrito1/pedidos/list es la url de la API
    public ResponseEntity<Object> getPedido(@PathVariable String id){
        return ResponseEntity.ok(pedidosService.getPedido(id)); // implementar el método findById(id) en la interface pedidosService para poder utilizarlo
    }

    @PostMapping("add")    // ../api/carrito1/pedidos/add es la url de la API
    public ResponseEntity<Pedidos> addPedido(@RequestBody Pedidos p){
        return ResponseEntity.ok(pedidosService.addPedido(p));
    }

    @DeleteMapping(value = "/{id}")    // ../api/pedidos/{id} la url de la API
    public void deletePedido(@PathVariable String id){
        pedidosService.deletePedido(id); // implementar el método deleteById(id) en la interface pedidosService para poder utilizarlo
    }

}
