
interface AnonFunc{
  int f(int x);
}
  
class Practice 
{ 
    public static void main(String args[]) 
    { 
        AnonFunc anon = (int x) -> x * 2;

        System.out.printf("%d\n", anon.f(3));
    } 
} 
