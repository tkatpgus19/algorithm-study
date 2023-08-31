import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// 퇴사
public class Bj14501 {
    static int[][] work;
    static int N;
    static int max  = 0; // 최대 수익.크
    private static void consult(int dep,int finish,int money) {
        // 종료 조건.
        if(dep == N){
            if(max<money) max = money;
            return;
        }

        // 그 날 일을 하지 않을 경우.
        consult(dep+1,finish,money);

        // 그 날 일을 할 경우.
        // 그 날 쉬고 있고, 그 날부터 퇴사 전까지 끝 마칠 수 있으면 진행.
        if(dep>=finish && dep+work[dep][0] <= N){
            consult(dep+1,dep+work[dep][0],money+work[dep][1]);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        work = new int[N][2]; // 일거리 배열.

        // 입력.
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            work[i][0] = Integer.parseInt(st.nextToken());
            work[i][1] = Integer.parseInt(st.nextToken());
        }

        consult(0,0,0);

        System.out.println(max);
    }


}
