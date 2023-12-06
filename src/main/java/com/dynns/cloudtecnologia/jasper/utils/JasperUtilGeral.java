package com.dynns.cloudtecnologia.jasper.utils;

import com.dynns.cloudtecnologia.jasper.exception.GeralException;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Map;

@Component
public class JasperUtilGeral {
    private JasperUtilGeral() {
    }

    public static JasperPrint gerarJasperPrintConexaoDiretaJASPER(Resource resourceJasper, Map<String, Object> parameters,Connection connection )  {
        InputStream streamJrxml = null;
        try {
            streamJrxml = resourceJasper.getInputStream();
            Log.info("::: streamJrxml gerado Com Sucesso! :::");
        } catch (IOException e) {
            throw new GeralException("Erro de IOException ao gerar InputStream do arquivo: "+resourceJasper.getFilename()+" - "+e.getCause());
        }

        JasperReport report = null;
        try {
            report = JasperCompileManager.compileReport(streamJrxml);
            Log.info("::: JasperReport gerado Com Sucesso! :::");
        } catch (JRException e) {
            throw new GeralException("Erro de JRException ao gerar JasperReport com o InputStream, causa: "+e.getCause());
        }

        try {
            return JasperFillManager.fillReport(report,parameters,connection);
        } catch (JRException e) {
            throw new GeralException("Erro de JRException gerarJasperPrintFolhaItemDtoResponseByJRXML(report,parameters,dataSource), causa: "+e.getCause());
        }
    }

}
