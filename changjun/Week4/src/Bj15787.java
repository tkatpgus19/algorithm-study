import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Bj15787 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 비트 마스킹
        int[] train = new int[N];

        for(int m=0;m<M;m++){
            st = new StringTokenizer(br.readLine());

            // 명령 1~4
            int command = Integer.parseInt(st.nextToken());

            if(command == 1){
                int i = Integer.parseInt(st.nextToken())-1;
                int x = Integer.parseInt(st.nextToken())-1;
                train[i] |= (1<<x); // | 연산으로 해당 비트 1로

            } else if(command == 2){
                int i = Integer.parseInt(st.nextToken())-1;
                int x = Integer.parseInt(st.nextToken())-1;
                train[i] &= ~(1<<x); // & ~ 연산으로 해당 비트 0로

            } else if(command == 3){
                int i = Integer.parseInt(st.nextToken())-1;
                train[i] = train[i] << 1; // 뒷 자리로 한 칸씩 이동
                train[i] %= 1<<20; // 20자리 까지 밖에 없으니 넘어간 사람 없애기.

            } else if(command == 4){
                int i = Integer.parseInt(st.nextToken())-1;
                train[i] = train[i] >> 1; // 앞 자리로 한 칸 씩 이동
            }
        }

        // Set 에 넣고 크기 측정.
        HashSet<Integer> hs = new HashSet<Integer>();
        for(int i=0;i<N;i++){
            hs.add(train[i]);
        }

        System.out.println(hs.size());
    }
}
