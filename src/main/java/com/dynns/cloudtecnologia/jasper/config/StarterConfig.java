package com.dynns.cloudtecnologia.jasper.config;

import com.dynns.cloudtecnologia.jasper.service.impl.JasperServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StarterConfig {

    private static final Logger LOG = LoggerFactory.getLogger(StarterConfig.class);

    @Autowired
    private JasperServiceImpl jasperService;

    @Bean
    public CommandLineRunner executar() {
        return args -> {
            LOG.info("::: Gerando Relatório... :::");
            String relatorioGerado = jasperService.geraRelatorio();
            LOG.info("::: Gerado com sucesso! "+relatorioGerado+" :::");
        };
    }
}
