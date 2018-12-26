public class DogLauncher {
    public static void main(String[] args) {
        Dog d1 = new Dog(3);
        Dog d2 = new Dog(52);
        Dog bigger = Dog.maxDog(d1, d2);
        bigger.makeNoise();
    } 
}