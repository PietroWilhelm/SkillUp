package br.com.fiap.to;


import jakarta.validation.constraints.NotBlank;

public class SoftSkill {

    // Atributos
    private int idSoftSkill;
    @NotBlank(message = "O nome da SoftSkill é obrigatória.")
    private String nomeSofSkill;
    @NotBlank(message = "A Descrição da SoftSkill é obrigatória.")
    private String descricaoSofSkill;

    // Construtores
    public SoftSkill() {}


    public SoftSkill(int idSoftSkill, String nomeSofSkill, String descricaoSofSkill) {
        this.idSoftSkill = idSoftSkill;
        this.nomeSofSkill = nomeSofSkill;
        this.descricaoSofSkill = descricaoSofSkill;
    }

    // Getters e Setters
    public int getIdSoftSkill() {
        return idSoftSkill;
    }

    public void setIdSoftSkill(int idSoftSkill) {
        this.idSoftSkill = idSoftSkill;
    }

    public String getNomeSofSkill() {
        return nomeSofSkill;
    }

    public void setNomeSofSkill(String nomeSofSkill) {
        this.nomeSofSkill = nomeSofSkill;
    }

    public String getDescricaoSofSkill() {
        return descricaoSofSkill;
    }

    public void setDescricaoSofSkill(String descricaoSofSkill) {
        this.descricaoSofSkill = descricaoSofSkill;
    }
}
