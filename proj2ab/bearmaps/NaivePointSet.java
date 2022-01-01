package bearmaps;
import java.util.List;
public class NaivePointSet implements PointSet{
    private Point[] points;
    public NaivePointSet(List<Point> points){
        this.points=new Point[points.size()];
        for(int i=0;i< points.size();i++){
            this.points[i]=points.get(i);
        }
    }
    @Override
    public Point nearest(double x,double y){
        Point detination=new Point(x,y);
        int indexNearest=0;
        double disNearest=Point.distance(detination,points[0]);
        for(int i=1;i<points.length;i++){
            if(Point.distance(detination,points[i])<disNearest){
                indexNearest=i;
                disNearest=Point.distance(detination,points[indexNearest]);
            }
        }
        return points[indexNearest];
    }
    public static void main(String[] args){
        Point p1=new Point(1.1,2.2);
        Point p2=new Point(3.3,4.4);
        Point p3=new Point(-2.9,4.2);
        NaivePointSet nn=new NaivePointSet(List.of(p1,p2,p3));
        Point ret=nn.nearest(3.0,4.0);
        System.out.println(ret.getX());
        System.out.println(ret.getY());
    }
}
