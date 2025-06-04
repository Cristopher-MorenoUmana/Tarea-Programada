package service;

import models.MatchDto;
import dao.MatchDao;
import util.Response;

public class MatchService {
    
    private final MatchDao userDao = new MatchDao();

    public MatchService(){  
    }
    
    public Response createMatch(MatchDto dto) {
        
        return userDao.addMatch(dto);
    }

    public Response updateMatch(MatchDto dto) {
        
        return userDao.updateMatchInDb(dto);
    }

    public Response deleteMatch(Integer id) {
        
        return userDao.deleteMatch(id);
    }

    public Response listMatchs() {
        
        return userDao.getMatches();
    }
}
