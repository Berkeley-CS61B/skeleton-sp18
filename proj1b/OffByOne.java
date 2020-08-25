public class OffByOne implements CharacterComparator{
    /** Returns true iff x comes after y or vise versa.
     *
     * @param x:char
     * @param y:char
     * @return boolean
     */
    @Override
    public boolean equalChars(char x, char y) {
        return Math.abs((int)x - (int)y) == 1;
    }
}