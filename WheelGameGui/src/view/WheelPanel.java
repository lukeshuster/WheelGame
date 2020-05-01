package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class WheelPanel extends JPanel{
	private boolean ball = false;
	private int position = 0;
	public WheelPanel(Frame frame) {
	}
	
	@Override
	public void paintComponent(Graphics g) {
		/* Set up Rendering Hints for sharp scaling */
		Graphics2D g2d = ((Graphics2D) g);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		super.paintComponent(g2d);
		
		
		/* Load Image */
        Image wheelImage = null;
		try {
			wheelImage = ImageIO.read(new File("img/Basic_roulette_wheel_1024x1024.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/* Scaling */
        int x1 = 0, y1 = 0, x2 = 0, y2 = 0;
        x2 = this.getWidth();
        y2 = this.getHeight();
        if (x2 < y2) {
        	y2 = x2;
        }
        else {
        	x2 = y2;
        }
        g2d.drawImage(wheelImage, x1, y1, x2, y2, 0, 0, wheelImage.getWidth(this), wheelImage.getHeight(this), this);	//Draw scaled wheel
        
        if (ball) {
        	
        }
        
        /* Ball */
        g2d.setColor(Color.BLUE);
        int slots = 38;
        double wheelCenterline = x2/13.5;				//the 13.5 is how many segments wheel segments the wheel is wide.
        double wheelRadius = (x2-wheelCenterline)/2; 	//this approximates a circle that cuts through the center of the wheel segments
        int ballRadius = 10;
        int centerx = x2/2 - ballRadius;
        int centery = y2/2 - ballRadius;
        double angleIncrement = 2*Math.PI/slots;
        
    	double currentAngle = (position*angleIncrement) - (10*2*Math.PI/38) + (2*Math.PI/76);		//Rotate to adjust for the orientation of the wheel and account for positional offset
        double xPos = centerx +  wheelRadius * Math.cos(currentAngle);
        double yPos = centery + wheelRadius * Math.sin(currentAngle);
    	int ballX = (int) Math.round(xPos);
    	int ballY = (int) Math.round(yPos);
        g2d.fillOval(ballX, ballY, 2*ballRadius, 2*ballRadius);
        
        /* Code to populate all slots */
/*        for (int i = 0; i < slots; i++) {
        	double currentAngle = i*angleIncrement + (2*Math.PI/76);		//Rotate to adjust for the orientation of the wheel
            double xPos = centerx +  wheelRadius * Math.cos(currentAngle);
            double yPos = centery + wheelRadius * Math.sin(currentAngle);
        	int ballX = (int) Math.round(xPos);
        	int ballY = (int) Math.round(yPos);
            g2d.fillOval(ballX, ballY, 2*ballRadius, 2*ballRadius);
        }*/
    }

	public void updateBall(int position) {
		/* draw the ball */
		this.ball = true;
		this.position = position;
		repaint();	
	}
}
