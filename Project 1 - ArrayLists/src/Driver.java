import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class Driver {
	static Scanner fin;
	static File[] files;
	static ArrayList<String> stopList = new ArrayList<>();
	static ArrayList<String> countList = new ArrayList<>();
	static ArrayList<Word> wordList;
	static ArrayList<ArrayList<Word>> fileList = new ArrayList<>();


	public static void main(String[] args) throws FileNotFoundException {
		fin = new Scanner(System.in);
		
		System.out.print("Enter the path to the folder: ");
		String data = fin.next();
		System.out.println();
		fin.close();
		
		long start = System.currentTimeMillis();
		
		loadStopList("stoplist.txt");
		loadWordList("words.txt");
		loadData(data);
		printReport();
		
		long stop = System.currentTimeMillis();
		long tmr = stop - start;
		System.out.println("\nRuntime = " + tmr + " Millseconds");
	}

	private static void printReport() {
		countUnique();
		top5Words();
		countWordList();

	}
	
	private static void countWordList() {
		System.out.println("\nThe number of occurrences of each word in the file (words.txt) in each file");
		
		for(String s : countList) {
			System.out.printf("%s\n", s);
			System.out.println("\tFile       Frequency");
			System.out.println("\t=========  =========");
			
			for(int i = 0; i < files.length; i++) {
				
				ArrayList<Word> temp = fileList.get(i);
				
				if(temp.contains(new Word(s, 1)))
					System.out.printf("\t%-9s  %9d\n", files[i].getName(), temp.get(temp.indexOf(new Word(s, 1))).getCount());
				else 
					System.out.printf("\t%-9s  %9d\n", files[i].getName(), 0);
			}
		}
	}

	private static void top5Words() {
		System.out.println("\nThe top 5 most common words in each file: ");
		
		for(int i = 0; i < files.length; i++) {
			Word[] temp = fileList.get(i).toArray(new Word[0]);
			Arrays.sort(temp);
			
			System.out.printf("%-10s\n", files[i].getName());   
			System.out.println("\tRank  Word             Frequency");
			System.out.println("\t====  ====             =========");
			
			for(int x = 1; x < 6; x++) {
				System.out.printf("\t %-4d %-15s %10d\n", x, 
													temp[temp.length-x].getWord(), 
													temp[temp.length-x].getCount());
			}
		}
	}

	private static void countUnique() {
		System.out.println("The number of unique words in each file: ");
		System.out.println("File        Num of Unique Words");
		System.out.println("==========  ===================");
		
		for(int i = 0; i < files.length; i++) {
			System.out.printf("%-10s %20d\n", files[i].getName(), fileList.get(i).size());
		}
		
	}

	private static void loadWordList(String words) throws FileNotFoundException {
		fin = new Scanner(new File(words));
		while(fin.hasNext()) {
			countList.add(fin.next());
		}
		
		fin.close();
	}

	private static void loadStopList(String stop) throws FileNotFoundException {
		fin = new Scanner(new File(stop));
		while(fin.hasNext()) {
			stopList.add(fin.next());
		}
		
		fin.close();
	}

	private static void loadData(String data) {
		File folder = new File(data);
        files = folder.listFiles();
        Arrays.sort(files);

        for (File f : files) {
            try {
            	wordList = new ArrayList<>();

				fin = new Scanner(f);
				while(fin.hasNext()) {
					Word str = new Word(fin.next().toLowerCase(), 1);
					if(stopList.contains(str.getWord())) {
						continue;
					} else if(!wordList.contains(str)) {
						wordList.add(str);
					} else {
						Word w = new Word(str.getWord(), wordList.get(wordList.indexOf(str)).getCount()+1);
						wordList.set(wordList.indexOf(str), w);
					}
				}
				
				fileList.add(wordList);
				fin.close();
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}           
        }     
	}
}
