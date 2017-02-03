import edu.princeton.cs.algs4.StdIn;

public class Permutation {
   public static void main(String[] args) {
        
        RandomizedQueue<String> rq = new RandomizedQueue<>();
       
        int K = Integer.parseInt(args[0]);

        RandomizedQueue<String> queue = new RandomizedQueue<>();

        while (!StdIn.isEmpty()) {
            queue.enqueue(StdIn.readString());
        }

        for (int k = 0; k < K; k++) {
            System.out.println(queue.dequeue());
        }
   }
}