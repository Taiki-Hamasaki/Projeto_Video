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
public class Canal extends Usuario implements Serializable {
    private Integer codCanal;
    private String nomeCanal;
    private String descricaoCanal;
    
    private Usuario donoCanal;
    
    
    public Integer getCodCanal() {
        return codCanal;
    }

    public void setCodCanal(Integer codCanal) {
        this.codCanal = codCanal;
    }

    public String getNomeCanal() {
        return nomeCanal;
    }

    public void setNomeCanal(String nomeCanal) {
        this.nomeCanal = nomeCanal;
    }

    public String getDescricaoCanal() {
        return descricaoCanal;
    }

    public void setDescricaoCanal(String descricaoCanal) {
        this.descricaoCanal = descricaoCanal;
    }

    public Usuario getDonoCanal() {
        return donoCanal;
    }

    public void setDonoCanal(Usuario donoCanal) {
        this.donoCanal = donoCanal;
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
        final Canal other = (Canal) obj;
        if (!Objects.equals(this.codCanal, other.codCanal)) {
            return false;
        }
        return true;
    }
    
    
}
