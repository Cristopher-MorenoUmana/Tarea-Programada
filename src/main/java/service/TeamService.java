package service;

import dao.TeamDao;
import models.TeamDto;
import util.Response;


public class TeamService {
    
    private final TeamDao userDao = new TeamDao();

    public TeamService(){  
    }
    
    public Response createTeam(TeamDto dto) {
        
        return userDao.addTeam(dto);
    }

    public Response updateTeam(TeamDto dto) {
        
        return userDao.updateTeamInDb(dto);
    }

    public Response deleteTeam(Integer id) {
        
        return userDao.deleteTeam(id);
    }

    public Response listTeams() {
        
        return userDao.getTeams();
    }
}
