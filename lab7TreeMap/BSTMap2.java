import java.util.Iterator;
import java.util.Set;

public class BSTMap2<K extends Comparable<K>,V> implements Map61B<K,V>{
    @Override
    public V remove(K key){
        throw new UnsupportedOperationException();
    }
    @Override
    public V remove(K key,V value){
        throw new UnsupportedOperationException();
    }
    @Override
    public Iterator<K> iterator(){
        throw new UnsupportedOperationException();
    }
    @Override
    public Set<K> keySet(){
        throw new SecurityException();
    }
    private static final boolean RED=true;
    private static final boolean BLACK=false;
    private class Node{
        K key;
        V value;
        Node left;
        Node right;
        int N;
        public Node(K key,V value,int N){
            this.key=key;
            this.value=value;
            this.N=N;
        }
    }
    private Node root;
    //默认构造函数root=null;
    @Override
    public int size(){
        return size(root);
    }
    private int size(Node h){
        if(h==null){
            return 0;
        }else{
            return h.N;
        }
    }
    @Override
    public void clear(){
        root=null;
    }

    @Override
    public void put(K key, V value){
        root=put(root,key,value);
    }
    private Node put(Node h,K key,V value){
        if(h==null){
            return new Node(key,value,1);
        }
        int cmp=key.compareTo(h.key);
        if(cmp>0){
            h.right=put(h.right,key,value);
        }else if(cmp<0){
            h.left=put(h.left,key,value);
        }else{
            h.value=value;
        }


        h.N=1+size(h.right)+size(h.left);
        return h;
    }
    @Override
    public V get(K key){
        return get(root,key);
    }
    private V get(Node h,K key){
        if(h==null){
            return null;
        }
        int cmp=key.compareTo(h.key);
        if(cmp>0){
            return get(h.right,key);
        }else if(cmp<0){
            return get(h.left,key);
        }else{
            return h.value;
        }
    }
    @Override
    public boolean containsKey(K key){
        return containsKey(root,key);
    }
    private boolean containsKey(Node h,K key){
        if(h==null){
            return false;
        }
        int cmp=key.compareTo(h.key);
        if(cmp>0){
            return containsKey(h.right,key);
        }else if(cmp<0){
            return containsKey(h.left,key);
        }else{
            return true;
        }
    }
    public void printInOrder(){
        printInOrder(root);
    }
    private void printInOrder(Node h){
        if(h==null){
            return;
        }
        printInOrder(h.left);
        System.out.println(h.key+": "+h.value);
        printInOrder(h.right);
        return;
    }
    public K min(){
        return min(root).key;
    }
    private Node min(Node h){
        if(h.left==null){
            return h;
        }
        return min(h.left);
    }
    public void deleteMin(){
        root=deleteMin(root);
    }
    //将以h为根节点的树中含有最小键值的节点删除并返回新树
    private Node deleteMin(Node h){
        if(h.left==null){
            return h.right;
        }
        h.left=deleteMin(h.left);
        h.N=size(h.left)+size(h.right)+1;
        return h;
    }
    public void delete(K key){
        root=delete(root,key);
    }
    private Node delete(Node h,K key){
        if(h==null){
            return null;
        }
        int cmp=key.compareTo(h.key);
        if(cmp>0){
            h.left=delete(h.left,key);
        }else if(cmp<0){
            h.left=delete(h.left,key);
        }else{
            if(h.right==null){
                return h.left;
            }
            if(h.left==null){
                return h.right;
            }
            Node t=h;
            h=min(t.right);
            t.right=deleteMin(t.right);
            h.left=t.left;
            h.right=t.right;
        }
        h.N=size(h.left)+size(h.right)+1;
        return h;
    }
    public static void main(String[] args) {
        BSTMap2<String, Integer> bst = new BSTMap2<>();
        bst.put("niko", 95);
        bst.put("s1mple", 95);
        bst.put("dev1ce", 90);
        bst.put("zywoo",94);
        bst.printInOrder();
        bst.delete("niko");
        bst.printInOrder();
        bst.delete("ropz");
        bst.printInOrder();
    }
}
