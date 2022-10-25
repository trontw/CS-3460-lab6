import java.util.Scanner;

public class RunwayReservation {
	private static int n;
	private static int k;
	public  Node root;// The root node of the tree
	public static int base_time = 0;
	//public static BST tree = new BST(); //BST for flights

	public static void main(String [] args) {
		Scanner kb = new Scanner(System.in);
		n = kb.nextInt(); // The total number of requests.
		k = kb.nextInt(); // Grace time between requests.

		// Variables for getting the input.
		String cmd;
		int time = 0;
		String flightname = null;
		String flightnumber = null;
		String source = null;
		String destination = null;
		int curtime = 0; // Current time, initialized to 0.

		/**  
		 * An array of requests. 
		 * This is the data stored outside of our binary search tree.
		**/
		Requests [] reqs = new Requests[n];
		int i = 0;

		/** 
		 *  Reading the input. 
		 * All requests are read from the input file and stored in array reqs.
		**/
		while(kb.hasNext()) {
			cmd = kb.next();

			if (cmd.equals("r")) {
				time = kb.nextInt();
				flightname = kb.next();
				flightnumber = kb.next();
				source = kb.next();
				destination = kb.next();

				reqs[i++] = new Requests(cmd, time, flightname, flightnumber, source, destination);
			}
			else {
				time = kb.nextInt();
				reqs[i++] = new Requests(cmd, time);
			}
			kb.nextLine();
		}

		/**
		 * TODO: Code to process each request and solve the 
		 * Runway Reservation problem.
		 * 
		 * Start by looping through the array, using the input 'n'
		 * **/
		//System.out.println("The number of lines is = "+n);
		BST tree = new BST();
		for (i = 0; i < reqs.length; ++i) {
			//Requests r = reqs[i];
			//System.out.println("Cmd = "+reqs[i].getCommand());
			if (reqs[i].getCommand().equals("r") ) {
				//System.out.println("Found a reservation cmd r");
				/**
				 * Now we check to see if it is a valid request
				 * We will call find from BST and check for the following conditions:
				 * 1) is this the first entry? - create root node
				 * 2) if not the first entry, is this request 'r'
				 *    first - k < r < first + k
				 */
				int t = reqs[i].getTime();
				// First entry becomes the root of the BST tree
				if (tree.root == null) {
					tree.insert(t, i);
				}
				// Try to insert valid flights using 'verify'
				// (Note: a recursive call is the only way to do this: thus verify)
				if (tree.verify(t, k))
					tree.insert(t, i);
				//tree.print();
			}
				
			if (reqs[i].getCommand().equals("t") ) {
				int t = reqs[i].getTime();
				//System.out.println("Found a reservation cmd t");
				//Update the current time
				time = t;
				time = base_time + time;
				base_time = time;
				System.out.println("Current time = "+time+" units.");
				Node smallest = tree.min();
				//System.out.println("Smallest time BEFORE WHILE = "+smallest.getTime());
				//Print all request from the Tree with times less than the current time.
				while (smallest.getTime() < time){
					//System.out.println("Smallest time INSIDE WHILE = "+smallest.getTime());
					System.out.println(reqs[smallest.getReq_index()].getAirline());			
					if (smallest != null) {
						Node output = tree.pred(tree.root, smallest.getTime());
						Node output2 = tree.succ(tree.root, smallest.getTime());
						//Remove all requests from the Tree with times less than the current time.
						tree.delete(smallest.getTime());
						smallest = tree.min();
						//System.out.println("Smallest AFTER DEL = "+smallest.getTime());
					} 					
				}										
				
			}
			
		}
		//Handle the last time, make it the last; print everything after it
		Node biggest = tree.max();
		System.out.println("Current time = "+biggest.getTime()+" units");
		Node smallest = null;
		while (tree.root != null){
			smallest = tree.min();
			System.out.println(reqs[smallest.getReq_index()].getAirline());	
			Node output = tree.pred(tree.root, smallest.getTime());
			Node output2 = tree.succ(tree.root, smallest.getTime());
			tree.delete(smallest.getTime());
		}
		//System.out.println(reqs[biggest.getReq_index()].getAirline());
		//tree.print();
	}
}
