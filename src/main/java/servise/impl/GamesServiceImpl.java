package servise.impl;

import java.util.Collection;
import java.util.List;

import dao.impl.GamesDaoImpl;
import model.entity.Games;
import servise.GamesService;

public class GamesServiceImpl implements GamesService{

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	GamesDaoImpl ga = new GamesDaoImpl();
	@Override
	public void createGame(Games games) {
		ga.insert(games);		
	}

	@Override
	public List<Games> findGameByName(String name) {
		
		return ga.getByname(name);
	}

	@Override
	public List<Games> findAllGames() {
		
		return ga.getGamesAll();
	}

	@Override
	public void update(Games game) {
		ga.updateGames(game);
		
	}

	@Override
	public void deleteByid(int id) {
		ga.deletGames(id);
		
	}

}
