package bearmaps;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import java.util.Random;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.ArrayList;
public class KDTreeTest{
    @Test
    public void testCorrectness(){
        List<Point> lst=new ArrayList<>();
        for(int i=0;i<1000;i++){
            lst.add(new Point(StdRandom.uniform(-20.0,20.0),StdRandom.uniform(-20.0,20.0)));
        }
        NaivePointSet nps=new NaivePointSet(lst);
        KDTree kdt=new KDTree(lst);
        Point p1=nps.nearest(0.0,0.0);
        Point p2=kdt.nearest(0.0,0.0);
        Point p0=new Point(0,0);
        assertTrue(Point.distance(p0,p1)==Point.distance(p0,p2));
    }
    public static void main(String[] args){
        List<Point> lst=new ArrayList<>();
        for(int i=0;i<10000000;i++){
            lst.add(new Point(StdRandom.uniform(-20.0,20.0),StdRandom.uniform(-20.0,20.0)));
        }
        NaivePointSet nps=new NaivePointSet(lst);
        KDTree kdt=new KDTree(lst);

        long start = System.currentTimeMillis();
        Point p1=nps.nearest(0.0,0.0);
        long end = System.currentTimeMillis();
        System.out.println("Total time elapsed: " + (end - start)/1000.0 +  " seconds.");

        start = System.currentTimeMillis();
        Point p2=kdt.nearest(0.0,0.0);
        end = System.currentTimeMillis();
        System.out.println("Total time elapsed: " + (end - start)/1000.0 +  " seconds.");
    }
}
