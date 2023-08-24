import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// 스타트와 링사
public class Bj14889 {
    static int N;
    static int[] team;
    static int[] isJoined;
    static int min = Integer.MAX_VALUE;
    static int[][] s;

    private static void soccer(int dep) {
        // 종료 조건
        if(dep == N/2){
            int teamStart = 0;
            int teamLink = 0;

            // start팀 능력치 합.
            for(int a: team){
                for(int b: team){
                    teamStart+=s[a][b];
                }
            }

            // link팀 능력치 합 구하기..
            int[][] tmp = new int[N][N]; // s[][] 짭.

            // s[][] 카피.
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    tmp[i][j]= s[i][j];
                }
            }

            // team에 있는 요소들 행, 열을 전부 0으로.
            for(int n:team) {
                for(int i=0;i<N;i++){
                    tmp[i][n] = 0;
                    tmp[n][i] = 0;
                }
            }

            // link팀 능력치 합.
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    teamLink+=tmp[i][j];
                }
            }

            int dif = Math.abs(teamStart - teamLink);
            if(dif<min) min = dif;
            return;
        }

        for(int i=0;i<N;i++){
            // 이미 팀에 속한 선수면 종료.
            if(isJoined[i] != 0){
                continue;
            }
            // dep>=1 이고, 오름차순 아니면 종료.
            if(dep!=0 && team[dep-1] >= i){
                continue;
            }
            // 1번이 들어갈 때, 아닐 때 중 하나만 구하면 된다.
            if(team[0] == 1)
                break;

            isJoined[i] = 1;
            team[dep] = i;
            soccer(dep+1);
            isJoined[i] = 0;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        team = new int[N/2];
        isJoined = new int[N]; // 팀에 속했는 지.
        s = new int[N][N];

        // 입력.
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                s[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        soccer(0);

        System.out.println(min);
    }


}
