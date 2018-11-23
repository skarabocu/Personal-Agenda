//Sinan Karabocuoglu
//4 November 2017
//Appointments

public class Appointment
{
  //attributes
  String day, description;
  //constructers
  
  public Appointment(String tarih, String acıklama)//creating appointment with parameters
  {
    day = tarih;
    description = acıklama;  
  }
  
  public Appointment()//creating empty appointment
  {
    day = "";
    description="";
  }
  //methods
  
  public String getAppointment()//method for getting the appointment details
  {
    String appointment = day + ":"+ description;//creates an apporopriate display of the appointment
    return appointment;
  }
  
  public void setAppointment(String oldappointment)//method for getting the old appointment data
  {
    int i = 0;
    if(!(oldappointment ==null))//checks if there is any old appointment
    {
    for(i = 0;i<oldappointment.length();i++)
    {
      if(oldappointment.substring(i,i+1).equals(":"))//finds the distincition between the day and description
      {
        day = oldappointment.substring(0,i);//sets the day
        break;
      }  
    }
    description = oldappointment.substring(i+1,oldappointment.length());//sets the description accordingly
    description.replace(":","");//fixes the description
    }
  }
   
}