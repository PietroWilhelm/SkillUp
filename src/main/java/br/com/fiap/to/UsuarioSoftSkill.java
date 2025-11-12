package br.com.fiap.to;


public class UsuarioSoftSkill {
    private int idUsuario;
    private int idSoftSkill;
    private String nivelDominio;
    private String nomeUsuario;
    private String nomeSoftSkill;

    // Construtores
    public UsuarioSoftSkill() {}

    public UsuarioSoftSkill(int idUsuario, int idSoftSkill, String nivelDominio,  String nomeUsuario, String nomeSoftSkill) {
        this.idUsuario = idUsuario;
        this.idSoftSkill = idSoftSkill;
        this.nivelDominio = nivelDominio;
        this.nomeUsuario = nomeUsuario;
        this.nomeSoftSkill = nomeSoftSkill;
    }

    //Getters e Setters

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdSoftSkill() {
        return idSoftSkill;
    }

    public void setIdSoftSkill(int idSoftSkill) {
        this.idSoftSkill = idSoftSkill;
    }

    public String getNivelDominio() {
        return nivelDominio;
    }

    public void setNivelDominio(String nivelDominio) {
        this.nivelDominio = nivelDominio;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getNomeSoftSkill() {
        return nomeSoftSkill;
    }

    public void setNomeSoftSkill(String nomeSoftSkill) {
        this.nomeSoftSkill = nomeSoftSkill;
    }
}
