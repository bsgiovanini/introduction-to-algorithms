import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;



public class RandomizedQueue<Item> implements Iterable<Item> {
   
   

   private int head = -1; 
   
   private static int INITIAL_CAPACITY = 4;

   private int capacity = INITIAL_CAPACITY;
   
   
   private class RandomizedIterator implements Iterator<Item> {
       

        private int[] indexes;
        private int current;

        public RandomizedIterator() {
            indexes = new int[size()];
            for (int i = 0; i < size(); i++) {
                indexes[i] = i;
            }
            StdRandom.shuffle(indexes);
            current = 0;
        }

        @Override
        public boolean hasNext() {
            return current < indexes.length;
        }

        @Override
        public Item next() {

            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            int index = indexes[current];
            current++;

            return array[index];
        }
        
       public void remove() {
           throw new java.lang.UnsupportedOperationException();
       }
       
    }
    
    private Item[] array;


   public RandomizedQueue() {
       array = (Item[]) new Object[capacity];
   } // construct an empty deque
   
   public boolean isEmpty() {
       return this.head ==  -1;
   }                 // is the deque empty?
   
   public int size() {
       return this.head + 1;
   }// return the number of items on the deque
   
   public void enqueue(Item item) {
       if (item == null) {
           throw new java.lang.NullPointerException();
       }
        
       this.head++;
       
       if (this.head == capacity-1) {
           capacity = capacity * 2;
           resizeQueue();
       }
       array[this.head] = item;
        
   }         // add the item
   
   
   private void resizeQueue() {
        Item[] newArray = (Item[]) new Object[capacity];
        for (int i = 0; i <= this.head; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
    }

   public Item dequeue() {
       if (isEmpty()) {
           throw new java.util.NoSuchElementException();
       }
       
       int index = StdRandom.uniform(size());
       Item removed = array[index];
       array[index] = array[this.head];
       this.head--;
       
       if (this.head < capacity/4 && capacity >= INITIAL_CAPACITY) {
           capacity = capacity/2;
           resizeQueue();
       }
       
       return removed;
       
   }     // remove and return a random item
   
   public Item sample() {
       if (isEmpty()) {
           throw new java.util.NoSuchElementException();
       }
        return array[StdRandom.uniform(size())];  
   }    // return (but do not remove) a random item
   
   public Iterator<Item> iterator()  {
       return new RandomizedIterator();
   }       // return an iterator over items in order from front to end
   
   public static void main(String[] args) {
       
       RandomizedQueue<Integer> deque = new RandomizedQueue<>();

        assert deque.isEmpty() == true;
        assert deque.size() == 0;

        deque.enqueue(1);
        assert deque.isEmpty() == false;
        assert deque.size() == 1;

        deque.enqueue(2);
        deque.dequeue();
        assert deque.isEmpty() == false;
        assert deque.size() == 1;

        deque.dequeue();
        assert deque.isEmpty() == true;
        assert deque.size() == 0;

        deque.enqueue(1);
        deque.enqueue(2);
        deque.enqueue(3);

        assert deque.isEmpty() == false;
        assert deque.size() == 3;

        deque.dequeue();
        deque.dequeue();
        deque.dequeue();

        assert deque.isEmpty() == true;
        assert deque.size() == 0;

        //Iterator

        deque.enqueue(1);
        deque.enqueue(2);
        deque.enqueue(3);

        Iterator<Integer> it = deque.iterator();

        assert it.hasNext() == true;
        it.next();        
        assert it.hasNext() == true;
        it.next();
        assert it.hasNext() == true;
        it.next();
        assert it.hasNext() == false;

        //boundaries
        boolean failed = false;
        try {
            it.remove();
        } catch (UnsupportedOperationException e) {
            failed = true;
        } finally {
            assert failed == true;
        }

        failed = false;
        try {
            it.next();
        } catch (NoSuchElementException e) {
            failed = true;
        } finally {
            assert failed == true;
        }

        failed = false;
        try {
            deque.enqueue(null);
        } catch (NullPointerException e) {
            failed = true;
        } finally {
            assert failed == true;
        }

        failed = false;
        try {
            deque.enqueue(null);
        } catch (NullPointerException e) {
            failed = true;
        } finally {
            assert failed == true;
        }

        deque = new RandomizedQueue<>();

        failed = false;
        try {
            deque.dequeue();
        } catch (NoSuchElementException e) {
            failed = true;
        } finally {
            assert failed == true;
        }

        failed = false;
        try {
            deque.dequeue();
        } catch (NoSuchElementException e) {
            failed = true;
        } finally {
            assert failed == true;
        }
        
        deque = new RandomizedQueue<>();
        
        deque.enqueue(1);
        deque.enqueue(2);
        deque.enqueue(3);
        
        assert deque.size() == 3;
        
        deque.dequeue();
        
        assert 2 == deque.size();
        
        deque.dequeue();
        
        assert 1 == deque.size();
        
        deque.dequeue();
        
        assert 0 == deque.size();

        System.out.println("the end");
   }
}