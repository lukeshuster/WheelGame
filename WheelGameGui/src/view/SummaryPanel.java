package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.AddPlayerListener;
import controller.PlaceBetListener;
import controller.RemovePlayerListener;
import model.interfaces.Player;

public class SummaryPanel extends JPanel{
	private Frame frame;
	private JPanel playerPanel;
	private ArrayList<JLabel> playerIdList;
	private ArrayList<JLabel> playerNameList;
	private ArrayList<JLabel> playerPointsList;
	private ArrayList<JLabel> playerBetList;
	private ArrayList<JLabel> playerBetTypeList;
	
	public SummaryPanel(Frame frame) {
		this.frame = frame;
		/* Collections for Player Detail JLabels */
		playerIdList = new ArrayList<JLabel>();
		playerNameList = new ArrayList<JLabel>();
		playerPointsList = new ArrayList<JLabel>();
		playerBetList = new ArrayList<JLabel>();
		playerBetTypeList = new ArrayList<JLabel>();
		
		playerPanel = new JPanel();
		playerPanel.setLayout(new BoxLayout(playerPanel,BoxLayout.Y_AXIS));
		
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.add(playerPanel, BorderLayout.CENTER);
		
		JPanel toolbar = new JPanel();
		JButton addPlayer = new JButton("Add Player");
		addPlayer.addActionListener(new AddPlayerListener(frame));
		
		JButton removePlayer = new JButton("Remove Player");
		removePlayer.addActionListener(new RemovePlayerListener(frame));
		
		JButton placeBet = new JButton("Place Bet");
		placeBet.addActionListener(new PlaceBetListener(frame));
		
		toolbar.add(addPlayer);
		toolbar.add(removePlayer);
		toolbar.add(placeBet);
		this.add(toolbar,BorderLayout.SOUTH);
	}
	
	public void addPlayer(Player player) {
		/* Adding JLabels to respective ArrayLists for added players */
		playerIdList.add(new JLabel("ID: " + player.getPlayerId()));
		playerPanel.add(playerIdList.get(playerIdList.size()-1));
		
		playerNameList.add(new JLabel("Name: " + player.getPlayerName()));
		playerPanel.add(playerNameList.get(playerNameList.size()-1));
		
		String playerPoints = Integer.toString(player.getPoints());
		playerPointsList.add(new JLabel("Points: " + playerPoints));
		playerPanel.add(playerPointsList.get(playerPointsList.size()-1));
		
		String bet= Integer.toString(player.getBet());
		playerBetList.add(new JLabel("Bet: " + bet));
		playerPanel.add(playerBetList.get(playerBetList.size()-1));
		
		if (player.getBetType() != null) {
			String betType= player.getBetType().name();
			playerBetTypeList.add(new JLabel("Bet Type: " + betType));
			playerPanel.add(playerBetTypeList.get(playerBetTypeList.size()-1));
		}
		else {
			playerBetTypeList.add(new JLabel("Bet Type: Null"));
			playerPanel.add(playerBetTypeList.get(playerBetTypeList.size()-1));
		}
	}

	public void removePlayer() {
		/* Removing JLabels from respective ArrayLists for removed players */
		playerPanel.remove(playerIdList.get(playerIdList.size()-1));
		playerIdList.remove(playerIdList.get(playerIdList.size()-1));
		
		playerPanel.remove(playerNameList.get(playerNameList.size()-1));
		playerNameList.remove(playerNameList.get(playerNameList.size()-1));
		
		playerPanel.remove(playerPointsList.get(playerPointsList.size()-1));
		playerPointsList.remove(playerPointsList.get(playerPointsList.size()-1));
		
		playerPanel.remove(playerBetList.get(playerBetList.size()-1));
		playerBetList.remove(playerBetList.get(playerBetList.size()-1));
		
		playerPanel.remove(playerBetTypeList.get(playerBetTypeList.size()-1));
		playerBetTypeList.remove(playerBetTypeList.get(playerBetTypeList.size()-1));
	}

	public void updateBet(String playerId, int betValue, String betType) {
		for (int i = 0; i < playerIdList.size(); i++) {
			if (playerIdList.get(i).getText().equals("ID: " + playerId)) {		//Find a player by their unique id
				playerBetList.get(i).setText("Bet: " + betValue);
				playerBetTypeList.get(i).setText("Bet Type: " + betType);
			}
		}
		
		/* If all players have a set betType then automatically spin the wheel */
		boolean autoSpin = true;
		for (int i = 0; i < playerBetTypeList.size(); i++) {
			String checkBetType = playerBetTypeList.get(i).getText();
			if (checkBetType.equals("Bet Type: Null")){
				autoSpin = false;
			}
		}
		
		if (autoSpin) {
			frame.spin();
		}
	}

	public void updateResult(String playerId, String playerName, int points) {
		for (int i = 0; i < playerIdList.size(); i++) {
			if (playerIdList.get(i).getText().equals("ID: " + playerId)) {		//Find a player by their unique id
				String oldPointsStr = playerPointsList.get(i).getText().substring(8);	// Cut off the "Points: " part of the string leaving just the points value
				int oldPoints = Integer.parseInt(oldPointsStr);
				if(points > oldPoints) {
					/* Winner */
					playerPointsList.get(i).setText("Points: " + points);
					playerNameList.get(i).setText("Name: " + playerName + " || WON LAST SPIN!");
				}
				else {
					/* Loser */
					playerPointsList.get(i).setText("Points: " + points);
					playerNameList.get(i).setText("Name: " + playerName + " || LOST LAST SPIN");
				}
				revalidate();
				repaint();
			}
		}
	}
}
