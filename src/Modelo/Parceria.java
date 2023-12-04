/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;

/**
 *
 * @author taiki-hamasaki
 */
public class Parceria implements Serializable{
    private Integer codParceria;
    private String nomeEmpresa;
    private Calendar inicioContrato, fimContrato;
    private Canal canalParceiro;

    public Parceria() {
    }
    
    public Integer getCodParceria() {
        return codParceria;
    }

    public void setCodParceria(Integer codParceria) {
        this.codParceria = codParceria;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public Calendar getInicioContrato() {
        return inicioContrato;
    }

    public void setInicioContrato(Calendar inicioContrato) {
        this.inicioContrato = inicioContrato;
    }

    public Calendar getFimContrato() {
        return fimContrato;
    }

    public void setFimContrato(Calendar fimContrato) {
        this.fimContrato = fimContrato;
    }

    public Canal getCanalParceiro() {
        return canalParceiro;
    }

    public void setCanalParceiro(Canal canalParceiro) {
        this.canalParceiro = canalParceiro;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.codParceria);
        hash = 47 * hash + Objects.hashCode(this.nomeEmpresa);
        hash = 47 * hash + Objects.hashCode(this.inicioContrato);
        hash = 47 * hash + Objects.hashCode(this.fimContrato);
        hash = 47 * hash + Objects.hashCode(this.canalParceiro);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Parceria other = (Parceria) obj;
        if (!Objects.equals(this.codParceria, other.codParceria)) {
            return false;
        }
        if (!Objects.equals(this.nomeEmpresa, other.nomeEmpresa)) {
            return false;
        }
        if (!Objects.equals(this.inicioContrato, other.inicioContrato)) {
            return false;
        }
        if (!Objects.equals(this.fimContrato, other.fimContrato)) {
            return false;
        }
        if (!Objects.equals(this.canalParceiro, other.canalParceiro)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Empresa " + nomeEmpresa;
    }
    
    
    
}
