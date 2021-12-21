public class MyChain<K,V>{
    private class Node{
        private K key;
        private V value;
        private Node next;
        public Node(K key,V value,Node next){
            this.key=key;
            this.value=value;
            this.next=next;
        }
    }
    private Node first;

    public void put(K key,V value){
        for(Node x=first;x!=null;x=x.next){
            if(x.key.equals(key)){
                x.value=value;
                return;
            }
        }
        first=new Node(key,value,first);
    }
    public V get(K key){
        for(Node x=first;x!=null;x=x.next){
            if(x.key.equals(key)){
                return x.value;
            }
        }
        return null;
    }
}