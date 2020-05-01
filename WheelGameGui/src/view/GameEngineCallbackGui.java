package view;


import model.interfaces.GameEngine;
import model.interfaces.Slot;
import view.interfaces.GameEngineCallback;

public class GameEngineCallbackGui implements GameEngineCallback {
	private Frame frame;
   public GameEngineCallbackGui(Frame frame)
   {
	   this.frame = frame;
   }

   @Override
   public void nextSlot(Slot slot, GameEngine engine)
   {
      frame.updateStatus(slot.toString());
      frame.updateBall(slot);
   }

   @Override
   public void result(Slot result, GameEngine engine)
   {
	   frame.updateResult(result);
	   frame.updateBall(result);
   }
}
