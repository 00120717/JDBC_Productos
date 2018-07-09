/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mramirez.Gui;

import com.mramirez.Dao.FiltroDao;
import com.mramirez.modelo.Filtro;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Marvin Ramirez
 */
public class Consulta extends JFrame{
    public JLabel lblCodigo, lblNombre, lblPrecio, lblCantidad, lblDisponibilidad, lblTipo;
    public JTextField codigo,precio, cantidad, nombre;
    
    public JComboBox tipo;
    ButtonGroup disponibilidad = new ButtonGroup();
    public JRadioButton si;
    public JRadioButton no;
    public JTable resultados;
    public JPanel table;
    public JButton insertar,actualizar,eliminar,buscar,limpiar;
    
    private static final int ANCHOC =130, ALTOC = 30;
        
    DefaultTableModel tm;
    
    public Consulta(){
        super("inventario");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        agregarLabels();
        formulario();
        llenarTabla();
        Container container = getContentPane();
        container.add(lblCodigo);
        container.add(lblPrecio);
        container.add(lblNombre);
        container.add(lblCantidad);
        container.add(lblTipo);
        container.add(lblDisponibilidad);
        container.add(codigo);
        container.add(precio);
        container.add(nombre);
        container.add(cantidad);
        container.add(tipo);
        container.add(si);
        container.add(no);
        container.add(buscar);
        container.add(insertar);
        container.add(actualizar);
        container.add(eliminar);
        container.add(limpiar);
        container.add(table);
        
        setSize(900, 600);
        eventos();
    }
    
    private void agregarLabels(){
        lblCodigo = new JLabel("Codigo");
        lblNombre = new JLabel("Nombre");
        lblPrecio = new JLabel("Precio");
        lblCantidad = new JLabel("Cantidad");
        lblTipo = new JLabel("Tipo");
        lblDisponibilidad = new JLabel("Disonibilidad");
        
        lblCodigo.setBounds(10, 10, ANCHOC, ALTOC);
        lblNombre.setBounds(10, 60, ANCHOC, ALTOC);
        lblPrecio.setBounds(10, 100, ANCHOC, ALTOC);
        lblCantidad.setBounds(10, 140, ANCHOC, ALTOC);
        lblTipo.setBounds(10, 180, ANCHOC, ALTOC);
        lblDisponibilidad.setBounds(10, 220, ANCHOC, ALTOC);
    }
    
    private void formulario(){
        codigo = new JTextField();
        tipo = new JComboBox();
        nombre = new JTextField();
        precio = new JTextField();
        cantidad = new JTextField();
        si = new JRadioButton("si", true);
        no = new JRadioButton("no");
        resultados = new JTable();
        buscar = new JButton("Buscar");
        insertar = new JButton("Insertar");
        eliminar = new JButton("Eliminar");
        actualizar = new JButton("Actualizar");
        limpiar = new JButton("Limpiar");

        table = new JPanel();

        tipo.addItem("Fruta");
        tipo.addItem("Bebida");
        tipo.addItem("Verdura");
        tipo.addItem("Dulce");

        disponibilidad = new ButtonGroup();
        disponibilidad.add(si);
        disponibilidad.add(no);
        //-------------------------------------------
        codigo.setBounds(140, 10, ANCHOC, ALTOC);
        precio.setBounds(140, 60, ANCHOC, ALTOC);
        tipo.setBounds(140, 180, ANCHOC, ALTOC);
        nombre.setBounds(140, 100, ANCHOC, ALTOC);
        cantidad.setBounds(140, 140, ANCHOC, ALTOC);
        si.setBounds(140, 220, 50, ALTOC);
        no.setBounds(210, 220, 50, ALTOC);

        buscar.setBounds(300, 10, ANCHOC, ALTOC);
        insertar.setBounds(550, 220, ANCHOC, ALTOC);
        actualizar.setBounds(270, 220, ANCHOC, ALTOC);
        eliminar.setBounds(410, 220, ANCHOC, ALTOC);
        limpiar.setBounds(690, 220, ANCHOC, ALTOC);

        resultados = new JTable();
        table.setBounds(10, 300, 500, 200);
        table.add(new JScrollPane(resultados));
    }
    
    public void llenarTabla(){
        tm = new DefaultTableModel(){
            public Class<?> getColumnClass(int column){
                switch(column){
                    case 0:
                        return String.class;
                    case 1:
                        return String.class;
                    case 2:
                        return String.class;
                    case 3:
                        return String.class;
                    case 4:
                        return String.class;
                    case 5:
                        return String.class;
                    default:
                        return boolean.class;
                }
            }
        };
        
        tm.addColumn("Codigo");
        tm.addColumn("Nombre");
        tm.addColumn("Tipo");
        tm.addColumn("Disponibilidad");
        tm.addColumn("Precio");
        tm.addColumn("Cantida");
        
        FiltroDao fd = new FiltroDao();
        ArrayList<Filtro> filtros= fd.readAll();
        
        for(Filtro fi : filtros){
            tm.addRow(new Object[]{fi.getNombre(),fi.getCodigo(),fi.getTipo(),fi.getDispo(),fi.getPrecio(),fi.getCantidad()});
        }
        resultados.setModel(tm);
    }
    
    private void eventos(){
        insertar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FiltroDao fd = new FiltroDao();
                Filtro f = new Filtro(codigo.getText(), nombre.getText(), tipo.getSelectedItem().toString(), Integer.parseInt(cantidad.getText()), Double.valueOf(precio.getText()), true);

                if (no.isSelected()) {
                    f.setDispo(false);
                }

                if (fd.insertar(f)) {
                    JOptionPane.showMessageDialog(null, "Producto registrado con éxito");
                    limpiarCampos();
                    llenarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Ocurrio un problema con la creación de este Producto.");
                }
            }
        });
        
        actualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FiltroDao fd = new FiltroDao();
                Filtro f = new Filtro(codigo.getText(), nombre.getText(), tipo.getSelectedItem().toString(), Integer.parseInt(cantidad.getText()), Double.valueOf(precio.getText()), true);

                if (no.isSelected()) {
                    f.setDispo(false);
                }

                if (fd.actualizar(f)) {
                    JOptionPane.showMessageDialog(null, "Producto modificado con éxito");
                    limpiarCampos();
                    llenarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Ocurrio un problema al momento de creación de este Producto.");
                }
            }
        });
        
        eliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FiltroDao fd = new FiltroDao();
                Filtro f = new Filtro(codigo.getText(), nombre.getText(), tipo.getSelectedItem().toString(), Integer.parseInt(cantidad.getText()), Double.valueOf(precio.getText()), true);
                if (fd.eliminar(codigo.getText())) {
                    JOptionPane.showMessageDialog(null, "Producto eliminado con éxito");
                    limpiarCampos();
                    llenarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Ocurrio un problema al momento de eliminar este Producto.");
                }
            }
        });
        
        buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FiltroDao fd = new FiltroDao();
                Filtro f = fd.leer(codigo.getText());
                if (f == null) {
                    JOptionPane.showMessageDialog(null, "El Producto buscado no ha sido encontrado");
                } else {

                    nombre.setText(f.getCodigo());
                    codigo.setText(f.getNombre());
                    tipo.setSelectedItem(f.getTipo());
                    precio.setText(Double.toString(f.getPrecio()));
                    cantidad.setText(Integer.toString(f.getCantidad()));

                    if (f.getDispo()) {
                        si.setSelected(true);
                    } else {
                        no.setSelected(true);
                    }
                }
            }
        });

        limpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });
        
    }
    
    public void limpiarCampos() {
        codigo.setText("");
        precio.setText("");
        cantidad.setText("");
        tipo.setSelectedItem("Fruta");
        nombre.setText("");
    }
}
