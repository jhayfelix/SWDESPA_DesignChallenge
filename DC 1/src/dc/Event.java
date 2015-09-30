/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dc;

import java.awt.Color;


public class Event {
    
    private String name, month, scolor;
    private Color color;
    private int day, year;
    
    public Event(String name, String month, int day, int year, String scolor){
        this.name = name;
        this.month = month;
        this.day = day;
        this.year = year;
        this.scolor = scolor;
        
        if(scolor.equalsIgnoreCase("red"))
            this.color = Color.RED;
        else if(scolor.equalsIgnoreCase("blue"))
            this.color = Color.BLUE;
        else if(scolor.equalsIgnoreCase("green"))
            this.color = Color.GREEN;
        else if(scolor.equalsIgnoreCase("black"))
            this.color = Color.BLACK;
        
    }

    public String getName() {
        return name;
    }

    public String getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getYear() {
        return year;
    }

    public Color getColor() {
        return color;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
