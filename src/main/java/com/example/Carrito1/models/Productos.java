package com.example.Carrito1.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.List;


// con esto ya CREA LA TABLA EN LA BASE DE DATOS

@Data   // Getters y Setters
@AllArgsConstructor     // Todos los Constructores (con todas las combinaciones posibles)
@NoArgsConstructor
@Entity
@Table(name="productos")  // le da el nombre a la tabla que se va a crear cuando se establezca la comm con la BD
@SQLDelete(sql = "UPDATE productos SET enable = false WHERE id = ?")   // consulta SQL para hacer eliminacion de datos
                                    // pero teniendo en cuenta esta clausula (en lugar de borrar deshabilitamos)
                                    // la annotation @SQLDelete me sobreescribe cualquier .delete que utilice desde JPARepository

@Where(clause = "enable = true")   // la annotation @Where me filtra cualquier query desde JPARepository
public class Productos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)   // generacion de la clave primaria
    @Column(name = "id", nullable = false, length = 11)
    private Long id;   //acá usar Long no long

    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name="productos_id")
    //private Pedidos pedido;

    //@OneToMany(fetch = FetchType.LAZY)
    //private List<Pedidos> pedido;

    @Column(name = "nombre", nullable = false, length = 255)
    @NotBlank @NotNull
    private String nombre;

    @Column(name = "categoria", length = 20)
    @NotBlank @NotNull
    private String categoria;

    @Column(name = "subcategoria", length = 20)
    private String subcategoria;

    @Column(name = "descripcion", length = 180)
    @NotBlank @NotNull
    private String descripcion;

    @Column(name = "precio")
    @DecimalMin(value = "0.1")
    @NotNull
    private Double precio;

    @Column(name = "foto", length = 255)
    private String foto;

    @Column(name = "tamanio", length = 10)
    @NotNull
    private int tamanio;

    @Column(name = "tipo", length = 150)
    @NotBlank @NotNull
    private String tipo;

    @Column(name = "enable")
    private boolean enabled = true;
}
