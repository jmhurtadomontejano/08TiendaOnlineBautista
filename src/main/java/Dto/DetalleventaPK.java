/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dto;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author jmhur
 */
@Embeddable
public class DetalleventaPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "codigoventa")
    private int codigoventa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigoproducto")
    private int codigoproducto;

    public DetalleventaPK() {
    }

    public DetalleventaPK(int codigoventa, int codigoproducto) {
        this.codigoventa = codigoventa;
        this.codigoproducto = codigoproducto;
    }

    public int getCodigoventa() {
        return codigoventa;
    }

    public void setCodigoventa(int codigoventa) {
        this.codigoventa = codigoventa;
    }

    public int getCodigoproducto() {
        return codigoproducto;
    }

    public void setCodigoproducto(int codigoproducto) {
        this.codigoproducto = codigoproducto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codigoventa;
        hash += (int) codigoproducto;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleventaPK)) {
            return false;
        }
        DetalleventaPK other = (DetalleventaPK) object;
        if (this.codigoventa != other.codigoventa) {
            return false;
        }
        if (this.codigoproducto != other.codigoproducto) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DetalleventaPK{" + "codigoventa=" + codigoventa + ", codigoproducto=" + codigoproducto + '}';
    }


}
