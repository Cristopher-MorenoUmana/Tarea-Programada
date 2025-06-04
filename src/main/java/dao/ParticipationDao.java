package dao;

import java.sql.SQLIntegrityConstraintViolationException;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Participation;
import models.ParticipationDto;
import util.Response;
import util.EntityManagerHelper;

public class ParticipationDao {
    
    private final EntityManager em = EntityManagerHelper.getManager();
    private EntityTransaction et;
    
    public Response getParticipations() {

        try {

            ObservableList<Participation> participations = FXCollections.observableArrayList(
                    em.createQuery("SELECT p FROM Participation p", Participation.class).getResultList());

            return new Response('S', "", "", "Participaciones", participations);
        } catch (Exception ex) {

            Logger.getLogger(ParticipationDao.class.getName()).log(Level.SEVERE, "Error al obtener las participaciones", ex);

            return new Response('N', "Error al obtener las participaciones", "getParticipationes");
        }
    }

    public Response addParticipation(ParticipationDto participationDto) {

        try {

            if (participationDto.getID() != null && participationDto.getID() > 0) {

                return new Response('N', "La participacion ya existe", "addParticipation ConflictException");
            }

            et = em.getTransaction();
            et.begin();

            Participation participation;

            participation = new Participation(participationDto);
            em.persist(participation);

            et.commit();

            return new Response('S', "", "", "Participacion", new ParticipationDto(participation));

        } catch (Exception ex) {

            et.rollback();

            Logger.getLogger(ParticipationDao.class.getName()).log(Level.SEVERE, "Error guardando la participacion.", ex);

            return new Response('N', "Error guardando la participacion.", "addParticipation " + ex.getMessage());
        }
    }

    public Response updateParticipationInDb(ParticipationDto participationDto) {

        try {

            if (participationDto.getID() == null || participationDto.getID() <= 0) {

                return new Response('N', "La participacion no existe", "updateParticipationInDb ConflictException");
            }

            et = em.getTransaction();
            et.begin();

            Participation participation;

            participation = em.find(Participation.class, participationDto.getID());

            if (participation == null) {
                et.rollback();
                return new Response('N', "No se encontro la participacion a modificar.", "updateParticipation NoResultException");
            }

            participation.updateParticipation(participationDto);
            participation = em.merge(participation);

            et.commit();

            return new Response('S', "", "", "Participacion", new ParticipationDto(participation));
        } catch (Exception ex) {

            et.rollback();

            Logger.getLogger(ParticipationDao.class.getName()).log(Level.SEVERE, "Error guardando la participacion.", ex);

            return new Response('N', "Error actualizando la participacion.", "updateParticipationInDb " + ex.getMessage());
        }
    }

    public Response deleteParticipation(Integer pId) {

        try {
            et = em.getTransaction();
            et.begin();

            if (pId != null && pId > 0) {

                Participation participation = em.find(Participation.class, pId);

                if (participation == null) {
                    et.rollback();
                    return new Response('N', "No se encontro la participacion a eliminar.", "deleteParticipation NoResultException");
                }
                em.remove(participation);
                et.commit();
            } else {
                et.rollback();
                return new Response('N', "La participacion no existe", "deleteParticipation InvalidID");
            }

            return new Response('S', "", "");
        } catch (Exception ex) {

            et.rollback();

            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Response('N', "No se puede eliminar la participacion porque tiene relaciones con otros registros.", "deleteParticipation " + ex.getMessage());
            }

            Logger.getLogger(ParticipationDao.class.getName()).log(Level.SEVERE, "Error eliminando la participacion.", ex);

            return new Response('N', "Error eliminando la participacion.", "deleteParticipation " + ex.getMessage());
        }
    }
}
