/**
 * A class that implements the ADT list with front or back entry or removal and capped to a given capacity,
 * using linked nodes.
 *
 * @author Trang Hoang (sect. 933)
 * @author Jared Roussel (sect. 933)
 * @author Brent Gannetta (sect. 932)
 * @version 1.1
 */

import java.util.*;

public class LinkedFrontBackCappedList<T> implements FrontBackCappedListInterface<T> {

	private Node head, tail;
	private int numberOfEntries, capacity;
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

	@Override
	public T removeFront() {
		return null;
	}

	@Override
	public T removeBack() {
		return null;
	}


	/**
	 * Clears the list to an empty list.
	 */
	@Override
	public void clear() {
		initializeDataFields();
	}

	@Override
	public T getEntry(int givenPosition) {
		return null;
	}

	@Override
	public int indexOf(T anEntry) {
		return 0;
	}

	@Override
	public int lastIndexOf(T anEntry) {
		return 0;
	}

	@Override
	public boolean contains(T anEntry) {
		return false;
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
//		Node currentNode = head;
//		while ((index < numberOfEntries) && (currentNode != null)) {
//			result[index] = currentNode.getData();
//			currentNode = currentNode.getNextNode();
//			index++;
//		}

		// Recursive version
		toArrayHelper(result, index, head);

		return result;
	}

	// Helper method to recursive version of toArray() method
	private void toArrayHelper(T[] array, int index, Node firstNode) {
		if (firstNode != null) {				// Recursive case
			array[index] = firstNode.getData();
			toArrayHelper(array, index + 1, firstNode.getNextNode());
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
