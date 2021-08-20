package com.br.mimundi;

import java.util.Random;

public class Miniatura {

    private int    id;
    private String fabricante;
    private String marca;
    private String modelo;
    private String ano;
    private String cor;
    private Boolean loose;
    private String raridade;

    public static int getRandomInt(int min, int max) {
        Random random = new Random();

        return random.nextInt((max - min) + 1) + min;
    }

    public Miniatura(int id, String fabricante, String marca, String modelo, String ano, String cor, Boolean loose, String raridade) {
        this.id = id;
        setFabricante(fabricante);
        setMarca(marca);
        setModelo(modelo);
        setAno(ano);
        setCor(cor);
        setLoose(loose);
        setRaridade(raridade);
    }

    public Miniatura(String fabricante, String marca, String modelo, String ano, String cor, Boolean loose, String raridade) {
        setId();
        setFabricante(fabricante);
        setMarca(marca);
        setModelo(modelo);
        setAno(ano);
        setCor(cor);
        setLoose(loose);
        setRaridade(raridade);
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

    public Boolean getLoose() { return loose; }

    public String getStringLoose() { return loose ? "T" : "F"; }

    public void setLoose(Boolean loose) { this.loose = loose; }

    public String getRaridade() { return raridade; }

    public void setRaridade(String raridade) {  this.raridade = raridade; }

    @Override
    public String toString() {
        return  //getId() + " - " +
                getFabricante() + " " +
                getMarca() + " " +
                getModelo() + " " +
                getAno()  + " " +
                getCor();

    }
}
