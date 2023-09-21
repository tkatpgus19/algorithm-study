import java.util.Scanner;

public class Bj2417{

    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);

        long N = sc.nextLong();
        System.out.println(Binary(N));
    }
    
    static long Binary(long x) {
        long left = 0, right = x, sqrt = -1;
        while(left <= right) {
            long m = (left + right) / 2;
            if (Math.pow(m, 2) >= x) {
                right = m - 1;
                sqrt = m;
            }
            else left = m + 1;
        }
        return sqrt;
    }
}