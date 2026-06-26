package dao;

import java.util.List;

import model.entity.Games;

public interface GamesDao {
	//create
	void insert(Games games);
	//read	
	List<Games> getByname(String name);
	List<Games> getGamesAll();
	//update
	void updateGames(Games games);
	
	//delete
	void deletGames(int id);
}
