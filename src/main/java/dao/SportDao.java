package dao;

import java.sql.SQLIntegrityConstraintViolationException;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Sport;
import models.SportDto;
import util.Response;
import util.EntityManagerHelper;

public class SportDao {
    
    private final EntityManager em = EntityManagerHelper.getManager();
    private EntityTransaction et;
    
    public Response getSports() {

        try {

            ObservableList<Sport> sports = FXCollections.observableArrayList(
                    em.createQuery("SELECT s FROM Sport s", Sport.class).getResultList());

            return new Response('S', "", "", "Deportes", sports);
        } catch (Exception ex) {

            Logger.getLogger(SportDao.class.getName()).log(Level.SEVERE, "Error al obtener los deportes", ex);

            return new Response('N', "Error al obtener los deportes", "getSports");
        }
    }

    public Response addSport(SportDto sportDto) {

        try {

            if (sportDto.getID() != null && sportDto.getID() > 0) {

                return new Response('N', "El deporte ya existe", "addSport ConflictException");
            }

            et = em.getTransaction();
            et.begin();

            Sport sport;

            sport = new Sport(sportDto);
            em.persist(sport);

            et.commit();

            return new Response('S', "", "", "Deporte", new SportDto(sport));

        } catch (Exception ex) {

            et.rollback();

            Logger.getLogger(SportDao.class.getName()).log(Level.SEVERE, "Error guardando el deporte.", ex);

            return new Response('N', "Error guardando el deporte.", "addSport " + ex.getMessage());
        }
    }

    public Response updateSportInDb(SportDto sportDto) {

        try {

            if (sportDto.getID() == null || sportDto.getID() <= 0) {

                return new Response('N', "El deporte no existe", "updateSportInDb ConflictException");
            }

            et = em.getTransaction();
            et.begin();

            Sport sport;

            sport = em.find(Sport.class, sportDto.getID());

            if (sport == null) {
                et.rollback();
                return new Response('N', "No se encontro el deporte a modificar.", "updateSport NoResultException");
            }

            sport.updateSport(sportDto);
            sport = em.merge(sport);

            et.commit();

            return new Response('S', "", "", "Deporte", new SportDto(sport));
        } catch (Exception ex) {

            et.rollback();

            Logger.getLogger(SportDao.class.getName()).log(Level.SEVERE, "Error guardando el deporte.", ex);

            return new Response('N', "Error actualizando el deporte.", "updateSportInDb " + ex.getMessage());
        }
    }

    public Response deleteSport(Integer pId) {

        try {
            et = em.getTransaction();
            et.begin();

            if (pId != null && pId > 0) {

                Sport sport = em.find(Sport.class, pId);

                if (sport == null) {
                    et.rollback();
                    return new Response('N', "No se encontro el deporte a eliminar.", "deleteSport NoResultException");
                }
                em.remove(sport);
                et.commit();
            } else {
                et.rollback();
                return new Response('N', "El deporte no existe", "deleteSport InvalidID");
            }

            return new Response('S', "", "");
        } catch (Exception ex) {

            et.rollback();

            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Response('N', "No se puede eliminar el deporte porque tiene relaciones con otros registros.", "deleteSport " + ex.getMessage());
            }

            Logger.getLogger(SportDao.class.getName()).log(Level.SEVERE, "Error eliminando el deporte.", ex);

            return new Response('N', "Error eliminando el deporte.", "deleteSport " + ex.getMessage());
        }
    }
}
