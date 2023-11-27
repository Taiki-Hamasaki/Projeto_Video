/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author 70670989630
 */
public class Video implements Serializable {
    private Integer codVideo;
    private String tituloVideo;
    private Integer numCurtidas;
    private Integer visualizacoes;
    // Relacionamentos
    private Genero generoVideo;
    private Canal donoVideo;
    

    public Integer getCodVideo() {
        return codVideo;
    }

    public void setCodVideo(Integer codVideo) {
        this.codVideo = codVideo;
    }

    public String getTituloVideo() {
        return tituloVideo;
    }

    public void setTituloVideo(String tituloVideo) {
        this.tituloVideo = tituloVideo;
    }

    public Integer getNumCurtidas() {
        return numCurtidas;
    }

    public void setNumCurtidas(Integer numCurtidas) {
        this.numCurtidas = numCurtidas;
    }

    public Integer getVisualizacoes() {
        return visualizacoes;
    }

    public void setVisualizacoes(Integer visualizacoes) {
        this.visualizacoes = visualizacoes;
    }

    public Genero getGeneroVideo() {
        return generoVideo;
    }

    public void setGeneroVideo(Genero generoVideo) {
        this.generoVideo = generoVideo;
    }

    public Canal getDonoVideo() {
        return donoVideo;
    }

    public void setDonoVideo(Canal donoVideo) {
        this.donoVideo = donoVideo;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Video other = (Video) obj;
        if (!Objects.equals(this.codVideo, other.codVideo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return tituloVideo;
    }
    
    
    
}
