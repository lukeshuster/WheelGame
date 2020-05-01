package view;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PlaceBetDialog extends JOptionPane {
	public PlaceBetDialog(Frame frame) {
		JPanel field = new JPanel();
		field.setLayout(new GridLayout(3,1));
		JTextField idField = new JTextField();
		field.add(new JLabel("Player ID to place Bet for: "));
		field.add(idField);
		
		JTextField valueField = new JTextField();
		field.add(new JLabel("Bet Value: "));
		field.add(valueField);
		
		JTextField typeField = new JTextField();
		field.add(new JLabel("Bet Type (BLACK|RED|ZEROS): "));
		field.add(typeField);
		
		int option = this.showConfirmDialog(null, field, "Please Enter Bet Details", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			/*Validate input and then if all good place the bet */
			boolean valid = true;
			String playerId = idField.getText();
			String tempBetValue = valueField.getText();
			int betValue = 0;
			if (tempBetValue.matches("[1-9]\\d*")){		// regex to check if natural number
				betValue = Integer.parseInt(tempBetValue);
			}
			else {
				valid = false;
			}
			String betType = typeField.getText();
			if (betType.equals("BLACK") || betType.equals("RED") || betType.equals("ZEROS")) {
				
			}
			else {
				valid = false;
			}
			if(valid) {
				frame.placeBet(playerId, betValue, betType);
			}
	    }
	}
}
