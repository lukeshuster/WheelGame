package model;


import javax.swing.SwingUtilities;

import view.Frame;


public class Client {
	
   public static void main(String[] args)
   {
      SwingUtilities.invokeLater(new Runnable()
      {
         @Override
         public void run()
         {
            new Frame();
         }
      });
   }
}
