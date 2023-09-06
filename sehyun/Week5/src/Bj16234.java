import java.io.*;
import java.util.*;

/**
 * [16234번 인구 이동]
 * 문제: 인접한 국가의 인구수 차가 주어진 범위 안에 있을 경우 해당 국가들의 인구수 평균으로 갱신하고, 이 과정을 반복했을 때 반복 횟수를 구하시오.
 * 해결: bfs로 연합해야 하는 국가들을 탐색해 저장하고, 인구를 이동시키고 이 과정을 반복한다.
 */
public class Bj16234 {
    static int[][] graph;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 입력
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        // bfs를 위한 배열들 초기화
        graph = new int[N][N];
        visited = new boolean[N][N];

        // 그래프 초기화
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int answer = 0;
        // 단순히 무한 반복문 탈출을 위한 변수 선언
        int cnt = 0;

        // 더이상 인구 이동이 없을 때 까지 무한 반복 bfs
        while(true) {
            cnt = 0;
            // visited 갱신을 하지 않으면 이전 결과값이 반영되므로 꼭 갱신
            visited= new boolean[N][N];

            // 탐색되지 않은 지역은 bfs로 탐색
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    if(!visited[i][j]) {
                        // true(인구이동 발생)이면 cnt++, false(인구이동 없음-완료)이면 스킵
                        if(bfs(i, j, N, L, R)) {
                            cnt++;
                        }
                    }
                }
            }
            // 한번의 인구이동도 발생하지 않았다면 인구이동이 모두 끝난 것이므로 탈출
            if(cnt == 0) {
                break;
            }
            answer++;
        }
        System.out.println(answer);

    }
    
    // bfs를 통해 인접 국가들 탐색
    static boolean bfs(int sx, int sy, int N, int L, int R) {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        // 범위에 해당되는 국가들의 좌표값 저장을 위해 country 리스트 선언
        List<int[]> country = new ArrayList<>();
        Deque<int[]> queue = new ArrayDeque<>();
        
        queue.addLast(new int[] {sx, sy});
        while(!queue.isEmpty()) {
            int[] tmp = queue.removeLast();
            int x = tmp[0];
            int y = tmp[1];
            for(int i=0; i<4; i++) {
                int nx = x+dx[i];
                int ny = y+dy[i];
                if(nx<0 || ny<0 || nx>=N || ny>=N) {
                    continue;
                }
                if(!visited[nx][ny]) {
                    // 인구수 차가 주어진 범위일 때 좌표값 저장
                    int diff = Math.abs(graph[nx][ny] - graph[x][y]);
                    if(diff>=L && diff<=R) {
                        visited[nx][ny] = true;
                        country.add(new int[] {nx, ny});
                        queue.addLast(new int[] {nx, ny});
                    }
                }
            }
        }
        
        // 연합이 존재하면 인구이동 후 true 반환
        if(!country.isEmpty()) {
            int avg = 0;
            // 저장된 연합 국가들 좌표를 뽑아서 인구수 계산 및 갱신
            for(int[] c: country) {
                avg += graph[c[0]][c[1]];
            }
            avg /= country.size();
            for(int[] c: country) {
                graph[c[0]][c[1]] = avg;
            }
            return true;
        }
        // 연합이 성사되지 않았으면 false 반환
        return false;
    }
}