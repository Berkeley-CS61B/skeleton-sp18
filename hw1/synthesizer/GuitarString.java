/**
 * @author Adnan H. Mohamed
 */
package synthesizer;

public class GuitarString {

    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */
    private BoundedQueue<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        int cap = (int) (Math.round(SR / frequency));
        buffer = new ArrayRingBuffer<>(cap);
    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        java.util.Set<Double> noise = new java.util.HashSet<>();
        int buffer_size = buffer.fillCount();

        while (noise.size() < buffer.capacity()) {
            noise.add(Math.random() - 0.5);
        }
        /** emptying the buffer. */
        while (!buffer.isEmpty()) {
            buffer.dequeue();
        }
        /** refill the buffer. */
        for (double d : noise) {
            buffer.enqueue(d);
        }
    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm. 
     */
    public void tic() {
        // TODO: Dequeue the front sample and enqueue a new sample that is
        //       the average of the two multiplied by the DECAY factor.
        //       Do not call StdAudio.play().
        double front = buffer.dequeue();
        double avg = (front + buffer.peek()) / 2;
        buffer.enqueue(avg * DECAY);
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        return buffer.peek();
    }
}
