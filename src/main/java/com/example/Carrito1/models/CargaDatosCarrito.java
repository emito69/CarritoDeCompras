package com.example.Carrito1.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public class CargaDatosCarrito implements Serializable {  //serializable para poder enviarlo y recibirlo como JSON

    //@NotBlank(message = "El idPedido no puede estar vacio")
    @NotNull (message = "El idPedido debe ser un String valido")
    public String idPedido;   //acá usar Long no long

    //@NotBlank(message = "El idProducto no puede estar vacio")
    @NotNull (message = "El idProducto debe ser un String valido")
    public String idProducto;   //acá usar Long no long

    @NotNull(message = "La cantidad debe ser un valor entero")
    @Min(value = 10, message = "La cantidad mínima es 10")
    public int cantidad;

}
