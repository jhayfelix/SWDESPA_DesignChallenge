/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dc;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CalendarProgram {
	
        /**** Day Components ****/
        Dates theDate = new Dates();

        /**** Swing Components ****/
        private JLabel monthLabel, yearLabel;
	private JButton btnPrev, btnNext, addEventButton,readCSV,readPSV;
        private JComboBox cmbYear;
	private JFrame frmMain;
	private Container pane;
	private JScrollPane scrollCalendarTable;
	private JPanel calendarPanel;
        
        /**** Calendar Table Components ***/
	private JTable calendarTable;
        private DefaultTableModel modelCalendarTable;
        
        private ListenerController lc;
        private ArrayList<Event> eventList = new ArrayList<Event>();
        private TableRenderer renderer;
        private String[] months =  {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        private facebook.FBView fb;
        private sms.SMSView smsv;
        
        public void refreshCalendar(int month, int year)
        {       System.out.print(year);
		String[] months =  {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
		int nod, som, i, j;
			
		btnPrev.setEnabled(true);
		btnNext.setEnabled(true);
		if (month == 0 && year <= theDate.getYearBound()-10)
                    btnPrev.setEnabled(false);
		if (month == 11 && year >= theDate.getYearBound()+100)
                    btnNext.setEnabled(false);
                
		monthLabel.setText(months[month]);
		monthLabel.setBounds(320-monthLabel.getPreferredSize().width/2, 50, 360, 50);
                
		cmbYear.setSelectedItem(""+year);
		
		for (i = 0; i < 6; i++)
			for (j = 0; j < 7; j++)
				modelCalendarTable.setValueAt(null, i, j);
		
		GregorianCalendar cal = new GregorianCalendar(year, month, 1);
		nod = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
		som = cal.get(GregorianCalendar.DAY_OF_WEEK);
		
                //PRINTS THE EVENTS IN THE CALENDAR
		for (i = 1; i <= nod; i++)
                {
			int row = new Integer((i+som-2)/7);
			int column  =  (i+som-2)%7;
                        
                        modelCalendarTable.setValueAt(i, row, column);
                        for(int a = 0; a<eventList.size();a++){
                            if(i == (eventList.get(a).getDay()) && months[month].equalsIgnoreCase(eventList.get(a).getMonth()) && year == eventList.get(a).getYear()){
                                int correctmonth = month+1;
                                modelCalendarTable.setValueAt(+i + "- " + eventList.get(a).getName(), row, column);
                                fb.showNewEvent(eventList.get(a).getName(), correctmonth, eventList.get(a).getDay(), eventList.get(a).getYear(), eventList.get(a).getColor());
                                sms.SMS s = new sms.SMS(eventList.get(a).getName(),cal,eventList.get(a).getColor());
                                smsv.sendSMS(s);      
                            }
                        }
			
		}
		calendarTable.setDefaultRenderer(calendarTable.getColumnClass(0), new TableRenderer()); 
                
	}
        
	public CalendarProgram(ListenerController lc, facebook.FBView fb, sms.SMSView smsv)
        {
		try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                }
		catch (Exception e) {}
                this.lc = lc;
                this.fb = fb;
                this.smsv = smsv;
		frmMain = new JFrame ("Calendar Application");
                frmMain.setSize(660, 750);
		pane = frmMain.getContentPane();
		pane.setLayout(null);
		frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		monthLabel = new JLabel ("January");
		yearLabel = new JLabel ("Change year:");
		cmbYear = new JComboBox();
		btnPrev = new JButton ("<<");
		btnNext = new JButton (">>");
                addEventButton = new JButton ("Add Event");
                readCSV = new JButton ("Read CSV");
                readPSV = new JButton ("Read PSV");
		modelCalendarTable = new DefaultTableModel()
                {
                    public boolean isCellEditable(int rowIndex, int mColIndex)
                    {
                        return false;
                    }
                };
                
		calendarTable = new JTable(modelCalendarTable);
                calendarTable.addMouseListener(new MouseAdapter()   
                {  
                    public void mouseClicked(MouseEvent evt)  
                    {  
                        int col = calendarTable.getSelectedColumn();  
                        int row = calendarTable.getSelectedRow();  
                    }
                });
                
		scrollCalendarTable = new JScrollPane(calendarTable);
		calendarPanel = new JPanel(null);

		calendarPanel.setBorder(BorderFactory.createTitledBorder("Calendar"));
		
		btnPrev.addActionListener(new handler());
		btnNext.addActionListener(new handler());
		cmbYear.addActionListener(new handler());
                addEventButton.addActionListener(new handler());
                readCSV.addActionListener(new handler());
                readPSV.addActionListener(new handler());
		
		pane.add(calendarPanel);
		calendarPanel.add(monthLabel);
		calendarPanel.add(yearLabel);
		calendarPanel.add(cmbYear);
		calendarPanel.add(btnPrev);
		calendarPanel.add(btnNext);
                calendarPanel.add(addEventButton);
                calendarPanel.add(readCSV);
                calendarPanel.add(readPSV);
		calendarPanel.add(scrollCalendarTable);
		
                calendarPanel.setBounds(0, 0, 640, 670);
                monthLabel.setBounds(320-monthLabel.getPreferredSize().width/2, 50, 200, 50);
		yearLabel.setBounds(370, 610, 100, 40);
		cmbYear.setBounds(460, 610, 160, 40);
		btnPrev.setBounds(20, 50, 100, 50);
		btnNext.setBounds(520, 50, 100, 50);
                addEventButton.setBounds(240, 610, 100, 40);
                readCSV.setBounds(130, 610, 100, 40);
                readPSV.setBounds(20, 610, 100, 40);
		scrollCalendarTable.setBounds(20, 100, 600, 500);
                
		frmMain.setResizable(false);
		frmMain.setVisible(true);
		
		GregorianCalendar cal = new GregorianCalendar();
		theDate.setDayBound(cal.get(GregorianCalendar.DAY_OF_MONTH));
		theDate.setMonthBound(cal.get(GregorianCalendar.MONTH));
		theDate.setYearBound(cal.get(GregorianCalendar.YEAR));
		theDate.setMonthToday(theDate.getMonthBound()); 
		theDate.setYearToday(theDate.getYearBound());
		
		String[] headers = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"}; //All headers
		for (int i=0; i<7; i++){
			modelCalendarTable.addColumn(headers[i]);
		}
		
		calendarTable.getParent().setBackground(calendarTable.getBackground()); //Set background

		calendarTable.getTableHeader().setResizingAllowed(false);
		calendarTable.getTableHeader().setReorderingAllowed(false);

		calendarTable.setColumnSelectionAllowed(true);
		calendarTable.setRowSelectionAllowed(true);
		calendarTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		calendarTable.setRowHeight(76);
		modelCalendarTable.setColumnCount(7);
		modelCalendarTable.setRowCount(6);
		
		for (int i = theDate.getYearBound()-100; i <= theDate.getYearBound()+100; i++)
                {
			cmbYear.addItem(String.valueOf(i));
		}
		
		refreshCalendar(theDate.getMonthToday(), theDate.getYearBound()); //Refresh calendar
                
	}
        
        public Calendar getCalendar(int month,int day, int year){
           SimpleDateFormat dateformatter = new SimpleDateFormat("MM/dd/yyyy");
           String m = months[month];
           Date date = null;
           try{
               date = dateformatter.parse(m+"/"+String.valueOf(day)+"/"+String.valueOf(year));
           }catch(ParseException p){
               p.printStackTrace();
           }
           Calendar cal = Calendar.getInstance();
           cal.setTime(date);
           
           return cal;
        }
        
        public class handler implements ActionListener{
  
            @Override
             public void actionPerformed(ActionEvent event){
                 
                if(event.getSource() == btnPrev){
                    
                     lc.prev(theDate);
                     refreshCalendar(theDate.getMonthToday(), theDate.getYearToday());
                }
                 
                else if(event.getSource() == btnNext){
                    lc.next(theDate);
                    refreshCalendar(theDate.getMonthToday(), theDate.getYearToday());
                }
                 
                else if(event.getSource() == cmbYear){
                     
                    if (cmbYear.getSelectedItem() != null){
                       
                       theDate.setYearToday(lc.getYear(cmbYear, theDate.getYearToday()));
                       refreshCalendar(theDate.getMonthToday(), theDate.getYearToday());
                    }                  
                }
                
                else if(event.getSource() == addEventButton){
                    lc.addEvent(eventList);
                    refreshCalendar(theDate.getMonthToday(), theDate.getYearBound());
                }
                
                else if(event.getSource() == readPSV){  
                    lc.loadPSV(eventList,months);
                    refreshCalendar(theDate.getMonthToday(), theDate.getYearBound());
                }
                
                else if(event.getSource() == readCSV){
                    lc.loadCSV(eventList,months);
                    refreshCalendar(theDate.getMonthToday(), theDate.getYearBound());
                }
            }  
        }

        
}

