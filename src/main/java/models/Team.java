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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author neynm
 */
@Entity
@Table(name = "TBL_TEAM")
@NamedQueries({
    @NamedQuery(name = "Team.findAll", query = "SELECT t FROM Team t"),
    @NamedQuery(name = "Team.findByTeamId", query = "SELECT t FROM Team t WHERE t.teamId = :teamId"),
    @NamedQuery(name = "Team.findByTeamLogoUrl", query = "SELECT t FROM Team t WHERE t.teamLogoUrl = :teamLogoUrl"),
    @NamedQuery(name = "Team.findByTeamName", query = "SELECT t FROM Team t WHERE t.teamName = :teamName")})
public class Team implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "TEAM_ID")
    private BigDecimal teamId;
    @Basic(optional = false)
    @Column(name = "TEAM_LOGO_URL")
    private String teamLogoUrl;
    @Basic(optional = false)
    @Column(name = "TEAM_NAME")
    private String teamName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mthTeam1", fetch = FetchType.EAGER)
    private Collection<Match> matchCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mthWinner", fetch = FetchType.EAGER)
    private Collection<Match> matchCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mthTeam2", fetch = FetchType.EAGER)
    private Collection<Match> matchCollection2;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pctTeamId", fetch = FetchType.EAGER)
    private Collection<Participation> participationCollection;
    @JoinColumn(name = "TEAM_SPT_ID", referencedColumnName = "SPT_ID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Sport teamSptId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trmWinnerId", fetch = FetchType.EAGER)
    private Collection<Tournament> tournamentCollection;

    public Team() {
    }

    public Team(BigDecimal teamId) {
        this.teamId = teamId;
    }

    public Team(BigDecimal teamId, String teamLogoUrl, String teamName) {
        this.teamId = teamId;
        this.teamLogoUrl = teamLogoUrl;
        this.teamName = teamName;
    }

    public BigDecimal getTeamId() {
        return teamId;
    }

    public void setTeamId(BigDecimal teamId) {
        this.teamId = teamId;
    }

    public String getTeamLogoUrl() {
        return teamLogoUrl;
    }

    public void setTeamLogoUrl(String teamLogoUrl) {
        this.teamLogoUrl = teamLogoUrl;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Collection<Match> getMatchCollection() {
        return matchCollection;
    }

    public void setMatchCollection(Collection<Match> matchCollection) {
        this.matchCollection = matchCollection;
    }

    public Collection<Match> getMatchCollection1() {
        return matchCollection1;
    }

    public void setMatchCollection1(Collection<Match> matchCollection1) {
        this.matchCollection1 = matchCollection1;
    }

    public Collection<Match> getMatchCollection2() {
        return matchCollection2;
    }

    public void setMatchCollection2(Collection<Match> matchCollection2) {
        this.matchCollection2 = matchCollection2;
    }

    public Collection<Participation> getParticipationCollection() {
        return participationCollection;
    }

    public void setParticipationCollection(Collection<Participation> participationCollection) {
        this.participationCollection = participationCollection;
    }

    public Sport getTeamSptId() {
        return teamSptId;
    }

    public void setTeamSptId(Sport teamSptId) {
        this.teamSptId = teamSptId;
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
        hash += (teamId != null ? teamId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Team)) {
            return false;
        }
        Team other = (Team) object;
        if ((this.teamId == null && other.teamId != null) || (this.teamId != null && !this.teamId.equals(other.teamId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Team[ teamId=" + teamId + " ]";
    }
    
}
