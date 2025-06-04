package dao;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import models.TournamentTeam;
import models.TournamentTeamDto;
import util.EntityManagerHelper;
import util.Response;

public class TournamentTeamDao {
    
    private final EntityManager em = EntityManagerHelper.getManager();
    private EntityTransaction et;
    
    public Response getTournamentTeams() {

        try {

            ObservableList<TournamentTeam> tournamentTeams = FXCollections.observableArrayList(
                    em.createQuery("SELECT t FROM TournamentTeam t", TournamentTeam.class).getResultList());

            return new Response('S', "", "", "Equipos del torneo", tournamentTeams);
        } catch (Exception ex) {

            Logger.getLogger(TournamentTeamDao.class.getName()).log(Level.SEVERE, "Error al obtener los equipos del torneo", ex);

            return new Response('N', "Error al obtener los equipos del torneo", "getTournamentTeams");
        }
    }

    public Response addTournamentTeam(TournamentTeamDto tournamentTeamDto) {

        try {

            if (tournamentTeamDto.getID() != null && tournamentTeamDto.getID() > 0) {

                return new Response('N', "El equipo del torneo ya existe", "addTournamentTeam ConflictException");
            }

            et = em.getTransaction();
            et.begin();

            TournamentTeam tournamentTeam;

            tournamentTeam = new TournamentTeam(tournamentTeamDto);
            em.persist(tournamentTeam);

            et.commit();

            return new Response('S', "", "", "Equipo del torneo", new TournamentTeamDto(tournamentTeam));

        } catch (Exception ex) {

            et.rollback();

            Logger.getLogger(TournamentTeamDao.class.getName()).log(Level.SEVERE, "Error guardando el equipo del torneo.", ex);

            return new Response('N', "Error guardando el equipo del torneo.", "addTournamentTeam " + ex.getMessage());
        }
    }

    public Response updateTournamentTeamInDb(TournamentTeamDto tournamentTeamDto) {

        try {

            if (tournamentTeamDto.getID() == null || tournamentTeamDto.getID() <= 0) {

                return new Response('N', "El equipo del torneo no existe", "updateTournamentTeamInDb ConflictException");
            }

            et = em.getTransaction();
            et.begin();

            TournamentTeam tournamentTeam;

            tournamentTeam = em.find(TournamentTeam.class, tournamentTeamDto.getID());

            if (tournamentTeam == null) {
                et.rollback();
                return new Response('N', "No se encontro el equipo del torneo a modificar.", "updateTournamentTeam NoResultException");
            }

            tournamentTeam.updateTournamentTeam(tournamentTeamDto);
            tournamentTeam = em.merge(tournamentTeam);

            et.commit();

            return new Response('S', "", "", "Equipo del torneo", new TournamentTeamDto(tournamentTeam));
        } catch (Exception ex) {

            et.rollback();

            Logger.getLogger(TournamentTeamDao.class.getName()).log(Level.SEVERE, "Error guardando el equipo del torneo.", ex);

            return new Response('N', "Error actualizando el equipo del torneo.", "updateTournamentTeamInDb " + ex.getMessage());
        }
    }

    public Response deleteTournamentTeam(Integer pId) {

        try {
            et = em.getTransaction();
            et.begin();

            if (pId != null && pId > 0) {

                TournamentTeam tournamentTeam = em.find(TournamentTeam.class, pId);

                if (tournamentTeam == null) {
                    et.rollback();
                    return new Response('N', "No se encontro el equipo del torneo a eliminar.", "deleteTournamentTeam NoResultException");
                }
                em.remove(tournamentTeam);
                et.commit();
            } else {
                et.rollback();
                return new Response('N', "El equipo del torneo no existe", "deleteTournamentTeam InvalidID");
            }

            return new Response('S', "", "");
        } catch (Exception ex) {

            et.rollback();

            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Response('N', "No se puede eliminar el equipo del torneo porque tiene relaciones con otros registros.", "deleteTournamentTeam " + ex.getMessage());
            }

            Logger.getLogger(TournamentTeamDao.class.getName()).log(Level.SEVERE, "Error eliminando el equipo del torneo.", ex);

            return new Response('N', "Error eliminando el equipo del torneo.", "deleteTournamentTeam " + ex.getMessage());
        }
    }
}
