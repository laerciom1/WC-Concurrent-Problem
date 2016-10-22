import java.util.ArrayList;
import java.util.Queue;
import java.util.Random;

public class Person extends Thread{
	private Boolean gender; 		// true = man, false = woman (=^.^=)
	private String id;				// eg.: M0, W0, M1, W1
	private Queue<Person> queue;	// queue to use the bathroom
	private ArrayList<Person> wc;	// list of people in the bathroom
	
	public Person(Boolean gender, WC wc, String id){
		this.gender = gender;
		this.id = id;
		this.queue = wc.getQueue();
		this.wc = wc.getWc();
	}
	
	public Boolean getGender(){
		return gender;
	}
	
	public String getID(){
		return id;
	}

	@Override
	public void run() {
		Random random = new Random();
		int time;
		while(true){
			time = gender ? (random.nextInt(6) + 5)*1000 : (random.nextInt(5) + 1)*1000;
			// time to wait before try to get in the queue to use the bathroom
			// men > wait 5-10 seconds | women > wait 1-5 seconds
			
			try {
				Thread.sleep(time);	// the thread will sleep "time" ms
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			synchronized(queue) {
				synchronized(wc){
					if(!queue.contains(this) && !wc.contains(this)){ // if the person is not even in the queue or in the bathroom
						queue.offer(this);	// get in the queue to use the bathroom
					}
				}
			}
		}
	}
}
