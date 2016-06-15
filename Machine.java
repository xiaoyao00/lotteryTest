import java.util.ArrayList;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class Machine {
	private static int numPick3 = 50;
	private static int numPick4 = 40;
	private static int numPick5 = 60;
	private static int types = 3;
	private static int totaltickets = numPick3+numPick4+numPick5;
	private String winPick3 = "";
	private String winPick4 = "";
	private String winPick5 = "";
	
	private int count3 = 0, count4 = 0, count5 = 0;
	Set<String> picks = new HashSet<String>();
	Hashtable<String, Integer> users = new Hashtable<String, Integer>();
	Set<Integer> selects = new HashSet<Integer>();
	private int[][] picksall = new int[5][2];
	private Set<Integer> soldout = new HashSet<Integer>();
	public Machine() {
		//soldout.add(1);
		//soldout.add(2);	
	}


	public Set<Integer> getSelects() {
		return selects;
	}


	public void setSelects(Set<Integer> selects) {
		this.selects = selects;
	}


	public static int getTotaltickets() {
		return totaltickets;
	}


	public static void setTotaltickets(int totaltickets) {
		Machine.totaltickets = totaltickets;
	}


	public static int getNumPick3() {
		return numPick3;
	}


	public static void setNumPick3(int numPick3) {
		Machine.numPick3 = numPick3;
	}


	public static int getNumPick4() {
		return numPick4;
	}


	public static void setNumPick4(int numPick4) {
		Machine.numPick4 = numPick4;
	}


	public static int getNumPick5() {
		return numPick5;
	}


	public static void setNumPick5(int numPick5) {
		Machine.numPick5 = numPick5;
	}


	public String getWinPick3() {
		return winPick3;
	}


	public void setWinPick3(String winPick3) {
		this.winPick3 = winPick3;
	}


	public String getWinPick4() {
		return winPick4;
	}


	public void setWinPick4(String winPick4) {
		this.winPick4 = winPick4;
	}


	public String getWinPick5() {
		return winPick5;
	}


	public void setWinPick5(String winPick5) {
		this.winPick5 = winPick5;
	}


	public int getCount3() {
		return count3;
	}


	public void setCount3(int count3) {
		this.count3 = count3;
	}


	public int getCount4() {
		return count4;
	}


	public void setCount4(int count4) {
		this.count4 = count4;
	}


	public int getCount5() {
		return count5;
	}


	public void setCount5(int count5) {
		this.count5 = count5;
	}




	public Set<String> getPicks() {
		return picks;
	}


	public void setPicks(Set<String> picks) {
		this.picks = picks;
	}


	public Hashtable<String, Integer> getUsers() {
		return users;
	}


	public void setUsers(Hashtable<String, Integer> users) {
		this.users = users;
	}



	public int[][] getPicksall() {
		return picksall;
	}


	public void setPicksall(int[][] picksall) {
		this.picksall = picksall;
	}


	public Set<Integer> getSoldout() {
		return soldout;
	}


	public void setSoldout(Set<Integer> soldout) {
		this.soldout = soldout;
	}


	public Integer pickTicket(){
		Integer pick = null;

		boolean sold = false;
		//create a ticket, if sold out, choose random from other type
		Iterator<Integer> it = soldout.iterator();
		while(!sold&& soldout.size() < types){
			pick = (int)(Math.random()*3+3);
			picksall[pick-1][0] ++;
			System.out.print(" choose: Pick"+pick+" ");
			this.getSelects().add(pick);
			
			while (it.hasNext()){
				int i =(Integer) it.next();
				if (pick == i){
					int pick2 = (int)(Math.random()*3+3);
					while(pick2 == i){
						pick2 = (int)(Math.random()*3+3);
					}
					pick = pick2;
					it = soldout.iterator();
					picksall[pick2-1][1] ++;
					System.out.print(" sold out, reselect: Pick"+pick+" ");
				}
			}
			
			
			if (pick == 3){
				count3 ++;	
				if (count3 >= numPick3){
					soldout.add(pick);
					//count3 = 0;
				}
				sold = true;
			}
			else if(pick == 4){
				count4 ++;
				if (count4 >= numPick4){
					soldout.add(pick);
					//count4 =0;
				}
				sold = true;
			}
			else if (pick == 5){
				count5++;
				if (count5 >= numPick5){
					soldout.add(pick);
					//count5 = 0;
				}
				sold = true;
			}
			else{
				sold = true;
			}
			it = soldout.iterator();
			
		}
		
		return pick;

	}
	public static void main(String[] args) {
		Machine m = new Machine();
		boolean sold = false, soldout = false;
		Random rand = new Random();
		for (int c = 0; c < m.getTotaltickets(); c++ ){
			//and a customer might bought at most 5 tickets
			int totalboughts = rand.nextInt(5)+1; 
			System.out.println("costumer:"+ c+" wants "+ totalboughts+" tickets: ");
			for (int b = 0; b < totalboughts; b++){
				//decide pick3 or pick4 or pick5
				Integer pick = m.pickTicket();
				
				if(pick != null){
					//return a new ticket number
					Ticket t = new Ticket(pick);
					sold = m.getPicks().add(t.getNumber());
					while(!sold){
						t = new Ticket(pick);
						sold = m.getPicks().add(t.getNumber());
					}
					if (sold){
						m.users.put(t.getNumber(),c);
					}
					System.out.println(" bought "+ t+" ");
				}
				if (pick == null){
					soldout = true;
					System.out.println("all sold out");
					break;
					
				}
			}
			if (soldout){
				break;
			}
			
		}
		//decide the winning ticks
		int totaltickets = m.getPicks().size();
		
		while(m.getWinPick3()==""||m.getWinPick4()==""||m.getWinPick5()==""){
			
			int winid = rand.nextInt(totaltickets);
			String win = (String) m.getUsers().keySet().toArray()[winid];
			
			if (win.length() == 3 && m.getWinPick3() == ""){
				m.setWinPick3(win);
			}
			else if (win.length() == 4 && m.getWinPick4() == ""){
				m.setWinPick4(win);
			}
			else if (win.length() == 5 && m.getWinPick5() == ""){
				m.setWinPick5(win);
			}	
		}
		System.out.println("winning tickets::"+m.getWinPick3()+" "+m.getWinPick4()+" "+m.getWinPick5());
		Integer win3 = m.getUsers().get(m.getWinPick3());
		Integer win4 = m.getUsers().get(m.getWinPick4());
		Integer win5 = m.getUsers().get(m.getWinPick5());
	
		//System.out.println(m.count3+" "+m.count4+" "+m.count5+" "+m.soldout.size()+" "+m.picks.size());
		System.out.println("type\twinid\tticket");
		System.out.println("Pick3\t"+win3+"\t"+m.getWinPick3());
		System.out.println("Pick4\t"+win4+"\t"+m.getWinPick4());
		System.out.println("Pick5\t"+win5+"\t"+m.getWinPick5());
		/*for (int i = 0; i <5; i++){
			for (int j = 0; j<2; j++){
				System.out.print(m.picksall[i][j]+" ");
			}
			System.out.println();
		}*/
	}

}
