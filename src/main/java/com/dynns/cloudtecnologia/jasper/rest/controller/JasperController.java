package com.dynns.cloudtecnologia.jasper.rest.controller;

import com.dynns.cloudtecnologia.jasper.service.impl.JasperServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jasper")
public class JasperController {

    @Autowired
    private JasperServiceImpl jasperService;

    @GetMapping("xls-emater")
    public ResponseEntity<String> geraRelatorioFolhaEmaterXLS(){
        String arquivoName = "Arquivo gerado: ".concat(jasperService.geraRelatorioFolhaEmater());
        return ResponseEntity.ok().body(arquivoName);
    }

}
