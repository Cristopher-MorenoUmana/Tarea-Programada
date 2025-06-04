package models;

import java.util.Objects;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class TournamentTeamDto {

    private SimpleStringProperty id;
    private ObjectProperty<Team> team;
    private ObjectProperty<Tournament> tournament;

    public TournamentTeamDto() {

        this.id = new SimpleStringProperty();
        this.team = new SimpleObjectProperty<>();
        this.tournament = new SimpleObjectProperty<>();
    }

    public TournamentTeamDto(TournamentTeam pTournament) {

        this();
        this.id.setValue(pTournament.getTttTrmId().toString());
        this.team.setValue(pTournament.getTttTeamId());
        this.tournament.setValue(pTournament.getTttTrmId());
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public ObjectProperty<Team> teamProperty() {
        return team;
    }

    public ObjectProperty<Tournament> tournamentProperty() {
        return tournament;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public Team getTeam() {
        return team.get();
    }

    public void setTeam(Team team) {
        this.team.set(team);
    }

    public Tournament getTournament() {
        return tournament.get();
    }

    public void setTournament(Tournament tournament) {
        this.tournament.set(tournament);
    }

    public Integer getID() {
        if (id.get() == null || id.get().isEmpty()) {
            return null;
        }
        try {
            return Integer.valueOf(id.get());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.getID());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        final TournamentTeamDto other = (TournamentTeamDto) obj;

        return Objects.equals(this.getID(), other.getID());
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("TournamentTeamDto{id=").append(this.id.get());
        sb.append(", torneo=").append(this.team.get());
        sb.append(", equipo=").append(this.tournament.get());
        sb.append('}');
        return sb.toString();
    }
}
