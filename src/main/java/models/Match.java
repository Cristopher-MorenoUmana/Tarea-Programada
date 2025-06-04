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

@Entity
@Table(name = "TBL_MATCH")
@NamedQueries({
    @NamedQuery(name = "Match.findAll", query = "SELECT m FROM Match m"),
    @NamedQuery(name = "Match.findByMthId", query = "SELECT m FROM Match m WHERE m.mthId = :mthId"),
    @NamedQuery(name = "Match.findByMthScoreTeam1", query = "SELECT m FROM Match m WHERE m.mthScoreTeam1 = :mthScoreTeam1"),
    @NamedQuery(name = "Match.findByMthScoreTeam2", query = "SELECT m FROM Match m WHERE m.mthScoreTeam2 = :mthScoreTeam2"),
    @NamedQuery(name = "Match.findByMthState", query = "SELECT m FROM Match m WHERE m.mthState = :mthState")})
public class Match implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MTH_ID")
    private Integer mthId;
    @Basic(optional = false)
    @Column(name = "MTH_STATE")
    private String mthState;
    @JoinColumn(name = "MTH_TEAM1", referencedColumnName = "TEAM_ID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Team mthTeam1;
    @JoinColumn(name = "MTH_WINNER", referencedColumnName = "TEAM_ID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Team mthWinner;
    @JoinColumn(name = "MTH_TEAM2", referencedColumnName = "TEAM_ID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Team mthTeam2;
    @Basic(optional = false)
    @Column(name = "MTH_SCORE_TEAM1")
    private Integer mthScoreTeam1;
    @Basic(optional = false)
    @Column(name = "MTH_SCORE_TEAM2")
    private Integer mthScoreTeam2;
    @OneToMany(mappedBy = "pctMthId", fetch = FetchType.EAGER)
    private Collection<Participation> participationCollection;

    public Match() {
    }

    public Match(Integer mthId) {
        this.mthId = mthId;
    }

    public Match(Integer mthId, Integer mthScoreTeam1, Integer mthScoreTeam2, String mthState) {
        this.mthId = mthId;
        this.mthScoreTeam1 = mthScoreTeam1;
        this.mthScoreTeam2 = mthScoreTeam2;
        this.mthState = mthState;
    }

    public Match(MatchDto pMatchDto) {
        
        updateMatch(pMatchDto);
    }
    
    public final void updateMatch(MatchDto pMatchDto){
        
        this.mthScoreTeam1 = Integer.valueOf(pMatchDto.getScoreTeam1());
        this.mthScoreTeam2 = Integer.valueOf(pMatchDto.getScoreTeam2());
        this.mthState = pMatchDto.getState();
        this.mthTeam1 = pMatchDto.getTeam1();
        this.mthTeam2 = pMatchDto.getTeam2();
        this.mthWinner = pMatchDto.getWinner();
    }
    
    public Integer getMthId() {
        return mthId;
    }

    public void setMthId(Integer mthId) {
        this.mthId = mthId;
    }

    public Integer getMthScoreTeam1() {
        return mthScoreTeam1;
    }

    public void setMthScoreTeam1(Integer mthScoreTeam1) {
        this.mthScoreTeam1 = mthScoreTeam1;
    }

    public Integer getMthScoreTeam2() {
        return mthScoreTeam2;
    }

    public void setMthScoreTeam2(Integer mthScoreTeam2) {
        this.mthScoreTeam2 = mthScoreTeam2;
    }

    public String getMthState() {
        return mthState;
    }

    public void setMthState(String mthState) {
        this.mthState = mthState;
    }

    public Team getMthTeam1() {
        return mthTeam1;
    }

    public void setMthTeam1(Team mthTeam1) {
        this.mthTeam1 = mthTeam1;
    }

    public Team getMthWinner() {
        return mthWinner;
    }

    public void setMthWinner(Team mthWinner) {
        this.mthWinner = mthWinner;
    }

    public Team getMthTeam2() {
        return mthTeam2;
    }

    public void setMthTeam2(Team mthTeam2) {
        this.mthTeam2 = mthTeam2;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mthId != null ? mthId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Match)) {
            return false;
        }
        Match other = (Match) object;
        return !((this.mthId == null && other.mthId != null) || (this.mthId != null && !this.mthId.equals(other.mthId)));
    }

    @Override
    public String toString() {
        return "models.Match[ mthId=" + mthId + " ]";
    }

    public Collection<Participation> getParticipationCollection() {
        return participationCollection;
    }

    public void setParticipationCollection(Collection<Participation> participationCollection) {
        this.participationCollection = participationCollection;
    }
    
}
