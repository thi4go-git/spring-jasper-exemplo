package com.dynns.cloudtecnologia.jasper.util;

import java.io.*;
import java.util.logging.Logger;

import com.dynns.cloudtecnologia.jasper.exception.GeralException;
import org.springframework.stereotype.Component;

@Component
public class ArquivoUtils {


    private static final Logger LOG = Logger.getLogger(ArquivoUtils.class.getName());

    private ArquivoUtils() {
    }

    public static File byteTofile(byte[] bytesArquivo, String nome) throws IOException {
        File file;
        file = new File(nome);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(bytesArquivo);
        }
        return file;
    }


    public static void lerArquivo(String path) {
        File arquivoTxt = new File(path);
        try {
            LOG.info("::: Lendo arquivo TXT :::");
            FileInputStream stream = new FileInputStream(arquivoTxt);
            InputStreamReader streamReader = new InputStreamReader(stream);
            try (BufferedReader buffer = new BufferedReader(streamReader)) {
                String linha;
                while ((linha = buffer.readLine()) != null) {
                    LOG.info(linha);
                }
            }
        } catch (IOException e) {
            throw new GeralException("Erro ao Ler TXT: " + e.getMessage());
        }
    }


}
