package com.example.Carrito1.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


// con esto ya CREA LA TABLA EN LA BASE DE DATOS

@Data   // Getters y Setters
@AllArgsConstructor     // Todos los Constructores (con todas las combinaciones posibles)
@NoArgsConstructor
@Entity
@Table(name="carrito")  // le da el nombre a la tabla que se va a crear cuando se establezca la comm con la BD

public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)   // generacion de la clave primaria
    @Column(name = "id", nullable = false, length = 11)
    private Long id;   //acá usar Long no long

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // https://stackoverflow.com/questions/52656517/no-serializer-found-for-class-org-hibernate-proxy-pojo-bytebuddy-bytebuddyinterc
    @ManyToOne(fetch = FetchType.LAZY,     //EAGER para que me traiga todos los productos cuando lo quiera ver
                optional = false        // siempre tiene que haber un pedido asociado a este carrito
                )
    @JoinColumn(name="pedidos_id")  // por defecto será pedidos_id-carrito_id
    private Pedidos pedidos;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // https://stackoverflow.com/questions/52656517/no-serializer-found-for-class-org-hibernate-proxy-pojo-bytebuddy-bytebuddyinterc
    @ManyToMany(fetch = FetchType.EAGER) //EAGER para que me traiga todos los productos cuando lo quiera ver
    @JoinTable(
            name="carrito_producto", // nombre de la "tabla" con las columnas que quiero como foreings
            inverseJoinColumns = {                       // toma por defecto la columna id de la tabla carrito
                    @JoinColumn(name = "fk_productos"), // toma por defecto la columna id de la tabla productos
            }
    )
    private Set<Productos> productos = new HashSet<>(); // para que no se repitan

    @Column(name = "cantidad", length = 10)
    //@NotNull
    private int cantidad;

    @Column(name = "total")
    @DecimalMin(value = "0.1")
    //@NotNull
    private Double total;

}
