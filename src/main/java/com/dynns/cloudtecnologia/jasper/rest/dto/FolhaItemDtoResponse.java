package com.dynns.cloudtecnologia.jasper.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FolhaItemDtoResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idItem;

    private Long idFolha;

    private String regimePrev;

    private Long id_natureza;

    private String nomeNatureza;

    private String rubrica;

    private String nomeEscolhido;

    private String numeroNatureza;

    private Long idTipoCodigoNatureza;

    private String descricaoTipoCodigoNatureza;

    private Long id_codigo_agregadores;

    private Long idCodigoNaturezaAgregado;

    private Long idUsuarioCadastro;

    private String nomeUsuarioCadastro;

    private Date dataCadastro;

    private Double valoRubrica;

    private Double valorDiferenca;

    private Double valorDevolucao;

    private Double valorFinal;

}
