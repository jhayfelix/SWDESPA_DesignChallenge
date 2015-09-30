/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dc;

import java.awt.Color;

//public class DC extends ListenerController{
public class DC{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ListenerController lc = new ListenerController();
        facebook.FBView fb = new facebook.FBView();
        sms.SMSView smsview = new sms.SMSView();
        CalendarProgram cp = new CalendarProgram(lc,fb,smsview);
    }
}

