import java.io.*;
import java.util.*;

/**
 * [15683번: 감시]
 * 문제: 2차원 배열에 1~5번 카메라가 위치하고 감시하는 방향을 90도씩 돌릴 수 있을 때, 사각지대의 최솟값을 구하시오.
 * 해결: 모든 경우의 사각지대를 각각 체크해 최솟값을 찾는다.
 */
public class Bj15683 {
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int answer = Integer.MAX_VALUE;
    
    // 카메라 찾는 시간을 줄이기 위한 리스트
    static List<int[]> camera = new ArrayList<>();
    static int N;
    static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[][] graph = new int[N][M];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                graph[i][j] = Integer.parseInt(st.nextToken());
                // 카메라면 카메라 좌표 저장
                if(graph[i][j] != 0 && graph[i][j] != 6){
                    camera.add(new int[]{i,j});
                }
            }
        }
        recur(graph, 0);
        System.out.println(answer);

        // 메모리: 185208KB, 시간: 592ms
    }

    // 재귀를 통해 카메라가 바라볼 수 있는 모든 경우 탐색
    static void recur(int[][] graph, int start){
        // 카메라의 좌표만 이용해 최소로 반복문 실행
        // 이전에 이미 탐색한 카메라의 중복 탐색을 방지하기 위해 start 부터 시작
        for (int cnt = start; cnt < camera.size(); cnt++){
            int[] pos = camera.get(cnt);
            int x = pos[0];
            int y = pos[1];
            
            // 몇번 카메라인지 체크
            switch (graph[x][y]) {
                case 1:
                    for (int idx = 0; idx < 4; idx++) {
                        // 원본 배열을 조작하면 추후 차질이 생기므로 복제한 배열에 조작
                        int[][] tmpGraph = copyGraph(graph);

                        // 감시 영역을 배열에 반영
                        watch(x, y, idx, tmpGraph);

                        // 결과가 반영된 배열을 재귀로 넘기고 현재 카메라 체크 표시
                        recur(tmpGraph, start+1);
                    }
                    break;
                case 2:
                    for (int idx = 0; idx < 2; idx++) {
                        int[][] tmpGraph = copyGraph(graph);
                        watch(x, y, idx, tmpGraph);
                        watch(x, y, idx + 2, tmpGraph);
                        recur(tmpGraph, start+1);
                    }
                    break;
                case 3:
                    for (int idx = 0; idx < 4; idx++) {
                        int[][] tmpGraph = copyGraph(graph);
                        watch(x, y, idx, tmpGraph);
                        watch(x, y, (idx + 1) % 4, tmpGraph);
                        recur(tmpGraph, start+1);
                    }
                    break;
                case 4:
                    for (int idx = 0; idx < 4; idx++) {
                        int[][] tmpGraph = copyGraph(graph);
                        watch(x, y, idx, tmpGraph);
                        watch(x, y, (idx + 1) % 4, tmpGraph);
                        watch(x, y, (idx + 2) % 4, tmpGraph);
                        recur(tmpGraph, start+1);
                    }
                    break;
                case 5:
                    int[][] tmpGraph = copyGraph(graph);
                    for (int idx = 0; idx < 4; idx++) {
                        watch(x, y, idx, tmpGraph);
                    }
                    recur(tmpGraph, start+1);
                    break;
            }

            // 재귀를 빠져 나오고도 해당 카메라가 탐색됐음을 알리기 위해 start+1
            start++;
        }
        
        // 카메라 감시가 끝나면 사각지대 체크
        count(graph);
    }

    // 감시받는 부분을 모두 8로 바꿔주는 메소드
    static void watch(int x, int y, int idx, int[][] tmpGraph){
        while(true){
            x += dx[idx];
            y += dy[idx];
            if(x<0 || y<0 || x>=N || y>=M || tmpGraph[x][y] == 6){
                break;
            }
            if(tmpGraph[x][y] == 0){
                tmpGraph[x][y] = 8;
            }
        }
    }
    
    // 조작할 그래프 배열을 복사하는 메소드
    static int[][] copyGraph(int[][] graph){
        int[][] tmpGraph = new int[N][M];
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                tmpGraph[i][j] = graph[i][j];
            }
        }
        return tmpGraph;
    }

    // 사각지대 체크 후, 최솟값 갱신
    static void count(int[][] tmpGraph){
        int cnt = 0;
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(tmpGraph[i][j] == 0){
                    cnt++;
                }
            }
        }
        answer = Math.min(answer, cnt);
    }
}