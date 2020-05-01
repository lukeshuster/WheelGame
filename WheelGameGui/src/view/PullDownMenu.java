package view;



import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controller.AddPlayerListener;
import controller.ExitActionListener;
import controller.PlaceBetListener;
import controller.RemovePlayerListener;
import controller.SpinActionListener;


public class PullDownMenu extends JMenuBar{
	public PullDownMenu(Frame frame) {
		JMenu playerOptionsMenu = new JMenu("Player Options");
		this.add(playerOptionsMenu);
		
		JMenu spinMenu = new JMenu("Spin Wheel");
		this.add(spinMenu);
		
		JMenu quitMenu = new JMenu("Quit");
		this.add(quitMenu);

		JMenuItem addPlayerItem = new JMenuItem("Add Player");
		addPlayerItem.addActionListener(new AddPlayerListener(frame));

		JMenuItem removePlayerItem = new JMenuItem("Remove Player");
		removePlayerItem.addActionListener(new RemovePlayerListener(frame));
		
		JMenuItem placeBetItem = new JMenuItem("Place Bet");
		placeBetItem.addActionListener(new PlaceBetListener(frame));
		
		JMenuItem spinWheelItem = new JMenuItem("Spin Wheel");
		spinWheelItem.addActionListener(new SpinActionListener(frame));

		JMenuItem exitItem = new JMenuItem("Exit Game");
		exitItem.addActionListener(new ExitActionListener());


		playerOptionsMenu.add(addPlayerItem);
		playerOptionsMenu.add(removePlayerItem);
		playerOptionsMenu.add(placeBetItem);
		spinMenu.add(spinWheelItem);
		quitMenu.add(exitItem);
	}
}
