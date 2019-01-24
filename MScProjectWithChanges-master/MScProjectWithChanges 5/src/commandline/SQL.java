package commandline;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class SQL {
	
	int GameID = 0;
	// before use the cardlist must call getCardData
	public ArrayList<CardModel> cardList;

	// get the all card data for the database, and set all card object.
	// easy way to get the cardList
	public SQL() {
		getCardData();
	}
    
	public void getCardData() {
		cardList = new ArrayList<CardModel>();
		try {
			Class.forName("org.postgresql.Driver");
		} catch (Exception e) {
			System.err.println("Not found the Driver");
		}
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5434/postgres", "postgres",
					"yf19921215");
			System.out.println("Opened database successfully");
		} catch (Exception e) {
			System.err.println("Can not connect the severs");
		}

		if (connection != null) {
			System.out.println("connect you database....");
			try {
				Statement stmt = connection.createStatement();

				ResultSet result = stmt.executeQuery("SELECT CardTable.*\r\n" + "FROM CardTable \r\n");
				while (result.next()) {
					int cardID = result.getInt("CardID");
					String description = result.getString("Description");
					int size = result.getInt("Size");
					int speed = result.getInt("Speed");
					int range = result.getInt("Range");
					int firepower = result.getInt("FirePower");
					int cargo = result.getInt("Cargo");
					CardModel card = new CardModel(cardID, description, size, speed, range, firepower, cargo,"");
					cardList.add(card);
				}
				result.close();
				stmt.close();
				connection.close();
			} catch (Exception e) {
				System.err.println(e.fillInStackTrace());
				;
				System.exit(0);
			}
		}
		System.out.println("Operation done successfully");
	}

	// set the result about the game information to database
	public void setGameDataToSQL(int GameID, int AmountOfRound, String GameWiner, int NumOfDraw, boolean isHuman) {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (Exception e) {
			System.err.println("Not found the Driver");
		}
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5434/postgres", "postgres",
					"yf19921215");
			System.out.println("Opened database successfully");
		} catch (Exception e) {
			System.err.println("Can not connect the severs");
		}
		try {
			Statement stmt = connection.createStatement();
			String sql = "INSERT INTO Game VALUES" + "(" + GameID + "," + AmountOfRound + "," + "'" + GameWiner + "'"
					+ "," + NumOfDraw + "," + "'" + isHuman + "'" + ");";
			System.out.println(sql);
			stmt.executeUpdate(sql);
			stmt.close();
			connection.close();
		} catch (Exception e) {
			System.err.println(" GameDataToSQL insert error");
			System.err.println(e.getMessage());
		}
	}

	// set the result about the round information to database
	public void setRoundDataToSQL(int GameID, int RoundID, String RoundWiner, boolean isDraw, boolean isHuman) {
		// in the postgre sql, boolean value is byte, true : 1, false: 0
		try {
			Class.forName("org.postgresql.Driver");
		} catch (Exception e) {
			System.err.println("Not found the Driver");
		}
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5434/postgres", "postgres",
					"yf19921215");
			System.out.println("Opened database successfully");
		} catch (Exception e) {
			System.err.println("Can not connect the severs");
		}
		try {
			Statement stmt = connection.createStatement();
			String sql = "INSERT INTO Round VALUES" + "(" + GameID + "," + RoundID + "," + "'" + RoundWiner + "'" + ","
					+ "'" + isDraw + "'" + "," + "'" + isHuman + "'" + ");";
			System.out.println(sql);
			stmt.executeUpdate(sql);
			stmt.close();
			connection.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.err.println("RoundDataToSQL insert error");
		}
	}

	// get the game statistics
	public String getGameStatistics() {
		
		String gameStatistics ="Game Statistics:\n";
		
		try {
			Class.forName("org.postgresql.Driver");
		} catch (Exception e) {
			System.err.println("Not found the Driver");
		}
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5434/postgres", "postgres",
					"yf19921215");
			System.out.println("Opened database successfully");
		} catch (Exception e) {
			System.err.println("Can not connect the severs");
		}

		if (connection != null) {
			System.out.println("connect you database....");
			try {
				Statement stmt = connection.createStatement();
				ResultSet resultNumberOfGames = stmt.executeQuery("SELECT MAX(Game.GameID)\r\n" + "FROM Game\r\n");
				while (resultNumberOfGames.next()) {
					int numberOfGames = resultNumberOfGames.getInt(1);
					gameStatistics += "   Number of Games: "+numberOfGames+"\n";
				}
				ResultSet resultNumberOfHumanWins = stmt.executeQuery("SELECT COUNT(Game.GameID)\r\n"+"FROM Game\r\n"+"WHERE Game.isHuman = true\r\n");
				while (resultNumberOfHumanWins.next()) {
					int numberOfHumanWins = resultNumberOfHumanWins.getInt(1);
					gameStatistics += "   Number of Human Wins: "+numberOfHumanWins+"\n";
				}
				ResultSet resultNumberOfAIWins = stmt.executeQuery("SELECT COUNT(Game.GameID)\r\n"+"FROM Game\r\n"+"WHERE Game.isHuman = false\r\n");
				while (resultNumberOfAIWins.next()) {
					int numberOfAIWins = resultNumberOfAIWins.getInt(1);
					gameStatistics += "   Number of AT Wins: "+numberOfAIWins+"\n";
				}
				ResultSet resultAVGOfDraws = stmt.executeQuery("SELECT SUM(Game.NumOfDraw) / COUNT(Game.GameID) \r\n"+"FROM Game\r\n");
				while (resultAVGOfDraws.next()) {
					int AVGOfDraws = resultAVGOfDraws.getInt(1);
					gameStatistics += "   Average number of Draws: "+AVGOfDraws+"\n";
				}
				ResultSet resultlongestGame = stmt.executeQuery("SELECT Game.GameID\r\n"+"FROM Game\r\n"+"WHERE Game.amountOfRound=(SELECT MAX(Game.amountOfRound) FROM Game)\r\n");
				while (resultlongestGame.next()) {
					int longestGame = resultlongestGame.getInt(1);
					gameStatistics += "   Longest GameID is: "+longestGame+", ";
				}
				ResultSet resultlongestRound = stmt.executeQuery("SELECT MAX(Game.amountOfRound)\r\n"+"FROM Game\r\n");
				while (resultlongestRound.next()) {
					int longestRound = resultlongestRound.getInt(1);
					gameStatistics += "it was "+longestRound+" times\n";
				}
				
				resultlongestGame.close();
				stmt.close();
				connection.close();
			} catch (Exception e) {
				System.err.println(e.fillInStackTrace());
				System.exit(0);
			}
		}
		return gameStatistics;
	}

	// get the current GameID
	public int getTheCurrentGameID() {
		
		
		try {
			Class.forName("org.postgresql.Driver");
		} catch (Exception e) {
			System.err.println("Not found the Driver");
		}
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5434/postgres", "postgres",
					"yf19921215");
			System.out.println("Opened database successfully");
		} catch (Exception e) {
			System.err.println("Can not connect the severs");
		}try {
			Statement stmt = connection.createStatement();
			String sql = "SELECT MAX(Game.GameID)\r\n" + "FROM Game";
			ResultSet resultSet = stmt.executeQuery(sql);
			while (resultSet.next()) {
				GameID = resultSet.getInt(1);
			}
			stmt.close();
			connection.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.err.println("getTheCurrentGameID error");
		}
		
		return GameID;
	}

}