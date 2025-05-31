/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author neynm
 */
@Entity
@Table(name = "TBL_PARTICIPATION")
@NamedQueries({
    @NamedQuery(name = "Participation.findAll", query = "SELECT p FROM Participation p"),
    @NamedQuery(name = "Participation.findByPctId", query = "SELECT p FROM Participation p WHERE p.pctId = :pctId"),
    @NamedQuery(name = "Participation.findByPctPoints", query = "SELECT p FROM Participation p WHERE p.pctPoints = :pctPoints"),
    @NamedQuery(name = "Participation.findByPctPosition", query = "SELECT p FROM Participation p WHERE p.pctPosition = :pctPosition")})
public class Participation implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PCT_ID")
    private BigDecimal pctId;
    @Basic(optional = false)
    @Column(name = "PCT_POINTS")
    private BigInteger pctPoints;
    @Basic(optional = false)
    @Column(name = "PCT_POSITION")
    private BigInteger pctPosition;
    @JoinColumn(name = "PCT_TEAM_ID", referencedColumnName = "TEAM_ID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Team pctTeamId;
    @JoinColumn(name = "PCT_TRM_ID", referencedColumnName = "TRM_ID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Tournament pctTrmId;

    public Participation() {
    }

    public Participation(BigDecimal pctId) {
        this.pctId = pctId;
    }

    public Participation(BigDecimal pctId, BigInteger pctPoints, BigInteger pctPosition) {
        this.pctId = pctId;
        this.pctPoints = pctPoints;
        this.pctPosition = pctPosition;
    }

    public BigDecimal getPctId() {
        return pctId;
    }

    public void setPctId(BigDecimal pctId) {
        this.pctId = pctId;
    }

    public BigInteger getPctPoints() {
        return pctPoints;
    }

    public void setPctPoints(BigInteger pctPoints) {
        this.pctPoints = pctPoints;
    }

    public BigInteger getPctPosition() {
        return pctPosition;
    }

    public void setPctPosition(BigInteger pctPosition) {
        this.pctPosition = pctPosition;
    }

    public Team getPctTeamId() {
        return pctTeamId;
    }

    public void setPctTeamId(Team pctTeamId) {
        this.pctTeamId = pctTeamId;
    }

    public Tournament getPctTrmId() {
        return pctTrmId;
    }

    public void setPctTrmId(Tournament pctTrmId) {
        this.pctTrmId = pctTrmId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pctId != null ? pctId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Participation)) {
            return false;
        }
        Participation other = (Participation) object;
        if ((this.pctId == null && other.pctId != null) || (this.pctId != null && !this.pctId.equals(other.pctId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Participation[ pctId=" + pctId + " ]";
    }
    
}
