import java.io.*;

public class Bj13909 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int answer = (int) Math.sqrt(N);
        System.out.println(answer);

        // 메모리: 11460KB, 시간: 80ms
    }
}
