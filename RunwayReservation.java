import java.util.Scanner;

public class RunwayReservation {
	private static int n;
	private static int k;
	public  Node root;// The root node of the tree
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
			Requests r = reqs[i];
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
					System.out.println("t in initial root = "+t);
					tree.insert(t, i);
				}
				//Now we try to insert valid flights 
				for (int j = 0; j < reqs.length; ++j) {
					int t2 = reqs[j].getTime();
					//Check to make sure they are within the allowed time range
					if (Math.abs(t2 - t) < k) {
						//System.out.println("Cannot make a reservation.");
					} else {
						//Make sure the node inserting is not already there
						for (tree.find(t); tree.find(t) != null; ++t) {
							if (tree.find(t2) == null) {
								tree.insert(t2, i);
								System.out.println("Made a reservation with t = "+t2);
							}
						}
					}
				}

			if (reqs[i].getCommand().equals("t") ) {
				System.out.println("Found a reservation cmd t");
				//System.out.println("Test value = "+test);
				//System.out.println("t is = "+t+" and time is = "+time);
			}
			
			//System.out.println("The 'n' lines is = "+i);
			}
		}
		tree.print();
	}
}
