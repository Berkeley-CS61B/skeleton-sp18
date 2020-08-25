public class Palindrome {
    /** Returns a Deque containing the characters of
     * the string word in the same order they occur in
     * word.
     * @param word
     * @returns Deque of characters
     */
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> d = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); ++i) {
            d.addLast(word.charAt(i));
        }
        return d;
    }

    /** Returns true iff word is a palindrome.
     *
     * @param word
     * @return boolean
     */
    public boolean isPalindrome(String word) {
        return isPalindrome(wordToDeque(word));
    }

    /** Returns true iff d contains a palindrome.
     *
     * @param d
     * @return boolean.
     */
    private boolean isPalindrome(Deque<Character> d) {
        if (d.size() < 2) {
            return true;
        } else {
            return d.removeFirst() == d.removeLast()
                    && isPalindrome(d);
        }
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        return isPalindrome(wordToDeque(word), cc);
    }

    private boolean isPalindrome(Deque<Character> d, CharacterComparator cc) {
        if (d.size() < 2) {
            return true;
        } else {
            return cc.equalChars(d.removeFirst(), d.removeLast())
                    && isPalindrome(d, cc);
        }
    }
}