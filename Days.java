//Sinan Karabocuoglu
//30 October 2017
//Days

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Days  
{
  //attributes
  int today;//the number of days in that month
  //constructer
  public Days(int daycount)
  {
    today = daycount;
  }
  
  //methods
  public int getCount()//gets the number of days
  {
    return today;
  }
  
  public JButton createBox(String label)
  {
      JButton button = new JButton(label);//creates a button with a label on it
      
      class ButtonListener implements ActionListener//creates a subclass for the action
      {
         public void actionPerformed(ActionEvent event)//method for the changing the background color
         {  
            String text = button.getText();//checks if the button has a day or not
            if(!(text.equals("")))
            {
              if(!(button.getBackground() == Color.BLUE))
              {    
            
            button.setBackground(Color.BLUE);//the statement for changing the background
            
            }
              else
              {
               button.setBackground(Color.WHITE);//the statement for changing the background
               
              }
            }
            else
            {
             button.setBackground(Color.WHITE);//the statement for changing the background
             
            }
            button.setOpaque(true);
            button.setBorderPainted(true);
         } 
         
      }

      ButtonListener listener = new ButtonListener();//creates an action listener
      button.addActionListener(listener);//sets the actionlistener to the button
      return button;//creates the button    
   }
}
    
    
    