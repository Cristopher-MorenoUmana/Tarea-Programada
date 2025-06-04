package service;

import models.ParticipationDto;
import dao.ParticipationDao;
import util.Response;

public class ParticipationService {
    
    private final ParticipationDao userDao = new ParticipationDao();

    public ParticipationService(){  
    }
    
    public Response createParticipation(ParticipationDto dto) {
        
        return userDao.addParticipation(dto);
    }

    public Response updateParticipation(ParticipationDto dto) {
        
        return userDao.updateParticipationInDb(dto);
    }

    public Response deleteParticipation(Integer id) {
        
        return userDao.deleteParticipation(id);
    }

    public Response listParticipations() {
        
        return userDao.getParticipations();
    }
}
