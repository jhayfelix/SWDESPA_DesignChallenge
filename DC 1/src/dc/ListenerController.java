/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dc;

import java.util.ArrayList;
import javax.swing.*;

public class ListenerController implements ListenerCollection{
    
   @Override
    public void prev(Dates date){
        date.prev();
    }
    
   @Override
    public void next(Dates date){
        
        date.next();
	
    }
    
    @Override
    public int getYear(JComboBox cmbYear, int yearToday){
        String b = cmbYear.getSelectedItem().toString();
        yearToday = Integer.parseInt(b);
        return yearToday;
    }
    
    public void addEvent(ArrayList<Event> eventList){
                 String sname,smonth,sday,syear,scolor;
                 Boolean syearly;
                 int iday,iyear;
                 String[] months =  {"january", "february", "march", "april", "may", "june", "july", "august", "september", "october", "november", "december"};
                 JTextField eName = new JTextField();
                 JComboBox eMonth = new JComboBox();
                 for(int x = 0; x<12; x++)
                 {
                     eMonth.addItem(months[x]);
                 }
                 
                 JComboBox eDay = new JComboBox();
                 for(int x = 1; x<32; x++)
                 {
                     eDay.addItem(Integer.toString(x));
                 }
                 
                 JComboBox eYear = new JComboBox();
                 for(int x = 1915; x<2116; x++)
                 {
                     eYear.addItem(Integer.toString(x));
                 }
                 JComboBox colors = new JComboBox();
                 colors.addItem("Red");
                 colors.addItem("Blue");
                 colors.addItem("Green");
                 colors.addItem("Black");
                 Object [] info = {
                     "Name of the event:", eName,
                     "Month of the event:", eMonth,
                     "Day of the event:", eDay,
                     "Year of the event:", eYear,
                     "Colors", colors,
                 };
                 
                 int option = JOptionPane.showConfirmDialog(null, info, "ADD EVENT", JOptionPane.OK_CANCEL_OPTION);
                 if(option == JOptionPane.OK_OPTION){
                     sname = eName.getText();
                     
                     sday = eDay.getSelectedItem().toString();
                     syear = eYear.getSelectedItem().toString();
                     scolor = colors.getSelectedItem().toString();
                     smonth = eMonth.getSelectedItem().toString();
                     iday = Integer.parseInt(sday);
                     iyear = Integer.parseInt(syear);
                     
                     Event e = new Event(sname,smonth,iday,iyear,scolor);
                     eventList.add(e);
                     System.out.println(sname + smonth + sday + syear + scolor);
                 }
             }
    
    public void loadCSV(ArrayList<Event> eventList, String[] months)
    {
        ReadCSV rc = new ReadCSV(eventList,months);
    }
    
    public void loadPSV(ArrayList<Event> eventList, String[] months)
    {
        ReadPSV rp = new ReadPSV(eventList,months);
    }
    
}
