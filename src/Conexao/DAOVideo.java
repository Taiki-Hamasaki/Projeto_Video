/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexao;

import Modelo.Usuario;
import Modelo.Genero;
import Modelo.Video;
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
public class DAOVideo {
    
    static DAOGenero daoGenero = new DAOGenero();
    static DAOCanal daocanal = new DAOCanal();
    
    public static List<Video> getLista() {
        String sql = "SELECT * FROM video";
        List<Video> listaVideo = new ArrayList<>();
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                Video objVideo = new Video();
                objVideo.setCodVideo(rs.getInt("codVideo"));
                objVideo.setTituloVideo(rs.getString("tituloVideo"));
                objVideo.setNumCurtidas(rs.getInt("numeroCurtidas"));
                objVideo.setVisualizacoes(rs.getInt("visualizacoes"));
                objVideo.setGeneroVideo(daoGenero.localizar(rs.getInt("genero_codCategoria")));
                objVideo.setDonoVideo(daocanal.localizar(rs.getInt("canal_codCanal")));
                
                listaVideo.add(objVideo);
            }
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL no método getLista na classe DAOUsuario\n" + Errors.getStackTraceFormatted(e));
        }
        return listaVideo;
    }
    
    public boolean salvar(Video objVideo) {
        if(objVideo.getCodVideo()== null) {
            return incluir(objVideo);
        } else {
            return alterar(objVideo);
        }
    }
    
    public boolean incluir(Video objVideo) {
        String sql = "INSERT INTO video (tituloVideo, numeroCurtidas, visualizacoes, genero_codCategoria, canal_codCanal) VALUES(?, ?, ?, ?, ?)";
        
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setString(1, objVideo.getTituloVideo());
            pst.setInt(2, objVideo.getNumCurtidas());
            pst.setInt(3, objVideo.getVisualizacoes());
            pst.setInt(4, objVideo.getGeneroVideo().getCodGenero());
            pst.setInt(5, objVideo.getDonoVideo().getCodCanal());
            
            
            if(pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Video cadastrado com sucesso!");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Video não cadastrado");
                return false;
            }
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL no método incluir da classe DAOVideo\n" + Errors.getStackTraceFormatted(e));
            return false;
        }
    }
    
    public boolean alterar(Video objVideo) {
        String sql = "UPDATE video SET tituloVideo = ?, numeroCurtidas = ?, visualizacoes = ?, genero_codCategoria=?, canal_codCanal=? WHERE codVideo = ?";
        
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setString(1, objVideo.getTituloVideo());
            pst.setInt(2, objVideo.getNumCurtidas());
            pst.setInt(3, objVideo.getVisualizacoes());
            pst.setInt(4, objVideo.getGeneroVideo().getCodGenero());
            pst.setInt(5, objVideo.getDonoVideo().getCodCanal());
            pst.setInt(6, objVideo.getCodVideo());
            if(pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Video alterado com sucesso!");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Video não alterado!");
                return false;
            }
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL no método alterar da classe DAOvideo\n" + Errors.getStackTraceFormatted(e));
            return false;
        }
    }
    
    public boolean remover(Video objVideo) {
        String sql = "DELETE FROM video WHERE codVideo = ?";
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setInt(0, objVideo.getCodVideo());
            if(pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Video excluído com sucesso!");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Videos não excluído");
                return false;
            }
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL no método remover da classe DAOVideo\n" + Errors.getStackTraceFormatted(e));
            return false;
        }
    }
    
    
    public static Video localizar(Integer id) {
        String sql = "SELECT * FROM video WHERE codVideo = ?";
        Video objVideo = new Video();
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                objVideo.setCodVideo(rs.getInt("codVideo"));
                objVideo.setTituloVideo(rs.getString("tituloVideo"));
                objVideo.setNumCurtidas(rs.getInt("numeroCurtidas"));
                objVideo.setVisualizacoes(rs.getInt("visualizacoes"));
                objVideo.setGeneroVideo(daoGenero.localizar(rs.getInt("genero_codCategoria")));
                objVideo.setDonoVideo(daocanal.localizar(rs.getInt("canal_codCanal")));
            }
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL no método localizar da classe DAOVideo\n" + Errors.getStackTraceFormatted(e));
            return null;
        }
        return objVideo;
    }
    
    public static void main(String[] args) {
        System.out.println(localizar(1).getTituloVideo());
    }
}
