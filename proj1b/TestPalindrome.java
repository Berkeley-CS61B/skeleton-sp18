import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testisPalindrome() {
        /** Normal tests */
        assertFalse(palindrome.isPalindrome("word"));
        assertFalse(palindrome.isPalindrome("Mom"));
        assertTrue(palindrome.isPalindrome("dAd"));
        assertTrue(palindrome.isPalindrome("1991"));
        assertTrue(palindrome.isPalindrome(""));
        assertTrue(palindrome.isPalindrome("!"));
        assertTrue(palindrome.isPalindrome(" "));

        /** Tricky tests */
        assertFalse(palindrome.isPalindrome("Nolemonnomelon"));
        assertTrue(palindrome.isPalindrome("anna"));
        assertTrue(palindrome.isPalindrome("ididdidi"));
        assertTrue(palindrome.isPalindrome("nolem on no melon"));
    }
}
