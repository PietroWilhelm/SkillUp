package br.com.fiap.to;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class Usuario {
    //Atributos
    private int idUsuario;

    @NotBlank(message = "O nome deve ser preenchido")
    private String nome;

    @Email
    @NotBlank(message = "O Email deve ser Preenchido")
    private String email;

    @NotBlank(message = "A Área de Interesse deve ser preenchida")
    private String areaInteresse;

    @NotBlank(message = "A senha deve ser preenchida")
    @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres")
    private String senha;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @PastOrPresent(message = "A data de cadastro não pode ser no futuro")
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
