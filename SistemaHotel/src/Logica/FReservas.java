/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import Dados.Reservas;
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
public class FReservas {
    private conexao mysql=new conexao();
    private Connection cn=mysql.conectar();
    private String sSQL="";
    public Integer totalRegistros;
    
    public DefaultTableModel mostrar(String buscar){
        DefaultTableModel modelo;
        String[] titulos = {"ID Reserva", "ID Quartos", "Número", "ID Clientes", "Nome Cliente", "ID Funcionário", "Funcionário", "Tipo Reserva", "Data Reserva", "Data Entrada", "Data Saída", "Valor Quarto", "Estado"};
        String[] registro =  new String[13];
        totalRegistros = 0;
        
        modelo = new DefaultTableModel(null, titulos);
        sSQL = "select r.id_reserva,r.id_quartos,q.numero,r.id_cliente,"+
                "(select nome_usuario from tb_usuario where id_usuario=r.id_cliente) as clienten,"+
                "r.id_funcionario,(select nome_usuario from tb_usuario where id_usuario=r.id_funcionario) as funcionarion,"+
                "r.tipo_reserva,r.data_reserva,r.data_entrada,r.data_saida,"+
                "r.valor_quarto, r.estado from tb_reservas r inner join tb_quartos q on r.id_quartos=q.id_quartos where r.data_reserva like '%" + buscar + "%' order by id_reserva desc";
        
        try{
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while (rs.next()){
                registro [0] = rs.getString("id_reserva");
                registro [1] = rs.getString("id_quartos");
                registro [2] = rs.getString("numero");
                registro [3] = rs.getString("id_cliente");
                registro [4] = rs.getString("clienten");
                registro [5] = rs.getString("id_funcionario");
                registro [6] = rs.getString("funcionarion");
                registro [7] = rs.getString("tipo_reserva");
                registro [8] = rs.getString("data_reserva");
                registro [9] = rs.getString("data_entrada");
                registro [10] = rs.getString("data_saida");
                registro [11] = rs.getString("valor_quarto");
                registro [12] = rs.getString("estado");
                                
                totalRegistros += 1;
                modelo.addRow(registro);
            }
            return modelo;
        } catch (Exception e){
           JOptionPane.showConfirmDialog(null, e); 
           return null;
        }
    }
    
    public boolean inserir(Reservas dts){
        sSQL = "insert into tb_reservas (id_quartos, id_cliente, id_funcionario, tipo_reserva, data_reserva, data_entrada, data_saida, valor_quarto, estado)" + 
                "values(?,?,?,?,?,?,?,?,?)";
        try{
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, dts.getIdquartos());
            pst.setInt(2, dts.getIdcliente());
            pst.setInt(3, dts.getIdfuncionario());
            pst.setString(4, dts.getTipo_reserva());
            pst.setDate(5, dts.getData_reserva());
            pst.setDate(6, dts.getData_entrada());
            pst.setDate(7, dts.getData_saida());
            pst.setDouble(8, dts.getValor_quarto());
            pst.setString(9, dts.getEstado());
            
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
    
    public boolean editar(Reservas dts){
        
        sSQL = "update tb_reservas set id_cliente=?, id_quartos=?, id_funcionario=?, tipo_reserva=?, data_reserva=?, data_entrada=?, data_saida=?, valor_quarto=?, estado=?" +
                "where id_reserva=?";
                
        try{            
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, dts.getIdquartos());
            pst.setInt(2, dts.getIdcliente());
            pst.setInt(3, dts.getIdfuncionario());
            pst.setString(4, dts.getTipo_reserva());
            pst.setDate(5, dts.getData_reserva());
            pst.setDate(6, dts.getData_entrada());
            pst.setDate(7, dts.getData_saida());
            pst.setDouble(8, dts.getValor_quarto());
            pst.setString(9, dts.getEstado());
            
            pst.setInt(10, dts.getIdreserva());
           
            
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
    
    public boolean deletar(Reservas dts){
        sSQL = "delete from tb_reservas where id_reserva=?";
        try{
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, dts.getIdreserva());        
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
    
    public boolean pagar (Reservas dts){
        
        sSQL="update tb_reservas set estado='PAGA'" +
                "where id_reserva=?";
                       
        try {
            PreparedStatement pst=cn.prepareStatement(sSQL);                 
          
            pst.setInt(1, dts.getIdreserva());
            
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
