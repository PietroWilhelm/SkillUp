package br.com.fiap.to;
public class Endereco {
    //Atributos
    private int idEndereco;
    private String dsLogradouro;
    private int numero;
    private String dsEstado;
    private String dsCidade;
    private String nrCep;
    private int idUsuario; // FK para SU_USUARIO

    //Construtor
    public  Endereco() {}

    public Endereco(int idEndereco, String dsLogradouro, int numero, String dsCidade, String dsEstado, String nrCep, int idUsuario) {
        this.idEndereco = idEndereco;
        this.dsLogradouro = dsLogradouro;
        this.numero = numero;
        this.dsCidade = dsCidade;
        this.dsEstado = dsEstado;
        this.nrCep = nrCep;
        this.idUsuario = idUsuario;
    }

    //Getters e Setters
    public int getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(int idEndereco) {
        this.idEndereco = idEndereco;
    }

    public String getDsLogradouro() {
        return dsLogradouro;
    }

    public void setDsLogradouro(String dsLogradouro) {
        this.dsLogradouro = dsLogradouro;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getDsEstado() {
        return dsEstado;
    }

    public void setDsEstado(String dsEstado) {
        this.dsEstado = dsEstado;
    }

    public String getDsCidade() {
        return dsCidade;
    }

    public void setDsCidade(String dsCidade) {
        this.dsCidade = dsCidade;
    }

    public String getNrCep() {
        return nrCep;
    }

    public void setNrCep(String nrCep) {
        this.nrCep = nrCep;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}
