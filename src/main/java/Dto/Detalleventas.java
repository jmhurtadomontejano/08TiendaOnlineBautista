/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dto;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jmhur
 */
@Entity
@Table(name = "detalleventas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detalleventa.findAll", query = "SELECT d FROM Detalleventa d"),
    @NamedQuery(name = "Detalleventa.findByCodigoventa", query = "SELECT d FROM Detalleventa d WHERE d.detalleventaPK.codigoventa = :codigoventa"),
    @NamedQuery(name = "Detalleventa.findByCodigoproducto", query = "SELECT d FROM Detalleventa d WHERE d.detalleventaPK.codigoproducto = :codigoproducto"),
    @NamedQuery(name = "Detalleventa.findByPvp", query = "SELECT d FROM Detalleventa d WHERE d.pvp = :pvp"),
    @NamedQuery(name = "Detalleventa.findByCantidad", query = "SELECT d FROM Detalleventa d WHERE d.cantidad = :cantidad"),
    @NamedQuery(name = "Detalleventa.findByPctDescuento", query = "SELECT d FROM Detalleventa d WHERE d.pctDescuento = :pctDescuento")})
public class Detalleventas implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetalleventaPK detalleventaPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "pvp")
    private BigDecimal pvp;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad")
    private int cantidad;
    @Column(name = "pctDescuento")
    private BigDecimal pctDescuento;
    @JoinColumn(name = "codigoproducto", referencedColumnName = "idProducto", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Productos producto;
    @JoinColumn(name = "codigoventa", referencedColumnName = "idVenta", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Ventas venta;

    public Detalleventas() {
    }

    public Detalleventas(DetalleventaPK detalleventaPK) {
        this.detalleventaPK = detalleventaPK;
    }

    public Detalleventas(DetalleventaPK detalleventaPK, BigDecimal pvp, int cantidad) {
        this.detalleventaPK = detalleventaPK;
        this.pvp = pvp;
        this.cantidad = cantidad;
    }

    public Detalleventas(int codigoventa, int codigoproducto) {
        this.detalleventaPK = new DetalleventaPK(codigoventa, codigoproducto);
    }

    public DetalleventaPK getDetalleventaPK() {
        return detalleventaPK;
    }

    public void setDetalleventaPK(DetalleventaPK detalleventaPK) {
        this.detalleventaPK = detalleventaPK;
    }

    public BigDecimal getPvp() {
        return pvp;
    }

    public void setPvp(BigDecimal pvp) {
        this.pvp = pvp;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPctDescuento() {
        return pctDescuento;
    }

    public void setPctDescuento(BigDecimal pctDescuento) {
        this.pctDescuento = pctDescuento;
    }

    public Productos getProducto() {
        return producto;
    }

    public void setProducto(Productos producto) {
        this.producto = producto;
    }

    public Ventas getVenta() {
        return venta;
    }

    public void setVenta(Ventas venta) {
        this.venta = venta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detalleventaPK != null ? detalleventaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detalleventas)) {
            return false;
        }
        Detalleventas other = (Detalleventas) object;
        if ((this.detalleventaPK == null && other.detalleventaPK != null) || (this.detalleventaPK != null && !this.detalleventaPK.equals(other.detalleventaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Detalleventa{" + "detalleventaPK=" + detalleventaPK + ", pvp=" + pvp + ", cantidad=" + cantidad + ", pctDescuento=" + pctDescuento + ", producto=" + producto + ", venta=" + venta + '}';
    }


    
}
