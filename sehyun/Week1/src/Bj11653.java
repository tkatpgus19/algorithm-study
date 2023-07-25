import java.io.*;

public class Bj11653 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int num = 2;

        // num으로 나눠지지 않을 때까지 나누고, num 값을 1씩 증가시킴
        while(N > 1){
            if(N % num == 0){
                System.out.println(num);
                N /= num;
                continue;
            }
            num++;
        }

        // 메모리: 11636KB, 시간: 116ms
    }
}
