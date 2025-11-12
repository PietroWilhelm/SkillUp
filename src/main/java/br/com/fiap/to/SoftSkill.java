package br.com.fiap.to;


public class SoftSkill {

    // Atributos
    private int idSoftSkill;
    private String nomeSofSkill;
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
