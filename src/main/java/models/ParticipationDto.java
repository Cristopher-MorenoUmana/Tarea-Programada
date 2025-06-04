package models;

import java.util.Objects;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class ParticipationDto {

    private SimpleStringProperty id;
    private SimpleStringProperty points;
    private SimpleStringProperty position;
    private ObjectProperty<Team> team;
    private ObjectProperty<Tournament> tournament;
    private ObjectProperty<Match> match;
    
    public ParticipationDto() {

        this.id = new SimpleStringProperty();
        this.points = new SimpleStringProperty();
        this.position = new SimpleStringProperty();
        this.team = new SimpleObjectProperty<>();
        this.tournament = new SimpleObjectProperty<>();
        this.match = new SimpleObjectProperty<>();
    }

    public ParticipationDto(Participation participation) {

        this();
        this.id.setValue(participation.getPctId().toString());
        this.points.setValue(participation.getPctPoints().toString());
        this.position.setValue(participation.getPctPosition().toString());
        this.team.setValue(participation.getPctTeamId());
        this.tournament.setValue(participation.getPctTrmId());
        this.match.setValue(participation.getPctMthId());
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public void setMatch(Match pMatch){
        this.match.set(pMatch);
    }
    
    public String getPoints() {
        return points.get();
    }

    public ObjectProperty<Match> matchProperty() {
        return this.match;
    }
    
    public Match getMatch(){
        return this.match.get();
    }
    
    public void setPoints(String points) {
        this.points.set(points);
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public SimpleStringProperty pointsProperty() {
        return points;
    }

    public String getPosition() {
        return position.get();
    }

    public void setPosition(String position) {
        this.position.set(position);
    }

    public SimpleStringProperty positionProperty() {
        return position;
    }

    public Team getTeam() {
        return team.get();
    }

    public void setTeam(Team team) {
        this.team.set(team);
    }

    public ObjectProperty<Team> teamProperty() {
        return team;
    }

    public Tournament getTournament() {
        return tournament.get();
    }

    public void setTournament(Tournament tournament) {
        this.tournament.set(tournament);
    }

    public ObjectProperty<Tournament> tournamentProperty() {
        return tournament;
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

        final ParticipationDto other = (ParticipationDto) obj;

        return Objects.equals(this.getID(), other.getID());
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("ParticipationDto{id=").append(this.id.get());
        sb.append("Equipo=").append(this.team.get());
        sb.append("Torneo=").append(this.tournament.get());
        sb.append(", posicion en el torneo=").append(this.position.get());
        sb.append(", puntos=").append(this.points.get());
        sb.append('}');
        return sb.toString();
    }
}
