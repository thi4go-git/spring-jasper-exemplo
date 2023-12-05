package com.dynns.cloudtecnologia.jasper.rest.dto;

import java.util.Date;
import java.util.Objects;

public class FolhaItemResponseDTO {
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

    public Long getIdItem() {
        return idItem;
    }

    public void setIdItem(Long idItem) {
        this.idItem = idItem;
    }

    public Long getIdFolha() {
        return idFolha;
    }

    public void setIdFolha(Long idFolha) {
        this.idFolha = idFolha;
    }

    public String getRegimePrev() {
        return regimePrev;
    }

    public void setRegimePrev(String regimePrev) {
        this.regimePrev = regimePrev;
    }

    public Long getId_natureza() {
        return id_natureza;
    }

    public void setId_natureza(Long id_natureza) {
        this.id_natureza = id_natureza;
    }

    public String getNomeNatureza() {
        return nomeNatureza;
    }

    public void setNomeNatureza(String nomeNatureza) {
        this.nomeNatureza = nomeNatureza;
    }

    public String getRubrica() {
        return rubrica;
    }

    public void setRubrica(String rubrica) {
        this.rubrica = rubrica;
    }

    public String getNomeEscolhido() {
        return nomeEscolhido;
    }

    public void setNomeEscolhido(String nomeEscolhido) {
        this.nomeEscolhido = nomeEscolhido;
    }

    public String getNumeroNatureza() {
        return numeroNatureza;
    }

    public void setNumeroNatureza(String numeroNatureza) {
        this.numeroNatureza = numeroNatureza;
    }

    public Long getIdTipoCodigoNatureza() {
        return idTipoCodigoNatureza;
    }

    public void setIdTipoCodigoNatureza(Long idTipoCodigoNatureza) {
        this.idTipoCodigoNatureza = idTipoCodigoNatureza;
    }

    public String getDescricaoTipoCodigoNatureza() {
        return descricaoTipoCodigoNatureza;
    }

    public void setDescricaoTipoCodigoNatureza(String descricaoTipoCodigoNatureza) {
        this.descricaoTipoCodigoNatureza = descricaoTipoCodigoNatureza;
    }

    public Long getId_codigo_agregadores() {
        return id_codigo_agregadores;
    }

    public void setId_codigo_agregadores(Long id_codigo_agregadores) {
        this.id_codigo_agregadores = id_codigo_agregadores;
    }

    public Long getIdCodigoNaturezaAgregado() {
        return idCodigoNaturezaAgregado;
    }

    public void setIdCodigoNaturezaAgregado(Long idCodigoNaturezaAgregado) {
        this.idCodigoNaturezaAgregado = idCodigoNaturezaAgregado;
    }

    public Long getIdUsuarioCadastro() {
        return idUsuarioCadastro;
    }

    public void setIdUsuarioCadastro(Long idUsuarioCadastro) {
        this.idUsuarioCadastro = idUsuarioCadastro;
    }

    public String getNomeUsuarioCadastro() {
        return nomeUsuarioCadastro;
    }

    public void setNomeUsuarioCadastro(String nomeUsuarioCadastro) {
        this.nomeUsuarioCadastro = nomeUsuarioCadastro;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Double getValoRubrica() {
        return valoRubrica;
    }

    public void setValoRubrica(Double valoRubrica) {
        this.valoRubrica = valoRubrica;
    }

    public Double getValorDiferenca() {
        return valorDiferenca;
    }

    public void setValorDiferenca(Double valorDiferenca) {
        this.valorDiferenca = valorDiferenca;
    }

    public Double getValorDevolucao() {
        return valorDevolucao;
    }

    public void setValorDevolucao(Double valorDevolucao) {
        this.valorDevolucao = valorDevolucao;
    }

    public Double getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(Double valorFinal) {
        this.valorFinal = valorFinal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FolhaItemResponseDTO that = (FolhaItemResponseDTO) o;
        return Objects.equals(idItem, that.idItem) && Objects.equals(idFolha, that.idFolha) && Objects.equals(regimePrev, that.regimePrev) && Objects.equals(id_natureza, that.id_natureza) && Objects.equals(nomeNatureza, that.nomeNatureza) && Objects.equals(rubrica, that.rubrica) && Objects.equals(nomeEscolhido, that.nomeEscolhido) && Objects.equals(numeroNatureza, that.numeroNatureza) && Objects.equals(idTipoCodigoNatureza, that.idTipoCodigoNatureza) && Objects.equals(descricaoTipoCodigoNatureza, that.descricaoTipoCodigoNatureza) && Objects.equals(id_codigo_agregadores, that.id_codigo_agregadores) && Objects.equals(idCodigoNaturezaAgregado, that.idCodigoNaturezaAgregado) && Objects.equals(idUsuarioCadastro, that.idUsuarioCadastro) && Objects.equals(nomeUsuarioCadastro, that.nomeUsuarioCadastro) && Objects.equals(dataCadastro, that.dataCadastro) && Objects.equals(valoRubrica, that.valoRubrica) && Objects.equals(valorDiferenca, that.valorDiferenca) && Objects.equals(valorDevolucao, that.valorDevolucao) && Objects.equals(valorFinal, that.valorFinal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idItem, idFolha, regimePrev, id_natureza, nomeNatureza, rubrica, nomeEscolhido, numeroNatureza, idTipoCodigoNatureza, descricaoTipoCodigoNatureza, id_codigo_agregadores, idCodigoNaturezaAgregado, idUsuarioCadastro, nomeUsuarioCadastro, dataCadastro, valoRubrica, valorDiferenca, valorDevolucao, valorFinal);
    }

    @Override
    public String toString() {
        return "FolhaItemResponseDTO{" +
                "idItem=" + idItem +
                ", idFolha=" + idFolha +
                ", regimePrev='" + regimePrev + '\'' +
                ", id_natureza=" + id_natureza +
                ", nomeNatureza='" + nomeNatureza + '\'' +
                ", rubrica='" + rubrica + '\'' +
                ", nomeEscolhido='" + nomeEscolhido + '\'' +
                ", numeroNatureza='" + numeroNatureza + '\'' +
                ", idTipoCodigoNatureza=" + idTipoCodigoNatureza +
                ", descricaoTipoCodigoNatureza='" + descricaoTipoCodigoNatureza + '\'' +
                ", id_codigo_agregadores=" + id_codigo_agregadores +
                ", idCodigoNaturezaAgregado=" + idCodigoNaturezaAgregado +
                ", idUsuarioCadastro=" + idUsuarioCadastro +
                ", nomeUsuarioCadastro='" + nomeUsuarioCadastro + '\'' +
                ", dataCadastro=" + dataCadastro +
                ", valoRubrica=" + valoRubrica +
                ", valorDiferenca=" + valorDiferenca +
                ", valorDevolucao=" + valorDevolucao +
                ", valorFinal=" + valorFinal +
                '}';
    }
}
