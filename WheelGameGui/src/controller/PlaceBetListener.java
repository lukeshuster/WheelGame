package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.Frame;

public class PlaceBetListener implements ActionListener{
	private Frame frame;
	public PlaceBetListener(Frame frame) {
		this.frame = frame;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		frame.placeBetDialog();
	}

}
