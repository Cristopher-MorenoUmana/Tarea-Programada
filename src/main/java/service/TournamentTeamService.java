package service;

import dao.TournamentTeamDao;
import models.TournamentTeamDto;
import util.Response;


public class TournamentTeamService {
    
    private final TournamentTeamDao userDao = new TournamentTeamDao();

    public TournamentTeamService(){  
    }
    
    public Response createTournamentTeam(TournamentTeamDto dto) {
        
        return userDao.addTournamentTeam(dto);
    }

    public Response updateTournamentTeam(TournamentTeamDto dto) {
        
        return userDao.updateTournamentTeamInDb(dto);
    }

    public Response deleteTournamentTeam(Integer id) {
        
        return userDao.deleteTournamentTeam(id);
    }

    public Response listTournamentTeams() {
        
        return userDao.getTournamentTeams();
    }
}
