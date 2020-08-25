import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
    static CharacterComparator offBy7 = new OffByN(7);

    @Test
    public void testOffBy7() {
        assertTrue(offBy7.equalChars('a', 'h'));
        assertTrue(offBy7.equalChars('i', 'b'));
        assertTrue(offBy7.equalChars('0', '7'));
        assertFalse(offBy7.equalChars('a', 'a'));
        assertFalse(offBy7.equalChars('a', 'b'));
    }

    private int howMany(int n) {
        In in = new In("../library-sp18/data/words.txt");
        Palindrome palindrome = new Palindrome();
        int total = 0;
        while (!in.isEmpty()) {
            String word = in.readString();
            if (palindrome.isPalindrome(word, new OffByN(n))) {
                total += 1;
            }
        }
        return total;
    }
    @Test
    public void algo1() {
        int bestN = 0;
        int best = howMany(0);
        for (int i = 1; i < 50; ++i) {
            int tmp = howMany(i);
            if (tmp > best) {
                best = tmp;
                bestN = i;
            }
        }
        System.out.println("N = " + bestN);
    }
}
