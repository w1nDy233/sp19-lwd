import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>,V> implements Map61B<K,V>{
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
        boolean color;
        public Node(K key,V value,int N,boolean color){
            this.key=key;
            this.value=value;
            this.N=N;
            this.color=color;
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
    private Node rotateLeft(Node h){
        Node x=h.right;
        h.right=x.left;
        x.left=h;
        x.N=h.N;
        h.N=1+size(h.left)+size(h.right);
        x.color=h.color;
        h.color=RED;
        return x;
    }
    private Node rotateRight(Node h){
        Node x=h.left;
        h.left=x.right;
        x.right=h;
        x.N=h.N;
        h.N=1+size(h.left)+size(h.right);
        x.color=h.color;
        h.color=RED;
        return x;
    }
    private void flipColor(Node h){
        h.color=RED;
        h.left.color=BLACK;
        h.right.color=BLACK;
    }
    private boolean isRed(Node h){
        if(h==null){
            return false;
        }
        return h.color==RED;
    }
    @Override
    public void put(K key, V value){
        root=put(root,key,value);
        root.color=BLACK;
    }
    private Node put(Node h,K key,V value){
        if(h==null){
            return new Node(key,value,1,RED);
        }
        int cmp=key.compareTo(h.key);
        if(cmp>0){
            h.right=put(h.right,key,value);
        }else if(cmp<0){
            h.left=put(h.left,key,value);
        }else{
            h.value=value;
        }

        if(isRed(h.right)&&!isRed(h.left)){
            h=rotateLeft(h);
        }
        if(isRed(h.left)&&isRed(h.left.left)){
            h=rotateRight(h);
        }
        if(isRed(h.left)&&isRed(h.right)){
            flipColor(h);
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
        System.out.println(h.key+": "+h.value+" N: "+h.N);
        printInOrder(h.right);
        return;
    }

    private Node mergeL(Node x,Node y){
        if(y==null){
            if(x!=null){
                x.color=RED;
            }
            return x;
        }
        y.left=mergeL(x,y.left);
        y.N=size(y.left)+size(y.right)+1;
        return y;
    }
    private Node mergeR(Node x,Node y){
        if(y==null){
            if(x!=null){
                x.color=RED;
            }
            return x;
        }
        y.right=mergeL(x,y.right);
        y.N=size(y.left)+size(y.right)+1;
        return y;
    }
    public void delete(K key){
        root=delete(root,key);
        root.color=BLACK;
    }
    private Node delete(Node h,K key){
        if(h==null){
            return null;
        }
        int cmp=key.compareTo(h.key);
        if(cmp>0){
            h.right=delete(h.right,key);
        }else if(cmp<0){
            h.left=delete(h.left,key);
        }else{
            //如果该节点没有红色链接接，与普通的删除类似
            if(!isRed(h)&&!isRed(h.left)){
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
            //如果该节点通过红色连接与父节点相连
            if(isRed(h)) {
                h.right = mergeL(h.left, h.right);//已经更新了N
                if (h.right != null) {
                    h.right.color = RED;
                }
                return h.right;
            }
            if(isRed(h.left)){
                h.left.right=mergeR(h.right,h.left.right);
                h.left.color=RED;
                h.left.N=size(h.left.left)+size(h.left.right)+1;
                return h.left;
            }
        }
        h.color=RED;
        h.N=size(h.left)+size(h.right)+1;
        return h;
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
            if(h.right!=null){
            }
            return h.right;
        }
        h.left=deleteMin(h.left);
        h.N=size(h.left)+size(h.right)+1;
        return h;
    }
    public  void mergeL(BSTMap bst){
        this.root=mergeL(bst.root,this.root);
    }
    public void printRight(){
        printRight(root);
    }
    private void printRight(Node h){
        if(h==null){
            return;
        }
        System.out.println(h.key+": "+h.value);
        printRight(h.right);
    }
    public static void main(String[] args) {
        BSTMap<Integer, Integer> bst = new BSTMap<>();
        bst.put(7,7);
        bst.put(9,9);
        bst.put(4,4);
        bst.put(3,3);
        bst.put(15,15);
        bst.put(20,20);
        bst.put(23,23);
        bst.delete(7);
        bst.printInOrder();
    }
}
