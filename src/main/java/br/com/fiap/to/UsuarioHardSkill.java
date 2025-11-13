package br.com.fiap.to;

import jakarta.validation.constraints.NotBlank;

public class UsuarioHardSkill {

    private int idUsuario;
    private int idHardSkill;
    @NotBlank(message = "Deve conter o nível de conhecimento ex: Básico, Intermédiario e Avançado")
    private String nivelConhecimento;

    //
    private String nomeUsuario;
    private String nomeHardSkill;

    // Construtor
    public UsuarioHardSkill(int idUsuario, int idHardSkill ,String nivelConhecimento,  String nomeUsuario,String nomeHardSkill ) {
        this.idUsuario = idUsuario;
        this.idHardSkill = idHardSkill;
        this.nivelConhecimento = nivelConhecimento;
        this.nomeUsuario = nomeUsuario;
        this.nomeHardSkill = nomeHardSkill;
    }

    public UsuarioHardSkill() {}

    // Getters e Setters
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdHardSkill() {
        return idHardSkill;
    }

    public void setIdHardSkill(int idHardSkill) {
        this.idHardSkill = idHardSkill;
    }

    public String getNivelConhecimento() {
        return nivelConhecimento;
    }

    public void setNivelConhecimento(String nivelConhecimento) {
        this.nivelConhecimento = nivelConhecimento;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getNomeHardSkill() {
        return nomeHardSkill;
    }

    public void setNomeHardSkill(String nomeHardSkill) {
        this.nomeHardSkill = nomeHardSkill;
    }
}
