package hw3.hash;
import java.util.ArrayList;
public class demo {
    public static void main(String[] args){
        ArrayList<Integer> lst=new ArrayList<>(10);
        for(int i=9;i>=0;i--){
            lst.add(i,i);
        }
        for(Integer i:lst){
            System.out.println(i);
        }
    }
}
