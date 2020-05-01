package model;

import model.enumeration.BetType;
import model.enumeration.Color;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.Slot;
import view.interfaces.GameEngineCallback;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ThreadLocalRandom;


public class GameEngineImpl implements GameEngine {
    /* Attributes */
    private ArrayList<Player> playerCollection = new ArrayList<>();
    private ArrayList<GameEngineCallback> gameEngineCallbackCollection = new ArrayList<>();
    private ArrayList<Slot> slotCollection = new ArrayList<>();

    public GameEngineImpl(){}

    public void addGameEngineCallback(GameEngineCallback gameEngineCallback){
        gameEngineCallbackCollection.add(gameEngineCallback);
    }

    public boolean removeGameEngineCallback(GameEngineCallback gameEngineCallback){
        boolean removed = false;
        if(gameEngineCallbackCollection.contains(gameEngineCallback)){
            gameEngineCallbackCollection.remove(gameEngineCallback);
            removed = true;
        }
        return removed;
    }

    public void addPlayer(Player player){
        boolean updated = false;
        for(int i = 0; i < playerCollection.size(); i++){
            if((player.getPlayerId()) == (playerCollection.get(i).getPlayerId())){
                playerCollection.set(i, player);
                updated = true;
            }
        }
        if(!updated){
            playerCollection.add(player);
        }
    }

    public boolean removePlayer(Player player){
        boolean removed = false;
        if(playerCollection.contains(player)){
            playerCollection.remove(player);
            removed = true;
        }
        return removed;
    }

    public void calculateResult(Slot winningSlot){
    	for(Player player : playerCollection) {
    		player.getBetType().applyWinLoss(player, winningSlot);
    	}
    }

    public Collection<Player> getAllPlayers(){
        return playerCollection;
    }

    public Player getPlayer(String playerId){
        Player playerFound = null;
        for(Player player : playerCollection){
            if ((player.getPlayerId()).equals(playerId)){
                playerFound = player;
            }
        }
        return playerFound;
    }

    public Collection<Slot> getWheelSlots(){
    	if (slotCollection.isEmpty()){
    		slotCollection.add(new SlotImpl(0, Color.GREEN00, 0));
	        slotCollection.add(new SlotImpl(1, Color.RED, 27));
	        slotCollection.add(new SlotImpl(2, Color.BLACK, 10));
	        slotCollection.add(new SlotImpl(3, Color.RED, 25));
	        slotCollection.add(new SlotImpl(4, Color.BLACK, 29));
	        slotCollection.add(new SlotImpl(5, Color.RED, 12));
	        slotCollection.add(new SlotImpl(6, Color.BLACK, 8));
	        slotCollection.add(new SlotImpl(7, Color.RED, 19));
	        slotCollection.add(new SlotImpl(8, Color.BLACK, 31));
	        slotCollection.add(new SlotImpl(9, Color.RED, 18));
	        slotCollection.add(new SlotImpl(10, Color.BLACK, 6));
	        slotCollection.add(new SlotImpl(11, Color.RED, 21));
	        slotCollection.add(new SlotImpl(12, Color.BLACK, 33));
	        slotCollection.add(new SlotImpl(13, Color.RED, 16));
	        slotCollection.add(new SlotImpl(14, Color.BLACK, 4));
	        slotCollection.add(new SlotImpl(15, Color.RED, 23));
	        slotCollection.add(new SlotImpl(16, Color.BLACK, 35));
	        slotCollection.add(new SlotImpl(17, Color.RED, 14));
	        slotCollection.add(new SlotImpl(18, Color.BLACK, 2));
	        slotCollection.add(new SlotImpl(19, Color.GREEN0, 0));
	        slotCollection.add(new SlotImpl(20, Color.BLACK, 28));
	        slotCollection.add(new SlotImpl(21, Color.RED, 9));
	        slotCollection.add(new SlotImpl(22, Color.BLACK, 26));
	        slotCollection.add(new SlotImpl(23, Color.RED, 30));
	        slotCollection.add(new SlotImpl(24, Color.BLACK, 11));
	        slotCollection.add(new SlotImpl(25, Color.RED, 7));
	        slotCollection.add(new SlotImpl(26, Color.BLACK, 20));
	        slotCollection.add(new SlotImpl(27, Color.RED, 32));
	        slotCollection.add(new SlotImpl(28, Color.BLACK, 17));
	        slotCollection.add(new SlotImpl(29, Color.RED, 5));
	        slotCollection.add(new SlotImpl(30, Color.BLACK, 22));
	        slotCollection.add(new SlotImpl(31, Color.RED, 34));
	        slotCollection.add(new SlotImpl(32, Color.BLACK, 15));
	        slotCollection.add(new SlotImpl(33, Color.RED, 3));
	        slotCollection.add(new SlotImpl(34, Color.BLACK, 24));
	        slotCollection.add(new SlotImpl(35, Color.RED, 36));
	        slotCollection.add(new SlotImpl(36, Color.BLACK, 13));
	        slotCollection.add(new SlotImpl(37, Color.RED, 1));
    	}
        return slotCollection;
    }

    public boolean placeBet(Player player, int bet, BetType betType){
    	boolean betPlaced = false;
    	if(player.setBet(bet)) {
    		player.setBetType(betType);
    		betPlaced = true;
    	}
    	return betPlaced;
    }

    public void spin(int initialDelay, int finalDelay, int delayIncrement){
    	/* cast slotCollection to ArrayList to use ArrayList Methods */
    	ArrayList<Slot> wheel = (ArrayList<Slot>) this.getWheelSlots();
    	
    	int delay = initialDelay;
    	int index = ThreadLocalRandom.current().nextInt(wheel.size());
    	while(delay < finalDelay) {
    		try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    		for(int i = 0; i < gameEngineCallbackCollection.size(); i++) {
    			gameEngineCallbackCollection.get(i).nextSlot(wheel.get(index), this);
    		}
    		delay += delayIncrement;
    		if (delay >= finalDelay) {
    			for(int i = 0; i < gameEngineCallbackCollection.size(); i++) {
    				gameEngineCallbackCollection.get(1).result(wheel.get(index), this);
    			}
    			this.calculateResult(wheel.get(index));
    		}
    		
    		if (index == 37) {
    			index = 0;
    		}
    		else{
    			index++;
    		}
    	}
    }
}
