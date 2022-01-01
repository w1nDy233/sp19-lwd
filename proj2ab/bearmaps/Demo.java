package bearmaps;
class Dog{
    public Integer ID;
}
public class Demo {
    public static void main(String[] args){
        Dog d1=new Dog();
        d1.ID=0;
        setDog(d1);
        System.out.println(d1.ID);
    }
    public static void setDog(Dog d){
        d.ID=20;
    }
}
