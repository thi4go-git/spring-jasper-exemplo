package com.dynns.cloudtecnologia.jasper.utils;

import com.dynns.cloudtecnologia.jasper.exception.GeralException;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import java.io.*;
import java.util.logging.Logger;


@Component
public class ArquivoUtil {

    private ArquivoUtil(){}

    private static final Logger LOG = Logger.getLogger(ArquivoUtil.class.getName());

    public static File byteTofile(byte[] bytesArquivo, String nome) {
        File file = new File(nome);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(bytesArquivo);
        } catch (FileNotFoundException e) {
            throw new GeralException("Erro ao converter byteToFile FileNotFoundException: "+e.getCause());
        } catch (IOException e) {
            throw new GeralException("Erro ao converter byteToFile IOException: "+e.getCause());
        }
        LOG.info("::: Sucesso ao converver byteTofile! :::");
        return file;
    }

    public static File gerarFileByPathStringToPDF( Resource resourceFolha ){

        InputStream streamFolha = null;
        try {
            streamFolha = resourceFolha.getInputStream();
        } catch (IOException e) {
            throw new GeralException("Erro IOException ao gerar InputStream do arquivo FolhaPDF: " + e.getCause());
        }

        try {
            File file = File.createTempFile("pdfTemp", ".pdf");
            try (FileOutputStream outputStream = new FileOutputStream(file)) {
                if (streamFolha != null) {
                    IOUtils.copy(streamFolha, outputStream);
                    return  file;
                }else{
                    throw new GeralException("ERRO: Não foi possível criar o ArquivoPDF.temp.. streamFolhaGerada is NULL");
                }
            }
        } catch (IOException e) {
            throw new GeralException("Erro IOException ao gerar PDF File Temp: " + e.getCause());
        }
    }

}
