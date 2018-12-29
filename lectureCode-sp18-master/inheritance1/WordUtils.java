public class WordUtils {
   /** Returns the length of the longest word. */
   public static String longest(List61B<String> list) {
      int maxDex = 0;
      for (int i = 0; i < list.size(); i += 1) {
         String longestString = list.get(maxDex);
         String thisString = list.get(i);
         if (thisString.length() > longestString.length()) {
            maxDex = i;
         }
      }
      return list.get(maxDex);
   }

    public static void main(String[] args) {
     AList<String> someList = new AList<>();
     someList.addLast("elk");
     someList.addLast("are");
     someList.addLast("watching");
     System.out.println(longest(someList));
     //someList.print();
  }
}

