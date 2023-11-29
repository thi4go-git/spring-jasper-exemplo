package com.dynns.cloudtecnologia.jasper.service.impl;

import com.dynns.cloudtecnologia.jasper.service.JarperService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Service
public class JarperServiceImpl implements JarperService {

    //Aponta para a o pacote resource na raiz
    private static final String JASPER_DIRETORIO = "classpath:/jasper/lancamentos_relatorio.jasper";

    @Autowired
    private ResourceLoader resourceLoader;

    @Override
    public  byte[] gerarRelatorio() {

        try {
            // Carregando o arquivo .jasper
            InputStream jasperStream = resourceLoader.getResource(JASPER_DIRETORIO).getInputStream();

            // Compilando o relatório
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);

            // Preenchendo o relatório com os dados, se necessário
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null);

            // Exportando para PDF
            return exportarParaPDF(jasperPrint);

        } catch (IOException | JRException e) {
            throw new RuntimeException(e);
        }

    }


    private byte[] exportarParaPDF(JasperPrint jasperPrint) throws JRException {
        // Exportador para PDF
        JRPdfExporter pdfExporter = new JRPdfExporter();

        // Configuração do exportador
        pdfExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);

        // Exportando para byte array
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        pdfExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
        pdfExporter.exportReport();

        return baos.toByteArray();
    }
}
