package br.com.fiap.to;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class Usuario {
    //Atributos
    private int idUsuario;
    private String nome;
    private String email;
    private String areaInteresse;
    private String senha;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDate dtCadastro;

    // Construtor
    public Usuario(int idUsuario, String nome, String email, String areaInteresse, String senha) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.email = email;
        this.areaInteresse = areaInteresse;
        this.senha = senha;
        this.dtCadastro = LocalDate.now();
    }

    public Usuario(){}

    //Getters e Setters

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAreaInteresse() {
        return areaInteresse;
    }

    public void setAreaInteresse(String areaInteresse) {
        this.areaInteresse = areaInteresse;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDate getDtCadastro() {
        return dtCadastro;
    }

    public void setDtCadastro(LocalDate dtCadastro) {
        this.dtCadastro = dtCadastro;
    }
}
