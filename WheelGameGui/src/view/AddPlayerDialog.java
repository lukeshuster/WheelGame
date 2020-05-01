package view;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddPlayerDialog extends JOptionPane {
	public AddPlayerDialog(Frame frame) {
		JPanel field = new JPanel();
		field.setLayout(new GridLayout(3,1));
		JTextField idField = new JTextField();
		field.add(new JLabel("Player ID: "));
		field.add(idField);
		
		JTextField nameField = new JTextField();
		field.add(new JLabel("Player Name: "));
		field.add(nameField);
		
		JTextField pointsField = new JTextField();
		field.add(new JLabel("Points: "));
		field.add(pointsField);
		
		int option = this.showConfirmDialog(null, field, "Please Enter Player Details", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			String playerId = idField.getText();
			String playerName = nameField.getText();
			String tempPlayerPoints = pointsField.getText();
			int playerPoints = Integer.parseInt(tempPlayerPoints);
			frame.addPlayer(playerId, playerName, playerPoints);
	    }
	}
}
