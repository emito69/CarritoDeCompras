package com.example.Carrito1.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.List;


// con esto ya CREA LA TABLA EN LA BASE DE DATOS

@Data   // Getters y Setters
@AllArgsConstructor // Todos los Constructores (con todas las combinaciones posibles)
@NoArgsConstructor
@Entity
@Table(name="productos")  // le da el nombre a la tabla que se va a crear cuando se establezca la comm con la BD
@SQLDelete(sql = "UPDATE productos SET enable = false WHERE id = ?")   // consulta SQL para hacer eliminacion de datos
                                    // pero teniendo en cuenta esta clausula (en lugar de borrar deshabilitamos)
                                    // la annotation @SQLDelete me sobreescribe cualquier .delete que utilice desde JPARepository

@Where(clause = "enable = true")   // la annotation @Where me filtra cualquier query desde JPARepository
// una vez implementado este SOFT-DELETE, sólo podrán actualizarse productos con enable=true.
// no se puede vovler a habilitarlos utilizando esta clase.
public class Productos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)   // generacion de la clave primaria
    @Column(name = "id", nullable = false, length = 11)
    private Long id;   //acá usar Long no long

    @Column(name = "nombre", nullable = false, length = 255)
    @NotBlank (message = "El nombre del producto no puede estar vacío")
    @NotNull (message = "El nombre del producto no puede estar vacío")
    private String nombre;

    @Column(name = "categoria", length = 20)
    @NotBlank (message = "La categoría de producto no puede estar vacía")
    @NotNull (message = "La categoría de producto no puede estar vacía")
    private String categoria;

    @Column(name = "subcategoria", length = 20)
    private String subcategoria;

    @Column(name = "descripcion", length = 180)
    @NotBlank (message = "La descripción del producto no puede estar vacía")
    @NotNull (message = "La categoría de producto no puede estar vacía")
    private String descripcion;

    @Column(name = "precio")
    @DecimalMin(value = "0.1", message = "EL precio mínimo es 0.1")
    @NotNull
    private Double precio;

    @Column(name = "foto", length = 255)
    private String foto;

    @Column(name = "tamanio", length = 10)
    @NotNull (message = "El tamaño debe ser un valor entero")
    @Min(value = 10, message = "El tamaño mínimo es 10")
    private int tamanio;

    @Column(name = "tipo", length = 150)
    @NotBlank (message = "El tipo no puede estar vacío")
    @NotNull (message = "El tipo no puede estar vacío")
    private String tipo;

    @Column(name = "enable")
    private boolean enabled = true;
}
