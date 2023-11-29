package com.dynns.cloudtecnologia.jasper.service.impl;

import com.dynns.cloudtecnologia.jasper.exception.GeralException;
import com.dynns.cloudtecnologia.jasper.service.JarperService;
import com.dynns.cloudtecnologia.jasper.util.ArquivoUtils;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.*;


@Service
public class JarperServiceImpl implements JarperService {

    //classpath: Aponta para a o pacote resource na raiz
    private static final String JASPER_DIRETORIO = "classpath:/jasper/lancamentos_relatorio.jasper";
    private static final Logger LOG = LoggerFactory.getLogger(JarperServiceImpl.class);

    @Autowired
    private ResourceLoader resourceLoader;

    @Override
    public String gerarRelatorio() {
        try {
            LOG.info(resourceLoader.getResource(JASPER_DIRETORIO).getFilename());

            // Carregando o arquivo .jasper
            InputStream jasperStream = resourceLoader.getResource(JASPER_DIRETORIO).getInputStream();

            // Compilando o relatório
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);

            // Preenchendo o relatório com os dados, se necessário
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null);

            // Exportando para PDF
            byte[] relatorioByte = exportarParaPDF2(jasperPrint);
            File arquivo = ArquivoUtils.byteTofile(relatorioByte, "relatorio.pdf");

            return arquivo.getName();

        } catch (IOException | JRException e) {
            throw new GeralException("Erro ao Gerar relatório! " + e.getCause());
        }
    }

    private byte[] exportarParaPDF2(JasperPrint jasperPrint) {
        try {
            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (JRException e) {
            throw new GeralException("Erro ao exportar para PDF" + e.getCause());
        }
    }
}
