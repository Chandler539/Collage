package transit;

import java.util.ArrayList;

/**
 * This class contains methods which perform various operations on a layered linked
 * list to simulate transit
 * 
 * @author Ishaan Ivaturi
 * @author Prince Rawal
 * Chandler Chen 203000379
 * 
 */
public class Transit {
	private TNode trainZero; // a reference to the zero node in the train layer

	/* 
	 * Default constructor used by the driver and Autolab. 
	 * DO NOT use in your code.
	 * DO NOT remove from this file
	 */ 
	public Transit() { trainZero = null; }

	/* 
	 * Default constructor used by the driver and Autolab. 
	 * DO NOT use in your code.
	 * DO NOT remove from this file
	 */
	public Transit(TNode tz) { trainZero = tz; }
	
	/*
	 * Getter method for trainZero
	 *
	 * DO NOT remove from this file.
	 */
	public TNode getTrainZero () {
		return trainZero;
	}

	/**
	 * Makes a layered linked list representing the given arrays of train stations, bus
	 * stops, and walking locations. Each layer begins with a location of 0, even though
	 * the arrays don't contain the value 0. Store the zero node in the train layer in
	 * the instance variable trainZero.
	 * 
	 * @param trainStations Int array listing all the train stations
	 * @param busStops Int array listing all the bus stops
	 * @param locations Int array listing all the walking locations (always increments by 1)
	 */
	public void makeList(int[] trainStations, int[] busStops, int[] locations) {

	    // UPDATE THIS METHOD
	
			TNode walk = new TNode();
			trainZero = new TNode();
	
			
			TNode temp;
			TNode bus = new TNode();
	
			
	
			TNode trainZeroCopy = trainZero;
			
	
			trainZero.setDown(bus);
	
			bus.setDown(walk);
	
			TNode thing1 = trainZero.getDown().getDown();
			TNode temp1 = thing1;
	
			for (int i = 0; i < locations.length; i++){
				temp = new TNode(locations[i]);
				walk.setNext(temp);
				walk = temp;
			}
	
			for (int i = 0; i < busStops.length; i++){
				temp = new TNode(busStops[i]);
				bus.setNext(temp);
				bus = temp;
	
				for (int r = 0; r <= locations.length; r++){
					if (thing1.getLocation() == temp.getLocation()){
						bus.setDown(thing1);
						break;
					}
	
					if (thing1.getNext() != null){
					temp1 = thing1.getNext();
					thing1 = temp1;
					}
				
	
				}
			}
	
			TNode thing2 = trainZero.getDown();
			
			for (int i = 0; i < trainStations.length; i++){
				temp = new TNode(trainStations[i]);
				trainZeroCopy.setNext(temp);
				trainZeroCopy = temp;
	
				for (int r = 0; r <= busStops.length; r++){
					if (thing2.getLocation() == temp.getLocation()){
						trainZeroCopy.setDown(thing2);
						break;
					}
	
					if (thing2.getNext() != null){
					temp1 = thing2.getNext();
					thing2 = temp1;
					}
				
	
				}
			}
	
	
		}
		
		/**
		 * Modifies the layered list to remove the given train station but NOT its associated
		 * bus stop or walking location. Do nothing if the train station doesn't exist
		 * 
		 * @param station The location of the train station to remove
		 */
		public void removeTrainStation(int station) {
			// UPDATE THIS METHOD
			for (TNode p = trainZero; p != null; p = p.getNext()){
				if (p.getNext() != null){

					if (p.getNext().getLocation() == station){
						p.setNext(p.getNext().getNext());

						break;
					}
				}
			}
		}
	
		/**
		 * Modifies the layered list to add a new bus stop at the specified location. Do nothing
		 * if there is no corresponding walking location.
		 * 
		 * @param busStop The location of the bus stop to add
		 */
		public void addBusStop(int busStop) {
			// UPDATE THIS METHOD
	
			boolean exist = false;
			TNode newNode = new TNode(busStop);
			TNode wNode = new TNode();
			
	
			for (TNode p = trainZero.getDown().getDown(); p != null; p = p.getNext()){
			
				if (p.getLocation() == busStop){
					exist = true;
					wNode = p;
					break;
				}
	
				if (p.getNext() == null) break;
				
			}
	
			if (exist){
			for (TNode owo = trainZero.getDown(); owo.getLocation() <= busStop; owo = owo.getNext()){
	
				if (owo.getNext() == null){
					
					owo.setNext(newNode);
					newNode.setDown(wNode);
					break;
				}
	
				if ((owo.getLocation() < busStop) && (owo.getNext().getLocation() > busStop)){
					newNode.setNext(owo.getNext());
					owo.setNext(newNode);
					newNode.setDown(wNode);
					break;
			
				}
	
	
	
				
	
				}
			}
	
		}
	
	
		
		/**
		 * Determines the optimal path to get to a given destination in the walking layer, and 
		 * collects all the nodes which are visited in this path into an arraylist. 
		 * 
		 * @param destination An int representing the destination
		 * @return
		 */
		public ArrayList<TNode> bestPath(int destination) {
	
			// UPDATE THIS METHOD

			ArrayList<TNode> owoCollection = new ArrayList<TNode>();
	
			for (TNode owo = trainZero; owo != null; owo = owo.getNext()){
				
				owoCollection.add(owo);
				if (owo.getNext() != null){
					if (owo.getNext().getLocation() > destination){
						break;
					}
	
				}
	
				if (owo.getNext() == null) break;
				
			}
		
			for (TNode owo = trainZero.getDown(); owo != null; owo = owo.getNext()){
	
				if (owoCollection.get(owoCollection.size() - 1).getLocation() <= owo.getLocation()){
					owoCollection.add(owo);
				}
	
				if (owo.getNext() != null){
					if (owo.getNext().getLocation() > destination){
						break;
					}
	
				}
	
				if (owo.getNext() == null)
					break;
				
			}
	
			for (TNode owo = trainZero.getDown().getDown(); owo != null; owo = owo.getNext()){
	
				if (owoCollection.get(owoCollection.size() - 1).getLocation() <= owo.getLocation()){
					owoCollection.add(owo);
				}
	
				if (owo.getNext() != null){
					if (owo.getNext().getLocation() > destination){
						break;
					}
	
				}
	
				if (owo.getNext() == null)
					break;
			
			}
	
	
			return owoCollection;
		}
	
		/**
		 * Returns a deep copy of the given layered list, which contains exactly the same
		 * locations and connections, but every node is a NEW node.
		 * 
		 * @return A reference to the train zero node of a deep copy
		 */
		public TNode duplicate() {
	
			// UPDATE THIS METHOD
			TNode newTrainZero = new TNode();
			TNode trainReference = newTrainZero;
	
			
	
			TNode newWalk = new TNode();
			TNode newBus = new TNode();
	

			newBus.setDown(newWalk);
			newTrainZero.setDown(newBus);
		
	
			
	
			TNode ynode = new TNode();
	
			for (TNode walk = trainZero.getDown().getDown().getNext(); walk != null; walk = walk.getNext()){
				ynode = new TNode(walk.getLocation());
				newWalk.setNext(ynode);
				newWalk = ynode;
			}
	
			for (TNode bus = trainZero.getDown().getNext(); bus != null; bus = bus.getNext()){
				ynode = new TNode(bus.getLocation());
				newBus.setNext(ynode);
				newBus = ynode;
	
				for (TNode buss = newTrainZero.getDown().getDown(); buss != null; buss = buss.getNext()){
	
	
					if (buss.getLocation() == newBus.getLocation()){
						newBus.setDown(buss);
					}
	
					if (buss.getNext() == null)
						break;
					
	
	
				}
				
			}
	
	
			for (TNode train = trainZero.getNext(); train != null; train = train.getNext()){
				ynode = new TNode(train.getLocation());
				newTrainZero.setNext(ynode);
				newTrainZero = ynode;
	
				for (TNode trainn = trainReference.getDown(); trainn != null; trainn = trainn.getNext()){
	
	
					if (trainn.getLocation() == newTrainZero.getLocation()){
						newTrainZero.setDown(trainn);
					}
	
					if (trainn.getNext() == null)
						break;
					
	
	
				}
			}
	
	
			return trainReference;
		}
	
		/**
		 * Modifies the given layered list to add a scooter layer in between the bus and
		 * walking layer.
		 * 
		 * @param scooterStops An int array representing where the scooter stops are located
		 */
		public void addScooter(int[] scooterStops) {
	
			// UPDATE THIS METHOD
			TNode bus = new TNode();
			TNode scooter = new TNode();
			TNode ynode = new TNode();
	
			TNode walk = new TNode();
			
			bus = trainZero.getDown();
			walk = bus.getDown();
	
			scooter.setDown(walk);
			bus.setDown(scooter);
	
			for (int i = 0; i < scooterStops.length; i++){
				ynode = new TNode(scooterStops[i]);
				scooter.setNext(ynode);
				scooter = ynode;
			}
	
			for (TNode buss = trainZero.getDown(); buss != null; buss = buss.getNext()){
	
				for (TNode scoot = trainZero.getDown().getDown(); scoot != null; scoot = scoot.getNext()){
	
					if (buss.getLocation() == scoot.getLocation()){
						buss.setDown(scoot);
					}
	
					
				}
	
				if (buss.getNext() == null)
					break;
				
			}
	
			for (TNode scoot = trainZero.getDown().getDown(); scoot != null; scoot = scoot.getNext()){
	
				for (TNode walker = trainZero.getDown().getDown().getDown(); walker != null; walker = walker.getNext()){
					if (scoot.getLocation() == walker.getLocation()){
						scoot.setDown(walker);
					}
	
					
				}
	
				if (scoot.getNext() == null)
					break;
				
			}
	
	
	
		}
	/**
	 * Used by the driver to display the layered linked list. 
	 * DO NOT edit.
	 */
	public void printList() {
		// Traverse the starts of the layers, then the layers within
		for (TNode vertPtr = trainZero; vertPtr != null; vertPtr = vertPtr.getDown()) {
			for (TNode horizPtr = vertPtr; horizPtr != null; horizPtr = horizPtr.getNext()) {
				// Output the location, then prepare for the arrow to the next
				StdOut.print(horizPtr.getLocation());
				if (horizPtr.getNext() == null) break;
				
				// Spacing is determined by the numbers in the walking layer
				for (int i = horizPtr.getLocation()+1; i < horizPtr.getNext().getLocation(); i++) {
					StdOut.print("--");
					int numLen = String.valueOf(i).length();
					for (int j = 0; j < numLen; j++) StdOut.print("-");
				}
				StdOut.print("->");
			}

			// Prepare for vertical lines
			if (vertPtr.getDown() == null) break;
			StdOut.println();
			
			TNode downPtr = vertPtr.getDown();
			// Reset horizPtr, and output a | under each number
			for (TNode horizPtr = vertPtr; horizPtr != null; horizPtr = horizPtr.getNext()) {
				while (downPtr.getLocation() < horizPtr.getLocation()) downPtr = downPtr.getNext();
				if (downPtr.getLocation() == horizPtr.getLocation() && horizPtr.getDown() == downPtr) StdOut.print("|");
				else StdOut.print(" ");
				int numLen = String.valueOf(horizPtr.getLocation()).length();
				for (int j = 0; j < numLen-1; j++) StdOut.print(" ");
				
				if (horizPtr.getNext() == null) break;
				
				for (int i = horizPtr.getLocation()+1; i <= horizPtr.getNext().getLocation(); i++) {
					StdOut.print("  ");

					if (i != horizPtr.getNext().getLocation()) {
						numLen = String.valueOf(i).length();
						for (int j = 0; j < numLen; j++) StdOut.print(" ");
					}
				}
			}
			StdOut.println();
		}
		StdOut.println();
	}
	
	/**
	 * Used by the driver to display best path. 
	 * DO NOT edit.
	 */
	public void printBestPath(int destination) {
		ArrayList<TNode> path = bestPath(destination);
		for (TNode vertPtr = trainZero; vertPtr != null; vertPtr = vertPtr.getDown()) {
			for (TNode horizPtr = vertPtr; horizPtr != null; horizPtr = horizPtr.getNext()) {
				// ONLY print the number if this node is in the path, otherwise spaces
				if (path.contains(horizPtr)) StdOut.print(horizPtr.getLocation());
				else {
					int numLen = String.valueOf(horizPtr.getLocation()).length();
					for (int i = 0; i < numLen; i++) StdOut.print(" ");
				}
				if (horizPtr.getNext() == null) break;
				
				// ONLY print the edge if both ends are in the path, otherwise spaces
				String separator = (path.contains(horizPtr) && path.contains(horizPtr.getNext())) ? ">" : " ";
				for (int i = horizPtr.getLocation()+1; i < horizPtr.getNext().getLocation(); i++) {
					StdOut.print(separator + separator);
					
					int numLen = String.valueOf(i).length();
					for (int j = 0; j < numLen; j++) StdOut.print(separator);
				}

				StdOut.print(separator + separator);
			}
			
			if (vertPtr.getDown() == null) break;
			StdOut.println();

			for (TNode horizPtr = vertPtr; horizPtr != null; horizPtr = horizPtr.getNext()) {
				// ONLY print the vertical edge if both ends are in the path, otherwise space
				StdOut.print((path.contains(horizPtr) && path.contains(horizPtr.getDown())) ? "V" : " ");
				int numLen = String.valueOf(horizPtr.getLocation()).length();
				for (int j = 0; j < numLen-1; j++) StdOut.print(" ");
				
				if (horizPtr.getNext() == null) break;
				
				for (int i = horizPtr.getLocation()+1; i <= horizPtr.getNext().getLocation(); i++) {
					StdOut.print("  ");

					if (i != horizPtr.getNext().getLocation()) {
						numLen = String.valueOf(i).length();
						for (int j = 0; j < numLen; j++) StdOut.print(" ");
					}
				}
			}
			StdOut.println();
		}
		StdOut.println();
	}
}
