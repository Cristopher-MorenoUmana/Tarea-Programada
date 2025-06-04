package service;

import dao.SportDao;
import models.SportDto;
import util.Response;


public class SportService {
    private final SportDao userDao = new SportDao();

    public SportService(){  
    }
    
    public Response createSport(SportDto dto) {
        
        return userDao.addSport(dto);
    }

    public Response updateSport(SportDto dto) {
        
        return userDao.updateSportInDb(dto);
    }

    public Response deleteSport(Integer id) {
        
        return userDao.deleteSport(id);
    }

    public Response listSports() {
        
        return userDao.getSports();
    }
}
