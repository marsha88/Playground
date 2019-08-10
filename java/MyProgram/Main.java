import java.util.Scanner;
import calculator.Calculator;

public class Main {
  public static void main(String[] args) {
    Scanner s = new Scanner(System.in);

    int toDouble = s.nextInt();

    System.out.printf("%d doubled is %d\n", toDouble, Calculator.mul(toDouble, 2));
  }
}