package com.dynns.cloudtecnologia.jasper.rest.controller;

import com.dynns.cloudtecnologia.jasper.service.impl.JasperServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;


@RestController
@RequestMapping("/jasper")
public class JasperController {

    @Autowired
    private JasperServiceImpl jasperService;

    @Autowired
    private Connection connection;


    @GetMapping("testa-conexao-direta")
    public ResponseEntity<String> testeDeConexaoDireta() {
        String status = connection != null ?
                "Conexão realizada com Sucesso!" :
                "Erro ao Conectar!";
        return ResponseEntity.ok().body(status);
    }

    @GetMapping("xls-emater")
    public ResponseEntity<String> geraRelatorioFolhaEmaterXLS() {
        String arquivoName = "Arquivo gerado: ".concat(jasperService.geraRelatorioFolhaEmater());
        return ResponseEntity.ok().body(arquivoName);
    }

    @GetMapping("pdf-conexao-direta")
    public ResponseEntity<String> geraRelatorioPdfCpnexaoDireta() {
        String arquivoName = "Arquivo gerado: ".concat(jasperService.gerarRelatorioConexaoDireta());
        return ResponseEntity.ok().body(arquivoName);
    }

}
