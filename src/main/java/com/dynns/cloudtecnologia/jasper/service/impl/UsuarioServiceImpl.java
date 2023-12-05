package com.dynns.cloudtecnologia.jasper.service.impl;

import com.dynns.cloudtecnologia.jasper.model.entity.Usuario;
import com.dynns.cloudtecnologia.jasper.model.repository.UsuarioRepository;
import com.dynns.cloudtecnologia.jasper.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {


    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario findByAll() {
        usuarioRepository.findAll();
        return usuarioRepository.findAll().
    }
}
