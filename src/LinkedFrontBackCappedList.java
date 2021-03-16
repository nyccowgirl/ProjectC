/**
 * A class that implements the ADT list with front or back entry or removal and capped to a given capacity,
 * using linked nodes.
 *
 * @author Trang Hoang (sect. 933)
 * @author Jared Roussel (sect. 933)
 * @author Brent Gannetta (sect. 932)
 * @version 1.4
 */

import java.util.*;

public class LinkedFrontBackCappedList<T> implements FrontBackCappedListInterface<T> {

	private Node head, tail;
	private int numberOfEntries;
	private final int capacity;
	private boolean initialized = false;


	/**
	 * Creates and initializes an empty list.
	 *
	 * Precondition: Capacity is not negative.
	 *
	 * @param capacity Maximum size of list
	 */
	public LinkedFrontBackCappedList(int capacity) {
		if (capacity < 0) {
			throw new IllegalArgumentException("Capacity cannot be negative.");
		}

		initializeDataFields();
		this.capacity = capacity;
		initialized = true;
	}


	/**
	 * Adds an entry to the beginning of the list if it is not full. If the list is empty, the head
	 * becomes the tail. If the entry is successfully added, entries currently in the list are shifted
	 * back, and the list size is increased by 1.
	 *
	 * @param newEntry The object to be added as a new entry.
	 * @return True if the entry was added; otherwise, returns false if the list is full.
	 */
	@Override
	public boolean addFront(T newEntry) {
		checkInitialization();
		Node newNode = new Node(newEntry);
		boolean result = true;

		if (isFull()) {
			result = false;
		} else if (isEmpty()) {				// Assertion: result is true
			head = newNode;
			tail = head;					// First node to an empty list becomes the tail
			numberOfEntries++;
		} else {
			newNode.setNextNode(head);
			head = newNode;
			numberOfEntries++;
		}
		return result;
	}


	/**
	 * Adds an entry to the end of the list if it is not full. If the list is empty, the tail becomes
	 * the head. If the entry is successfully added, entries currently in the list are shifted forward,
	 * and the list size is increased by 1.
	 *
	 * @param newEntry The object to be added as a new entry.
	 * @return True if the entry was added; otherwise, returns false if the list is full.
	 */
	@Override
	public boolean addBack(T newEntry) {
		checkInitialization();
		Node newNode = new Node(newEntry);
		boolean result = true;

		if (isFull()) {
			result = false;
		} else if (isEmpty()) {				// Assertion: result is true
			tail = newNode;
			head = tail;					// First node to an empty list becomes the head
			numberOfEntries++;
		} else {
			tail.setNextNode(newNode);
			tail = newNode;					// New node becomes the tail
			numberOfEntries++;
		}

		return result;
	}


	/**
	 * Removes an entry from the beginning of the list if the list is not empty. The remaining entries
	 * are shifted forwards, and the list size is decreased by 1.
	 *
	 * @return A reference to the removed entry or null if the list is empty
	 */
	@Override
	public T removeFront() {
		checkInitialization();
		T result = null;

		if (!(isEmpty())) {
			result = head.getData();
			head = head.getNextNode();
			numberOfEntries--;
		}

		return result;
	}


	/**
	 * Removes an entry from the end of the list if the list is not empty. The rest of the list is not
	 * impacted, and the list is decreased by 1.
	 *
	 * @return A reference to the removed entry or null if the list is empty
	 */
	@Override
	public T removeBack() {
		checkInitialization();
		T result = null;

		if (!(isEmpty())) {
			result = tail.getData();

			if (numberOfEntries == 1) {
				initializeDataFields();
			} else {
				tail = getNodeAt(numberOfEntries - 2);
				tail.setNextNode(null);
				numberOfEntries--;

			}
		}

		return result;
	}


	/**
	 * Clears the list to an empty list.
	 */
	@Override
	public void clear() {
		initializeDataFields();
	}


	/**
	 * Retrieves the entry at a certain position in the list, after determining if the position is valid.
	 *
	 * @param givenPosition An integer that indicates the position of the desired entry
	 * @return A reference to the indicated entry or null if the index is out of bounds
	 */
	@Override
	public T getEntry(int givenPosition) {
		checkInitialization();
		T result = null;

		if (validPosition(givenPosition)) { // Assertion: list is not empty
			result = getNodeAt(givenPosition).getData();
		}
		return result;
	}


	/**
	 * Determines the position in the list of a given entry. If the entry appears more than once, the first index
	 * is returned.
	 *
	 * @param anEntry The object to search for in the list
	 * @return The first position that the entry was found or -1 if the entry is not found
	 */
	@Override
	public int indexOf(T anEntry) {
		checkInitialization();
		int position = -1;
		// ADD CODE FOR ITERATIVE VERSION

//		return position;

		// Recursive version
		return indexOf(anEntry, position, 0, head);
	}


	/**
	 * Determines the position in the list of a given entry. If the entry appears more than once, the last index
	 * is returned.
	 *
	 * @param anEntry The object to search for in the list
	 * @return The last position that the entry was found or -1 if the entry is not found
	 */
	@Override
	public int lastIndexOf(T anEntry) {
		checkInitialization();
		int position = -1;
		// ADD CODE FOR ITERATIVE VERSION

		// Recursive version
		position = lastIndexOf(anEntry, position, numberOfEntries - 1, tail);
		return position;
	}


	/**
	 * Determines whether an entry is in the list.
	 *
	 * @param anEntry The object to search for in the list
	 * @return True if the entry is in the list; otherwise, false if list is empty or entry is not found
	 */
	@Override
	public boolean contains(T anEntry) {
		checkInitialization();
//		boolean result = false;
		// ADD CODE FOR ITERATIVE VERSION
//		return result;

		// Recursive version
//		return contains(anEntry, head);


		// Utilize indexOf(anEntry)
		return (indexOf(anEntry) >= 0);
	}


	/**
	 * Retrieves length of the list.
	 *
	 * @return Integer number of entries currently in the list.
	 */
	@Override
	public int size() {
		return numberOfEntries;
	}


	/**
	 * Determines if the list is empty.
	 *
	 * @return True if the list is empty; otherwise, returns false if the list contains one or more entries.
	 */
	@Override
	public boolean isEmpty() {
		return (numberOfEntries == 0);
	}


	/**
	 * Determines if the list is full.
	 *
	 * @return True if the list is full; otherwise, returns false if the list contains entries less than maximum size.
	 */
	@Override
	public boolean isFull() {
		return (numberOfEntries >= capacity);
	}


	/**
	 * Retrieves the entries in the list, number of entries, and capacity of the list. If the list is not empty, the
	 * entries in the head and tail nodes are retrieved.
	 *
	 * @return String representation of the list, with number of elements, capacity of the list, and entries in head
	 * tail nodes, if applicable
	 */
	public String toString() {
		if (isEmpty()) {
			return Arrays.toString(toArray()) + "\tsize=" + numberOfEntries + "\tcapacity=" + capacity;
		} else {
			return Arrays.toString(toArray()) + "\tsize=" + numberOfEntries + "\tcapacity=" + capacity +
					"\thead=" + head.getData() + " tail=" + tail.getData();
		}
	}


	/*
	****************
	HELPER METHODS:
	****************
	 */

	/**
	 * Initializes the class's data fields to indicate an empty list.
	 */
	private void initializeDataFields() {
		head = null;
		tail = null;
		numberOfEntries = 0;
	}


	/**
	 * Checks if list is properly initialized.
	 *
	 * @throws SecurityException if this object is not initialized
	 */
	private void checkInitialization() {
		if (!initialized) {
			throw new SecurityException("LinkedFrontBackCappedList object is not initialized properly.");
		}
	}


	/**
	 * Determines if given position is a valid position within the bounds of the list.
	 *
	 * @param position The position in the list
	 * @return True if the position is valid; otherwise, returns false.
	 */
	private boolean validPosition(int position) {
		return position >= 0 && position < numberOfEntries;
	}


	/**
	 * Casts the list to an array containing the entries in the list.
	 *
	 * @return An array containing all the entries in the list
	 */
	private T[] toArray() {
		checkInitialization();

		// The cast is safe because the new list contains null entries
		@SuppressWarnings("unchecked")
		T[] result = (T[]) new Object[numberOfEntries]; // TO DO: change to Comparable[] for extra credit

		int index = 0;

		// Iterative version
//		Node current = head;
//		while ((index < numberOfEntries) && (current != null)) {
//			result[index] = current.getData();
//			current = current.getNextNode();
//			index++;
//		}

		// Recursive version
		toArray(result, index, head);

		return result;
	}

	// Helper method for recursive version of toArray() method
	private void toArray(T[] array, int index, Node current) {
		if (current != null) {				// Recursive case
			array[index] = current.getData();
			toArray(array, index + 1, current.getNextNode());
		}
	}


	/**
	 * Retrieves a reference to the node at a given position.
	 *
	 * Precondition: The list is not empty; 0 <= givenPosition <= numberOfEntries - 1.
	 *
	 * @param givenPosition The position in the list
	 * @return A reference to the node at the given position
	 */
	private Node getNodeAt(int givenPosition) {
		// Assertion: (head != null) && (0 <= givenPosition) && (givenPosition <= numberOfEntries - 1)

		// Traverse the list to locate the desired node (skipped if givenPosition is 0)
		// Iterative version
//		Node current = head;
//
//		for (int counter = 0; counter < givenPosition; counter++) {
//			current = current.getNextNode();
//		}
//
//		return current;

		// Recursive version
		return getNodeAt(0, givenPosition, head);
	}

	// Helper method for recursive version of getNodeAt(int givenPosition)
	private Node getNodeAt(int counter, int givenPosition, Node current) {
		if (counter == givenPosition) {
			return current;
		} else {
			return getNodeAt(counter + 1, givenPosition, current.getNextNode());
		}
	}


	// Helper method for recursive version of indexOf(T anEntry) method
	private int indexOf(T anEntry, int position, int index, Node current) {
		if (current == null) {
			return position;
		} else {
			// Option 1
			return (current.getData().equals(anEntry) ? index :
					indexOf(anEntry, position, index + 1, current.getNextNode()));

			// Option 2
//			if (current.getData().equals(anEntry)) {
//				return index;
//			} else {
//				return indexOf(anEntry, position, index + 1, current.getNextNode());
//			}
		}
	}


	// Helper method for recursive version of lastIndexOf(T anEntry) method
	private int lastIndexOf(T anEntry, int position, int index, Node current) {
		// TBD: To be refined...may have duplicate code
		if (current == null) {
			return position;
		} else {
			if (current.getData().equals(anEntry)) {
				return index;
			} else {
				if (index == 0) {
					return position;
				} else {
					return lastIndexOf(anEntry, position, index - 1, getNodeAt(index - 1));
				}
			}
		}
	}

	// Helper method for recursive version of contains(T anEntry) method
	private boolean contains(T anEntry, Node current) {
		if (current == null) {				// Base case
			return false;
		} else {
			return (current.getData().equals(anEntry) || contains(anEntry, current.getNextNode()));
		}
	}


	/*
	****************
	NODE CLASS:
	****************
	 */

	public class Node {
		public T data; 
		public Node next; 

		private Node(T dataPortion) {
			data = dataPortion;
			next = null;
		}

		private Node(T dataPortion, Node nextNode) {
			data = dataPortion;
			next = nextNode;
		}

		private T getData() {
			return data;
		}

		private void setData(T newData) {
			data = newData;
		}

		private Node getNextNode() {
			return next;
		}

		private void setNextNode(Node nextNode) {
			next = nextNode;
		} 
	} // end Node
} // end LinkedFrontBackCappedList
