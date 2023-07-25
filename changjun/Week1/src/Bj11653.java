import java.util.Scanner;


public class Bj11653 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        
        // <N 이아닌 <=N 으로 해야 함.
        // i를 1로 초기화 해야 i++에 의해 다시 2부터 시작.
        for(int i=2;i<=N;i++) {
        	if(N%i==0) {
        		System.out.println(i);
        		N/=i;
        		i=1;
        	}
        }
    }
}
