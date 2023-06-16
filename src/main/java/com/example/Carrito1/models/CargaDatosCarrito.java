package com.example.Carrito1.models;

import java.io.Serializable;

public class CargaDatosCarrito implements Serializable {  //serializable para poder enviarlo y recibirlo como JSON

    public String idPedido;   //acá usar Long no long

    public String idProducto;   //acá usar Long no long

    public int cantidad;

}
