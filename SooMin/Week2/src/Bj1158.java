import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Bj1158 {
    public static void main(String[] args) throws IOException {

        // 마피아 게임 산사람 모임
        Queue<Integer> q1 = new LinkedList<>();

        // 죽은 사람들 모임
        ArrayList<Integer> dead = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // 참가자 전부 넣기
        for (int i = 1; i <= N; i++) {
            q1.add(i);
        }

        // k번 러시안 룰렛 돌려서 맨 앞에 남은 사람 제거
        for (int a = 0; a < N; a++) {
            for (int i = 0; i < K-1; i++) {
                int last = q1.poll();
                q1.add(last);
            }
            dead.add(q1.poll());
        }

        System.out.print("<");
        for (int i = 0; i < dead.size()-1; i++) {
            System.out.printf("%d, ",dead.get(i));
        }
        System.out.printf("%d",dead.get(dead.size()-1));
        System.out.print(">");
    }
}
