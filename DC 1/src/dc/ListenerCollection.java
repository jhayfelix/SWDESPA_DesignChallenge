/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dc;

import java.util.ArrayList;
import javax.swing.*;

public interface ListenerCollection {
    
    public abstract void prev(Dates date);
    public abstract void next(Dates date);
    public abstract int getYear(JComboBox cmbYear, int yearToday);
    public abstract void addEvent(ArrayList<Event> eventList);
    public abstract void loadPSV(ArrayList<Event> eventList, String[] months);
    public abstract void loadCSV(ArrayList<Event> eventList, String[] months);
}
