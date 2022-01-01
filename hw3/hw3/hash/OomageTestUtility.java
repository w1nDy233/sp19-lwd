package hw3.hash;

import edu.princeton.cs.algs4.In;

import java.util.List;
import java.util.HashMap;
public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        /* TODO:
         * Write a utility function that returns true if the given oomages
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */
        HashMap<Integer,Integer> count=new HashMap<>();
        double lowerBound=(double) oomages.size()/50;
        double upperBound=(double) oomages.size()/2.5;
        for(int i=0;i<oomages.size();i++){
            Oomage s=oomages.get(i);
            int hashCode=(s.hashCode()&0x7fffffff%M);
            if(!count.containsKey(hashCode)){
                count.put(hashCode,0);
            }else{
                count.put(hashCode,count.get(hashCode)+1);
            }
        }
        for(Integer i: count.values()){
            if(i<lowerBound||i>upperBound){
                return false;
            }
        }
        return true;
    }
}
