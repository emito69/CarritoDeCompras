package com.example.Carrito1.repositories;


import com.example.Carrito1.models.Productos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface IProductosRepository extends JpaRepository <Productos, String> {

}
