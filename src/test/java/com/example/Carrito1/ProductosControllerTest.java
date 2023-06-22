package com.example.Carrito1;

import com.example.Carrito1.controllers.ProductosController;
import com.example.Carrito1.models.Productos;
import com.example.Carrito1.services.ProductosService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.swing.interop.SwingInterOpUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.StatusResultMatchersExtensionsKt.isEqualTo;

// Vamos a probar la Capa COntroller -: https://reflectoring.io/spring-boot-web-controller-test/
//  normalmente webMvcTest se usa para TestUnitarios (es decir, sólo probamos una capa (por ejemplo la Controller)
// y nos da herramientas para simular (mock) el resto de las capas.
// vamos a simular la capa de SERVICIOS y conentramos las pruebas en la capa controller

//@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")    // ver CarritoApplication.java
@WebMvcTest(ProductosController.class) // Nos permite realizar pruebas sobre la capa del Controlador. Le definimos la Clase a probar para que limitar el alcance
public class ProductosControllerTest {

    @Autowired
    private MockMvc mvc; // Permite levantar un cliente que puede hacer llamadas HTTP

    @MockBean
    private ProductosService productosService;

    @Autowired
    private ObjectMapper mapper; // mapper para convertir a JSON

    @Test
    public void solo_chequear_URL_OK() throws Exception { // test POSITIVO

        mvc.perform(get("/api/carrito1/productos/list")
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }


    @Test
    public void getListProductos_OK() throws Exception { // test POSITIVO

        Productos producto1  = new Productos(1L, "Zanahoria", "Verduras", null, "Bolsa", 299.0, null, 1000, "Perecedero", true);
        Productos producto2  = new Productos(2L, "Manzana Roja", "Frutas", null, "Bolsa", 599.0, null, 1000, "Perecedero", true);
        Productos producto3  = new Productos(3L, "Manzana Roja", "Frutas", null, "Bolsa", 599.0, null, 1000, "Perecedero", true);

        List<Productos> listita = List.of(producto1, producto2, producto3);

        // Simulamos el Servicio al que llama el controlador:
        when(productosService.getListProductos()).thenReturn(listita);

        // System.out.println(listita.size());  //en este caso es 3
        mvc.perform(get("/api/carrito1/productos/list")
                        .contentType("application/json")       // le decimos el formato de envío del GET
                        .accept(MediaType.APPLICATION_JSON))   // le decimos el formato de recepción
                            .andExpect(content().contentType("application/json"))
                            .andExpect(jsonPath("$.length()").value(listita.size()))  // verificamos que el Service que estamos simulando ande bien y se está llamando chequeando el tamaño del JSON
                            .andExpect(status().isOk());
    }



    @Test
    public void getProducto_OK() throws Exception { // test POSITIVO
        Long id = 3L;
        Productos producto1  = new Productos( id, "Zanahoria", "Verduras", null, "Bolsa", 299.0, null, 1000, "Perecedero", true);

        // Simulamos el Servicio al que llama el controlador:
        when(productosService.getProducto(id.toString())).thenReturn(producto1); //

        mvc.perform(get("/api/carrito1/productos/{id}", id) // la pasamos el path variable id
                        // en este caso es {id} es 3, así que el controller va a llamar a productosService.getProducto("4")
                        .contentType("application/json")       // le decimos el formato de envío del GET
                        .accept(MediaType.APPLICATION_JSON))   // le decimos el formato de recepción
                            .andExpect(content().contentType("application/json"))
                            .andExpect(jsonPath("$.id").value(producto1.getId()))  // verificamos que el Service que estamos simulando ande bien y se está llamando chequeando el tamaño del JSON
                            .andExpect(status().isOk());
    }

    @Test
    public void getProducto_con_CAPTOR_OK() throws Exception { // test POSITIVO de llamada al SERVICIO usando el pathvariable correspondiente
        Long id = 3L;
        Productos producto1  = new Productos( id, "Zanahoria", "Verduras", null, "Bolsa", 299.0, null, 1000, "Perecedero", true);

        // OTRA ALTERNATIVA SIN SIMULAR EL SERVICE
        //when(productosService.getProducto(id.toString())).thenReturn(producto1); //

        mvc.perform(get("/api/carrito1/productos/{id}", id) // la pasamos el path variable id
                        // en este caso es {id} es 3, así que el controller va a llamar a productosService.getProducto("4")
                        .contentType("application/json")       // le decimos el formato de envío del GET
                        .accept(MediaType.APPLICATION_JSON));   // le decimos el formato de recepción

        // EN LUGAR DE SIMULAR EL SERVICIO, CAPTURAMOS el objeto (String) que le pasamos al servicio
/*
Luego de la llamada HTTP al controlador, usamos un ArgumentCaptor para capturar el objeto (en este caso el String)
 que le estamos pasando al service productosService.getProducto(id) y validamos que contiene
  los valores esperados.
 */

        ArgumentCaptor<String> argCaptor = ArgumentCaptor.forClass(String.class);
        verify(productosService, times(1)).getProducto(argCaptor.capture());
        //assertThat(argCaptor.capture().toString()).equals(id.toString());
        assertEquals(argCaptor.getValue(), id.toString());

        // con esto verificamos que en la desserialziación del ID fue correcta
        // que se llama una única vez al servicio


    }


    @Test //en este vamos a hacer el Test integrando el servicio completo (Controlador-Service)
    public void addProducto_OK() throws Exception { // test POSITIVO

        var request = new Productos(null, "Jabón Líquido", "Limpieza",
                null, "Bolsa", 522.19, null, 1000,
                "No perecedero", true);

        // Simulamos el Servicio al que llama el controlador:
        when(productosService.addProducto(request)).thenReturn(new Productos(1L, "Jabón Líquido", "Limpieza",
                null, "Bolsa", 522.19, null, 1000,
                "No perecedero", true)); //

        // armamos la estructura para realizar la llamada al Controlador
        mvc.perform(post("/api/carrito1/productos/add")
                        .content(mapper.writeValueAsString(request))  // transformamos a JSON
                        .contentType(MediaType.APPLICATION_JSON)   // le decimos el formato del envío
                        .accept(MediaType.APPLICATION_JSON))   // le decimos el formato de recepción
                .andExpect(jsonPath("$.nombre").value(request.getNombre()))
                .andExpect(jsonPath("$.categoria").value(request.getCategoria()))
                .andExpect(jsonPath("$.subcategoria").value(request.getSubcategoria()))
                .andExpect(jsonPath("$.descripcion").value(request.getDescripcion()))
                .andExpect(jsonPath("$.precio").value(request.getPrecio()))
                .andExpect(jsonPath("$.foto").value(request.getFoto()))
                .andExpect(jsonPath("$.tamanio").value(request.getTamanio()))
                .andExpect(jsonPath("$.tipo").value(request.getTipo()))
                .andExpect(status().isCreated());    //devolvermos CREATED si todo salio bien

    }

    @Test //en este vamos a verificar la respuesta de las Validaciones con el @Valid
    public void addProducto_CON_ARGUMENTOS_INVALIDOS_OK() throws Exception { // test POSITIVO de entradas inválidas

        // armamos un request con valores NO PERMITIDOS (POR EJEMPLO, EL NOMBRE vacio)
        Productos request = new Productos(null, "", "Limpieza",
                null, "Bolsa", 522.22, null, 1000,
                "No perecedero", true);

        // la validación se da cuando llega el llamado HTTP, por lo que ni siquiera va a llamar al servicio

        // armamos la estructura para realizar la llamada al Controlador
        mvc.perform(post("/api/carrito1/productos/add")
                        .content(mapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());    // verificamos que devuelve HttpStatus.BAD_REQUEST como lo programamos en la Excepción manejoDeArgumentosInvalidos

        // SE PODRIA HACER UN TEST PARA CADA TIPOD E DATO INVALIDO

    }

}
