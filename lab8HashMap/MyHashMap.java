import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;
import java.util.LinkedList;
public class MyHashMap<K,V> implements Map61B<K,V> {
    private int M;
    private double loadFactor;
    private MyChain<K,V>[] chain;
    private HashSet<K> allKeys;

    public MyHashMap(int M,double loadFactor){
        this.M=M;
        this.loadFactor=loadFactor;
        this.chain=(MyChain<K,V>[]) new MyChain[M];
        for(int i=0;i<M;i++){
            chain[i]=new MyChain<>();
        }
        this.allKeys=new HashSet<>();
    }
    public MyHashMap(int M){
        this(M,0.75);
    }
    public MyHashMap(){
        this(16,0.75);
    }
    @Override
    public V remove(K key){
        throw new UnsupportedOperationException();
    }
    @Override
    public V remove(K key, V value){
        throw new UnsupportedOperationException();
    }
    @Override
    public void clear(){
        this.M=16;
        this.loadFactor=0.75;
        this.allKeys=new HashSet<>();
        this.chain=(MyChain<K,V>[]) new MyChain[M];
        for(int i=0;i<M;i++){
            chain[i]=new MyChain<>();
        }
    }
    @Override
    public Set<K> keySet(){
        return allKeys;
    }
    @Override
    public int size(){
        return allKeys.size();
    }
    public int hash(K key){
        return (key.hashCode()&0x7fffffff%M);
    }
    private double load(){
        return (double)size()/M;
    }
    private void resize(int cap){
        MyHashMap<K,V> t=new MyHashMap<>(cap,this.loadFactor);
        for(K x:this.allKeys){
            t.put(x,this.get(x));
        }
        this.chain=t.chain;
        this.allKeys=t.allKeys;
        this.M=t.M;
        //loadFactor不用变
    }
    @Override
    public void put(K key,V value){
        if(load()>loadFactor){
            resize(size()*2);
        }
        chain[hash(key)].put(key,value);
        allKeys.add(key);
    }
    @Override
    public V get(K key){
        return chain[hash(key)].get(key);
    }
    @Override
    public boolean containsKey(K key){
        return allKeys.contains(key);
    }
    @Override
    public Iterator<K> iterator(){
        return new MyHashMapIterator();
    }
    private class MyHashMapIterator implements Iterator<K>{
        private LinkedList<K> keys;
        public MyHashMapIterator(){
            for(K x:allKeys){
                keys.add(x);
            }
        }
        public boolean hasNext(){
            return keys.isEmpty();
        }
        public K next(){
            return keys.removeFirst();
        }
    }
}
