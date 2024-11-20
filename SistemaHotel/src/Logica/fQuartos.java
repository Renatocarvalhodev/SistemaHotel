/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import Dados.Quartos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.table.DefaultTableModel;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author renat
 */
public class fQuartos {
    private conexao mysql=new conexao();
    private Connection cn=mysql.conectar();
    private String sSQL="";
    public Integer totalRegistros;
    
    public DefaultTableModel mostrar(String buscar){
        DefaultTableModel modelo;
        String[] titulos = {"ID", "Numero", "Andar", "Descrição", "Caracteristicas", "Preço", "Estado", "Tipo de Quarto"};
        String[] registro =  new String[8];
        totalRegistros = 0;
        
        modelo = new DefaultTableModel(null, titulos);
        sSQL = "select * from tb_quartos where andar like '%" + buscar + "%' order by id_quartos";
        
        try{
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while (rs.next()){
                registro [0] = rs.getString("id_quartos");
                registro [1] = rs.getString("numero");
                registro [2] = rs.getString("andar");
                registro [3] = rs.getString("descricao");
                registro [4] = rs.getString("caracteristicas");
                registro [5] = rs.getString("preco_diaria");
                registro [6] = rs.getString("estado");
                registro [7] = rs.getString("tipo_quarto");
                
                totalRegistros += 1;
                modelo.addRow(registro);
            }
            return modelo;
        } catch (Exception e){
           JOptionPane.showConfirmDialog(null, e); 
           return null;
        }
    }
    
     public DefaultTableModel mostrarQuartos(String buscar){
        DefaultTableModel modelo;
        String[] titulos = {"ID", "Numero", "Andar", "Descrição", "Caracteristicas", "Preço", "Estado", "Tipo de Quarto"};
        String[] registro =  new String[8];
        totalRegistros = 0;
        
        modelo = new DefaultTableModel(null, titulos);
        sSQL = "select * from tb_quartos where andar like '%" + buscar + "%' and estado='Disponível' order by id_quartos";
        
        try{
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while (rs.next()){
                registro [0] = rs.getString("id_quartos");
                registro [1] = rs.getString("numero");
                registro [2] = rs.getString("andar");
                registro [3] = rs.getString("descricao");
                registro [4] = rs.getString("caracteristicas");
                registro [5] = rs.getString("preco_diaria");
                registro [6] = rs.getString("estado");
                registro [7] = rs.getString("tipo_quarto");
                
                totalRegistros += 1;
                modelo.addRow(registro);
            }
            return modelo;
        } catch (Exception e){
           JOptionPane.showConfirmDialog(null, e); 
           return null;
        }
    }
    
    public boolean inserir(Quartos dts){
        sSQL = "insert into tb_quartos (numero, andar, descricao, caracteristicas, preco_diaria, estado, tipo_quarto)" + 
                "values(?,?,?,?,?,?,?)";
        try{
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setString(1, dts.getNumero());
            pst.setString(2, dts.getAndar());
            pst.setString(3, dts.getDescricao());
            pst.setString(4, dts.getCaracteristicas());
            pst.setDouble(5, dts.getValor_diaria());
            pst.setString(6, dts.getEstado());
            pst.setString(7, dts.getTipo_quarto());
            
            int n = pst.executeUpdate();
            if(n != 0){
                return true;
            } else {
                return false;
            }
            
        } catch (Exception e){
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }
    
    public boolean editar(Quartos dts){
        
        sSQL = "update tb_quartos set numero=?, andar=?, descricao=?, caracteristicas=?, preco_diaria=?, estado=?, tipo_quarto=?" +
                "where id_quartos=?";
                
        try{            
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setString(1, dts.getNumero());
            pst.setString(2, dts.getAndar());
            pst.setString(3, dts.getDescricao());
            pst.setString(4, dts.getCaracteristicas());
            pst.setDouble(5, dts.getValor_diaria());
            pst.setString(6, dts.getEstado());
            pst.setString(7, dts.getTipo_quarto());
            pst.setInt(8, dts.getId_quartos());
            
            int n = pst.executeUpdate();
            if(n != 0){
                return true;
            } else {
                return false;
            }
            
        } catch (Exception e){
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }
    
    public boolean deletar(Quartos dts){
        sSQL = "delete from tb_quartos where id_quartos=?";
        try{
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, dts.getId_quartos());        
            int n = pst.executeUpdate();
            if(n != 0){
                return true;
            } else {
                return false;
            }
            
        } catch (Exception e){
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }
    
    public boolean desocupar (Quartos dts){
        
        sSQL="update tb_quartos set  estado='Disponí­vel'" +
                "where id_quartos=?";
        
               
        try {
            PreparedStatement pst=cn.prepareStatement(sSQL);
           
            pst.setInt(1, dts.getId_quartos());
            
             int n=pst.executeUpdate();
            if (n!=0){
                return true;
            }else{
                return false;
            }
            
        }catch (Exception e){
             JOptionPane.showConfirmDialog(null, e);
         return false;
        }
    }
    
    public boolean ocupar (Quartos dts){
        
        sSQL="update tb_quartos set  estado='Ocupado'" +
                "where id_quartos=?";
        
               
        try {
            PreparedStatement pst=cn.prepareStatement(sSQL);
           
            pst.setInt(1, dts.getId_quartos());
            
             int n=pst.executeUpdate();
            if (n!=0){
                return true;
            }else{
                return false;
            }
            
        }catch (Exception e){
             JOptionPane.showConfirmDialog(null, e);
         return false;
        }
    }


}
