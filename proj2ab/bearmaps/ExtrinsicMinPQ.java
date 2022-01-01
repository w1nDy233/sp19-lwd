package bearmaps;
/**
 * Priority queue where objects have a priority that is provided
 * extrinsically, i.e. are are supplied as an argument during insertion
 * and can be changed using the changePriority method.
 */
public interface ExtrinsicMinPQ<T> {
    /* Adds an item with the given priority value. Throws an
     * IllegalArgumentExceptionb if item is already present.
     * You may assume that item is never null. */
    public void add(T item, double priority);
    /* Returns true if the PQ contains the given item. */
    public boolean contains(T item);
    /* Returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    public T getSmallest();
    /* Removes and returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    public T removeSmallest();
    /* Returns the number of items in the PQ. */
    public int size();
    /* Changes the priority of the given item. Throws NoSuchElementException if the item 
     * doesn't exist. */
    public void changePriority(T item, double priority);
}
