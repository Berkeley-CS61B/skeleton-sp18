public class OffByN implements CharacterComparator{
    private int diff;
    public OffByN(int N) {
        diff = N;
    }
    /** Returns true iff abs(x - y) == n
     *
     * @param x:char
     * @param y:char
     * @return boolean
     */
    @Override
    public boolean equalChars(char x, char y) {
        return Math.abs(x - y) == diff;
    }
}