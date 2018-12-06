import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MarkovRunner {

	public void runMarkovModel() throws FileNotFoundException {
		String fileName = "romeo.txt";
		File textFile = new File(fileName);
		Scanner sc = new Scanner(textFile);
		String st = sc.useDelimiter("\\A").next();
		int N = 12;
		MarkovModel markov = new MarkovModel(N);
		markov.setRandom(953);
		markov.setTraining(st);

		for (int k = 0; k < 3; k++) {
			String text = markov.getRandomText(500);
			printOut(text);
		}

		sc.close();
	}

	private void printOut(String s) {
		String[] words = s.split("\\s+");
		int psize = 0;
		System.out.println("----------------------------------");
		for (int k = 0; k < words.length; k++) {
			System.out.print(words[k] + " ");
			psize += words[k].length() + 1;
			if (psize > 60) {
				System.out.println();
				psize = 0;
			}
		}
		System.out.println("\n----------------------------------");
	}

}
