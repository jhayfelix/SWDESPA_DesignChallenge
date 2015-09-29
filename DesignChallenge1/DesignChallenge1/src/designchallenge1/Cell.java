/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package designchallenge1;

/**
 *
 * @author AzureKite
 */

import java.util.*;
public class Cell
{
    private String event;
    private Date date;
    
    public Cell(int year, int month, int date, String event)
    {
        this.date = new Date(year, month, date);
        this.event = event;
    }
    
    public Date getDate()
    {
        return date;
    }
    
    public String getEvent()
    {
        return event;
    }
}
