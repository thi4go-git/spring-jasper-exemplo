package com.dynns.cloudtecnologia.jasper.configuration;

import com.dynns.cloudtecnologia.jasper.exception.GeralException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Configuration
public class ConfigConexao {

    @Bean
    public Connection connection(DataSource dataSourcer) {
        try {
            return dataSourcer.getConnection();
        } catch (SQLException e) {
            throw new GeralException("Erro de Conexão com o Banco: " + e.getCause());
        }
    }

}
