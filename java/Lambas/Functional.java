

interface IntToInt 
{ 
    // An abstract function 
    int f(int x); 
} 

public class Functional {
  public static int calc() {
    IntToInt fObj = (int x) -> x * 2;
    return fObj.f(2);
  }
}
