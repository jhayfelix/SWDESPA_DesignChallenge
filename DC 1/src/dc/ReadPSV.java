/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dc;

import java.io.*;
import java.util.*;

public class ReadPSV {
	ArrayList<Event> event = new ArrayList<Event>();
	
	public ReadPSV(ArrayList<Event> event, String[] months){
		String file = "src/dc/DLSU Unicalendar.psv"; // the csv file to be read.
		String line;
		BufferedReader buffRead= null; 
		
		try{
			buffRead = new BufferedReader(new FileReader(file));
			while((line = buffRead.readLine()) != null){
				System.out.println(line);
				String info[] = line.split(" \\| ");
				String dateinfo[] = info[1].split("/");
                                int month = Integer.parseInt(dateinfo[0]);
                                month--;
				Event e = new Event(info[0],months[month],Integer.parseInt(dateinfo[1]) ,Integer.parseInt(dateinfo[2]),info[2] );
				event.add(e);
			}
			
		}catch(Exception e){
			System.out.println(e);
		}finally{
			 if (buffRead != null) {  
	                try {  
	                    buffRead.close();  
	                } catch (Exception e) {  
	                    e.printStackTrace();  
	                }  
		}
	}
    }
}