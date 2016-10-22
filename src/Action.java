import java.util.ArrayList;
import java.util.Random;

public class Action extends Thread{
	private ArrayList<Person> wc;	// list of people in the bathroom
	private Person person;			// person who is going to the bathroom

	public Action(ArrayList<Person> wc, Person person) {
		this.wc = wc;
		this.person = person;
	}
	
	@Override
	public void run() {
		Random random = new Random();		
		int time = person.getGender() ? (random.nextInt(3) + 1)*1000 : (random.nextInt(4) + 3)*1000;		
		// time of this person is going to stay in the bathroom
		// men > stay 1-3 seconds | women > stay 3-6 seconds
		
		synchronized(wc){
			wc.add(person);		// put the person into the bathroom
		}
		
		try {
			Thread.sleep(time);	// waiting "time" ms before remove the person from the bathroom
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		synchronized(wc){
			wc.remove(person);	// removing the person from the bathroom
		}
	}

}
