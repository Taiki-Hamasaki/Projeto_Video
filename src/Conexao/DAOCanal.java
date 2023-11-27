/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexao;

import Modelo.Canal;
import Modelo.Usuario;
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
public class DAOCanal {
    private  DAOUsuario daoUsuario = new DAOUsuario();
    
    public  List<Canal> getLista(int id) {
        String sql = "SELECT C.codCanal, C.nomeCanal, C.descricaoCanal, U.codusuario FROM canal AS C INNER JOIN channelowner AS CO INNER JOIN usuario AS U WHERE C.codCanal = CO.canal_codCanal AND U.codusuario = CO.usuario_codusuario AND U.codUsuario = ?";
        List<Canal> listaCanal = new ArrayList<>();
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            // Para filtrar por usuário
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                Canal objCanal = new Canal();
                objCanal.setCodCanal(rs.getInt("codCanal"));
                objCanal.setNomeCanal(rs.getString("nomeCanal"));
                objCanal.setDescricaoCanal(rs.getString("descricaoCanal"));
                objCanal.setDonoCanal(daoUsuario.localizar(rs.getInt("codUsuario")));
                listaCanal.add(objCanal);
            }
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL no método getLista na classe DAOCanal\n" + Errors.getStackTraceFormatted(e));
        }
        return listaCanal;
    }
    
    public boolean salvar(Canal objCanal) {
        if(objCanal.getCodCanal()== null) {
            return incluir(objCanal);
        } else {
            return alterar(objCanal);
        }
    }
    
    public  boolean incluir(Canal objCanal) {
        boolean retorno = false;
        String sql = "INSERT INTO canal (nomeCanal, descricaoCanal) VALUES(?, ?)";
        String sql2 = "INSERT INTO channelowner (canal_codCanal, usuario_codusuario) VALUES (?, ?)";
        
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setString(1, objCanal.getNomeCanal());
            pst.setObject(2, objCanal.getDescricaoCanal());
            
            if(pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Canal cadastrado com sucesso!");
                retorno = true;
            } else {
                JOptionPane.showMessageDialog(null, "Canal não cadastrado");
                retorno = false;
            }
            
            PreparedStatement pst2 = Conexao.getPreparedStatement(sql2);
            pst2.setInt(1, objCanal.getCodCanal());
            pst2.setInt(2, objCanal.getDonoCanal().getCodUsuario());
            
            if(pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Relacionamento cadastrado com sucesso!");
                retorno = true;
            } else {
                JOptionPane.showMessageDialog(null, "Relacionamento não cadastrado");
                retorno = false;
            }
            
        } catch(SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro de SQL no método incluir da classe DAOCanal\n" + Errors.getStackTraceFormatted(e));
            retorno = false;
        }
        return retorno;
    }
    
    public boolean alterar(Canal objCanal) {
        String sql = "UPDATE canal SET nomeCanal = ?, descricaoCanal = ? WHERE codCanal = ?";
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setString(1, objCanal.getNomeCanal());
            pst.setString(2, objCanal.getDescricaoCanal());
            pst.setInt(3, objCanal.getCodCanal());
            if(pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Canal alterado com sucesso!");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Canal não alterado!");
                return false;
            }
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL no método alterar da classe DAOCanal\n" + Errors.getStackTraceFormatted(e));
            return false;
        }
    }
    
    public boolean remover(Canal objCanal) {
        boolean retorno = false;
        String sql = "DELETE FROM channelowner WHERE canal_codCanal = ?";
        String sql2 = "DELETE FROM canal WHERE codCanal = ?";
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setInt(1, objCanal.getCodCanal());
            if(pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Relacionamento excluído com sucesso!");
                retorno =  true;
            } else {
                JOptionPane.showMessageDialog(null, "Relacionamento não excluído");
                retorno = false;
            }
            
            pst = Conexao.getPreparedStatement(sql2);
            pst.setInt(1, objCanal.getCodCanal());
            
            if(pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Canal excluído com sucesso!");
                retorno =  true;
            } else {
                JOptionPane.showMessageDialog(null, "Canal não excluído");
                retorno = false;
            }
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL no método remover da classe DAOCanal\n" + Errors.getStackTraceFormatted(e));
            retorno = false;
        }
        return retorno;
    }
    
    
    public Canal localizar(Integer id) {
        //
        String sql = "SELECT  C.codCanal, C.nomeCanal, C.descricaoCanal, U.codusuario FROM canal AS C INNER JOIN channelowner AS CO INNER JOIN usuario AS U WHERE C.codCanal = CO.canal_codCanal AND U.codusuario = CO.usuario_codusuario AND C.codCanal = ?";
        Canal objCanal = new Canal();
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if(rs.next()) {
                objCanal.setCodCanal(rs.getInt("codCanal"));
                objCanal.setNomeCanal(rs.getString("nomeCanal"));
                objCanal.setDescricaoCanal(rs.getString("descricaoCanal"));
                objCanal.setDonoCanal(daoUsuario.localizar(rs.getInt("codusuario")));
            }
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL no método localizar da classe DAOCanal\n" + Errors.getStackTraceFormatted(e));
            return null;
        }
        return objCanal;
    }
    
    public List<Canal> pesquisa(String pesquisa) {
        List<Canal> resultadoPesquisa = new ArrayList<>();
        Canal channel = new Canal();
        int codigo = 0;
        String sql = "SELECT C.codCanal, C.nomeCanal, C.descricaoCanal, U.codusuario FROM canal AS C INNER JOIN channelowner AS CO INNER JOIN usuario AS U WHERE C.codCanal = CO.canal_codCanal AND U.codusuario = CO.usuario_codusuario ";
        // para continuar com a filtragem de dados
        String continuaSQL = "";
        // Uma maneira de verificar se a pesquisa eh um valor numerico != 0
        boolean pesquisaEhZero = false;
        try{
            pesquisaEhZero = Integer.parseInt(pesquisa) == 0 ? true : false;
            codigo = Integer.parseInt(pesquisa);
            // continuaSQL = "codUsuario LIKE \"%?\" OR codUsuario LIKE ? OR codUsuario LIKE \"%?%\"";
            continuaSQL = "AND (C.codCanal = ? OR C.codCanal = ? OR C.codCanal = ?)";
        } catch(NumberFormatException e) {
            // ... WHERE nomeCanal LIKE "%string" OR nomeCanal LIKE "string%"
            continuaSQL = "AND (C.nomeCanal LIKE ? OR C.nomeCanal LIKE ? OR C.nomeCanal LIKE ?)";
        }
        sql += continuaSQL;
        
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            if( (codigo != 0 && !(pesquisaEhZero)) || (codigo == 0 && (pesquisaEhZero))  ) {
                pst.setString(1, pesquisa + "%");
                pst.setString(2, "%" + pesquisa);
                pst.setString(3, "%" + pesquisa + "%");
            } else {
                pst.setString(1, pesquisa + "%");
                pst.setString(2, "%" + pesquisa);
                pst.setString(3, "%" + pesquisa + "%");
            }
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                channel.setCodCanal(rs.getInt("codCanal"));
                channel.setNomeCanal(rs.getString("nomeCanal"));
                channel.setDescricaoCanal(rs.getString("descricaoCanal"));
                channel.setDonoCanal(daoUsuario.localizar(rs.getInt("codusuario")));
                
                resultadoPesquisa.add(channel);
            }
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL no método pesquisa da classe DAOUsuario\n" + Errors.getStackTraceFormatted(e));
            return null;
        }
        return resultadoPesquisa;
    }
}
