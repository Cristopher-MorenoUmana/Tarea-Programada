package dao;

import java.sql.SQLIntegrityConstraintViolationException;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Match;
import models.MatchDto;
import util.Response;
import util.EntityManagerHelper;

public class MatchDao {
    
    private final EntityManager em = EntityManagerHelper.getManager();
    private EntityTransaction et;
    
    public Response getMatches() {

        try {

            ObservableList<Match> matches = FXCollections.observableArrayList(
                    em.createQuery("SELECT m FROM Match m", Match.class).getResultList());

            return new Response('S', "", "", "Partidos", matches);
        } catch (Exception ex) {

            Logger.getLogger(MatchDao.class.getName()).log(Level.SEVERE, "Error al obtener los partidos", ex);

            return new Response('N', "Error al obtener los partidos", "getMatches");
        }
    }

    public Response addMatch(MatchDto matchDto) {

        try {

            if (matchDto.getID() != null && matchDto.getID() > 0) {

                return new Response('N', "El partido ya existe", "addMatch ConflictException");
            }

            et = em.getTransaction();
            et.begin();

            Match match;

            match = new Match(matchDto);
            em.persist(match);

            et.commit();

            return new Response('S', "", "", "Partido", new MatchDto(match));

        } catch (Exception ex) {

            et.rollback();

            Logger.getLogger(MatchDao.class.getName()).log(Level.SEVERE, "Error guardando el partido.", ex);

            return new Response('N', "Error guardando el partido.", "addMatch " + ex.getMessage());
        }
    }

    public Response updateMatchInDb(MatchDto matchDto) {

        try {

            if (matchDto.getID() == null || matchDto.getID() <= 0) {

                return new Response('N', "El partido no existe", "updateMatchInDb ConflictException");
            }

            et = em.getTransaction();
            et.begin();

            Match match;

            match = em.find(Match.class, matchDto.getID());

            if (match == null) {
                et.rollback();
                return new Response('N', "No se encontro el partido a modificar.", "updateMatch NoResultException");
            }

            match.updateMatch(matchDto);
            match = em.merge(match);

            et.commit();

            return new Response('S', "", "", "Partido", new MatchDto(match));
        } catch (Exception ex) {

            et.rollback();

            Logger.getLogger(MatchDao.class.getName()).log(Level.SEVERE, "Error guardando el partido.", ex);

            return new Response('N', "Error actualizando el partido.", "updateMatchInDb " + ex.getMessage());
        }
    }

    public Response deleteMatch(Integer pId) {

        try {
            et = em.getTransaction();
            et.begin();

            if (pId != null && pId > 0) {

                Match match = em.find(Match.class, pId);

                if (match == null) {
                    et.rollback();
                    return new Response('N', "No se encontro el partido a eliminar.", "deleteMatch NoResultException");
                }
                em.remove(match);
                et.commit();
            } else {
                et.rollback();
                return new Response('N', "El partido no existe", "deleteMatch InvalidID");
            }

            return new Response('S', "", "");
        } catch (Exception ex) {

            et.rollback();

            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Response('N', "No se puede eliminar el partido porque tiene relaciones con otros registros.", "deleteMatch " + ex.getMessage());
            }

            Logger.getLogger(MatchDao.class.getName()).log(Level.SEVERE, "Error eliminando el partido.", ex);

            return new Response('N', "Error eliminando el partido.", "deleteMatch " + ex.getMessage());
        }
    }
}
