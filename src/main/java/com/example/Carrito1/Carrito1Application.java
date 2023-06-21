package com.example.Carrito1;

import com.example.Carrito1.models.Carrito;
import com.example.Carrito1.models.Pedidos;
import com.example.Carrito1.models.Productos;
import com.example.Carrito1.repositories.ICarritoRepository;
import com.example.Carrito1.repositories.IPedidosRepository;
import com.example.Carrito1.repositories.IProductosRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class Carrito1Application {

	public static void main(String[] args) {
		SpringApplication.run(Carrito1Application.class, args);
	}

	@Profile("!test") // para evitar que se ejecute durante el Test, sino me fallaba. además incluir @ActiveProfiles("test") en la clase de los Test
	@Bean
	CommandLineRunner commandLineRunner(
			IProductosRepository productosRepo,  // inyecto el repositorio (equivalente al autowired)
			IPedidosRepository pedidosRepo,
			ICarritoRepository carritoRepo
		)
		{
		return args -> {
			Productos producto1  = productosRepo.save(new Productos(null, "Zanahoria", "Verduras", null, "Bolsa", 299.0, null, 1000, "Perecedero", true));
			Productos producto2  = productosRepo.save(new Productos(null, "Manzana Roja", "Frutas", null, "Bolsa", 599.0, null, 1000, "Perecedero", true));
			Productos producto3  = productosRepo.save(new Productos(null, "Lomo", "Carnes", null, "Embasado", 4500.0, null, 1000, "Perecedero", true));
			Productos producto4  = productosRepo.save(new Productos(null, "Leche", "Lacteos", null, "Sachet", 292.0, null, 1000, "Perecedero", true));

			/*
			Pedidos pedido1 = pedidosRepo.save(new Pedidos(null, null));
			Pedidos pedido2 = pedidosRepo.save(new Pedidos(null, null));
			*/

			/*
			carritoRepo.save(new Carrito(null, pedido1, Set.of(producto1), 500, (producto1.getPrecio()/ producto1.getTamanio()) * 500));
			carritoRepo.save(new Carrito(null, pedido1, Set.of(producto2), 1000, (producto2.getPrecio()/ producto2.getTamanio()) * 1000));
			carritoRepo.save(new Carrito(null, pedido2, Set.of(producto3), 1500, (producto3.getPrecio()/ producto3.getTamanio()) * 1500));
			carritoRepo.save(new Carrito(null, pedido2, Set.of(producto4), 1000, (producto4.getPrecio()/ producto4.getTamanio()) * 1000));
			*/
			//pedido1.setProductos(Set.of(producto1, producto2));

			// System.out.println(pedido1.toString());


			// System.out.println(carritoRepo.toString());


			//pedido1.getProductos().forEach(a -> System.out.println("Nombre: " + a.getNombre() + " ; Precio: " + a.getPrecio()));

			//System.out.println(pedido1.toString());
		};
	};



}
