import java.io.*;
import java.util.*;

public class Bj2609 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        int gcd = 0;
        int lcm = a*b;

        // 유클리드 호제법 활용
        int tmp;
        while(b > 0){
            tmp = b;
            b = a % b;
            a = tmp;
        }
        gcd = a;

        // LCM은 a x b / gcd
        lcm /= gcd;

        System.out.println(gcd);
        System.out.println(lcm);

        // 메모리: 14204KB, 시간: 124ms
    }
}
