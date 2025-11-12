package br.com.fiap.to;

import javax.swing.*;

public class HardSkill {

    //Atributos
    private int idHardSkill;
    private String nomeHardSkill;
    private String descricaoHardSkill;
    private String categoriaHardSkill;

    //Construtores
    public HardSkill(){}

    public HardSkill(int idHardSkill, String nomeHardSkill, String descricaoHardSkill, String categoriaHardSkill) {
        this.idHardSkill = idHardSkill;
        this.nomeHardSkill = nomeHardSkill;
        this.descricaoHardSkill = descricaoHardSkill;
        this.categoriaHardSkill = categoriaHardSkill;
    }

    //Getters e Setters

    public int getIdHardSkill() {
        return idHardSkill;
    }

    public void setIdHardSkill(int idHardSkill) {
        this.idHardSkill = idHardSkill;
    }

    public String getNomeHardSkill() {
        return nomeHardSkill;
    }

    public void setNomeHardSkill(String nomeHardSkill) {
        this.nomeHardSkill = nomeHardSkill;
    }

    public String getDescricaoHardSkill() {
        return descricaoHardSkill;
    }

    public void setDescricaoHardSkill(String descricaoHardSkill) {
        this.descricaoHardSkill = descricaoHardSkill;
    }

    public String getCategoriaHardSkill() {
        return categoriaHardSkill;
    }

    public void setCategoriaHardSkill(String categoriaHardSkill) {
        this.categoriaHardSkill = categoriaHardSkill;
    }
}
