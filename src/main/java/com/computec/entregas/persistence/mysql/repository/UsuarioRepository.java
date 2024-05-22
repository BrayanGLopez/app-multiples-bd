package com.computec.entregas.persistence.mysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.computec.entregas.persistence.mysql.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

}
