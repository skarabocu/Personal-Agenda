//Sinan Karabocuoglu
//30 October 2017
//Tester

import javax.swing.*;
import java.awt.*;
import java.io.*;
public class CalendarTester
{
  public static void main(String [] args) throws IOException
  {
    
    JFrame frame = new FramePanel();//creates the panel
    frame.setSize(600,400);//sets the size of the panel
    frame.setResizable(false);//not resizable
    frame.setTitle("My Calendar");//sets the title
    frame.setLocationRelativeTo(null);//puts the calendar to the center of the screen
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//closes on exit
    frame.setVisible(true);//sets it visible
  }
}
