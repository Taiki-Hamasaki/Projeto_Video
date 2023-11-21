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
public class Genero implements Serializable{
    private Integer codGenero;
    private String tipoGenero;
    private String pbAlvo;
    private String agePublico;

    public Integer getCodGenero() {
        return codGenero;
    }

    public void setCodGenero(Integer codGenero) {
        this.codGenero = codGenero;
    }

    public String getTipoGenero() {
        return tipoGenero;
    }

    public void setTipoGenero(String tipoGenero) {
        this.tipoGenero = tipoGenero;
    }

    public String getPbAlvo() {
        return pbAlvo;
    }

    public void setPbAlvo(String pbAlvo) {
        this.pbAlvo = pbAlvo;
    }

    public String getAgePublico() {
        return agePublico;
    }

    public void setAgePublico(String agePublico) {
        this.agePublico = agePublico;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Genero other = (Genero) obj;
        if (!Objects.equals(this.codGenero, other.codGenero)) {
            return false;
        }
        return true;
    }
}

