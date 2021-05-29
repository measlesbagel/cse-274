
/**
 * A linked-node implementation of a list.
 * From the client's perspective, this works exactly the
 * same as an array-based list. The only difference with this
 * implementation is that there is no maximum size to the list.
 *
 */


public class WordList {
	private Node head;
	private int size;

	/**
	 * Constructs a new empty word list.
	 */
	public WordList() {
		head = null;
		size = 0;
	}
	
	/**
	 * Returns the number of words in this list.
	 * @return the number of words in this list
	 */
	public int size() {
		return size;
	}

	/**
	 * Adds a specified string to this word list
	 * 
	 * @param s string to be added
	 * @return true because the add is always going to be successful
	 */
	public boolean add(String s) {
		Node temp = new Node(s);
		temp.next = head;
		head = temp;
		
		size++;
		return true;
	}

	/**
	 * Adds a specified string to this word list at a specified index. The word may
	 * be added to the end of the list (when the index is the size of the list) or
	 * anywhere earlier (in which case, words will be shifted down to make room for
	 * the added word)
	 * 
	 * @param s string to be added
	 * @param index the index where the word is to be added (any value from 0 up to
	 *              the size of the list).
	 * @param s     the word to be added
	 * @return true because the add is always going to be successful
	 * @throws IndexOutOfBoundsException if index < 0 or index > size
	 */
	public boolean add(int index, String s) {
		if(index > size || index < 0) throw new IndexOutOfBoundsException("Node cannot be added at index " + index);
		
		Node temp = new Node(s);
		temp.next = head;
		head = temp;
		Node curr = head;
		
		for(int i = 0; i < size - index; i++) {
			String hold = curr.next.data;
			curr.next.data = curr.data;
			curr.data = hold;
			curr = curr.next;
		}
		
		size++;
		return true;
	}

	/**
	 * Removes an element from a particular location. If the location is valid,
	 * return the word that is removed. If the location is not valid, throw an
	 * OutOfBoundsException.
	 * @param index the index of the word to be removed
	 * @return the word at the specified index
	 * @throws IndexOutOfBoundsException if < 0 or index >= size
	 */
	public String remove(int index) {
		Node curr = head, prev = curr;
		String result = null;
		
		if(size > 0) {
			result = curr.data;
		}
		
		if(index < 0 || index >= size) throw new IndexOutOfBoundsException("Node does not exist at index " + index);
		
		for(int i = 0; i < size - index - 1; i++) {
			prev = curr;
			curr = curr.next;
			
			result = curr.data;
		}
		
		if(index == size - 1) {
			head = curr.next;
		} else {
			prev.next = curr.next;
		}

		size--;
		return result;
	}

	/**
	 * Removes all words from the list.
	 */
	public void clear() {
		head = new Node(null);
		size = 0;
	}

	/**
	 * Returns true if the specified word is in this list, and false otherwise.
	 * 
	 * @param s the word to look for
	 * @return true if the word is in this list, and false otherwise
	 */
	public boolean contains(String s) {
		Node temp = head;
		boolean result = false;
		
		for(int i = 0; i < size; i++) {
			if(s.equals(temp.data)) {
				result = true;
			}
			
			temp = temp.next;
		}
		
		return result;
	}

	/**
	 * Removes the first occurrence of a particular string, if it exists in the
	 * list.
	 * 
	 * @param s the string to be removed
	 * @return true if the string was removed, and false otherwise
	 */
	public boolean remove(String s) {
		boolean result = false;
		
		if(contains(s)) {
			remove(indexOf(s));
			result = true;
		}
		
		return result;
	}

	/**
	 * Returns the string at a particular index, if the index is valid
	 * 
	 * @param index The location of word to be removed. Valid values are 0 through 1
	 *              less than the size of the list.
	 * @return the string at the specified location
	 * @throws IndexOutOfBoundsException if index < 0 or index >= size
	 */
	public String get(int index) {
		Node temp = head;
		
		if(index < 0 || index >= size) throw new IndexOutOfBoundsException("Node does not exist at index " + index);
		
		for(int i = 0; i < size - index - 1 ; i++) {
			temp = temp.next;
		}
		
		return temp.data;
	}
	
	/*
	 * Returns the index of the first occurrence of the specified value,
	 * or -1 if the value is not in the list.
	 */
	public int indexOf(String value) {
		int result = -1;
		int index = 0;
		Node temp = head;
		
		for(int i = 0; i < size; i++) {
			if(temp.data.equals(value)) {
				result = size - i - 1;
			}
			
			temp = temp.next;
		}
		
		return result;
	}

	/**
	 * Sets a particular pre-existing location to a new word, and returns
	 * the old word that was in that location
	 * 
	 * @param index The location of word to be updated. Valid values are 0 through 1
	 *              less than the size of the list.
	 * @param s     the new value
	 * @return the old string that was just replaced
	 * @throws IndexOutOfBoundsException if index < 0 or index >= size
	 */
	public String set(int index, String s) {
		Node temp = head;
		String result = null;
		
		if(size > 0) {
			result = temp.data;
		}
		
		if(index < 0 || index >= size) throw new IndexOutOfBoundsException("Node does not exist at index " + index);
		
		for(int i = 0; i < size - index - 1 ; i++) {
			result = temp.data;
			temp = temp.next;
		}
		
		temp.data = s;
		
		return result;
	}

	/**
	 * Returns an array of the words in the list, in the order that the client
	 * would expect.
	 * 
	 * @return an array of all the words in the list 
	 */
	public String[] toArray() {
		String[] result = new String[size];
		
		for(int i = 0; i < size; i++) {
			result[i] = get(i);
		}
		
		return result;
	}
	
	private class Node {
		private String data;
		private Node next;

		// Constructs a new node with the specified data
		private Node(String data) {
			this.data = data;
			this.next = null;
		}
	}

}
