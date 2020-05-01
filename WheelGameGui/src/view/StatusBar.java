package view;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.SpinActionListener;

public class StatusBar extends JPanel{
	private JLabel status;
	public StatusBar(Frame frame) {
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createLineBorder(Color.black));
		
	    JButton spin = new JButton("Spin!");
	    spin.addActionListener(new SpinActionListener(frame));
		this.add(spin,BorderLayout.WEST);
		
		status = new JLabel("Status: Idle");
	    this.add(status,BorderLayout.CENTER);  
	}

	public void updateStatus(String slotStatus) {
		status.setText("Status: " +slotStatus);
	}

	public void updateResult(String slotResult) {
		status.setText("Result: " +slotResult);
	}
}
