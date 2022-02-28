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
    @NamedQuery(name = "Detalleventas.findAll", query = "SELECT d FROM Detalleventas d"),
    @NamedQuery(name = "Detalleventas.findByCodigoventa", query = "SELECT d FROM Detalleventas d WHERE d.detalleventasPK.codigoventa = :codigoventa"),
    @NamedQuery(name = "Detalleventas.findByCodigoproducto", query = "SELECT d FROM Detalleventas d WHERE d.detalleventasPK.codigoproducto = :codigoproducto"),
    @NamedQuery(name = "Detalleventas.findByPvp", query = "SELECT d FROM Detalleventas d WHERE d.pvp = :pvp"),
    @NamedQuery(name = "Detalleventas.findByCantidad", query = "SELECT d FROM Detalleventas d WHERE d.cantidad = :cantidad"),
    @NamedQuery(name = "Detalleventas.findByPctDescuento", query = "SELECT d FROM Detalleventas d WHERE d.pctDescuento = :pctDescuento")})
public class Detalleventas implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetalleventasPK detalleventasPK;
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
    private Productos productos;
    @JoinColumn(name = "codigoventa", referencedColumnName = "idVenta", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Ventas ventas;

    public Detalleventas() {
    }

    public Detalleventas(DetalleventasPK detalleventasPK) {
        this.detalleventasPK = detalleventasPK;
    }

    public Detalleventas(DetalleventasPK detalleventasPK, BigDecimal pvp, int cantidad) {
        this.detalleventasPK = detalleventasPK;
        this.pvp = pvp;
        this.cantidad = cantidad;
    }

    public Detalleventas(int codigoventa, int codigoproducto) {
        this.detalleventasPK = new DetalleventasPK(codigoventa, codigoproducto);
    }

    public DetalleventasPK getDetalleventasPK() {
        return detalleventasPK;
    }

    public void setDetalleventasPK(DetalleventasPK detalleventasPK) {
        this.detalleventasPK = detalleventasPK;
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

    public Productos getProductos() {
        return productos;
    }

    public void setProductos(Productos productos) {
        this.productos = productos;
    }

    public Ventas getVentas() {
        return ventas;
    }

    public void setVentas(Ventas ventas) {
        this.ventas = ventas;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detalleventasPK != null ? detalleventasPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detalleventas)) {
            return false;
        }
        Detalleventas other = (Detalleventas) object;
        if ((this.detalleventasPK == null && other.detalleventasPK != null) || (this.detalleventasPK != null && !this.detalleventasPK.equals(other.detalleventasPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Dto.Detalleventas[ detalleventasPK=" + detalleventasPK + " ]";
    }
    
}
