import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class WC extends Thread{
	private int limite; 			// maximum of people in the bathroom
	private ArrayList<Person> wc; 	// list of people in the bathroom
	private Queue<Person> queue; 	// queue to use the bathroom
	
	public WC(int limite){
		this.limite = limite;
		this.wc = new ArrayList<Person>();
		this.queue = new LinkedList<Person>();
	}
	
	public ArrayList<Person> getWc() {
		return wc;
	}

	public Queue<Person> getQueue() {
		return queue;
	}
	
	private void print(){	// shows the current scenario in console
		System.out.print("FILA: [ ");
		for(Person p : queue){
			System.out.print(p.getID() + " ");
		}
		System.out.println("]");
		
		System.out.print("BATH: [ ");
		for(Person p : wc){
			System.out.print(p.getID() + " ");
		}
		System.out.println("]\n");
	}
		
	@Override
	public void run() {
		while(true){
			synchronized(queue){
				if(queue.size() > 0){	// if is there a person or more in the queue
					synchronized(wc){
						if(wc.size() == 0){	// if there is no one in the bathroom
							Action action = new Action(wc, queue.poll());	// create a thread that will put head of the queue in the bathroom
							action.start();
						}
						else if(wc.size() > 0 && wc.size() < limite && queue.peek().getGender() == wc.get(0).getGender()){
							// if there is some one in the bathroom, but still remain slots and the gender of the people in the bathroom is the same of the gender of the head of queue
							Action action = new Action(wc, queue.poll());	// create a thread that will put head of the queue in the bathroom
							action.start();
						}
					}
				}
			}
			
			try {
				Thread.sleep(250);	// wait a bit before show the current scenario in console
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			synchronized(queue){
					synchronized(wc){
						print();	// showing the current scenario
					}
			}	
		}
	}
	
	public static void main(String[] args) {  
		int NUMERO_DE_PESSOAS = 16;				// number of people
		int NUMERO_DE_VAGAS_NO_BANHEIRO = 7; 	// number of slots in the bathroom
		
        WC wc = new WC(NUMERO_DE_VAGAS_NO_BANHEIRO);        
        Person[] person = new Person[NUMERO_DE_PESSOAS];
        for(int i = 0; i < NUMERO_DE_PESSOAS; i++){	// creating people, alternating between men and women
        	person[i] = i % 2 == 0 ?
        				new Person(true, wc, "M"+(i/2)) :
        				new Person(false, wc, "W"+((i/2)+1));
        }
        wc.start();
        for(int i = 0; i < NUMERO_DE_PESSOAS; i++){
        	person[i].start();
        }
    }
}