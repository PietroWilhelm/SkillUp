package br.com.fiap.to;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Curso {
    // Atributos
    // ID deve existir para updates, mas não para inserts → não validamos aqui
    private int idCurso;

    @NotBlank(message = "O nome do curso é obrigatório.")
    private String nome;

    @NotBlank(message = "A área do curso é obrigatória.")
    private String area;

    @NotBlank(message = "O nível do curso é obrigatório.")
    private String nivel;

    @NotNull(message = "A carga horária é obrigatória.")
    private int cargaHoraria;;

    // Construtor vazio
    public Curso() {}

    //Construtor
    public Curso(int idCurso, String nome, String area, String nivel ,int cargaHoraria) {
        this.idCurso = idCurso;
        this.nome = nome;
        this.area = area;
        this.nivel = nivel;
        this.cargaHoraria = cargaHoraria;

    }

    //Getters e Setters

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }
}
