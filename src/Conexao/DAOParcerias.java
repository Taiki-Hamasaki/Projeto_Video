/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexao;

import Modelo.Canal;
import Modelo.ConverteDataWEB;
import Modelo.Parceria;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author taiki-hamasaki
 */
public class DAOParcerias {

    static ConverteDataWEB converte = new ConverteDataWEB();
    static DAOCanal daoCanal = new DAOCanal();
    static private Integer lastId;

    public Integer getLastId() {
        return lastId;
    }

    public static boolean incluir(Parceria objParceria) {
        String sql = "insert into parceria (nomeEmpresa, inicioContrato, fimContrato, canal_codCanal) values (?,?,?,?)";
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setString(1, objParceria.getNomeEmpresa());
            pst.setDate(2, converte.converteBanco(objParceria.getInicioContrato()));
            pst.setDate(3, converte.converteBanco(objParceria.getFimContrato()));
            pst.setInt(4, objParceria.getCanalParceiro().getCodCanal());

            if (pst.executeUpdate() > 0) {
                ResultSet rs = pst.getGeneratedKeys();// retorna o último idCaixa cadastrado
                if (rs.next()) {
                    lastId = rs.getInt(1); //armazena o último idCaixa cadastrado
                }
                JOptionPane.showMessageDialog(null, "Parceria cadastrada com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Parceria não cadastrada");
            }
            pst.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL no método incluir da classe DAOParceria " + Errors.getStackTraceFormatted(e));
        }
        return false;
    }

    public boolean removeParceria(Parceria objParceria) {
        String sql = "delete from parceria where codParceria=?";
        try {
            PreparedStatement stmt = Conexao.getPreparedStatement(sql);
            stmt.setInt(1, objParceria.getCodParceria());
            if (stmt.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Parceria cancelada");

            } else {
                JOptionPane.showMessageDialog(null, "Parceria não cancelada");

            }
            stmt.close();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL no cancela parceria " + Errors.getStackTraceFormatted(e) + "\nComando SQL = " + sql);
        }
        return false;
    }
}
