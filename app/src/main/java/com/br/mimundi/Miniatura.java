package com.br.mimundi;

public class Miniatura {

    private String fabricante;
    private String marca;
    private String modelo;
    private String ano;
    private String cor;

    public Miniatura(String fabricante, String marca, String modelo, String ano, String cor) {
        setFabricante(fabricante);
        setMarca(marca);
        setModelo(modelo);
        setAno(ano);
        setCor(cor);
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    @Override
    public String toString() {
        return getMarca() + " " +
                getModelo() + " " +
                getAno()  + " " +
                getCor();

    }
}
