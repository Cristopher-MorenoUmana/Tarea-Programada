package dao;

import java.sql.SQLIntegrityConstraintViolationException;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Team;
import models.TeamDto;
import util.Response;
import util.EntityManagerHelper;

public class TeamDao {
    
    private final EntityManager em = EntityManagerHelper.getManager();
    private EntityTransaction et;
    
    public Response getTeams() {

        try {

            ObservableList<Team> teams = FXCollections.observableArrayList(
                    em.createQuery("SELECT t FROM Team t", Team.class).getResultList());

            return new Response('S', "", "", "Equipos", teams);
        } catch (Exception ex) {

            Logger.getLogger(TeamDao.class.getName()).log(Level.SEVERE, "Error al obtener los equipos", ex);

            return new Response('N', "Error al obtener los equipos", "getTeam");
        }
    }

    public Response addTeam(TeamDto teamDto) {

        try {

            if (teamDto.getID() != null && teamDto.getID() > 0) {

                return new Response('N', "El equipo ya existe", "addTeam ConflictException");
            }

            et = em.getTransaction();
            et.begin();

            Team team;

            team = new Team(teamDto);
            em.persist(team);

            et.commit();

            return new Response('S', "", "", "Equipo", new TeamDto(team));

        } catch (Exception ex) {

            et.rollback();

            Logger.getLogger(TeamDao.class.getName()).log(Level.SEVERE, "Error guardando el equipo.", ex);

            return new Response('N', "Error guardando el equipo.", "addTeam " + ex.getMessage());
        }
    }

    public Response updateTeamInDb(TeamDto teamDto) {

        try {

            if (teamDto.getID() == null || teamDto.getID() <= 0) {

                return new Response('N', "El equipo no existe", "updateTeamInDb ConflictException");
            }

            et = em.getTransaction();
            et.begin();

            Team team;

            team = em.find(Team.class, teamDto.getID());

            if (team == null) {
                et.rollback();
                return new Response('N', "No se encontro el equipo a modificar.", "updateTeam NoResultException");
            }

            team.updateTeam(teamDto);
            team = em.merge(team);

            et.commit();

            return new Response('S', "", "", "Equipo", new TeamDto(team));
        } catch (Exception ex) {

            et.rollback();

            Logger.getLogger(TeamDao.class.getName()).log(Level.SEVERE, "Error guardando el equipo.", ex);

            return new Response('N', "Error actualizando el equipo.", "updateTeamInDb " + ex.getMessage());
        }
    }

    public Response deleteTeam(Integer pId) {

        try {
            et = em.getTransaction();
            et.begin();

            if (pId != null && pId > 0) {

                Team team = em.find(Team.class, pId);

                if (team == null) {
                    et.rollback();
                    return new Response('N', "No se encontro el equipo a eliminar.", "deleteTeam NoResultException");
                }
                em.remove(team);
                et.commit();
            } else {
                et.rollback();
                return new Response('N', "El equipo no existe", "deleteTeam InvalidID");
            }

            return new Response('S', "", "");
        } catch (Exception ex) {

            et.rollback();

            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Response('N', "No se puede eliminar el equipo porque tiene relaciones con otros registros.", "deleteTeam " + ex.getMessage());
            }

            Logger.getLogger(TeamDao.class.getName()).log(Level.SEVERE, "Error eliminando el equipo.", ex);

            return new Response('N', "Error eliminando el equipo.", "deleteTeam " + ex.getMessage());
        }
    }
}
