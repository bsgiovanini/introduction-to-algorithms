import java.util.Iterator;
import java.util.NoSuchElementException;



public class Deque<Item> implements Iterable<Item> {
   
   private class Node {
        Item item;
        Node next;
        Node prev;
        
   }
   
   private class DequeIterator implements Iterator<Item> {
       
       private Node current = first;
       public boolean hasNext() { return current != null;}
       public Item next() {
           
           if (!hasNext()) {
               throw new java.util.NoSuchElementException();
           } 
           
           Item item = current.item;
           current = current.next;
           return item;
       }
       public void remove() {
           throw new java.lang.UnsupportedOperationException();
       }
       
    }
   
   private Node last, first;
   
   private int numberOfItems = 0;
   
   public Deque() {
       
   } // construct an empty deque
   
   public boolean isEmpty() {
       return this.first == null;
   }                 // is the deque empty?
   
   public int size() {
       return numberOfItems;
   }                        // return the number of items on the deque
   
   public void addLast(Item item) {
       if (item == null) {
           throw new java.lang.NullPointerException();
       }
       
       Node newLast = new Node();
       newLast.item = item;
       
       numberOfItems++;
      
       if (isEmpty()) {
           this.first = this.last = newLast;
           return;
       }

       Node previousLast = this.last;
       previousLast.next = newLast;
       newLast.prev = previousLast;
       this.last = newLast;
       
   }         // add the item to the front
   
   public void addFirst(Item item) {
       if (item == null) {
           throw new java.lang.NullPointerException();
       }
       
       Node newFirst = new Node();
       newFirst.item = item;

        numberOfItems++;
        
        if (isEmpty()) {
           this.first = this.last = newFirst;
           return;
        }
        
        newFirst.next = this.first;
        this.first.prev = newFirst;
        this.first = newFirst;

   }          // add the item to the end
   
   public Item removeLast() {
       if (isEmpty()) {
           throw new java.util.NoSuchElementException();
       }
       
       numberOfItems--;
       Item removed = this.last.item;
       
       if (this.last.prev != null) {
           this.last = this.last.prev;
           this.last.next = null;
       } else {
           this.first = this.last = null;
       }
     
       return removed;
   }                // remove and return the item from the front
   
   public Item removeFirst() {
       if (isEmpty()) {
           throw new java.util.NoSuchElementException();
       }
       
       numberOfItems--;
       Item removed = this.first.item;
       if(this.first.next != null) {
            this.first = this.first.next;
            this.first.prev = null;    
       } else {
           this.last = this.first = null;
       }
       
       return removed;
   }                // remove and return the item from the end
   
   public Iterator<Item> iterator()  {
       return new DequeIterator();
   }       // return an iterator over items in order from front to end
   
   public static void main(String[] args) {
       
       Deque<Integer> deque = new Deque<>();

        assert deque.isEmpty() == true;
        assert deque.size() == 0;

        deque.addFirst(1);
        assert deque.isEmpty() == false;
        assert deque.size() == 1;

        deque.addFirst(2);
        assert deque.removeFirst() == 2;
        assert deque.isEmpty() == false;
        assert deque.size() == 1;

        assert deque.removeFirst() == 1;
        assert deque.isEmpty() == true;
        assert deque.size() == 0;

        deque.addFirst(1);
        deque.addLast(2);
        deque.addLast(3);

        assert deque.isEmpty() == false;
        assert deque.size() == 3;

        assert deque.removeLast() == 3;
        assert deque.removeLast() == 2;
        assert deque.removeLast() == 1;

        assert deque.isEmpty() == true;
        assert deque.size() == 0;

        //Iterator

        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);

        Iterator<Integer> it = deque.iterator();

        assert it.hasNext() == true;
        assert it.next() == 1;
        assert it.hasNext() == true;
        assert it.next() == 2;
        assert it.hasNext() == true;
        assert it.next() == 3;
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
            deque.addFirst(null);
        } catch (NullPointerException e) {
            failed = true;
        } finally {
            assert failed == true;
        }

        failed = false;
        try {
            deque.addLast(null);
        } catch (NullPointerException e) {
            failed = true;
        } finally {
            assert failed == true;
        }

        deque = new Deque<>();

        failed = false;
        try {
            deque.removeFirst();
        } catch (NoSuchElementException e) {
            failed = true;
        } finally {
            assert failed == true;
        }

        failed = false;
        try {
            deque.removeLast();
        } catch (NoSuchElementException e) {
            failed = true;
        } finally {
            assert failed == true;
        }
        
        deque = new Deque<>();
        
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        
        assert deque.size() == 3;
        
        assert 1 == deque.removeFirst();
        
        assert 2 == deque.size();

        System.out.println("the end");
   }
}