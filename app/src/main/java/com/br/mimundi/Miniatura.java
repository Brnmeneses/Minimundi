package com.br.mimundi;

import java.util.Random;

public class Miniatura {

    private int    id;
    private String fabricante;
    private String marca;
    private String modelo;
    private String ano;
    private String cor;

    public static int getRandomInt(int min, int max) {
        Random random = new Random();

        return random.nextInt((max - min) + 1) + min;
    }

    public Miniatura(int id, String fabricante, String marca, String modelo, String ano, String cor) {
        this.id = id;
        setFabricante(fabricante);
        setMarca(marca);
        setModelo(modelo);
        setAno(ano);
        setCor(cor);
    }

    public Miniatura(String fabricante, String marca, String modelo, String ano, String cor) {
        setId();
        setFabricante(fabricante);
        setMarca(marca);
        setModelo(modelo);
        setAno(ano);
        setCor(cor);
    }

    public int getId() {return id;}

    public void setId() {this.id = getRandomInt(1,100);}

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
        return  getId() + " - " +
                getFabricante() + " " +
                getMarca() + " " +
                getModelo() + " " +
                getAno()  + " " +
                getCor();

    }
}
