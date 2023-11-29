package com.dynns.cloudtecnologia.jasper.rest.controller;

import com.dynns.cloudtecnologia.jasper.service.impl.JarperServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/relatorio-jasper")
public class JasperController {


    @Autowired
    private JarperServiceImpl jasperService;

    @GetMapping
    public ResponseEntity<String> gerarRelatorio() {
        String name = jasperService.gerarRelatorio();
        return ResponseEntity.ok(name);
    }

}
