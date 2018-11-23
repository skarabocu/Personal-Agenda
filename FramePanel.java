 //Sinan Karabocuoglu
//30 October 2017
//Panel

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.text.DateFormat;
import java.util.GregorianCalendar;
import java.io.*;
public class FramePanel extends JFrame
{
  //Panels
  JPanel months = new JPanel();//panel for displaying the month, year and two position buttons
  JPanel days = new JPanel();//panel for the days of the month
  JPanel currentDate = new JPanel();//panel for showing the current date
  JPanel appointments = new JPanel();//appointment list
  //________
  //attitudes
  int sira = 0;//the order of the month in the aylar array
  int year = 2017;//current year
  String month ="";//name of the month
  int dayofweek = 0;//the name of the current day
  int nextFirstday = 0;//first day of the next month
  int bugun = 0;//today
  int previousDay = 0;//the name of the last day of the previous month
  int nextMonth, nextYear;//appointment month and year
  String appointmentView = "";//appointment list
  String appointmentDate = "";//appointment date
  int appmonth = 0;
  int appyear = 0;
  int appday = 0;
  //______
  //Arrays
  int newdays [] = new int[7]; //the changing order of the days of the week
  String [] aylar = {"January","February","March","April","May","June","July","August","September","October","November","December"};//aylar array
  String [] week = {"   Sunday","  Monday","  Tuesday","Wednesday","  Thursday","   Friday","  Saturday"};//array for the days of the week
  //_________
  Calendar appointmentDay = new GregorianCalendar();
  ArrayList <Days> dates = new ArrayList <Days>();//the arraylist of the months and their total days
  ArrayList <Appointment> planlar = new ArrayList <Appointment>();//the arraylist for the appointments
  ArrayList <JRadioButton> silinecekler = new ArrayList <JRadioButton>();//appointments that will be erased
  //Buttons
  JButton next = new JButton(">>");//next button
  JButton previous = new JButton("<<");//previous button
  JButton seeAppointment = new JButton("Appointments");
  JButton newAppointment = new JButton("New Appointment");
  JButton deleteAppointment = new JButton("Delete Appointment");
  JButton button;//buttons for displaying days
  JButton label = new JButton();//label for showing the month and year
  ButtonGroup group = new ButtonGroup();//group for radio buttons
  //______
  ButtonListener4 listener4 = new ButtonListener4();
  
  public FramePanel()//adds all the methods to the constructer
  {
   this.lookAppointment();
   this.oldAppointments();
   this.setCurrentDate();
   this.setMonths();
   this.setMonth();
   this.setAppointment();
   this.eraseAppointment();
   this.add(months, BorderLayout.NORTH);
   this.add(days, BorderLayout.CENTER);
   this.add(currentDate, BorderLayout.SOUTH);
  }
  
  public void setCurrentDate()//gets the current day
  {
   Calendar cal = Calendar.getInstance();//gets the time and the date from the computer
   dayofweek = cal.get(Calendar.DAY_OF_WEEK);
   DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");//formats the day
   Date day = new Date();
   String date = dateFormat.format(day);//gets the current day
   year = Integer.parseInt(date.substring(6,10));//gets the current year
   sira = Integer.parseInt(date.substring(3,5))-1;//gets the current month 
   month = aylar[sira];////gets the current month and turns it into a name
   String gun = date.substring(0,2);//gets the day
   bugun = Integer.parseInt(gun);//turns the day into an integer
   JLabel current = new JLabel("               Today is: "+gun+" "+month+" "+year);
   currentDate.setLayout(new GridLayout(2,2));
   currentDate.add(current);//adds the day into the panel
   currentDate.add(seeAppointment);//adds the button
   currentDate.add(deleteAppointment);//adds the button
   currentDate.add(newAppointment);//adds the button
   appmonth = sira+1;//variable that will be used for limiting the appointment times
   appyear = year;//variable that will be used for limitin the appointment times
   appday = bugun;//variable that will be used for limitin the appointment times
  }
  
  public void setMonths()//enters each month with their number of days
  {
    Days january = new Days(31);
    dates.add(january);
    Days february = new Days(28);
    dates.add(february);
    Days march = new Days(31);
    dates.add(march);
    Days april = new Days(30);
    dates.add(april);
    Days may = new Days(31);
    dates.add(may);
    Days june = new Days(30);
    dates.add(june);
    Days july = new Days(31);
    dates.add(july);
    Days august = new Days(31);
    dates.add(august);
    Days september = new Days(30);
    dates.add(september);
    Days october = new Days(31);
    dates.add(october);
    Days november = new Days(30);
    dates.add(november);
    Days december = new Days(31);
    dates.add(december);
  }
  
  //the major method for defining the days of the month
  public void setMonth()
  {
    months.setLayout(new GridLayout(1,3));
    label.setText(" "+month+" "+year);//sets the text for the displaying month and year
    String day ="";
    days.setLayout(new GridLayout(6,7));
    int counter = 0;//counter for understanding how many times if the newarray loops run
    int arraycounter = 0;//counter for the sequence of the newarray list
    int firstday = 0;//first day of the month
    //setting the first day
    boolean found2 = true;
    while(found2)
    {
      bugun = bugun- 7;//decreases the date of the current day until the first week of the month
      if(bugun < 0)//when it reaches the first month
      {
        bugun += 7;//fix the number
        firstday = dayofweek-bugun;//substract the day from the order of the date in order to find which date is the first day of the month 
        if(firstday<0)//fixes the value if it fall under 0
        {
         firstday +=7; 
        }
        found2 = false;
      }
    }
    //____________________
    for(int e = firstday;e<=7;e++)//loop for creating the new order of dates for that spesific month
    {
        if(e == 7)//fixes the value after saturday
            {
              e = 0;
            }
        newdays[arraycounter] = e;//sets the new order of dates
        arraycounter++;
            JLabel l = new JLabel(week[e]);//prints the name of the dates
            days.add(l);
            counter++;
            if(counter == 7)//if loop runs for seven times it stops
            {
              break;
            }
          }  
          int limit = dates.get(sira).getCount();//gets the maximum number of days
          for(int i = 1;i<=35;i++)//the loop runs and creates the days of the month 
          {
            if((dates.get(sira).getCount()==28)&&((year-1988)%4==0))//special case for february for once in each 4 years
            {
              limit = 29;
            }
            day = Integer.toString(i);
            if(i == limit+1)//when the days of the month ends it stores the value for the next month
            {
              int copyi = i;
              boolean found = true;
              while(found)//same procedure of finding the first day of the next month
              {
                copyi= copyi- 7;//decreases the number of days by 7 for going back by one whole week
                if(copyi < 0)
                {
                  copyi += 7;//fixes the value if the number is less than zero
                  nextFirstday = newdays[copyi];//sets the firstday of the next month
                  found = false;
                }
              }
            }
            if (i >=limit+1)//after the maximum day number, rest of the table will be filled with emphty boxes
            {
              day = "";
            }
            button = dates.get(sira).createBox(day);
            if(!(button.getText().equals("")))//checks if the button is has an actual day
            {
              if((year>appyear)||((year == appyear)&&(sira+1 >=appmonth))||((year == appyear)&&(sira+1 >=appmonth)&&(i>appday)))//checks if the day that the button resembles is in the correct range for an appointment
            {
            button.addActionListener(listener4);//adds the action listener
              }
            }
            days.add(button);//adds the button to the panel
          }
           previousDay = newdays[6];//sets the last day of the previous month
           
           //action of the next button
           
    class ButtonListener1 implements ActionListener
      {
        public void actionPerformed(ActionEvent event)
        {
          sira++;//order of the number increases by one
          if(sira == 12)//increases the year after december
          {
            sira = 0;
            year++;
          }
          month = aylar[sira];//resets the month
          label.setText(" "+month+ " "+year);//resets the calendar text
          days.removeAll();//cleans the day panel
          days.setLayout(new GridLayout(6,7));
          int counter = 0;//counter for understanding how many times does the newarray list loop run
          int arraycounter = 0;//counter for setting the sequence of the newarray list
          for(int e = nextFirstday-1;e<=7;e++)//creates the new array of dates for that specific month
          {
            if(e == 7)//fixes the value after saturday
            {
              e = 0;
            }
            if(e==-1)//fixes the value after sunday
            {
              e = 6;
            }
            newdays[arraycounter] = e;//adds the names to the array list
            arraycounter++;
            JLabel l = new JLabel(week[e]);//prints the name of the dates
            days.add(l);//adds the label to the panel
            counter++;
            if(counter == 7)
            {
              break;
            }
          } 
          String day ="";
          int limit = dates.get(sira).getCount();//gets the maximum number of days for that specific month
          for(int i = 1;i<=35;i++)//creates the days of the month
          {
            if((dates.get(sira).getCount()==28)&&((year-2012)%4==0))//special case for february for once in each 4 years
            {
              limit = 29;
            }
            day = Integer.toString(i);
            if(i == limit+1)//finds the next first day of the next month
            {
              int copyi = i;
              boolean found = true;
              while(found)
              {
                copyi= copyi- 7;//goes back by one whole week to find the same day on the first week
                if(copyi < 0)
                {
                  copyi += 7;//fixes the value if it falls under 0
                  nextFirstday = newdays[copyi];//gets the value of first day of the next month
                  found = false;
                }
              }
            }
            if(i >=limit+1)//fills the rest of the table with empthy boxes
            {
              day = "";
            }
            button = dates.get(sira).createBox(day);//creates the button
            if(!(button.getText().equals("")))//checks if the button has an actual date
            {
            if((year>appyear)||((year == appyear)&&(sira+1 >=appmonth))||((year == appyear)&&(sira+1 >=appmonth)&&(i>appday)))//checks if the day that the button resembles is in the correct range for an appointment
            {
            button.addActionListener(listener4);//adds the action listener
            }
            }
            days.add(button);//adds the button to the panel
          }
             previousDay = newdays[6];//sets the last day of the previous month
        }
      }
    
    //action of the previous button
    
    class ButtonListener2 implements ActionListener
      {
        public void actionPerformed(ActionEvent event)
        {
          sira--;//order of the months decreased by one
          if(sira == -1)//changes the year after january
          {
            sira = 11;
            year--;
          }
          String month = aylar[sira];//finds the last month
          label.setText(" "+month+ " "+year);//resets the calendar text
          days.removeAll();//cleans the days
          int limit = dates.get(sira).getCount();//gets the maximum number of days of that month
          if((dates.get(sira).getCount()==28)&&((year-2012)%4==0))//special case for february
            {
              limit = 29;
            }
          int limit2 = limit;//copies the day number for manipulation
          //setting the first day
          boolean found2 = true;
          while(found2)//finds the first day of the past month with the same logic in next button
          {
            limit2= limit2- 7;
            if(limit2 < 0)
            {
              limit2 += 7;
              nextFirstday = previousDay-limit2;
              if(nextFirstday<0)
              {
                nextFirstday +=7; 
              }
              found2 = false;
            }
          }
          //____________________
          days.setLayout(new GridLayout(6,7));
          int counter = 0;//counter for checking how many times does the newarray loop runs
          int arraycounter = 0;//counter for the sequence of the newarray list
          for(int e = nextFirstday+1;e<=7;e++)//creates the new sequance of dates
          {
            if(e == 7)
            {
              e = 0;
            }
            if(e==-1)
            {
              e = 6;
            }
            newdays[arraycounter] = e;//adds the days to the array
            arraycounter++;
            JLabel l = new JLabel(week[e]);//prints the dates
            days.add(l);
            counter++;
            if(counter == 7)
            {
              break;
            }
          } 
          String day ="";
          
          for(int i = 1;i<=35;i++)//recreates the days of the month
          {
            if((dates.get(sira).getCount()==28)&&((year-2012)%4==0))//special case for february
            {
              limit = 29;
            }
            day = Integer.toString(i);
            if(i == limit+1)//finds the next first day of the next month with the same logic
            {
              int copyi = i;
              boolean found = true;
              while(found)
              {
                copyi= copyi- 7;//goes back by one whole week each time to find the day on the first week
                if(copyi < 0)
                {
                  copyi += 7;//fixes the value if it falls below 0
                  nextFirstday = newdays[copyi];//gets the next first day
                  found = false;
                }
              }
            }
            if(i >=limit+1)
            {
              day = "";
            }
            button = dates.get(sira).createBox(day);//creates the button
            if(!(button.getText().equals("")))//checks if the button actually has a day value
            {
              if((year>appyear)||((year == appyear)&&(sira+1 >=appmonth))||((year == appyear)&&(sira+1 >=appmonth)&&(i>appday)))//checks if the day that the button resembles is in the correct range for an appointment
            {
            button.addActionListener(listener4);//adds the action listener
            }
            }
            days.add(button);//adds the button to the days panel
          }
          previousDay = newdays[6];//sets the first day of the previous month
        }
      }
    ButtonListener1 listener = new ButtonListener1();//creates listener1
    ButtonListener2 listener2 = new ButtonListener2();//creates listener2
    next.addActionListener(listener);//adds the listener1 to next button 
    previous.addActionListener(listener2);//adds the listener2 to previous button
    months.add(previous);//adds the button to the panel
    months.add(label);//adds the label to the panel
    months.add(next);//adds the button to the panel
  }
  
  public void setAppointment()
  {
   class ButtonListener3 implements ActionListener
   {
   public void actionPerformed(ActionEvent event)
  {
     if(event.getSource()== newAppointment)//if the listener will be used for setting new appointment
     {
      String inDate = JOptionPane.showInputDialog(null,"Please enter the date you want for an appointment:(MM/YYYY)", "Appointment", JOptionPane.PLAIN_MESSAGE);//asks the user which day they want to get an appointment
      nextMonth = Integer.parseInt(inDate.substring(0, 2));//get the month 
      nextYear = Integer.parseInt(inDate.substring(3, 7));//gets the year
      while(true)
      {
        if(nextYear>appyear)//checks if the year in the future
        {
          break;
        }
        else if(nextYear == appyear)
        {
          if(nextMonth>=appmonth)//checks if the month in the user
          {
            break;
          }
        }
          JOptionPane.showMessageDialog(null,"Please enter a future time for appointment!","Appointment",JOptionPane.PLAIN_MESSAGE);//warning
          inDate = JOptionPane.showInputDialog(null,"Please enter the date you want to display:(MM/YYYY)", "Appointment", JOptionPane.PLAIN_MESSAGE);//asks the user which day they want to get an appointment
          nextMonth = Integer.parseInt(inDate.substring(0, 2));//get the month 
          nextYear = Integer.parseInt(inDate.substring(3, 7));//gets the year
      }
     }
     if(event.getSource() == label)//if the listener will be used for searching a random month
     {
      String inDate = JOptionPane.showInputDialog(null,"Please enter the date you want to display:(MM/YYYY)", "Calendar", JOptionPane.PLAIN_MESSAGE);//asks the user which day they want to get an appointment
      nextMonth = Integer.parseInt(inDate.substring(0, 2));//get the month 
      nextYear = Integer.parseInt(inDate.substring(3, 7));//gets the year
     }
      sira = nextMonth-1;//sets the major variable sira accordingly
      year = nextYear;//sets the major variable year accordingly
      month = aylar[sira];////gets the given month and turns it into a name
      label.setText(" "+month+ " "+year);//sets the calendar label
      appointmentDay.set(nextYear,sira,1);//sets the calendar to the choosen day
      int gun = appointmentDay.get(Calendar.DAY_OF_WEEK)-1;//finds which day is the first day of chosen month in chosen year
      String day ="";
      days.removeAll();//clears the days panel
      days.setLayout(new GridLayout(6,7));
      int limit = dates.get(nextMonth-1).getCount();//gets the maximum number of days of that month
      int counter = 0;//counter for checking how many times the loop for the newarray list runs
      int arraycounter = 0;//counter for creating the sequence of the newarray list
      for(int e = gun;e<=7;e++)//creates the new sequance of dates
          {
            if(e == 7)
            {
              e = 0;
            }
            if(e==-1)
            {
              e = 6;
            }
            newdays[arraycounter] = e;//adds the day to the array
            arraycounter++;
            JLabel l = new JLabel(week[e]);//prints the dates
            days.add(l);//adds the label to the panel
            counter++;
            if(counter == 7)
            {
              break;
            }
          } 
      for(int i = 1;i<=35;i++)//recreates the days of the month
          {
            if((dates.get(sira).getCount()==28)&&((year-2012)%4==0))//special case for february
            {
              limit = 29;
            }
            day = Integer.toString(i);
            if(i == limit+1)//finds the next first day of the next month
            {
              int copyi = i;
              boolean found = true;
              while(found)
              {
                copyi= copyi- 7;//goes back for one whole week each time to find the day in the first week
                if(copyi < 0)
                {
                  copyi += 7;
                  nextFirstday = newdays[copyi];//sets the value as the next first day
                  found = false;
                }
              }
            }
            if(i >=limit+1)
            {
              day = "";
            }
            button = dates.get(sira).createBox(day);//creates the button
            if(!(button.getText().equals("")))//checks if the button has an actual day value
            {
              if((nextYear>appyear)||((nextYear == appyear)&&(nextMonth >=appmonth))||((nextYear == appyear)&&(nextMonth >=appmonth)&&(i>appday)))//checks if the day that the button resembles is in the correct range for an appointment
            {
            button.addActionListener(listener4);//adds the action listener
              }
            }
            days.add(button);//adds the button to the panel
          }
          previousDay = newdays[6];//sets the first day of the previous month
   }
   
  }
   ButtonListener3 listener3 = new ButtonListener3();
   newAppointment.addActionListener(listener3);
   label.addActionListener(listener3);
  }
  //method for checking which appointments are present
  public void lookAppointment()
  {
    
    class ButtonListener5 implements ActionListener
    {
      public void actionPerformed(ActionEvent event)
      {
        appointmentView = "";//sets the list to null
        for(Appointment activity : planlar)//searches each appointment in the list
        {
          appointmentView = appointmentView +activity.getAppointment()+"\n"; //creates a list of appointments
        }
        JOptionPane.showMessageDialog(null,appointmentView,"Appointments",JOptionPane.PLAIN_MESSAGE);//displays the list
        
        try
        {
          BufferedWriter writer = new BufferedWriter(new FileWriter("Appointments.txt"));//creates the data storage file
          writer.write(appointmentView);//writes the appointments
          writer.close();//closes the writer
        }
        catch(IOException e)
        {
          System.out.println(e);
        }
      } 
    }
    ButtonListener5 listener5 = new ButtonListener5();//creates the action listener
    seeAppointment.addActionListener(listener5);//assigns the action listener
  }
  //____________
  //method for deleting appointments
  public void eraseAppointment()
  {
    class ButtonListener6 implements ActionListener
    {
      public void actionPerformed(ActionEvent event)
      {
       int counter = 0;//sets a counter 
       JPanel appointmentPanel = new JPanel();//creates a panel to display the appointments
       appointmentPanel.setLayout(new GridLayout(10,1));
       for(Appointment activity : planlar)//loop goes for all the planlar arraylist
        {
          JRadioButton box = new JRadioButton(activity.getAppointment());//creates a checkbox for each appointment
          group.add(box);//adds the box to the button group so that the user will be able to choose only one appointment
          silinecekler.add(box);//adds them to the silinecekeler list
          appointmentPanel.add(box);//adds them to the panel
        }
        JOptionPane.showMessageDialog(null,appointmentPanel,"Delete Appointment",JOptionPane.PLAIN_MESSAGE);//show the panel to the user for choosing the appointment
        for(JRadioButton selected : silinecekler)//loop goes for all the silinecekler arraylist
        {
          counter++;//counter increases by 1 every time
          if(selected.isSelected())//checks if the box is chosen
          {
            if(planlar.size()==1)//if there is only one element, it clears all the arraylist
            {planlar.clear();//clears the arraylist
            }
            else
            {
            planlar.remove(counter-1);//if there is multiple elements it removes the chosen box
            }
          }
        }
      } 
    }
    ButtonListener6 listener6 = new ButtonListener6();//creates the action listener
    deleteAppointment.addActionListener(listener6);//assigns the action listener to the button
  }
  //____________________
  //action listener for making appointments by pressing buttons
  class ButtonListener4 implements ActionListener
    {
      public void actionPerformed(ActionEvent event)
      {
        String description = JOptionPane.showInputDialog(null,"Please describe your activity:", "Appointment", JOptionPane.PLAIN_MESSAGE);//gets the description of the activity
        if(!(description ==null))
        {
        String clock = JOptionPane.showInputDialog(null,"Which time do you want?(hh.mm)","Appointment",JOptionPane.PLAIN_MESSAGE);//gets the time
        description = description +" ("+clock+")";//adds the time to the appointment
        }
        appointmentDate = ((JButton) event.getSource()).getText()+" "+ label.getText();//sets the date of the activity
        Appointment app = new Appointment(appointmentDate, description);//creates an appointment for that day
        if(description.equals(null)||description.equals(""))
        { app = null;
        }
        else{
        planlar.add(app);//adds the appointment to the list
        }
      } 
    }
  public void oldAppointments()//method for getting the old appointments
  {
    try
        {
          BufferedReader reader = new BufferedReader(new FileReader("Appointments.txt"));//the reader reads the file that is created
          boolean found = true;
          while(found)//loop for checking all the lines in a txt file
          {
          String oldappointment = reader.readLine();//oldappointment is the line of the appointment
          if(oldappointment==null||oldappointment.equals(" "))//if the file is empty or it is the last line the loop breaks
          {
            found = false;
            reader.close();//reader closes
            break;
          }
          else
          {
          Appointment oldapp = new Appointment();//an appointment is created
          oldapp.setAppointment(oldappointment);//the appointment is set to one of the old appointment's feature
          planlar.add(oldapp);//appointment is added to the planlar arraylist
          }
          }
        }
        catch(IOException e)//exception for using the BufferedReader
        {
          System.out.println(e);
        }
  }  
}
