package com.example.barbearia3;

public class Pessoa {


    private String id;
    private String nome;
    private String servico;
    private String telef;
    private String dia;
    private String hora;
    private String msg;

    public Pessoa() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getServico() { return servico;  }

    public void setServico(String servico) { this.servico = servico;}

    public String getTelef() {
        return telef;
    }

    public void setTelef(String telef) {
        this.telef = telef;
    }

    public String getDia() { return dia; }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getMsg() {return msg; }

    public void setMsg(String msg) {this.msg = msg; }

    @Override
    public String toString() {
        return  nome;
    }

}
