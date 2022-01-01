public class SeparateChainingHashST<K,V> {
    private int N;
    private int M;
    private SeqSearchST<K,V>[] st;

    public SeparateChainingHashST(){
        this(4973);
    }
    public SeparateChainingHashST(int M){
        this.M=M;
        st=(SeqSearchST<K, V>[]) new SeqSearchST[M];
        for(int i=0;i<M;i++){
            st[i]=new SeqSearchST<>();
        }
    }
    private int hash(K key){
        return (key.hashCode()&0x7fffffff%M);
    }
    public V get(K key){
        return st[hash(key)].get(key);
    }
    public void put(K key,V value){
        st[hash(key)].put(key,value);
    }
    public static void main(String[] args){
        SeparateChainingHashST<String,Integer> st=new SeparateChainingHashST<>();
        st.put("zywoo",90);
    }
}
