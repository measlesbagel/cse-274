/**
 * An implementation of the bag interface that 
 * uses linked nodes to handle and manage the data being held in the bag
 * @author Myles Cagle
 * @date March 1, 2020
 */

public class LinkedBag implements BagInterface{
	Node head;
	int size;
	
	public LinkedBag() {
		head = null;
		size = 0;
	}
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		boolean result = false;
		
		if(size == 0) {
			result = true;
		}
		
		return result;
	}

	@Override
	public boolean add(String newEntry) {
		boolean result = true; 
		
		Node temp = new Node(newEntry);
		temp.next = head;
		head = temp;
		size++;
		
		return result;
	}

	@Override
	public String remove() {
		String result = head.data;
		head = head.next;
		size--;
		
		return result;
	}

	@Override
	public boolean remove(String anEntry) {
		Node temp = head;
		boolean result = false;
		
		while(temp != null) {
			if(temp.data.equals(anEntry)) {
				temp.data = head.data;
				head = head.next;
				size--;
				result = true;
				break;
			}
			
			temp = temp.next;
		}
		
		return result;
	}

	@Override
	public void clear() {
		head = null;
		size = 0;
	}

	@Override
	public int getFrequencyOf(String anEntry) {
		int result = 0;
		Node temp = head;
		
		while(temp != null) {
			if(temp.data.equals(anEntry)) {
				result++;
			}
			
			temp = temp.next;
		}
		
		return result;
	}

	@Override
	public boolean contains(String anEntry) {
		Node temp = head;
		boolean result = false;
		
		while(temp != null) {
			if(temp.data.equals(anEntry)) {
				result = true;
				break;
			}
			
			temp = temp.next;
		}
		
		return result;
	}

	@Override
	public String[] toArray() {
		String[] result = new String[size];
		Node temp = head;
		
		for(int i = 0; i < size; i++) {
			result[i] = temp.data;
			temp = temp.next;
		}
		
		return result;
	}

	@Override
	public void removeDuplicates() {
		Node temp = head;
		
		while(temp != null) {
			if(getFrequencyOf(temp.data) >= 2) {
				remove(temp.data);
			}
			
			temp = temp.next;
		}
	}

	@Override
	public boolean containsAll(BagInterface aBag) {
		boolean result = true;
		String[] testArray = aBag.toArray();
		
		for(String w : testArray) {
			if(!this.contains(w)) {
				result = false;
				break;
			}
		}
		
		return result;
	}

	@Override
	public boolean sameItems(BagInterface aBag) {
		boolean result = false;
		
		if(this.containsAll(aBag)) {
			if(aBag.containsAll(this)) {
				if(this.size() == aBag.size()) {
					result = true;
				}
			}
		}
		
		return result;
	}
	
	private class Node {
		String data;
		Node next;
		
		private Node(String data) {
			this.data = data;
			this.next = null;
		}
	}

}
