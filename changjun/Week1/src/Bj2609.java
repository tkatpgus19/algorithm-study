import java.util.Scanner;


public class Bj2609 {

    public static int GCD(int a, int b){
    	// 같으면 그 자체로 최대공약수 
        if(a==b)
            return a;

        // B가 크면 a,b 바꾸기 
        if(a<b){
            int tmp = a;
            a = b;
            b = tmp;
        }

        //나머지가 0이면 b가 최대공약수.
     
        if(a%b == 0){
            return b;
        } else{
        	//아니면 b와 나머지로 다시 GCD 돌림.
            return (GCD(b,a%b));
        }
    }
     
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n1 = sc.nextInt();
        int n2 = sc.nextInt();

        int gcd = GCD(n1,n2);
        
        //lcm은 gcd를 이용해 계산.
        int lcm = n1*n2/gcd;

        System.out.println(gcd);
        System.out.println(lcm);

    }



}
