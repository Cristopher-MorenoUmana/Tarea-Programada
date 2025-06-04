package service;

import dao.TournamentDao;
import models.TournamentDto;
import util.Response;


public class TournamentService {
    
    private final TournamentDao userDao = new TournamentDao();

    public TournamentService(){  
    }
    
    public Response createTournament(TournamentDto dto) {
        
        return userDao.addTournament(dto);
    }

    public Response updateTournament(TournamentDto dto) {
        
        return userDao.updateTournamentInDb(dto);
    }

    public Response deleteTournament(Integer id) {
        
        return userDao.deleteTournament(id);
    }

    public Response listTournaments() {
        
        return userDao.getTournaments();
    }
}
