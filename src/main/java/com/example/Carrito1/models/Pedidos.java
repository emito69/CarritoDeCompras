package com.example.Carrito1.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


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
    private Long id;   //acá usar Long no long

    @OneToMany(fetch = FetchType.EAGER,     //EAGER para que me traiga todos los productos cuando lo quiera ver
            cascade = CascadeType.ALL) // si cambiamos un nro de pedido, que se actualice automáticamente
    private List<Carrito> carrito;

/*    @Column(name = "cantidad", length = 10)
    //@NotNull
    private int cantidad;*/


}
