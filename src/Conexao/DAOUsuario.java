/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexao;


import java.util.ArrayList;
import java.util.List;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Modelo.Usuario;

/**
 *
 * @author 70670989630
 */
public class DAOUsuario {
    public List<Usuario> getLista() {
        String sql = "SELECT * FROM usuario;";
        List<Usuario> listaUsuario = new ArrayList<>();
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                Usuario objUsuario = new Usuario();
                objUsuario.setCodUsuario(rs.getInt("codUsuario"));
                objUsuario.setNomeUsuario(rs.getString("nomeUsuario"));
                objUsuario.setEmail(rs.getString("email"));
                objUsuario.setSenha(rs.getString("senha"));
                listaUsuario.add(objUsuario);
            }
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL no método getLista na classe DAOUsuario\n" + Errors.getStackTraceFormatted(e));
        }
        return listaUsuario;
    }
    
    public boolean salvar(Usuario objUsuario) {
        if(objUsuario.getCodUsuario()== null) {
            return incluir(objUsuario);
        } else {
            return alterar(objUsuario);
        }
    }
    
    public boolean incluir(Usuario objUsuario) {
        String sql = "INSERT INTO usuario (nomeUsuario, email, senha) VALUES(?, ?, ?)";
        
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setString(1, objUsuario.getNomeUsuario());
            pst.setString(2, objUsuario.getEmail());
            pst.setString(3, objUsuario.getSenha());
            
            
            if(pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Usuario cadastrado com sucesso!");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Usuario não cadastrado");
                return false;
            }
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL no método incluir da classe DAOUsuario\n" + Errors.getStackTraceFormatted(e));
            return false;
        }
    }
    
    public boolean alterar(Usuario objUsuario) {
        String sql = "UPDATE usuario SET nomeUsuario = ?, email = ?, senha = ? WHERE codusuario = ?";
        
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setString(1, objUsuario.getNomeUsuario());
            pst.setString(2, objUsuario.getEmail());
            pst.setString(3, objUsuario.getSenha());
            pst.setInt(4, objUsuario.getCodUsuario());
            if(pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Usuario alterado com sucesso!");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Usuario não alterado!");
                return false;
            }
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL no método alterar da classe DAOUsuario\n" + Errors.getStackTraceFormatted(e));
            return false;
        }
    }
    
    public boolean remover(Usuario objUsuario) {
        String sql = "DELETE FROM usuario WHERE codusuario = ?";
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setInt(0, objUsuario.getCodUsuario());
            if(pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Usuário excluído com sucesso!");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Usuário não excluído");
                return false;
            }
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL no método remover da classe DAOUsuario\n" + Errors.getStackTraceFormatted(e));
            return false;
        }
    }
    
    
    public Usuario localizar(Integer id) {
        String sql = "SELECT * FROM usuario WHERE codusuario = ?";
        Usuario objUsuario = new Usuario();
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                objUsuario.setCodUsuario(rs.getInt("codusuario"));
                objUsuario.setNomeUsuario(rs.getString("nomeUsuario"));
                objUsuario.setEmail(rs.getString("email"));
                objUsuario.setSenha(rs.getString("senha"));
            }
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL no método localizar da classe DAOUsuario\n" + Errors.getStackTraceFormatted(e));
            return null;
        }
        return objUsuario;
    }
    
    public List<Usuario> pesquisa(String pesquisa) {
        List<Usuario> resultadoPesquisa = new ArrayList<>();
        Usuario u = new Usuario();
        int codigo = 0;
        String sql = "SELECT * FROM usuario WHERE ";
        // para continuar com a filtragem de dados
        String continuaSQL = "";
        // Uma maneira de verificar se a pesquisa eh um valor numerico != 0
        boolean pesquisaEhZero = false;
        try{
            pesquisaEhZero = Integer.parseInt(pesquisa) == 0 ? true : false;
            codigo = Integer.parseInt(pesquisa);
            //continuaSQL = "codUsuario LIKE \"%?\" OR codUsuario LIKE ? OR codUsuario LIKE \"%?%\"";
            continuaSQL = "codUsuario LIKE ? OR codUsuario LIKE ? OR codUsuario LIKE ?";
        } catch(NumberFormatException e) {
            // ... WHERE nomeUsuario LIKE "%string" OR nomeUsuario LIKE "string%"
            continuaSQL = "nomeUsuario LIKE ? OR nomeUsuario LIKE ? OR nomeUsuario LIKE ? OR email LIKE ? OR email LIKE ? OR email LIKE ?";
            //continuaSQL = "nomeUsuario LIKE \"%?\" OR nomeUsuario LIKE \"?%\" OR nomeUsuario LIKE \"%?%\" OR email LIKE \"%?\" OR email LIKE \"?%\" OR email LIKE \"%?%\"";
            
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
                pst.setString(4, pesquisa + "%");
                pst.setString(5, "%" + pesquisa);
                pst.setString(6, "%" + pesquisa + "%");
            }
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                u.setCodUsuario(rs.getInt("codusuario"));
                u.setNomeUsuario(rs.getString("nomeUsuario"));
                u.setEmail(rs.getString("email"));
                u.setSenha(rs.getString("senha"));
                
                resultadoPesquisa.add(u);
            }
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL no método pesquisa da classe DAOUsuario\n" + Errors.getStackTraceFormatted(e));
            e.printStackTrace();
            return null;
        }
        return resultadoPesquisa;
    }
    
    public Usuario login(String nomeOrUser, String senha) {
        String sql = "SELECT * FROM usuario WHERE nomeUsuario = ? AND senha = ? OR email = ? AND senha = ?";
        Usuario objUsuario = new Usuario();
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setString(1, nomeOrUser);
            pst.setString(2, senha);
            pst.setString(3, nomeOrUser);
            pst.setString(4, senha);
            ResultSet rs = pst.executeQuery();
            if(rs.next()) {
                objUsuario.setCodUsuario(rs.getInt("codusuario"));
                objUsuario.setNomeUsuario(rs.getString("nomeUsuario"));
                objUsuario.setEmail(rs.getString("email"));
                objUsuario.setSenha(rs.getString("senha"));
                JOptionPane.showMessageDialog(null, "Bem vindo " + objUsuario.getNomeUsuario(), "Usuário Encontrado!", JOptionPane.INFORMATION_MESSAGE);
                return objUsuario;
            } else {
                JOptionPane.showMessageDialog(null, "Usuário não encontrado", "Seus dados estão incorretos", JOptionPane.ERROR_MESSAGE);
                return null;
            }
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL no método login da classe DAOUsuario\n" + Errors.getStackTraceFormatted(e));
            return null;
        }
    }
}
