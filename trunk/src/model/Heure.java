package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Heure implements Comparable<Heure> {

	private DateFormat df;
	private GregorianCalendar heur0;
	private GregorianCalendar heurMin;
	private GregorianCalendar heurMax;
	private static int tolerence = 30;// en minutes

	public Heure(String HHmm) {
		this.df = new SimpleDateFormat("HH':'mm");
		this.heur0 = new GregorianCalendar();
		int heur = Integer.parseInt(HHmm.substring(0, 2)); // chope les heurs
															// dans le string
		this.heur0.set(Calendar.HOUR_OF_DAY, heur);
		int min = Integer.parseInt(HHmm.substring(2)); // chope les minutes dans
														// le string
		this.heur0.set(Calendar.MINUTE, min);
		this.calculateTimeIntervale();
	}

	private void calculateTimeIntervale() {
		// heure max et min d'arrivé
		this.heurMin = new GregorianCalendar();
		this.heurMax = new GregorianCalendar();
		this.heurMin.set(Calendar.HOUR_OF_DAY, heur0.get(Calendar.HOUR_OF_DAY));
		this.heurMin.set(Calendar.MINUTE, heur0.get(Calendar.MINUTE));
		this.heurMax.set(Calendar.HOUR_OF_DAY, heur0.get(Calendar.HOUR_OF_DAY));
		this.heurMax.set(Calendar.MINUTE, heur0.get(Calendar.MINUTE));
		this.heurMin.add(Calendar.MINUTE, -tolerence);
		this.heurMax.add(Calendar.MINUTE, tolerence);
	}
	
	public GregorianCalendar getMinTime(){
		calculateTimeIntervale();
		return this.heurMin;
	}
	public GregorianCalendar getMaxTime(){
		calculateTimeIntervale();
		return this.heurMax;
	}
	
	public String toString() {
		return df.format(heur0.getTime());
	}

	@Override
	public int compareTo(Heure o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/*
	public static void main (String[] args){
		DateFormat df = new SimpleDateFormat("HH':'mm");
		Heure date0 = new Heure("1200");
		GregorianCalendar datemin= date0.getMinTime();
		GregorianCalendar datemax= date0.getMaxTime();
		
		
		System.out.println("Date0 : "+date0);
		System.out.println("Datemin : "+df.format(datemin.getTime()));
		System.out.println("Datemax : "+df.format(datemax.getTime()));
	}
	*/
}
