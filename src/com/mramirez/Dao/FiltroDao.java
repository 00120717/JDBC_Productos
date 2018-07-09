/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mramirez.Dao;

import com.mramirez.Interface.metodos;
import com.mramirez.conexion.Conexion;
import com.mramirez.modelo.Filtro;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marvin Ramirez
 */
public class FiltroDao implements metodos<Filtro>{

    private static final String SQL_INSERT = "INSERT INTO productos(codigo,nombre,tipo,cantidad,precio,disponibilidad) VALUES(?,?,?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE productos SET tipo = ?, cantidad = ?, precio = ?, disponibilidad = ? WHERE codigo = ?";
    private static final String SQL_DELETE = "DELETE  FROM productos WHERE codigo = ?";
    private static final String SQL_READ = "SELECT * FROM productos WHERE codigo = ?";
    private static final String SQL_READALL = "SELECT * FROM productos ";
    private static final Conexion con = Conexion.conectar();
    
    @Override
    public boolean insertar(Filtro i) {
        PreparedStatement ps;
        
        try{
            ps = con.getCnx().prepareStatement(SQL_INSERT);
            ps.setString(1, i.getCodigo());
            ps.setString(2, i.getNombre());
            ps.setString(3, i.getTipo());
            ps.setInt(4, i.getCantidad());
            ps.setDouble(5, i.getPrecio());
            ps.setBoolean(1, i.getDispo());
            
            if(ps.executeUpdate()>0){
                return true;
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
            Logger.getLogger(FiltroDao.class.getName()).log(Level.SEVERE, null,e);
        }finally{
            con.cerrarConexion();
        }
        return false;
    }

    @Override
    public boolean actualizar(Filtro a) {
        PreparedStatement ps;
        try{
            ps = con.getCnx().prepareStatement(SQL_UPDATE);
            ps.setString(1,a.getTipo());
            ps.setInt(2,a.getCantidad());
            ps.setDouble(3,a.getPrecio());
            ps.setBoolean(4,a.getDispo());
            if(ps.executeUpdate()>0){
                return true;
            }
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
            Logger.getLogger(FiltroDao.class.getName()).log(Level.SEVERE, null,e);
        }finally{
            con.cerrarConexion();
        }
        return false;
    }

    @Override
    public boolean eliminar(Object key) {
        PreparedStatement ps;
        
        try{
            ps = con.getCnx().prepareStatement(SQL_DELETE);
            ps.setString(1, key.toString());
            if(ps.executeUpdate()>0){
                return true;
            }
                   
        }catch(SQLException e){
            System.out.println(e.getMessage());
            Logger.getLogger(FiltroDao.class.getName()).log(Level.SEVERE, null,e);
        }finally{
            con.cerrarConexion();
        }
        return false;
    }

    @Override
    public Filtro leer(Object key) {
        Filtro f = null;
        PreparedStatement ps;
        ResultSet rs;
        
        try{
            ps = con.getCnx().prepareStatement(SQL_READ);
            ps.setString(1, key.toString());
            rs = ps.executeQuery();
            
            while(rs.next()){
                f = new Filtro(rs.getInt(1),rs.getString(2) ,rs.getString(3) ,rs.getString(4), rs.getInt(5), rs.getDouble(6), rs.getBoolean(7));
                rs.close();
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
            Logger.getLogger(FiltroDao.class.getName()).log(Level.SEVERE, null,e);
        }finally{
            con.cerrarConexion();
        }
        return f;
    }

    @Override
    public ArrayList<Filtro> readAll() {
        ArrayList<Filtro> all = new ArrayList();
        Statement s;
        ResultSet rs;
        
        try{
            
        s = con.getCnx().prepareStatement(SQL_READALL);
        rs = s.executeQuery(SQL_READALL);
            
        while(rs.next()){
            all.add(new Filtro(rs.getInt(1),rs.getString(2) ,rs.getString(3) ,rs.getString(4), rs.getInt(5), rs.getDouble(6), rs.getBoolean(7)));
        }
        }catch(SQLException e){
            System.out.println(e.getMessage());
            Logger.getLogger(FiltroDao.class.getName()).log(Level.SEVERE, null,e);
        }finally{
            con.cerrarConexion();
        }
        return all;
    }
    
}
