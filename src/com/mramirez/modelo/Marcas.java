/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mramirez.modelo;

/**
 *
 * @author Zonia Gonzalez
 */
public class Marcas {
    private String marca;
    private boolean existencia;
    private String codigo;

    public Marcas() {
    }

    public Marcas(String marca, boolean existencia, String codigo) {
        this.marca = marca;
        this.existencia = existencia;
        this.codigo = codigo;
    }

    public Marcas(String marca, boolean existencia) {
        this.marca = marca;
        this.existencia = existencia;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public boolean getExistencia() {
        return existencia;
    }

    public void setExistencia(boolean existencia) {
        this.existencia = existencia;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    
}
