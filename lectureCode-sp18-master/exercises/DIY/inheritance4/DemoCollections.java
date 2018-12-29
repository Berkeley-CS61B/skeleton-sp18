import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

public class DemoCollections {

	/** Returns a lower case version of the string with
	  * all characters except letters removed. */
	public static String cleanString(String s) {
		return s.toLowerCase().replaceAll("[^a-z]", "");
	}

	/** Gets a list of all words in the file. */
	public static List<String> getWords(String inputFilename) {
		return null;
	}

	/** Returns the count of the number of unique words in words. */
	public static int countUniqueWords(List<String> words) {
		return 0;
	}

	/** Returns a map (a.k.a. dictionary) that tracks the count of all specified
	  * target words in words. */
	public static Map<String, Integer> collectWordCount(List<String> words, List<String> targets) {
		return null;
	}

	public static void main(String[] args) {
		List<String> w = getWords("lotteryOfBabylon.txt");
		System.out.println(w);
		/*System.out.println(countUniqueWords(w));

		List<String> targets = new ArrayList<String>();
		targets.add("lottery");
		targets.add("the");
		targets.add("babylon");

		System.out.println(collectWordCount(w, targets));*/
	}
} 