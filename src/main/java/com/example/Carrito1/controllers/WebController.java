package com.example.Carrito1.controllers;

import com.example.Carrito1.models.CargaDatosCarrito;
import com.example.Carrito1.models.Carrito;
import com.example.Carrito1.models.Pedidos;
import com.example.Carrito1.models.Productos;
import com.example.Carrito1.services.CarritosService;
import com.example.Carrito1.services.PedidosService;
import com.example.Carrito1.services.ProductosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/api/carrito1/")
public class WebController {

    @Autowired
    private ProductosService productosService;

    @Autowired
    private PedidosService pedidosService;

    @Autowired
    private CarritosService carritosService;

    /******** ******** ******* ******** ********/
    /******** END-POINTs para FRONT-END ********/
    /******** ******** ******* ******** ********/

    /******** PRODUCTOS: ********/

    @GetMapping("/productos/weblist")    // http://localhost:8080/api/carrito1/productos/weblist es la url de la API
    public String getListProductos(Model modelo){
        modelo.addAttribute("listaProductos", productosService.getListProductos());
        return "productos";
    }

    @GetMapping("/pedidos/weblist")    // http://localhost:8080/api/carrito1/productos/weblist es la url de la API
    public String getListPedidos(Model modelo){
        modelo.addAttribute("listaPedidos", pedidosService.getListPedidos());
        return "pedidos";
    }

    @PostMapping("/pedidos/webAddPedido1")    // http://localhost:8080/api/carrito1/productos/weblist es la url de la API
    public String addPedido1(){

        Pedidos nuevoPedido = new Pedidos(null, null);
        pedidosService.addPedido(nuevoPedido);

        return "redirect:/api/carrito1/pedidos/weblist";
    }

    @PostMapping("/pedidos/webAddPedido2")    // http://localhost:8080/api/carrito1/productos/weblist es la url de la API
    public String addPedido2(){

        Pedidos nuevoPedido = new Pedidos(null, null);
        pedidosService.addPedido(nuevoPedido);

        return "redirect:/api/carrito1/carrito/weblist";
    }

    @GetMapping("/carrito/weblist")    // http://localhost:8080/api/carrito1/productos/weblist es la url de la API
    public String getCarrito(Model modelo){

        Carrito carrito = new Carrito();

        CargaDatosCarrito cargaDatosCarrito = new CargaDatosCarrito();

        modelo.addAttribute("carrito", carrito);

        modelo.addAttribute("cargaDatosCarrito", cargaDatosCarrito);

        modelo.addAttribute("listaCarritos", carritosService.getListCarritos());
        modelo.addAttribute("listaProductos", productosService.getListProductos());
        modelo.addAttribute("listaPedidos", pedidosService.getListPedidos());

        return "carrito";
    }

    @PostMapping("/carrito/webAddProdCarr")    // http://localhost:8080/api/carrito1/productos/weblist es la url de la API
    public String addProductoAlCarrito(@ModelAttribute("cargaDatosCarrito") CargaDatosCarrito cargaDatosCarrito){

        carritosService.addProductoAlCarrito(cargaDatosCarrito);

        return "redirect:/api/carrito1/carrito/weblist";
    }

    @PostMapping("/carrito/totalPedido")    // http://localhost:8080/api/carrito1/productos/weblist es la url de la API
    public String getTotalPedido(@ModelAttribute("idPedidoCarrito") Long idPedidoCarrito, Model modelo){

        modelo.addAttribute("listaCarritoPedido", carritosService.getCarritoxPedido(idPedidoCarrito));
        Double total = carritosService.getTotalxPedido(idPedidoCarrito);
        modelo.addAttribute("total", total);

        return "carritoPedido";
    }

}
