import java.util.*;
import java.io.*;

/**
 * [14889번 스타트와 링크]
 * 문제: 굳이굳이 시너지 포인트 제도를 만들어 조합에 따라 팀별 능력치 점수를 더하고, 두 팀의 능력치 점수 차의 최솟값을 구하시오.
 * 해결: 조합을 구해 팀을 나누고 점수를 계산해 최솟값을 갱신한다.
 */
public class Bj14889 {
    static int[][] graph;
    static boolean[] visited;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        graph = new int[N][N];

        // 능력치 입력
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[N];
        comb(0, 0, N);

        System.out.println(answer);

        // 메모리: 31172KB, 시간: 296ms
    }

    static void comb(int start, int depth, int N){
        // 팀 인원(N/2명)이 모두 정해졌으면 팀원의 번호를 배열에 저장
        if(depth == N/2){
            int[] teamStart = new int[N/2];
            int[] teamLink = new int[N/2];
            int idx1 = 0;
            int idx2 = 0;

            // visited = [false, false, true, true] or [false, true, true, false] or ...
            // visited의 요소가 참인지 거짓인지에 따라 구분하여 팀원 번호를 저장
            for(int i=0; i<N; i++){
                if(visited[i]){
                    teamStart[idx1++] = i;
                }
                else{
                    teamLink[idx2++] = i;
                }
            }
            // 계산
            calculate(teamStart, teamLink);
            return;
        }
        
        // visited 배열로 팀 조합 탐색
        for(int i=start; i<N/2+N/2-1; i++){
            visited[i] = true;
            comb(i+1, depth+1, N);
            visited[i] = false;
        }
    }

    // 능력치 점수 계산
    static void calculate(int[] teamStart, int[] teamLink){
        int start = 0;
        int link = 0;

        // 각 좌표에 위치한 값 더하기
        // 1, 2, 3이라 가정하면 G(1,2)+G(2,1) + G(1,3)+G(3,1) + G(2,3)+G(3,2) 계산
        for(int i=0; i<teamStart.length-1; i++){
            for(int j=i+1; j<teamStart.length; j++){
                start += graph[teamStart[i]][teamStart[j]] + graph[teamStart[j]][teamStart[i]];
            }
        }
        for(int i=0; i<teamLink.length-1; i++){
            for(int j=i+1; j<teamLink.length; j++){
                link += graph[teamLink[i]][teamLink[j]] + graph[teamLink[j]][teamLink[i]];
            }
        }
        // 차가 최솟값이면 갱신
        int total = Math.abs(start-link);
        if(total <= answer){
            answer = total;
        }
    }
}
