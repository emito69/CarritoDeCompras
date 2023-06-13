package com.example.Carrito1.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;


// con esto ya CREA LA TABLA EN LA BASE DE DATOS

@Data   // Getters y Setters
@AllArgsConstructor     // Todos los Constructores (con todas las combinaciones posibles)
@NoArgsConstructor
@Entity
@Table(name="pedidos")  // le da el nombre a la tabla que se va a crear cuando se establezca la comm con la BD
public class Pedidos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)   // generacion de la clave primaria
    @Column(name = "id", nullable = false, length = 11)
    private long id;

    //@OneToMany(fetch = FetchType.LAZY)
    //@JoinColumn(name="productos_id")
    //private List<Productos> producto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="productos_id")
    private Productos producto;

    @Column(name = "cantidad", length = 10)
    @NotBlank @NotNull
    private int cantidad;

    @Column(name = "precio")
    @DecimalMin(value = "0.1")
    @NotBlank @NotNull
    private Double precio;

    @Column(name = "total")
    @DecimalMin(value = "0.1")
    @NotBlank @NotNull
    private Double total;


}
