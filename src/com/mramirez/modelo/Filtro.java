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
public class Filtro{
    
    private int id;
    private String codigo;
    private String nombre;
    private String tipo;
    private int cantidad;
    private double precio;
    private boolean dispo;

    public Filtro() {
    }

    public Filtro(int id, String codigo, String nombre, String tipo, int cantidad, double precio, boolean dispo) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.precio = precio;
        this.dispo = dispo;
    }

    public Filtro(String codigo, String nombre, String tipo, int cantidad, double precio, boolean dispo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.precio = precio;
        this.dispo = dispo;
    }

    public Filtro(String nombre, String tipo, int cantidad, double precio, boolean dispo) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.precio = precio;
        this.dispo = dispo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public boolean getDispo() {
        return dispo;
    }

    public void setDispo(boolean dispo) {
        this.dispo = dispo;
    }

   
    
            
            
            
}
