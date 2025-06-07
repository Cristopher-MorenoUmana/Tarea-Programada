package models;

import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.SequenceGenerator;

@Entity
@Table(name = "TBL_TEAM")
@NamedQueries({
    @NamedQuery(name = "Team.findAll", query = "SELECT t FROM Team t"),
    @NamedQuery(name = "Team.findByTeamId", query = "SELECT t FROM Team t WHERE t.teamId = :teamId"),
    @NamedQuery(name = "Team.findByTeamLogoUrl", query = "SELECT t FROM Team t WHERE t.teamLogoUrl = :teamLogoUrl"),
    @NamedQuery(name = "Team.findByTeamName", query = "SELECT t FROM Team t WHERE t.teamName = :teamName")})
public class Team implements Serializable {

    @OneToMany(mappedBy = "tttTeamId", fetch = FetchType.EAGER)
    private Collection<TournamentTeam> tournamentTeamCollection;

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "team_seq_gen")
    @SequenceGenerator(name = "team_seq_gen", sequenceName = "team_seq", allocationSize = 1)
    @Column(name = "TEAM_ID")
    private Integer teamId;
    @Basic(optional = false)
    @Column(name = "TEAM_LOGO_URL")
    private String teamLogoUrl;
    @Basic(optional = false)
    @Column(name = "TEAM_NAME")
    private String teamName;
    @OneToMany(mappedBy = "mthTeam1", fetch = FetchType.EAGER)
    private Collection<Match> matchCollection;
    @OneToMany(mappedBy = "mthWinner", fetch = FetchType.EAGER)
    private Collection<Match> matchCollection1;
    @OneToMany(mappedBy = "mthTeam2", fetch = FetchType.EAGER)
    private Collection<Match> matchCollection2;
    @OneToMany(mappedBy = "pctTeamId", fetch = FetchType.EAGER)
    private Collection<Participation> participationCollection;
    @JoinColumn(name = "TEAM_SPT_ID", referencedColumnName = "SPT_ID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Sport teamSptId;
    @OneToMany(mappedBy = "trmWinnerId", fetch = FetchType.EAGER)
    private Collection<Tournament> tournamentCollection;

    public Team() {
    }

    public Team(Integer teamId) {
        this.teamId = teamId;
    }

    public Team(Integer teamId, String teamLogoUrl, String teamName) {
        this.teamId = teamId;
        this.teamLogoUrl = teamLogoUrl;
        this.teamName = teamName;
    }

    public Team(TeamDto pTeamDto){
        
        updateTeam(pTeamDto);
    }
    
    public final void updateTeam(TeamDto pTeamDto){
        
        this.teamLogoUrl = pTeamDto.getLogoUrl();
        this.teamName = pTeamDto.getName();
        this.teamSptId = pTeamDto.getSport();
    }
        
    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
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

        if (!(object instanceof Team)) {
            return false;
        }
        Team other = (Team) object;
        return !((this.teamId == null && other.teamId != null) || (this.teamId != null && !this.teamId.equals(other.teamId)));
    }

    @Override
    public String toString() {
        return "models.Team[ teamId=" + teamId + " ]";
    }

    public Collection<TournamentTeam> getTournamentTeamCollection() {
        return tournamentTeamCollection;
    }

    public void setTournamentTeamCollection(Collection<TournamentTeam> tournamentTeamCollection) {
        this.tournamentTeamCollection = tournamentTeamCollection;
    }
    
}
