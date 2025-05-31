/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author neynm
 */
@Entity
@Table(name = "TBL_SPORT")
@NamedQueries({
    @NamedQuery(name = "Sport.findAll", query = "SELECT s FROM Sport s"),
    @NamedQuery(name = "Sport.findBySptId", query = "SELECT s FROM Sport s WHERE s.sptId = :sptId"),
    @NamedQuery(name = "Sport.findBySptName", query = "SELECT s FROM Sport s WHERE s.sptName = :sptName"),
    @NamedQuery(name = "Sport.findBySptBallUrl", query = "SELECT s FROM Sport s WHERE s.sptBallUrl = :sptBallUrl")})
public class Sport implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "SPT_ID")
    private BigDecimal sptId;
    @Basic(optional = false)
    @Column(name = "SPT_NAME")
    private String sptName;
    @Basic(optional = false)
    @Column(name = "SPT_BALL_URL")
    private String sptBallUrl;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "teamSptId", fetch = FetchType.EAGER)
    private Collection<Team> teamCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trmSptId", fetch = FetchType.EAGER)
    private Collection<Tournament> tournamentCollection;

    public Sport() {
    }

    public Sport(BigDecimal sptId) {
        this.sptId = sptId;
    }

    public Sport(BigDecimal sptId, String sptName, String sptBallUrl) {
        this.sptId = sptId;
        this.sptName = sptName;
        this.sptBallUrl = sptBallUrl;
    }

    public BigDecimal getSptId() {
        return sptId;
    }

    public void setSptId(BigDecimal sptId) {
        this.sptId = sptId;
    }

    public String getSptName() {
        return sptName;
    }

    public void setSptName(String sptName) {
        this.sptName = sptName;
    }

    public String getSptBallUrl() {
        return sptBallUrl;
    }

    public void setSptBallUrl(String sptBallUrl) {
        this.sptBallUrl = sptBallUrl;
    }

    public Collection<Team> getTeamCollection() {
        return teamCollection;
    }

    public void setTeamCollection(Collection<Team> teamCollection) {
        this.teamCollection = teamCollection;
    }

    public Collection<Tournament> getTournamentCollection() {
        return tournamentCollection;
    }

    public void setTournamentCollection(Collection<Tournament> tournamentCollection) {
        this.tournamentCollection = tournamentCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sptId != null ? sptId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sport)) {
            return false;
        }
        Sport other = (Sport) object;
        if ((this.sptId == null && other.sptId != null) || (this.sptId != null && !this.sptId.equals(other.sptId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Sport[ sptId=" + sptId + " ]";
    }
    
}
