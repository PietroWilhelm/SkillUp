package br.com.fiap.to;

import java.time.LocalDate;

public class Matricula {
    private int idMatricula;
    private String numeroProgresso;
    private LocalDate dataInicio;
    private int idUsuario;
    private int idCurso;

    // Construtores
    public Matricula() {}

    public Matricula(int idCurso, int idUsuario, LocalDate dataInicio, String numeroProgresso, int idMatricula) {
        this.idCurso = idCurso;
        this.idUsuario = idUsuario;
        this.dataInicio = dataInicio;
        this.numeroProgresso = numeroProgresso;
        this.idMatricula = idMatricula;
    }

    // Getters e Setters

    public int getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(int idMatricula) {
        this.idMatricula = idMatricula;
    }

    public String getNumeroProgresso() {
        return numeroProgresso;
    }

    public void setNumeroProgresso(String numeroProgresso) {
        this.numeroProgresso = numeroProgresso;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }
}
