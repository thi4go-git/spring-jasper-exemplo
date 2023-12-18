package com.dynns.cloudtecnologia.jasper.utils;

import com.dynns.cloudtecnologia.jasper.exception.GeralException;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.*;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import java.io.*;
import java.nio.file.Files;
import java.util.Map;

@Component
public class UtilJasper {
    private UtilJasper() {
    }

    /**
     * Para gerar o relatório JASPER Usando o arquivo .jrxml
     * que se encontra na pasta "jasper" na raiz resource,
     * foi necessário adicionar uma fonte padrão no DOCKERFILE
     * para não ocorrer os erros no DOCKER:
     * -> Error initializing graphic environment
     * -> Error while loading available fonts
     */
    public static JasperPrint gerarJasperPrintByJRXML(
            Resource resourceJrxml, Map<String, Object> parameters, JRBeanCollectionDataSource dataSource
    )  {

        InputStream streamJrxml = null;
        try {
            streamJrxml = resourceJrxml.getInputStream();
        } catch (IOException e) {
            throw new GeralException("Erro de IOException ao gerar InputStream do arquivo: "+resourceJrxml.getFilename()+" - "+e.getCause());
        }

        JasperReport report = null;
        try {
            report = JasperCompileManager.compileReport(streamJrxml);
        } catch (JRException e) {
            throw new GeralException("Erro de JRException ao gerar JasperReport com o InputStream, causa: "+e.getCause());
        }

        try {
            return JasperFillManager.fillReport(report,parameters,dataSource);
        } catch (JRException e) {
            throw new GeralException("Erro de JRException ao gerar JasperPrint(report,parameters,dataSource), causa: "+e.getCause());
        }
    }

    public static byte[] exportarJasperPrintParaXLS(File file , JasperPrint print ){
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             OutputStream fileOutputStream = new FileOutputStream(file)) {

            configurarRelatorioJasperXLS(print, byteArrayOutputStream);
            byteArrayOutputStream.writeTo(fileOutputStream);

            return Files.readAllBytes(file.toPath());

        } catch (FileNotFoundException e) {
            throw new GeralException("Arquivo não encontrado FileNotFoundException: "+e.getCause());
        } catch (IOException e) {
            throw new GeralException("Erro de E/S IOException: "+ e.getCause());
        } catch (JRException e) {
            throw new GeralException("Erro no JasperReports JRException: "+ e.getCause());
        }
    }

    private static void configurarRelatorioJasperXLS(JasperPrint print, ByteArrayOutputStream byteArrayOutputStream) throws JRException {
        SimpleXlsxReportConfiguration reportConfig = new SimpleXlsxReportConfiguration();
        reportConfig.setSheetNames(new String[]{"Employee Data"});
        reportConfig.setWhitePageBackground(false);
        reportConfig.setOnePagePerSheet(false);
        reportConfig.setIgnoreGraphics(false);

        Exporter<ExporterInput, XlsxReportConfiguration, XlsxExporterConfiguration, OutputStreamExporterOutput> exporter = new JRXlsxExporter();
        exporter.setExporterInput(new SimpleExporterInput(print));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(byteArrayOutputStream));
        exporter.setConfiguration(reportConfig);
        exporter.exportReport();
    }


}
