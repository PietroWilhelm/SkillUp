package br.com.fiap.to;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class Recomendacao {

    private int idRecomendacao;

    @Min(value = 1, message = "O valor de relevância deve ser maior ou igual a 1.")
    private int valorRelevancia;

    @NotBlank(message = "A descrição do motivo é obrigatória.")
    private String descricaoMotivo;
    private int idCurso;
    private int idUsuario;

    // Campos derivados de JOIN — não recebem validação obrigatória
    private String nomeUsuario;
    private String nomeCurso;

    // Construtores
    public Recomendacao() {}

    public Recomendacao(int idRecomendacao, int valorRelevancia, String descricaoMotivo, int idCurso, int idUsuario, String nomeUsuario, String nomeCurso) {
        this.idRecomendacao = idRecomendacao;
        this.valorRelevancia = valorRelevancia;
        this.descricaoMotivo = descricaoMotivo;
        this.idCurso = idCurso;
        this.idUsuario = idUsuario;
        this.nomeUsuario = nomeUsuario;
        this.nomeCurso = nomeCurso;

    }

    // Getters e Setters

    public int getIdRecomendacao() {
        return idRecomendacao;
    }

    public void setIdRecomendacao(int idRecomendacao) {
        this.idRecomendacao = idRecomendacao;
    }

    public int getValorRelevancia() {
        return valorRelevancia;
    }

    public void setValorRelevancia(int valorRelevancia) {
        this.valorRelevancia = valorRelevancia;
    }

    public String getDescricaoMotivo() {
        return descricaoMotivo;
    }

    public void setDescricaoMotivo(String descricaoMotivo) {
        this.descricaoMotivo = descricaoMotivo;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }
}
