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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_SPORT")
@NamedQueries({
    @NamedQuery(name = "Sport.findAll", query = "SELECT s FROM Sport s"),
    @NamedQuery(name = "Sport.findBySptId", query = "SELECT s FROM Sport s WHERE s.sptId = :sptId"),
    @NamedQuery(name = "Sport.findBySptName", query = "SELECT s FROM Sport s WHERE s.sptName = :sptName"),
    @NamedQuery(name = "Sport.findBySptBallUrl", query = "SELECT s FROM Sport s WHERE s.sptBallUrl = :sptBallUrl")})
public class Sport implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "SPT_ID")
    private Integer sptId;
    @Basic(optional = false)
    @Column(name = "SPT_NAME")
    private String sptName;
    @Basic(optional = false)
    @Column(name = "SPT_BALL_URL")
    private String sptBallUrl;
    @OneToMany(mappedBy = "teamSptId", fetch = FetchType.EAGER)
    private Collection<Team> teamCollection;
    @OneToMany(mappedBy = "trmSptId", fetch = FetchType.EAGER)
    private Collection<Tournament> tournamentCollection;

    public Sport() {
    }

    public Sport(Integer sptId) {
        this.sptId = sptId;
    }

    public Sport(Integer sptId, String sptName, String sptBallUrl) {
        this.sptId = sptId;
        this.sptName = sptName;
        this.sptBallUrl = sptBallUrl;
    }

    public Sport(SportDto pSportDto){
        
        updateSport(pSportDto);
    }
    
    public final void updateSport(SportDto pSportDto){
        
        this.sptBallUrl = pSportDto.getBallUrl();
        this.sptName = pSportDto.getName();
    }
    
    public Integer getSptId() {
        return sptId;
    }

    public void setSptId(Integer sptId) {
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

        if (!(object instanceof Sport)) {
            return false;
        }
        Sport other = (Sport) object;
        return !((this.sptId == null && other.sptId != null) || (this.sptId != null && !this.sptId.equals(other.sptId)));
    }

    @Override
    public String toString() {
        return "models.Sport[ sptId=" + sptId + " ]";
    }
    
}
