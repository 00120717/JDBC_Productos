/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mramirez.conexion;

import java.sql.*;
//import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marvin Ramirez
 */
public class Conexion {
    
  private String user,pass,url,driver;
  private Connection cnx;
    
  public static Conexion instance;
  
  public synchronized static Conexion conectar(){
      if(instance == null){
          return new Conexion();
      }
      return instance;
  }
  
  private Conexion(){
      caragarCredenciales();
      
      try{
          Class.forName(this.driver);
          cnx = (Connection)DriverManager.getConnection(this.url,this.user,this.pass);
      }catch(ClassNotFoundException | SQLException e){
          Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null,e);
      }
  }

    private void caragarCredenciales() {
        user = "root";
        pass = "";
        url = "jdbc:mysql://localhost/productos";
        driver = "com.mysql.jdbc.Driver";
    }
  
    public Connection getCnx(){
        return cnx;
    }
    
    public void cerrarConexion(){
        instance = null;
    }
  
}
