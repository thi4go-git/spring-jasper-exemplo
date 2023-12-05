package com.dynns.cloudtecnologia.jasper.utils;


import com.dynns.cloudtecnologia.jasper.exception.GeralException;
import com.dynns.cloudtecnologia.jasper.rest.dto.FolhaItemResponseDTO;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import java.io.*;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;



@Component
public abstract class JasperUtil {

    private JasperUtil(){}


    private static final String PATH_RELATORIOS_SAVE = "C:\\Users\\thiago-am\\Desktop\\DEVELOPMENT\\jasper-relatorios\\";
    private static final Log LOG = LogFactory.getLog(JasperUtil.class);

    public static JasperPrint gerarJasperPrintFolhaItemDtoResponseByJRXML(List<FolhaItemResponseDTO> itensFolha, Resource resourceJrxml, Map<String, Object> parameters )  {
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
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(itensFolha);
            return JasperFillManager.fillReport(report,parameters,dataSource);
        } catch (JRException e) {
            throw new GeralException("Erro de JRException ao gerar JasperPrint(report,parameters,dataSource), causa: "+e.getCause());
        }
    }

    public static String  exportarJasperPrintToXLS(File file, JasperPrint print){
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             OutputStream fileOutputStream = new FileOutputStream(file)) {

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

            byteArrayOutputStream.writeTo(fileOutputStream);

            byte[] bytesXLS = Files.readAllBytes(file.toPath());

            File xlsGerado = ArquivoUtil.byteTofile(bytesXLS,PATH_RELATORIOS_SAVE.concat("testeXLS.xls"));
            LOG.info("Relatório XLS gerado com Sucesso! "+xlsGerado.getAbsolutePath());
            return xlsGerado.getAbsolutePath();

        } catch (FileNotFoundException e) {
            throw new GeralException("Arquivo não encontrado FileNotFoundException: "+e.getCause());
        } catch (IOException e) {
            throw new GeralException("Erro de E/S IOException: "+ e.getCause());
        } catch (JRException e) {
            throw new GeralException("Erro no JasperReports JRException: "+ e.getCause());
        }
    }

    public static void exportarJasperPrintToPDF(JasperPrint jasperPrint ){
        try {
            byte[] bytesPDF = JasperExportManager.exportReportToPdf(jasperPrint);
            File xlsGerado = ArquivoUtil.byteTofile(bytesPDF,PATH_RELATORIOS_SAVE.concat("testePDF.pdf"));
            LOG.info("Relatório PDF gerado com Sucesso! "+xlsGerado.getAbsolutePath());
        } catch (JRException e) {
            throw new GeralException("Erro ao exportar para PDF" + e.getCause());
        }
    }

}
