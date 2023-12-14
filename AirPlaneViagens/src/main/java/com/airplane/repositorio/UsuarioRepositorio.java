package com.airplane.repositorio;


import com.airplane.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
    
}