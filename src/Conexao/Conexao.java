/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexao;

import Modelo.Video;
import java.sql.Connection;
import java.sql.DriverManager;
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
public class Conexao {
    private static final String bd = "jdbc:mysql://localhost:3306/plataforma_video";
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String usuario = "root";
    private static final String senha = "";
    private static Connection con = null;
    
    public Conexao() {}
    
    public static Connection getConexao() {
        if(con==null) {
            try {
                Class.forName(driver);
                con = DriverManager.getConnection(bd, usuario, senha);
            } catch(ClassNotFoundException e) {
                System.out.println("Não encontrou o driver: " + e.getMessage());
            } catch(SQLException e) {
                System.out.println("Erro de conexão: " + e.getMessage());
            }
            return con;
        }
        return con;
    }
    
    public static PreparedStatement getPreparedStatement(String sqlCommand) {
        if(con==null) {
            con = getConexao();
        }
        try {
            return con.prepareStatement(sqlCommand);
        } catch(SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        return null;
    }
}
