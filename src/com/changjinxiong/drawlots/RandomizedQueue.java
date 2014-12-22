/****************************************************************************
 *  Author:        Jinxiong Chang
 *  Written:       2/20/2014
 *  Last updated:  2/20/2014
 *
 *  Compilation:   javac RandomizedQueue.java
 *  Execution:     java RandomizedQueue
 *  Dependencies:  -
 *
****************************************************************************/
package com.changjinxiong.drawlots;
import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 *  The <tt>RandomizedQueue</tt> class is similar to a stack or queue, except 
 *  that the item removed is chosen uniformly at random from items in the data 
 *  structure.<p>
 *  This implementation uses a resizing-array which double the underlying array
 *  when it is full and halves the underlying array when it is one-quarter full.
 *  The <em>enqueue</em> and <em>dequeue</em> operations take constant amortized 
 *  time. The <em>size</em>, <em>peek</em>, and <em>is-empty</em> operations takes
 *  constant time in the worst case. 
 *  <p>
 *  @author Jinxiong Chang
 */
public class RandomizedQueue<Item> implements Iterable<Item> 
{
    private Item[] q;                        // queue elements
    private int N = 0;                       // number of elements on queue
    private int first = 0;                   // index of first element of queue
    private int last  = 0;                   // index of next available slot
    
    /**
     * Initializes an empty queue.
     */
    public RandomizedQueue()                 // construct an empty randomized queue
    {
        // cast needed since no generic array creation in Java
        q = (Item[]) new Object[2];
    }
    /**
     * Is this queue empty?
     * @return true if this queue is empty; false otherwise
     */
    public boolean isEmpty()                 // is the queue empty?
    {
        return N == 0;
    }
    /**
     * Returns the number of items in this queue.
     * @return the number of items in this queue
     */
    public int size()                        // return the number of items on the queue
    {
        return N;
    }
   // resize the underlying array
    private void resize(int max) 
    {
//        StdOut.println("resize " + max);
        assert max >= N;
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < N; i++) {
            temp[i] = q[(first + i) % q.length];
        }
        q = temp;
        first = 0;
        last  = N;
    }
    /**
     * add the item
     * @param item the item to add
     * @throws  NullPointerException
     */
    public void enqueue(Item item) throws  NullPointerException          // add the item
    {
        if (item == null)
            throw new NullPointerException();
        if (N == q.length) 
            resize(2*q.length);              // double size of array if necessary
        q[last++] = item;
        if (last == q.length) last = 0;          // wrap-around
        N++;
    }
    /**
     * Removes and returns a item randomly.
     * @return a item randomly
     * @throws java.util.NoSuchElementException if this queue is empty
     */
    public Item dequeue() 
    {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        int i = StdRandom.uniform(size());
        int r;
        if (first + i < q.length)
            r = first + i;
        else
            r = first + i - q.length;
        Item item = q[r];
        q[r] = q[first];                            
        q[first] = null;                            // to avoid loitering
        N--;
        first++;
        if (first == q.length) first = 0;           // wrap-around
        // shrink size of array if necessary
        if (N > 0 && N == q.length/4) resize(q.length/2); 
        return item;
    }

    /**
     * Returns a item randomly.
     * @return a item randomly
     * @throws java.util.NoSuchElementException if this queue is empty
     */
    public Item sample()                     // return (but do not delete) a random item
    {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        int i = StdRandom.uniform(size());
        int r;
        if (first + i < q.length)
            r = first + i;
        else
            r = first + i - q.length;
        Item item = q[r];
        return item;
    }
    /**
     * return an independent iterator over items in random order
     * @return an independent iterator over items in random order
     */    
    public Iterator<Item> iterator()         // return an independent iterator over items in random order
    {
        return new ArrayIterator();  
    }
    // an iterator, doesn't implement remove() since it's optional
    private class ArrayIterator implements Iterator<Item> 
    {
        private int i = 0;
        private int[] a;
        public ArrayIterator()
        {
            int S = size();
            a = new int[S];
            for (int i = 0; i < S; i++)
            	a[i] = i;
            StdRandom.shuffle(a);
            
        }
        public boolean hasNext()  
        { 
            return i < N;                               
        }
        public void remove()      
        { 
            throw new UnsupportedOperationException(); 
        }

        public Item next() 
        {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = q[(a[i] + first) % q.length];
            i++;
            return item;
        }
    }    
    public static void main(String[] args)   // unit testing
    {
//        RandomizedQueue<String> q = new RandomizedQueue<String>();
//        while (!StdIn.isEmpty()) 
//        {
//            String item = StdIn.readString();
//            if (!item.equals("-")) q.enqueue(item);
//            else if (!q.isEmpty()) StdOut.print(q.dequeue() + " ");
//        }
//        while (!StdIn.isEmpty()) 
//        {
//             String item = StdIn.readString();
//             if (!item.equals("-")) 
//                 q.enqueue(item);
//             else if (!q.isEmpty()) 
//             {
//                 for (String s : q)
//                 {
//                     StdOut.println(s);
//                     for (String ss : q)
//                         StdOut.println("- " + ss);
//                 }
//             }
//        }
//        StdOut.println("(" + q.size() + " left on queue)");
    }
}