package bearmaps;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayHeapMinPQTest {
    @Test
    public void testContains(){
        ArrayHeapMinPQ<String> pq=new ArrayHeapMinPQ<>();
        assertFalse(pq.contains("niko"));
        pq.add("niko",1);
        assertTrue(pq.contains("niko"));
    }
    @Test
    public void testAdd(){
        ArrayHeapMinPQ<String> pq=new ArrayHeapMinPQ<>();
        pq.add("niko",1);
        pq.add("zywoo",0);
        pq.add("ropz",5);
        assertTrue(pq.contains("niko")&&pq.contains("zywoo")&&pq.contains("ropz"));
    }
    @Test
    public void testGetSmallest(){
        ArrayHeapMinPQ<String> pq=new ArrayHeapMinPQ<>();
        pq.add("niko",1);
        pq.add("zywoo",0);
        pq.add("ropz",5);
        assertEquals("zywoo",pq.getSmallest());
    }
    @Test
    public void testRemoveSmallest(){
        ArrayHeapMinPQ<String> pq=new ArrayHeapMinPQ<>();
        pq.add("niko",1);
        pq.add("zywoo",0);
        pq.add("ropz",5);
        pq.removeSmallest();
        assertEquals("niko",pq.getSmallest());
        assertFalse(pq.contains("zywoo"));
    }
    @Test
    public void testChangePriority(){
        ArrayHeapMinPQ<String> pq=new ArrayHeapMinPQ<>();
        pq.add("niko",1);
        pq.add("zywoo",0);
        pq.add("ropz",5);
        pq.changePriority("niko",-1);
        assertEquals("niko",pq.getSmallest());
        pq.changePriority("ropz",-100);
        assertEquals("ropz",pq.getSmallest());
    }
    @Test
    public void testSize(){
        ArrayHeapMinPQ<String> pq=new ArrayHeapMinPQ<>();
        pq.add("niko",1);
        pq.add("zywoo",0);
        pq.add("ropz",5);
        assertEquals(pq.size(),3);
        pq.removeSmallest();
        assertEquals(pq.size(),2);
    }
}
