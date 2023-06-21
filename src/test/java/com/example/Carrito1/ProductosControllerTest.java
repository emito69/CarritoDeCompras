package com.example.Carrito1;



import com.example.Carrito1.controllers.ProductosController;
import com.example.Carrito1.models.Productos;
import com.example.Carrito1.services.ProductosService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
                    .andExpect(status().isCreated());       //devolvermos CREATED si todo salió bien

    }
@Test
    public void getListProductos_OK() throws Exception { // test POSITIVO

        Productos producto1  = new Productos(1L, "Zanahoria", "Verduras", null, "Bolsa", 299.0, null, 1000, "Perecedero", true);
        Productos producto2  = new Productos(2L, "Manzana Roja", "Frutas", null, "Bolsa", 599.0, null, 1000, "Perecedero", true);

        List<Productos> listita = List.of(producto1, producto2);

        // Simulamos el Servicio al que llama el controlador:
        when(productosService.getListProductos()).thenReturn(listita); //

        mvc.perform(get("/api/carrito1/productos/list")
                        .contentType("application/json"))
                        .andExpect(status().isOk());
    }

}
