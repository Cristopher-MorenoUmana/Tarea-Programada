package models;

import java.io.Serializable;
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

@Entity
@Table(name = "TBL_PARTICIPATION")
@NamedQueries({
    @NamedQuery(name = "Participation.findAll", query = "SELECT p FROM Participation p"),
    @NamedQuery(name = "Participation.findByPctId", query = "SELECT p FROM Participation p WHERE p.pctId = :pctId"),
    @NamedQuery(name = "Participation.findByPctPoints", query = "SELECT p FROM Participation p WHERE p.pctPoints = :pctPoints"),
    @NamedQuery(name = "Participation.findByPctPosition", query = "SELECT p FROM Participation p WHERE p.pctPosition = :pctPosition")})
public class Participation implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PCT_ID")
    private Integer pctId;
    @Basic(optional = false)
    @Column(name = "PCT_POINTS")
    private Integer pctPoints;
    @Basic(optional = false)
    @Column(name = "PCT_POSITION")
    private Integer pctPosition;
    @JoinColumn(name = "PCT_MTH_ID", referencedColumnName = "MTH_ID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Match pctMthId;
    @JoinColumn(name = "PCT_TEAM_ID", referencedColumnName = "TEAM_ID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Team pctTeamId;
    @JoinColumn(name = "PCT_TRM_ID", referencedColumnName = "TRM_ID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Tournament pctTrmId;

    public Participation() {
    }

    public Participation(Integer pctId) {
        this.pctId = pctId;
    }

    public Participation(Integer pctId, Integer pctPoints, Integer pctPosition) {
        this.pctId = pctId;
        this.pctPoints = pctPoints;
        this.pctPosition = pctPosition;
    }

    public Integer getPctId() {
        return pctId;
    }

    public void setPctId(Integer pctId) {
        this.pctId = pctId;
    }

    public Integer getPctPoints() {
        return pctPoints;
    }

    public void setPctPoints(Integer pctPoints) {
        this.pctPoints = pctPoints;
    }

    public Integer getPctPosition() {
        return pctPosition;
    }

    public void setPctPosition(Integer pctPosition) {
        this.pctPosition = pctPosition;
    }

    public Match getPctMthId() {
        return pctMthId;
    }

    public void setPctMthId(Match pctMthId) {
        this.pctMthId = pctMthId;
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

        if (!(object instanceof Participation)) {
            return false;
        }
        Participation other = (Participation) object;
        return !((this.pctId == null && other.pctId != null) || (this.pctId != null && !this.pctId.equals(other.pctId)));
    }

    @Override
    public String toString() {
        return "models.Participation[ pctId=" + pctId + " ]";
    }
    
}
