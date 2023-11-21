/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexao;

import Modelo.Genero;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author 70670989630
 */
public class DAOGenero {
    public static List<Genero> getLista() {
        String sql = "SELECT * FROM genero";
        List<Genero> listaGenero = new ArrayList<>();
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                Genero objGenero = new Genero();
                objGenero.setCodGenero(rs.getInt("codGenero"));
                objGenero.setTipoGenero(rs.getString("tipoGenero"));
                objGenero.setPbAlvo(rs.getString("tipoPublico"));
                objGenero.setAgePublico(rs.getString("idadePublico"));
                listaGenero.add(objGenero);
            }
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL no método getLista na classe DAOGenero\n" + Errors.getStackTraceFormatted(e));
        }
        return listaGenero;
    }
    
    public boolean salvar(Genero objGenero) {
        if(objGenero.getCodGenero()== null) {
            return incluir(objGenero);
        } else {
            return alterar(objGenero);
        }
    }
    
    public boolean incluir(Genero objGenero) {
        String sql = "INSERT INTO genero (tipoGenero, tipoPublico, idadePublico) VALUES(?, ?, ?)";
        
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setString(1, objGenero.getTipoGenero());
            pst.setObject(2, objGenero.getPbAlvo());
            pst.setObject(3, objGenero.getAgePublico());
            
            
            if(pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Genero cadastrado com sucesso!");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Genero não cadastrado");
                return false;
            }
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL no método incluir da classe DAOGenero\n" + Errors.getStackTraceFormatted(e));
            return false;
        }
    }
    
    public boolean alterar(Genero objGenero) {
        String sql = "UPDATE genero SET tipoGenero = ?, tipoPublico = ?, idadePublico = ? WHERE codGenero = ?";
        
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setString(1, objGenero.getTipoGenero());
            pst.setString(2, objGenero.getPbAlvo());
            pst.setString(3, objGenero.getAgePublico());
            pst.setInt(4, objGenero.getCodGenero());
            if(pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Genero alterado com sucesso!");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Genero não alterado!");
                return false;
            }
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL no método alterar da classe DAOGenero\n" + Errors.getStackTraceFormatted(e));
            return false;
        }
    }
    
    public boolean remover(Genero objGenero) {
        String sql = "DELETE FROM genero WHERE codGenero = ?";
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setInt(1, objGenero.getCodGenero());
            if(pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Genero excluído com sucesso!");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Genero não excluído");
                return false;
            }
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL no método remover da classe DAOGenero\n" + Errors.getStackTraceFormatted(e));
            return false;
        }
    }
    
    
    public Genero localizar(Integer id) {
        String sql = "SELECT * FROM genero WHERE codGenero = ?";
        Genero objGenero = new Genero();
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                objGenero.setCodGenero(rs.getInt("codGenero"));
                objGenero.setTipoGenero(rs.getString("tipoGenero"));
                objGenero.setPbAlvo(rs.getString("tipoPublico"));
                objGenero.setAgePublico(rs.getString("idadePublico"));
            }
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL no método localizar da classe DAOGenero\n" + Errors.getStackTraceFormatted(e));
            return null;
        }
        return objGenero;
    }
}
