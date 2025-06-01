package models;

import java.util.Objects;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class ParticipationDto {

    private SimpleStringProperty id;
    private SimpleStringProperty points;
    private SimpleStringProperty position;
    private ObjectProperty<Team> teamId;
    private ObjectProperty<Tournament> tournamentId;
    private ObjectProperty<Match> matchId;
    
    public ParticipationDto() {

        this.id = new SimpleStringProperty();
        this.points = new SimpleStringProperty();
        this.position = new SimpleStringProperty();
        this.teamId = new SimpleObjectProperty<>();
        this.tournamentId = new SimpleObjectProperty<>();
        this.matchId = new SimpleObjectProperty<>();
    }

    public ParticipationDto(Participation participation) {

        this();
        this.id.setValue(participation.getPctId().toString());
        this.points.setValue(participation.getPctPoints().toString());
        this.position.setValue(participation.getPctPosition().toString());
        this.teamId.setValue(participation.getPctTeamId());
        this.tournamentId.setValue(participation.getPctTrmId());
        this.matchId.setValue(participation.getPctMthId());
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public void setMatchId(Match pMatchId){
        this.matchId.set(pMatchId);
    }
    
    public String getPoints() {
        return points.get();
    }

    public ObjectProperty<Match> matchIdProperty() {
        return this.matchId;
    }
    
    public Match getMatchId(){
        return this.matchId.get();
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

    public Team getTeamId() {
        return teamId.get();
    }

    public void setTeamId(Team teamId) {
        this.teamId.set(teamId);
    }

    public ObjectProperty<Team> teamIdProperty() {
        return teamId;
    }

    public Tournament getTournamentId() {
        return tournamentId.get();
    }

    public void setTournamentId(Tournament tournamentId) {
        this.tournamentId.set(tournamentId);
    }

    public ObjectProperty<Tournament> tournamentIdProperty() {
        return tournamentId;
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
        sb.append("Equipo=").append(this.teamId.get());
        sb.append("Torneo=").append(this.tournamentId.get());
        sb.append(", posicion en el torneo=").append(this.position.get());
        sb.append(", puntos=").append(this.points.get());
        sb.append('}');
        return sb.toString();
    }
}
