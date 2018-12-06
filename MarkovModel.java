import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

public class MarkovModel {

	public static void main(String[] args) throws FileNotFoundException {
		MarkovRunner m = new MarkovRunner();
		m.runMarkovModel();
	}

	private String myText;
	private Random myRandom;
	private int charPassed;

	public MarkovModel(int N) {
		charPassed = N;
		myRandom = new Random();
	}

	public void setRandom(int seed) {
		myRandom = new Random(seed);
	}

	public void setTraining(String s) {
		myText = s.trim();
	}

	public String getRandomText(int numChars) {
		if (myText == null) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length() - charPassed);
		String key = myText.substring(index, index + charPassed);
		sb.append(key);
		for (int k = 0; k < numChars - charPassed; k++) {
			ArrayList<String> stringList = getFollows(key);
			if (stringList.size() == 0) {
				break;
			}
			index = myRandom.nextInt(stringList.size());
			String next = stringList.get(index);
			sb.append(next);
			key = key.substring(1) + next;
		}

		return sb.toString();
	}

	public ArrayList<String> getFollows(String key) {
		ArrayList<String> stringList = new ArrayList<String>();
		int keylen = key.length();
		for (int k = 0; k < myText.length() - keylen; k++) {
			String currStr = myText.substring(k, k + keylen);
			if (currStr.equals(key)) {
				String desiredStr = myText.substring(k + keylen, k + (keylen + 1));
				stringList.add(desiredStr);
			}

		}

		return stringList;
	}

}
