package com.dynns.cloudtecnologia.jasper.utils;

import com.dynns.cloudtecnologia.jasper.exception.GeralException;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;


@Component
public class ArquivoUtil {

    private ArquivoUtil(){}

    @Autowired
    private static ResourceLoader resourceLoader;

    private static final Log LOG = LogFactory.getLog(ArquivoUtil.class);

    public static File gerarPdfFileByResource( Resource resource ){

        InputStream streamArquivo = null;
        try {
            streamArquivo = resource.getInputStream();
        } catch (IOException e) {
            throw new GeralException("Erro IOException ao gerar InputStream do arquivo: " + e.getCause());
        }

        try {
            File file = File.createTempFile("pdfTemp", ".pdf");
            try (FileOutputStream outputStream = new FileOutputStream(file)) {
                if (streamArquivo != null) {
                    IOUtils.copy(streamArquivo, outputStream);
                    return  file;
                }else{
                    throw new GeralException("ERRO: Não foi possível criar o ArquivoPDF.temp: streamArquivo is NULL");
                }
            }
        } catch (IOException e) {
            throw new GeralException("Erro IOException ao gerar PDF File Temp: " + e.getCause());
        }
    }

    public static File byteTofile(byte[] bytesArquivo, String nome) {
        File pdfExistente = new File(nome);
        if (pdfExistente.exists()) {
            Path pdfPath = pdfExistente.toPath();
            try {
                Files.delete(pdfPath);
                LOG.info("Arquivo excluído com sucesso.");
            } catch (IOException e) {
                LOG.error("Falha ao excluir o arquivo {}");
            }
        }
        File file;
        try {
            byte[] bytes = bytesArquivo;
            file = new File(nome);
            try (FileOutputStream fos = new FileOutputStream(file)) {
                fos.write(bytes);
            }
            return file;
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
        return null;
    }

}
