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
    
     DAOGenero daoGenero = new DAOGenero();
     DAOCanal daocanal = new DAOCanal();
    
    public  List<Video> getLista() {
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
            objVideo.setNumCurtidas(0);
            objVideo.setVisualizacoes(0);
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
            if(objVideo.getNumCurtidas() == null && objVideo.getVisualizacoes() == null) {
                pst.setInt(2, 0);
                pst.setInt(3, 0);
            } else {
                pst.setInt(2, objVideo.getNumCurtidas());
                pst.setInt(3, objVideo.getVisualizacoes());
            }
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
    
    
    public  Video localizar(Integer id) {
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
    
    public List<Video> pesquisa(String pesquisa) {
        List<Video> resultadoPesquisa = new ArrayList<>();
        Video video = new Video();
        int codigo = 0;
        String sql = "SELECT * FROM video AS V WHERE ";
        // para continuar com a filtragem de dados
        String continuaSQL = "";
        // Uma maneira de verificar se a pesquisa eh um valor numerico != 0
        boolean pesquisaEhZero = false;
        try{
            pesquisaEhZero = Integer.parseInt(pesquisa) == 0 ? true : false;
            codigo = Integer.parseInt(pesquisa);
            // continuaSQL = "codUsuario LIKE \"%?\" OR codUsuario LIKE ? OR codUsuario LIKE \"%?%\"";
            continuaSQL = "(codVideo = ? OR codVideo = ? OR codVideo = ?)";
        } catch(NumberFormatException e) {
            // ... WHERE nomeCanal LIKE "%string" OR nomeCanal LIKE "string%"
            continuaSQL = "(V.tituloVideo LIKE ? OR V.tituloVideo LIKE ? OR V.tituloVideo LIKE ?)";
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
                video.setCodVideo(rs.getInt("codVideo"));
                video.setTituloVideo(rs.getString("tituloVideo"));
                video.setNumCurtidas(rs.getInt("numeroCurtidas"));
                video.setVisualizacoes(rs.getInt("visualizacoes"));
                video.setGeneroVideo(daoGenero.localizar(rs.getInt("genero_codCategoria")));
                video.setDonoVideo(daocanal.localizar(rs.getInt("canal_codCanal")));
                
                resultadoPesquisa.add(video);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro de SQL no método pesquisa da classe DAOVideo\n" + Errors.getStackTraceFormatted(e));
            return null;
        }
        return resultadoPesquisa;
    }
}
