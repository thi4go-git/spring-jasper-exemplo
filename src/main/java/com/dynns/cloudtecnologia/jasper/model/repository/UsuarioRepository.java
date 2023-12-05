package com.dynns.cloudtecnologia.jasper.model.repository;

import com.dynns.cloudtecnologia.jasper.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
