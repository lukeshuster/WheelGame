package view;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.logging.Logger;
import javax.swing.JFrame;
import model.GameEngineImpl;
import model.SimplePlayer;
import model.enumeration.BetType;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.Slot;

public class Frame extends JFrame{
	private static final Logger logger = Logger.getLogger(GameEngineCallbackGui.class.getName());
	private final GameEngine gameEngine;
	private  PullDownMenu pulldownMenu;
	private  SummaryPanel summaryPanel;
	private  StatusBar statusBar;
	private  WheelPanel wheelPanel;
	private ArrayList<Player> playerList;
	private boolean spinning = false;
	
	public Frame() {
		playerList = new ArrayList<Player>();
		gameEngine = new GameEngineImpl();
		
        // add logging callback
        gameEngine.addGameEngineCallback(new GameEngineCallbackImpl());
        gameEngine.addGameEngineCallback(new GameEngineCallbackGui(this));
		
		/* Setting up the Frame */
		setBounds(250, 250, 1060, 800);
		setLayout(new BorderLayout());
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    /* Setting up the pulldown menu */
	    pulldownMenu = new PullDownMenu(this);
	    setJMenuBar(pulldownMenu);
	    
	    /* Setting up the Status Bar (bottom bar) */
	    statusBar = new StatusBar(this);
	    this.add(statusBar, BorderLayout.SOUTH);
	    
	    /* Setting up the Wheel Panel (center) */
	    wheelPanel = new WheelPanel(this);
	    this.add(wheelPanel, BorderLayout.CENTER);
	    
	    /* Setting up the Summary Panel (left) */
	    summaryPanel = new SummaryPanel(this);
	    this.add(summaryPanel, BorderLayout.WEST);
	    
        setVisible(true);
	}
	
	public void addNewPlayerDialog(){
		AddPlayerDialog addPlayer = new AddPlayerDialog(this);
		revalidate();
		repaint();
	}
	
	public void spin() {
		this.spinning = true;					//flag to no longer allow bets
		new Thread()
		{
		@Override
		public void run()
		{
			gameEngine.spin(1, 500, 25);			// Spin speed settings
		}
		}.start();
	}

	public void addPlayer(String playerId, String playerName, int playerPoints) {
		playerList.add(new SimplePlayer(playerId, playerName, playerPoints));
		
		/*add the last element of the playerList */
		gameEngine.addPlayer(playerList.get(playerList.size()-1));
		summaryPanel.addPlayer(playerList.get(playerList.size()-1));
	}

	public void removePlayer() {
		/* remove the last element of the playerList */
		if (playerList.size() > 0) {
			Player player = playerList.get(playerList.size()-1);
			playerList.remove(playerList.get(playerList.size()-1));
			gameEngine.removePlayer(player);
			summaryPanel.removePlayer();
			revalidate();
			repaint();
		}
	}
	
	public void placeBetDialog() {
		/* check spinning flag, can place bets if not spinning */
		if(!spinning) {
			PlaceBetDialog placeBetDialog = new PlaceBetDialog(this);
			revalidate();
			repaint();
		}
	}
	
	public void placeBet(String playerId, int betValue, String betType) {
		Player player = gameEngine.getPlayer(playerId);
		BetType betTypeEnum = BetType.valueOf(betType);
		player.setBet(betValue);
		player.setBetType(betTypeEnum);
		summaryPanel.updateBet(playerId, betValue, betType);
	}

	public void updateStatus(String slotStatus) {
		statusBar.updateStatus(slotStatus);
		revalidate();
		repaint();
	}

	public void updateResult(Slot result) {
		this.spinning = false; 						//flag to allow betting to resume
		gameEngine.calculateResult(result);
		statusBar.updateResult(result.toString());
		/* Iterate through places and update their visual scores */
        for(Player player : playerList){
        	summaryPanel.updateResult(player.getPlayerId(), player.getPlayerName(), player.getPoints());
        }
		
		revalidate();
		repaint();
	}

	public void updateBall(Slot slot) {
		/* Pass the ball off at current position to be drawn */
		int position = slot.getPosition();
		wheelPanel.updateBall(position);
	}
}
