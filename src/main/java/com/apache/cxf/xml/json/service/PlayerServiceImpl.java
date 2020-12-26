package com.apache.cxf.xml.json.service;

import in.benchresources.cdm.player.PlayerListType;
import in.benchresources.cdm.player.PlayerType;

import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("playerService")
public class PlayerServiceImpl implements IPlayerService {
	@Autowired
	private ValidateDatabaseConnection validateDatabaseConnection;
	@Autowired
	Map<String, DataSource> dataSourceMap;

	/**
	 * returns a String value with SUCCESS message after adding a player
	 */
	@Override
	public String createOrSaveNewPLayerInfo(PlayerType playerType) {

		// get the player information from formal arguments and inserts into database & return playerId (primary_key)
		return "Player information saved successfully with PLAYER_ID " + 238;
	}

	/**
	 * retrieves a player object based on the playerId supplied in the formal argument using @PathParam
	 */
	@Override
	public PlayerType getPlayerInfo(int playerId) {

		// retrieve player based on the id supplied in the formal argument
		PlayerType getplayer = new PlayerType	();
		getplayer.setPlayerId(playerId);
		getplayer.setName("Allan Donald");
		getplayer.setAge(47);
		getplayer.setMatches(72);
		return getplayer;
	}

	/**
	 * returns a String value with SUCCESS message after updating a player
	 */
	@Override
	public String updatePlayerInfo(PlayerType playerType) {

		// update player info & return SUCCESS message
		return "Player information updated successfully";
	}

	/**
	 * returns a String value with SUCCESS message after deleting a player
	 */
	@Override
	public String deletePlayerInfo(PlayerType playerType) {

		// delete player info & return SUCCESS message
		return "Player information deleted successfully";
	}

	/**
	 * retrieves all players stored
	 * @throws Exception 
	 */
	@Override
	public PlayerListType getAllPlayerInfo() throws Exception {

		// create a object of type PlayerType which takes player objects in its list
		PlayerListType playerListType = new PlayerListType();

		// player 1 info
		PlayerType playerOne = new PlayerType();
		playerOne.setPlayerId(237);
		playerOne.setName("Hansie Cronje");
		playerOne.setAge(33);
		playerOne.setMatches(68);
		playerListType.getPlayerType().add(playerOne); // add to playerListType

		// player 2 info
		PlayerType playerTwo = new PlayerType();
		playerTwo.setPlayerId(265);
		playerTwo.setName("Lance Klusener");
		playerTwo.setAge(42);
		playerTwo.setMatches(49);
		playerListType.getPlayerType().add(playerTwo); // add to playerListType
		Set<String> datasourceNames=dataSourceMap.keySet();
		for (String dataSourceName : datasourceNames) {
			validateDatabaseConnection.setDataSource(dataSourceMap.get(dataSourceName));
			System.out.println("DatasourceValidation for :"+dataSourceName+ " > "+validateDatabaseConnection.getValidateDatabaseConnection());
		}
		return playerListType;
	}
}