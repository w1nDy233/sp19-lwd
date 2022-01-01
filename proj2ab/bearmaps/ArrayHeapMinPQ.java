package bearmaps;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.HashMap;
public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {
    private static class Node{
        private Object value;
        private double priority;
        private int index;
        public Node(Object value,double priority){
            this.value=value;
            this.priority=priority;
        }
        public Node(){
            this.value=null;
            this.priority=0;
        }
    }
    public Node[] pq;
    public HashMap<T,Integer> items;
    public ArrayHeapMinPQ(){
        pq=new Node[10];
        items=new HashMap<>();
    }
    private void resize(int capacity){
        Node[] tmp=new Node[capacity];
        for(int i=1;i<=size();i++){
            tmp[i]=pq[i];
        }
        pq=tmp;
    }
    private boolean less(int i,int j){
        return pq[i].priority-pq[j].priority>0;
    }
    private void exch(int i,int j){
        items.put((T)pq[i].value,j);
        items.put((T)pq[j].value,i);
        Node tmp;
        tmp=pq[i];
        pq[i]=pq[j];
        pq[j]=tmp;
    }
    private int swim(int k){
        while(k>1&&less(k/2,k)){
            exch(k/2,k);
            k=k/2;
        }
        return k;
    }
    private int sink(int k){
        while(2*k<=size()){
            int i=2*k;
            if(i<size()&&less(i,i+1)){
                i++;
            }
            if(less(i,k)){
                break;
            }
            exch(k,i);
            k=i;
        }
        return k;
    }
    /* Adds an item with the given priority value. Throws an
     * IllegalArgumentExceptionb if item is already present.
     * You may assume that item is never null. */
    @Override
    public void add(T item, double priority){
        if(items.containsKey(item)){
            throw new IllegalArgumentException();
        }
        double loadFactor=(double) size()/pq.length;
        if(loadFactor>0.75){
            resize(2* pq.length);
        }
        items.put(item,size()+1);
        pq[size()]=new Node(item,priority);
        int i=swim(size());
        items.put(item,i);
    }
    /* Returns true if the PQ contains the given item. */
    @Override
    public boolean contains(T item){
        return items.containsKey(item);
    }
    /* Returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    @Override
    public T getSmallest(){
        if(size()==0){
            throw new NoSuchElementException();
        }
        return (T) pq[1].value;
    }
    /* Removes and returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    @Override
    public T removeSmallest(){
        if(size()==0){
            throw new NoSuchElementException();
        }
        Object tmp=pq[1].value;
        exch(1,size());
        pq[size()]=null;
        items.remove(tmp);
        sink(1);
        return (T) tmp;
    }
    /* Returns the number of items in the PQ. */
    @Override
    public int size(){
        return items.size();
    }
    /* Changes the priority of the given item. Throws NoSuchElementException if the item
     * doesn't exist. */
    @Override
    public void changePriority(T item, double priority){
        if(!contains(item)){
            throw new NoSuchElementException();
        }
        int i=items.get(item);
        pq[i].priority=priority;
        int j=swim(i);
        int k=sink(j);
        items.put(item,k);
    }
    public static void main(String[] args){
    }
}
