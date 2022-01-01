package bearmaps;
import java.util.List;
public class KDTree implements PointSet{
    private static class Node{
        Point point;
        Node left;
        Node right;
        public Node(Point point){
            this.point=point;
        }
        //假定根节点深度为1
        public boolean less(Point p,int cmp){
            if(cmp%2==1){
                return this.point.getX()-p.getX()<=0;
            }
            return this.point.getY()-p.getY()<=0;
        }
        public double distance(Point point){
            return Point.distance(this.point,point);
        }
    }
    private Node root;
    public KDTree(List<Point> points){
        for(int i=0;i<points.size();i++){
            root=insert(root,points.get(i),1);
        }
    }
    private Node insert(Node x,Point point,int depth){
        if(x==null){
            return new Node(point);
        }
        if(x.point.equals(point)){
            return x;
        }
        if(x.less(point,depth)){
            x.right=insert(x.right,point,++depth);
        }else{
            x.left=insert(x.left,point,++depth);
        }
        return x;
    }
    @Override
    public Point nearest(double x, double y){
        Point desPoint=new Point(x,y);
        Node nearestNode=nearest(desPoint,root,root,1);
        return nearestNode.point;
    }
    private Node nearest(Point desPoint,Node nearestNode,Node x,int depth){
        if(x==null){
            return nearestNode;
        }
        //
        if(x.distance(desPoint)<nearestNode.distance(desPoint)){
            nearestNode=x;
        }
        double minDis;//desPoint到坏区间的最小距离
        if(depth%2==1){
            minDis=Math.abs(desPoint.getX()-x.point.getX());
        }else{
            minDis=Math.abs(desPoint.getY()-x.point.getY());
        }
        //
        if (depth%2==1&&desPoint.getX()-x.point.getX()<0||depth%2==0&&desPoint.getY()-x.point.getY()<0) {
            depth++;
            nearestNode=nearest(desPoint,nearestNode,x.left,depth);
            if(minDis<nearestNode.distance(desPoint)) {
                nearestNode = nearest(desPoint, nearestNode,x.right, depth);
            }
        }
        //dePoint在x.point的右或者上侧，所以right是好子空间，left是坏子空间
        else{
            depth++;
            nearestNode=nearest(desPoint,nearestNode,x.right,depth);
            if(minDis<nearestNode.distance(desPoint)) {
                nearestNode = nearest(desPoint, nearestNode,x.left, depth);
            }
        }
        return nearestNode;
    }
    //判断好子空间，
    public void travesal(){
        travesal(root);
    }
    private void travesal(Node x){
        if(x==null){
            return;
        }
        travesal(x.left);
        System.out.println("("+x.point.getX()+","+x.point.getY()+")"+" ");
        travesal(x.right);
    }
    public static void main(String[] args){
    }
}
