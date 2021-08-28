package com.br.mimundi;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Random;

@Entity
public class Miniatura implements Comparable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    private String fabricante;
    private String marca;
    private String modelo;
    private String ano;
    private String cor;
    private Boolean loose;
    private String raridade;

/*    public static int getRandomInt(int min, int max) {
        Random random = new Random();

        return random.nextInt((max - min) + 1) + min;
    }*/

    public Miniatura() {
    }

    public Miniatura(int id, String fabricante, String marca, String modelo, String ano, String cor, Boolean loose, String raridade) {
        setId(id);
        setFabricante(fabricante);
        setMarca(marca);
        setModelo(modelo);
        setAno(ano);
        setCor(cor);
        setLoose(loose);
        setRaridade(raridade);
    }

    public Miniatura(String fabricante, String marca, String modelo, String ano, String cor, Boolean loose, String raridade) {
        setFabricante(fabricante);
        setMarca(marca);
        setModelo(modelo);
        setAno(ano);
        setCor(cor);
        setLoose(loose);
        setRaridade(raridade);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Boolean getLoose() {
        return loose;
    }

    public String getStringLoose() {
        return loose ? "T" : "F";
    }

    public void setLoose(Boolean loose) {
        this.loose = loose;
    }

    public String getRaridade() {
        return raridade;
    }

    public void setRaridade(String raridade) {
        this.raridade = raridade;
    }

    @Override
    public String toString() {
        return  "(" + getId() + ") - " +
                getFabricante() + " " +
                        getMarca() + " " +
                        getModelo() + " " +
                        getAno() + " " +
                        getCor();

    }

    @Override
    public int compareTo(Object o) {

        Miniatura mini = (Miniatura) o;

        int compAlfabetica = getMarca().compareToIgnoreCase(mini.getMarca());
        if (compAlfabetica == 0) {
            return getModelo().compareToIgnoreCase(mini.getModelo());
        }
        return compAlfabetica;

    }
}
