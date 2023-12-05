package com.dynns.cloudtecnologia.jasper.service.impl;

import com.dynns.cloudtecnologia.jasper.rest.dto.FolhaItemResponseDTO;
import com.dynns.cloudtecnologia.jasper.service.JasperService;
import com.dynns.cloudtecnologia.jasper.utils.ArquivoUtil;
import com.dynns.cloudtecnologia.jasper.utils.JasperUtil;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import java.io.File;
import java.util.*;

@Service
public class JasperServiceImpl implements JasperService {

    @Autowired
    private ResourceLoader resourceLoader;
    @Value("${jasper.path}")
    private String jasperPath;
    private static final String JRXML = "folha.jrxml";
    private static final String FOLHA_GERADA = "folhaGerada.pdf";

    @Override
    public String geraRelatorioFolhaEmater() {

        //Cabeçalho do relatório
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("mesAno", "Abril / 2023");
        parameters.put("numeroProcesso", "1235512610054");
        parameters.put("valorTotal", "5.468.223,45");

        //Preenchendo LISTA
        FolhaItemResponseDTO iten = new FolhaItemResponseDTO();
        iten.setNomeEscolhido("Nome");
        iten.setNomeNatureza("Natureza 12345");
        iten.setNomeUsuarioCadastro("User teste");
        iten.setDataCadastro(new Date());
        iten.setId_natureza(1L);
        iten.setDescricaoTipoCodigoNatureza("Descrição");
        iten.setIdFolha(2L);
        iten.setValorDevolucao(15.2);
        iten.setValorFinal(45.66);
        List<FolhaItemResponseDTO> itensFolha = new ArrayList<>();
        itensFolha.add(iten);

        //Lendo caminho .jrxml
        Resource resourceJrxml= resourceLoader.getResource(jasperPath.concat(JRXML));

        JasperPrint jasperPrint = JasperUtil.gerarJasperPrintFolhaItemDtoResponseByJRXML(itensFolha,resourceJrxml,parameters);
        Resource resourceFolha = resourceLoader.getResource(jasperPath.concat(FOLHA_GERADA));

        File fileFolhaGeradaPDF = ArquivoUtil.gerarFileByPathStringToPDF(resourceFolha);

       return JasperUtil.exportarJasperPrintToXLS(fileFolhaGeradaPDF,jasperPrint);
    }


}
