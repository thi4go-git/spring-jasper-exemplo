package com.dynns.cloudtecnologia.jasper.service.impl;

import com.dynns.cloudtecnologia.jasper.rest.dto.FolhaItemDtoResponse;
import com.dynns.cloudtecnologia.jasper.service.JasperService;
import com.dynns.cloudtecnologia.jasper.utils.ArquivoUtil;
import com.dynns.cloudtecnologia.jasper.utils.UtilJasper;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import java.io.File;
import java.util.*;


@Service
public class JasperServiceImpl implements JasperService {


    private static final Log LOG = LogFactory.getLog(JasperServiceImpl.class);

    @Autowired
    private ResourceLoader resourceLoader;

    @Value("${jasper.path}")
    private String jasperPath;
    private static final String JRXML = "folha.jrxml";
    private static final String FOLHA_GERADA = "folhaGerada.pdf";


    @Override
    public String geraRelatorio() {

        List<FolhaItemDtoResponse> itensFolha = getListFolhaItem();

        byte[] bytesFolha = getByteExcelEnvironment(itensFolha);
        LOG.info("::: bytesFolha GERADO com Sucesso :::");

        File arquivo = ArquivoUtil.byteTofile(bytesFolha, "C:\\Users\\thiago-am\\Downloads\\FolhaGerada.xls");
        LOG.info("::: FolhaGerada GERADA com Sucesso :::");

        assert arquivo != null;
        return  arquivo.getName();
    }

    private byte[] getByteExcelEnvironment(List<FolhaItemDtoResponse> itensFolha)   {

        Resource resourceJrxml = resourceLoader.getResource(jasperPath.concat(JRXML));

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("mesAno", "Abril / 2023");
        parameters.put("numeroProcesso", "1235512610054");
        parameters.put("valorTotal", "5.468.223,45");

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(itensFolha);

        JasperPrint jasperPrint = UtilJasper.gerarJasperPrintByJRXML(resourceJrxml, parameters, dataSource);
        LOG.info("::: jasperPrint GERADO com Sucesso :::");

        Resource resourceFolhaPdf = resourceLoader.getResource(jasperPath.concat(FOLHA_GERADA));
        File fileFolhaGeradaPDF = ArquivoUtil.gerarPdfFileByResource(resourceFolhaPdf);
        LOG.info("::: fileFolhaGeradaPDF GERADO com Sucesso :::");

        return UtilJasper.exportarJasperPrintParaXLS(fileFolhaGeradaPDF, jasperPrint);
    }

    private List<FolhaItemDtoResponse> getListFolhaItem(){
        List<FolhaItemDtoResponse> itensFolha = new ArrayList<>();

        FolhaItemDtoResponse obj1 = new FolhaItemDtoResponse();
        obj1.setNomeNatureza("3.1.90.11.01");
        obj1.setNomeEscolhido("AJUSTE REM. - LEI 17.030 - RPPS");
        obj1.setNumeroNatureza("3.1.90.11.01");
        obj1.setValorFinal(1500.00);
        obj1.setRegimePrev("RPPS");

        FolhaItemDtoResponse obj2 = new FolhaItemDtoResponse();
        obj2.setNomeNatureza("3.1.90.11.01");
        obj2.setNomeEscolhido("ANUENIO - VI - RGPS");
        obj2.setNumeroNatureza("3.1.90.11.01");
        obj2.setValorFinal(500.00);
        obj2.setRegimePrev("RGPS");

        FolhaItemDtoResponse obj3 = new FolhaItemDtoResponse();
        obj3.setNomeNatureza("3.1.90.11.02");
        obj3.setNomeEscolhido("FUNCAO COMISSIONADA - FCPE - RPPS");
        obj3.setNumeroNatureza("3.1.90.11.02");
        obj3.setValorFinal(4500.00);
        obj3.setRegimePrev("RPPS");

        FolhaItemDtoResponse obj4 = new FolhaItemDtoResponse();
        obj4.setNomeNatureza("3.1.90.11.02");
        obj4.setNomeEscolhido("FUNCAO COMISSIONADA - FCPE - RGPS");
        obj4.setNumeroNatureza("3.1.90.11.02");
        obj4.setValorFinal(4567.65);
        obj4.setRegimePrev("RGPS");

        itensFolha.add(obj1);
        itensFolha.add(obj2);
        itensFolha.add(obj3);
        itensFolha.add(obj4);

        return itensFolha;
    }


}
