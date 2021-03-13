/**
 * A class that implements the ADT list with front or back entry or removal and capped to a given capacity,
 * using linked nodes.
 *
 * Trang Hoang (sect. 933)
 * Jared Roussel (sect. 933)
 * Brent Gannetta (sect. 932)
 */

import java.util.*;

public class LinkedFrontBackCappedList<T> implements FrontBackCappedListInterface<T> {

	private Node head, tail;

	@Override
	public boolean addFront(T newEntry) {
		return false;
	}

	@Override
	public boolean addBack(T newEntry) {
		return false;
	}

	@Override
	public T removeFront() {
		return null;
	}

	@Override
	public T removeBack() {
		return null;
	}

	@Override
	public void clear() {

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

	@Override
	public int size() {
		return 0;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public boolean isFull() {
		return false;
	}

/*
***********
NODE CLASS:
***********
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
	} 
}
