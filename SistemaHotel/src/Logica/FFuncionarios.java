/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import Dados.Funcionarios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author renat
 */
public class FFuncionarios {
  private conexao mysql=new conexao();
    private Connection cn=mysql.conectar();
    private String sSQL="";
    private String sSQL2=""; 
    public Integer totalRegistros;
    
    public DefaultTableModel mostrar(String buscar){
        DefaultTableModel modelo;
        String[] titulos = {"ID", "Nome", "Nome Pai", "Nome Mãe", "Documento", "Número Doc", "Endereço", "Telefone", "Email", "Salário", "Acesso", "Login", "Senha", "Estado"};
        String[] registro =  new String[14];
        totalRegistros = 0;
        
        modelo = new DefaultTableModel(null, titulos);
        sSQL = "select p.id_usuario,p.nome_usuario,p.nome_pai,p.nome_mae,p.tipo_documento,p.num_documento," +
               "p.endereco,p.telefone,p.email,f.salario,f.acesso,f.login,f.password,f.estado from tb_usuario p inner join tb_funcionarios f " +
               "on p.id_usuario=f.id_usuario where num_documento like '%" +
               buscar + "%' order by id_usuario desc";        
        try{
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while (rs.next()){
                registro [0] = rs.getString("id_usuario");
                registro [1] = rs.getString("nome_usuario");
                registro [2] = rs.getString("nome_pai");
                registro [3] = rs.getString("nome_mae");
                registro [4] = rs.getString("tipo_documento");
                registro [5] = rs.getString("num_documento");
                registro [6] = rs.getString("endereco");
                registro [7] = rs.getString("telefone");
                registro [8] = rs.getString("email");
                registro [9] = rs.getString("salario");
                registro [10] = rs.getString("acesso");
                registro [11] = rs.getString("login");
                registro [12] = rs.getString("password");
                registro [13] = rs.getString("estado");
                
                totalRegistros += 1;
                modelo.addRow(registro);
            }
            return modelo;
        } catch (Exception e){
           JOptionPane.showConfirmDialog(null, e); 
           return null;
        }
    }
    
    public boolean inserir(Funcionarios dts){
        sSQL = "insert into tb_usuario (nome_usuario, nome_pai, nome_mae, tipo_documento, num_documento, endereco, telefone, email)" + 
                "values(?,?,?,?,?,?,?,?)";
        sSQL2 = "insert into tb_funcionarios (id_usuario, salario, acesso, login, password, estado)" + 
                "values((select id_usuario from tb_usuario order by id_usuario desc limit 1),?,?,?,?,?)";
        try {
            
            PreparedStatement pst=cn.prepareStatement(sSQL);
            PreparedStatement pst2=cn.prepareStatement(sSQL2);
            
            pst.setString(1, dts.getNome());
            pst.setString(2, dts.getNome_pai());
            pst.setString(3, dts.getNome_mae());
            pst.setString(4, dts.getTipo_documento());
            pst.setString(5, dts.getNum_documento());
            pst.setString(6, dts.getEndereco());
            pst.setString(7, dts.getTelefone());
            pst.setString(8, dts.getEmail());
                        
            pst2.setDouble(1, dts.getSalario());
            pst2.setString(2, dts.getAcesso());
            pst2.setString(3, dts.getLogin());
            pst2.setString(4, dts.getPassword());
            pst2.setString(5, dts.getEstado());
            
            int n=pst.executeUpdate();
            if (n!=0){
                
                int n2=pst2.executeUpdate();
                if (n2!=0){
                return true;
                }
                
                else{
                return false;
            }
                
            }else{
                return false;
            }
            
        }catch (Exception e){
             JOptionPane.showConfirmDialog(null, e);
             return false;
        }

    }
    
    public boolean editar(Funcionarios dts){
        
        sSQL = "update tb_usuario set nome_usuario=?, nome_pai=?, nome_mae=?, tipo_documento=?, num_documento=?, endereco=?, telefone=?, email=?" +
                "where id_usuario=?";
        sSQL = "update tb_funcionarios set salario=?, acesso=?, login=?, password=?, estado=? " +
                "where id_usuario=?";
        try{
            PreparedStatement pst = cn.prepareStatement(sSQL);
            PreparedStatement pst2 = cn.prepareStatement(sSQL2);
            pst.setString(1, dts.getNome());
            pst.setString(2, dts.getNome_pai());
            pst.setString(3, dts.getNome_mae());
            pst.setString(4, dts.getTipo_documento());
            pst.setString(5, dts.getNum_documento());
            pst.setString(6, dts.getEndereco());
            pst.setString(7, dts.getTelefone());
            pst.setString(8, dts.getEmail());
            pst.setInt(9, dts.getId_usuario());
            
            pst2.setDouble(1, dts.getSalario());
            pst2.setString(2, dts.getAcesso());
            pst2.setString(3, dts.getLogin());
            pst2.setString(4, dts.getPassword());
            pst2.setString(5, dts.getEstado());
            //pst2.setInt(6, dts.getId_usuario());
            
            int n = pst.executeUpdate();
            if(n != 0){
                int n2 = pst2.executeUpdate();
                if (n2!= 0){
                    return true;
                }                
                else {
                    return false;    
                }
            } else {
                return false;
            }
            
        } catch (Exception e){
            //JOptionPane.showConfirmDialog(null, e);
            return false;
        }       
        
    }
    
    public boolean deletar(Funcionarios dts){
        sSQL = "delete from tb_funcionarios where id_usuario=?";
        sSQL2 = "delete from tb_usuario where id_usuario=?";
        
        try{
            PreparedStatement pst = cn.prepareStatement(sSQL);
            PreparedStatement pst2 = cn.prepareStatement(sSQL2);
            
            pst.setInt(1, dts.getId_usuario());           
            
            pst2.setInt(1, dts.getId_usuario());
            
            int n = pst.executeUpdate();
            if(n != 0){
                int n2 = pst2.executeUpdate();
                if (n2!= 0){
                    return true;
                }                
                else {
                    return false;    
                }
            } else {
                return false;
            }
            
        } catch (Exception e){
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }  
    
    public DefaultTableModel login(String login, String password){
        
        DefaultTableModel modelo;
        String[] titulos = {"ID", "Nome", "Nome Pai", "Nome Mãe",  "Acesso", "Login", "Senha", "Estado"};
        String[] registro = new String[8];
        totalRegistros = 0;
    
        modelo = new DefaultTableModel(null, titulos);
        sSQL = "select p.id_usuario,p.nome_usuario,p.nome_pai,p.nome_mae,"
                + "f.acesso,f.login,f.password,f.estado from tb_usuario p inner join tb_funcionarios f "
                + "on p.id_usuario=f.id_usuario where f.login='" 
                + login + "' and f.password='" + password + "' and f.estado='A'";
    
        try{
            Statement st = cn.createStatement();
            ResultSet rs=st.executeQuery(sSQL);
            while(rs.next()){
                   registro [0]=rs.getString("id_usuario");
                   registro [1]=rs.getString("nome_usuario");
                   registro [2]=rs.getString("nome_pai");
                   registro [3]=rs.getString("nome_mae");
                   registro [4]=rs.getString("acesso");
                   registro [5]=rs.getString("login");
                   registro [6]=rs.getString("password");
                   registro [7]=rs.getString("estado");

                   totalRegistros += 1;
                   modelo.addRow(registro);
            }
            return modelo;
        }catch (Exception e){
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }    
    }
}
