/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mramirez.Interface;

import java.util.ArrayList;

/**
 *
 * @author Zonia Gonzalez
 */
public interface metodos <Generic>{
    
    public boolean insertar(Generic i);
    public boolean actualizar(Generic a);
    public boolean eliminar(Object key);
    
    public Generic leer(Object key);
    public ArrayList<Generic> readAll();
}
