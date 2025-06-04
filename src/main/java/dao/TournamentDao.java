package dao;

import java.sql.SQLIntegrityConstraintViolationException;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Tournament;
import models.TournamentDto;
import util.Response;
import util.EntityManagerHelper;

public class TournamentDao {
    
    private final EntityManager em = EntityManagerHelper.getManager();
    private EntityTransaction et;
    
    public Response getTournaments() {

        try {

            ObservableList<Tournament> tournaments = FXCollections.observableArrayList(
                    em.createQuery("SELECT t FROM Tournament t", Tournament.class).getResultList());

            return new Response('S', "", "", "Torneos", tournaments);
        } catch (Exception ex) {

            Logger.getLogger(TournamentDao.class.getName()).log(Level.SEVERE, "Error al obtener los torneos", ex);

            return new Response('N', "Error al obtener los torneos", "getTournaments");
        }
    }

    public Response addTournament(TournamentDto tournamentDto) {

        try {

            if (tournamentDto.getID() != null && tournamentDto.getID() > 0) {

                return new Response('N', "El torneo ya existe", "addTournament ConflictException");
            }

            et = em.getTransaction();
            et.begin();

            Tournament tournament;

            tournament = new Tournament(tournamentDto);
            em.persist(tournament);

            et.commit();

            return new Response('S', "", "", "Torneo", new TournamentDto(tournament));

        } catch (Exception ex) {

            et.rollback();

            Logger.getLogger(TournamentDao.class.getName()).log(Level.SEVERE, "Error guardando el torneo.", ex);

            return new Response('N', "Error guardando el torneo.", "addTournament " + ex.getMessage());
        }
    }

    public Response updateTournamentInDb(TournamentDto tournamentDto) {

        try {

            if (tournamentDto.getID() == null || tournamentDto.getID() <= 0) {

                return new Response('N', "El torneo no existe", "updateTournamentInDb ConflictException");
            }

            et = em.getTransaction();
            et.begin();

            Tournament tournament;

            tournament = em.find(Tournament.class, tournamentDto.getID());

            if (tournament == null) {
                et.rollback();
                return new Response('N', "No se encontro el torneo a modificar.", "updateTournament NoResultException");
            }

            tournament.updateTournament(tournamentDto);
            tournament = em.merge(tournament);

            et.commit();

            return new Response('S', "", "", "Torneo", new TournamentDto(tournament));
        } catch (Exception ex) {

            et.rollback();

            Logger.getLogger(TournamentDao.class.getName()).log(Level.SEVERE, "Error guardando el torneo.", ex);

            return new Response('N', "Error actualizando el torneo.", "updateTournamentInDb " + ex.getMessage());
        }
    }

    public Response deleteTournament(Integer pId) {

        try {
            et = em.getTransaction();
            et.begin();

            if (pId != null && pId > 0) {

                Tournament tournament = em.find(Tournament.class, pId);

                if (tournament == null) {
                    et.rollback();
                    return new Response('N', "No se encontro el torneo a eliminar.", "deleteTournament NoResultException");
                }
                em.remove(tournament);
                et.commit();
            } else {
                et.rollback();
                return new Response('N', "El torneo no existe", "deleteTournament InvalidID");
            }

            return new Response('S', "", "");
        } catch (Exception ex) {

            et.rollback();

            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Response('N', "No se puede eliminar el torneo porque tiene relaciones con otros registros.", "deleteTournament " + ex.getMessage());
            }

            Logger.getLogger(TournamentDao.class.getName()).log(Level.SEVERE, "Error eliminando el torneo.", ex);

            return new Response('N', "Error eliminando el torneo.", "deleteTournament " + ex.getMessage());
        }
    }
}
