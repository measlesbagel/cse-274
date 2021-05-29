
public class Word implements Comparable<Word>{
	private String word;
	private int count;
	
	public Word(String word, int count) {
		this.word = word;
		this.count = count;
	}
	
	public int getCount() {
		return count;
	}
	
	public Word increment() {
		return new Word(this.word, this.count+1);
	}
	
	public String getWord() {
		return word;
	}

	@Override
	public int compareTo(Word o) {
		int compare = count > o.count ? 1 : count < o.count ? -1 : 0;
		return compare;
	}

	
	@Override
	public String toString() {
		return String.format("%-15s %10d", word, count);
	}
	
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof Word)) return false;
		
		Word w = (Word)o;
		return w.word.equalsIgnoreCase(this.word);
	}
}
