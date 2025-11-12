package br.com.fiap.to;

import java.time.LocalDateTime;

public class Curso {

    // Atributos
    private int idCurso;
    private String nome;
    private String area;
    private String nivel;
    private int cargaHoraria;

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
