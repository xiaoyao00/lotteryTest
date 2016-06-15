import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Ticket {
	private String number;
	private Random rand = new Random();
	

	public Ticket(int pick) {
		if (pick == 3){
			this.number = ""+ rand.nextInt(9) +rand.nextInt(9)+ rand.nextInt(9);
			
		}
		else if (pick == 4){
			this.number = ""+ rand.nextInt(9) +rand.nextInt(9)+rand.nextInt(9)+rand.nextInt(9);
			
		}
		else if (pick == 5){
			this.number = ""+ rand.nextInt(9) +rand.nextInt(9)+rand.nextInt(9)+rand.nextInt(9)+rand.nextInt(9);
		}
	}



	public String getNumber() {
		return number;
	}



	public void setNumber(String number) {
		this.number = number;
	}

	public String toString(){
		return this.number;
	}


	
	

}
