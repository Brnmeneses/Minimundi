package com.br.mimundi;

public class Fabricante {

    private String nome;
    private String origem;

    public Fabricante(String nome, String origem) {
        setNome(nome);
        setOrigem(origem);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    @Override
    public String toString() {
        return getNome();
    }
}
