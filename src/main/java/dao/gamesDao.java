package dao;

import java.util.List;

import model.entity.Games;

public interface gamesDao {
	//create
	void insert(Games games);
	//read	
	Games getByname(String name);
	List<Games> getGamesAll();
	//update
	void updateGames(Games games);
	
	//delete
	void deletGames(String uid);
}
