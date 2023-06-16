package com.example.Carrito1.services;

import com.example.Carrito1.models.Pedidos;
import com.example.Carrito1.repositories.IPedidosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidosService {

    @Autowired
    private IPedidosRepository Ipeds;

    public List<Pedidos> getListPedidos(){

        return Ipeds.findAll();
    }

    public Pedidos getPedido(String id){

        return Ipeds.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe el registro solicitado")); // hay que atajar que devuelva un null, que no sería compatible con la clase Pedidos
    }

    public Pedidos addPedido(Pedidos p){

        return Ipeds.save(p);
    }

    public void deletePedido(String id){

        Ipeds.deleteById(id);
    }

}
