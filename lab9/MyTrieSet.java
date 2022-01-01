import java.util.List;
import java.util.LinkedList;
public class MyTrieSet implements TrieSet61B {
    private static final int R=256;
    private Node root;
    private static class Node{
        private boolean isKey=false;
        private Node[] next=new Node[R];
    }
    @Override
    public String longestPrefixOf(String key){
        throw new UnsupportedOperationException();
    }
    @Override
    public void clear(){
        root.isKey=false;
        root.next=new Node[R];
    }
    @Override
    public boolean contains(String key){
        Node x=contains(key,-1,root);
        if(x==null){
            return false;
        }
        if(!x.isKey){
            return false;
        }
        return true;
    }
    private Node contains(String key,int pos,Node x){
        if(x==null){
            return null;
        }
        if(pos==key.length()-1){
            return x;
        }
        char c=key.charAt(++pos);
        return contains(key,pos,x.next[c]);
    }
    @Override
    public List<String> keysWithPrefix(String prefix){
        List<String> lst=new LinkedList<>();
        collect(contains(prefix,-1,root),prefix,lst);
        return lst;
    }
    private void collect(Node x,String pre,List<String> lst){
        if(x==null){
            return;
        }
        if(x.isKey){
            lst.add(pre);
        }
        for(char c=0;c<R;c++){
            collect(x.next[c],pre+c,lst);
        }
    }
    @Override
    public void add(String key){
        root=add(key,-1,root);
    }
    private Node add(String key,int pos,Node x){
        if(x==null){
            x=new Node();
        }
        if(pos==key.length()-1){
            x.isKey=true;
            return x;
        }
        char c=key.charAt(++pos);
        x.next[c]=add(key,pos,x.next[c]);
        return x;
    }
}


