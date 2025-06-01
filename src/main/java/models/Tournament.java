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
@Table(name = "TBL_TOURNAMENT")
@NamedQueries({
    @NamedQuery(name = "Tournament.findAll", query = "SELECT t FROM Tournament t"),
    @NamedQuery(name = "Tournament.findByTrmId", query = "SELECT t FROM Tournament t WHERE t.trmId = :trmId"),
    @NamedQuery(name = "Tournament.findByTrmName", query = "SELECT t FROM Tournament t WHERE t.trmName = :trmName"),
    @NamedQuery(name = "Tournament.findByTrmTeamsQuantity", query = "SELECT t FROM Tournament t WHERE t.trmTeamsQuantity = :trmTeamsQuantity")})
public class Tournament implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "TRM_ID")
    private Integer trmId;
    @Basic(optional = false)
    @Column(name = "TRM_NAME")
    private String trmName;
    @Basic(optional = false)
    @Column(name = "TRM_TEAMS_QUANTITY")
    private Integer trmTeamsQuantity;
    @OneToMany(mappedBy = "pctTrmId", fetch = FetchType.EAGER)
    private Collection<Participation> participationCollection;
    @JoinColumn(name = "TRM_SPT_ID", referencedColumnName = "SPT_ID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Sport trmSptId;
    @JoinColumn(name = "TRM_WINNER_ID", referencedColumnName = "TEAM_ID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Team trmWinnerId;

    public Tournament() {
    }

    public Tournament(Integer trmId) {
        this.trmId = trmId;
    }

    public Tournament(Integer trmId, String trmName, Integer trmTeamsQuantity) {
        this.trmId = trmId;
        this.trmName = trmName;
        this.trmTeamsQuantity = trmTeamsQuantity;
    }

    public Integer getTrmId() {
        return trmId;
    }

    public void setTrmId(Integer trmId) {
        this.trmId = trmId;
    }

    public String getTrmName() {
        return trmName;
    }

    public void setTrmName(String trmName) {
        this.trmName = trmName;
    }

    public Integer getTrmTeamsQuantity() {
        return trmTeamsQuantity;
    }

    public void setTrmTeamsQuantity(Integer trmTeamsQuantity) {
        this.trmTeamsQuantity = trmTeamsQuantity;
    }

    public Collection<Participation> getParticipationCollection() {
        return participationCollection;
    }

    public void setParticipationCollection(Collection<Participation> participationCollection) {
        this.participationCollection = participationCollection;
    }

    public Sport getTrmSptId() {
        return trmSptId;
    }

    public void setTrmSptId(Sport trmSptId) {
        this.trmSptId = trmSptId;
    }

    public Team getTrmWinnerId() {
        return trmWinnerId;
    }

    public void setTrmWinnerId(Team trmWinnerId) {
        this.trmWinnerId = trmWinnerId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (trmId != null ? trmId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Tournament)) {
            return false;
        }
        Tournament other = (Tournament) object;
        return !((this.trmId == null && other.trmId != null) || (this.trmId != null && !this.trmId.equals(other.trmId)));
    }

    @Override
    public String toString() {
        return "models.Tournament[ trmId=" + trmId + " ]";
    }
    
}
