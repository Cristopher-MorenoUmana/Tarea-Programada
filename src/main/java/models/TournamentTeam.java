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
@Table(name = "TBL_TOURNAMENT_TEAMS")
@NamedQueries({
    @NamedQuery(name = "TournamentTeam.findAll", query = "SELECT t FROM TournamentTeam t"),
    @NamedQuery(name = "TournamentTeam.findByTttId", query = "SELECT t FROM TournamentTeam t WHERE t.tttId = :tttId")})
public class TournamentTeam implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "TTT_ID")
    private Integer tttId;
    @JoinColumn(name = "TTT_TEAM_ID", referencedColumnName = "TEAM_ID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Team tttTeamId;
    @JoinColumn(name = "TTT_TRM_ID", referencedColumnName = "TRM_ID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Tournament tttTrmId;

    public TournamentTeam() {
    }

    public TournamentTeam(Integer tttId) {
        this.tttId = tttId;
    }

    public TournamentTeam(TournamentTeamDto pTournamentTeamDto){
        
        updateTournamentTeam(pTournamentTeamDto);
    }

    public final void updateTournamentTeam(TournamentTeamDto pTournamentTeamDto) {

        this.tttTeamId = pTournamentTeamDto.getTeam();
        this.tttTrmId = pTournamentTeamDto.getTournament();
    }

    public Integer getTttId() {
        return tttId;
    }

    public void setTttId(Integer tttId) {
        this.tttId = tttId;
    }

    public Team getTttTeamId() {
        return tttTeamId;
    }

    public void setTttTeamId(Team tttTeamId) {
        this.tttTeamId = tttTeamId;
    }

    public Tournament getTttTrmId() {
        return tttTrmId;
    }

    public void setTttTrmId(Tournament tttTrmId) {
        this.tttTrmId = tttTrmId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tttId != null ? tttId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof TournamentTeam)) {
            return false;
        }
        TournamentTeam other = (TournamentTeam) object;
        return !((this.tttId == null && other.tttId != null) || (this.tttId != null && !this.tttId.equals(other.tttId)));
    }

    @Override
    public String toString() {
        return "models.TournamentTeam[ tttId=" + tttId + " ]";
    }
    
}
