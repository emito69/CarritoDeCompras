package com.example.Carrito1.services;

import com.example.Carrito1.models.CargaDatosCarrito;
import com.example.Carrito1.models.Carrito;
import com.example.Carrito1.models.Pedidos;
import com.example.Carrito1.models.Productos;
import com.example.Carrito1.repositories.ICarritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CarritosService {

    @Autowired
    private ICarritoRepository Icarritos;

    @Autowired
    private ProductosService productosService;   // esto debería hacerlo con mappers, pero por ahora lo implemento solapando capas

    @Autowired
    private PedidosService pedidosService;   // esto debería hacerlo con mappers, pero por ahora lo implemento solapando capas


    public List<Carrito> getListCarritos(){

        return Icarritos.findAll();
    }

    public List<Carrito> getCarritoxPedido(Long id){

        return Icarritos.findByPedidos_Id(id);
                //.orElseThrow(() -> new RuntimeException("Compte Introuvable")); // hay que atajar que devuelva un null, que no sería compatible con la clase Carrito
    }

    public Double getTotalxPedido(Long id){

        List<Carrito> carritosX = Icarritos.findByPedidos_Id(id);
                //orElseThrow(() -> new RuntimeException("No existe el registro solicitado")); // hay que atajar que devuelva un null, que no sería compatible con la clase Carrito

        Double total = 0.0;

        //carritosX.forEach(a -> Double total += a.getTotal()); // Error: local variables referenced from a lambda expression must be final or effectively final

        for (Carrito c: carritosX) {

            total += c.getTotal();
        }

        return total;
    }

    public Carrito addProductoAlCarrito(CargaDatosCarrito carr){

        Pedidos pedidoX = pedidosService.getPedido(carr.idPedido);
        Productos productoX = productosService.getProducto(carr.idProducto);

        return Icarritos.save(new Carrito(null, pedidoX , Set.of(productoX), carr.cantidad, (productoX.getPrecio() /productoX.getTamanio() )* carr.cantidad));

    }

    public void deleteCarrito(String id){

        Icarritos.deleteById(id);
    }

    public Carrito updateCarrito(Carrito p){

        return Icarritos.saveAndFlush(p);
    }

}
