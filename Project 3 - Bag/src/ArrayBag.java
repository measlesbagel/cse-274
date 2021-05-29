/**
 * An implementation of the bag interface that 
 * uses arrays to handle and manage the data being held in the bag
 * @author Myles Cagle
 * @date March 1, 2020
 */

import java.util.Arrays;

public class ArrayBag implements BagInterface {
	int size;
	final static int DEFAULT_CAPACITY = 10;
	String[] bag;
	
	public ArrayBag() {
		this(DEFAULT_CAPACITY);
	}

	public ArrayBag(int capacity) {
		if(capacity < 5) throw new IllegalArgumentException("Capacity was set to " + capacity + ", it must be greater than or equal to 5.");
		size = 0;
		bag = new String[capacity];
	}

	@Override
	public int size() {

		return size;
	}

	@Override
	public boolean isEmpty() {
		boolean result = false;
		if(size == 0) result = true;
		
		return result;
	}

	@Override
	public boolean add(String newEntry) {
		boolean result = true;
		
		if(size == bag.length) {
			bag = Arrays.copyOf(bag, bag.length*2);
		}
		
		bag[size] = newEntry;
		
		size++;
		return result;
	}

	@Override
	public String remove() {
		String result = null;
		
		if(size <= bag.length/2 && bag.length/2 >= 5) {
			bag = Arrays.copyOf(bag, bag.length/2);
		}
		
		if(!isEmpty()) {
			result = bag[size - 1];
			bag[size - 1] = null;
			size--;
		}
		
		return result;
	}

	@Override
	public boolean remove(String anEntry) {
		boolean result = false;
		
		if(size < bag.length/2 && bag.length/2 >= 5) {
			bag = Arrays.copyOf(bag, bag.length/2);
		}
		
		for(int i = 0; i < size; i++) {
			if(bag[i].equals(anEntry)) {
				bag[i] = bag[size - 1];
				bag[size - 1] = null;
				size--;
				result = true;
				break;
			}
		}
		return result;
	}

	@Override
	public void clear() {
		for(int i = 0; i < size; i++) {
			bag[i] = null;
		}
		
		size = 0;
	}

	@Override
	public int getFrequencyOf(String anEntry) {
		int count = 0;

		for(int i = 0; i < size; i++) {
			if(bag[i].equals(anEntry)) {
				count++;
			}
		}
		
		return count;
	}

	@Override
	public boolean contains(String anEntry) {
		boolean result = false;
		
		for(int i = 0; i < size; i++) {
			if(bag[i].equals(anEntry)) {
				result = true;
				break;
			}
		}
		
		return result;
	}

	@Override
	public String[] toArray() {
		return Arrays.copyOf(bag, size);
	}

	@Override
	public void removeDuplicates() {
		for(int i = 0; i < size; i++) {
			if(getFrequencyOf(bag[i]) >= 2) {
				remove(bag[i]);
			}
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
	
	public int checkTrueSize() {
		return bag.length;
	}

}
