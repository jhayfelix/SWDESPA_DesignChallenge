/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dc;

public class Dates {
    
    private int yearBound, monthBound, dayBound, yearToday, monthToday;

    public void setDayBound(int dayBound) {
        this.dayBound = dayBound;
    }

    public void setMonthBound(int monthBound) {
        this.monthBound = monthBound;
    }

    public void setYearBound(int yearBound) {
        this.yearBound = yearBound;
    }

    public void setMonthToday(int monthToday) {
        this.monthToday = monthToday;
    }

    public void setYearToday(int yearToday) {
        this.yearToday = yearToday;
    }

    public int getDayBound() {
        return dayBound;
    }

    public int getMonthBound() {
        return monthBound;
    }

    public int getYearBound() {
        return yearBound;
    }

    public int getMonthToday() {
        return monthToday;
    }

    public int getYearToday() {
        return yearToday;
    }
    
    
    public void prev()
    {
         if (this.monthToday == 0){
            this.monthToday=11;
            this.yearToday-=1;
	}else
            this.monthToday-=1;     
    }
    
    public void next(){
        
        if (this.monthToday == 11){
            this.monthToday = 0;
            this.yearToday+=1;
	}else
            this.monthToday+=1;	
    }
    
    
}
