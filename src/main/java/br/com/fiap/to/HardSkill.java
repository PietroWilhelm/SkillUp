package br.com.fiap.to;

import jakarta.validation.constraints.NotBlank;

import javax.swing.*;

public class HardSkill {

    //Atributos
    private int idHardSkill;

    @NotBlank(message = "O nome da HardSkill é obrigatória.")
    private String nomeHardSkill;

    @NotBlank(message = "A Descrição da HardSkill é obrigatório.")
    private String descricaoHardSkill;

    @NotBlank(message = "A Categoria da HardSkill é obrigatório.")
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
