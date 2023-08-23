import java.util.*;
import java.io.*;

/**
 * [15686번 치킨 배달]
 * 문제: M개의 치킨집을 남기고 나머지를 폐점할 때 도시 치킨거리의 최솟값을 구하시오.
 * 해결: 조합을 통해 M개의 치킨집을 선택한다. 이후 치킨거리를 계산하는데, 특정 가정집이 어떤 치킨집과 가장 가까운지 알 수 없으므로 모든 거리를 계산해 구하고 최솟값을 사용한다.
 */
public class Bj15686 {
    static boolean[] visited;
    static char[][] graph;
    //치킨집, 가정집 좌표 저장을 위한 리스트 선언
    static List<int[]> chicken = new ArrayList<>();
    static List<int[]> house = new ArrayList<>();
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 입력
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        graph = new char[N][N];

        // 지도정보 입력
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                char tmp = st.nextToken().charAt(0);
                // 치킨집이면 치킨집 리스트에 좌표 추가
                if(tmp == '2') {
                    chicken.add(new int[] {i, j});
                }
                // 가정집이면 가정집 리스트에 좌표 추가
                else if(tmp == '1') {
                    house.add(new int[] {i, j});
                }
                graph[i][j] = tmp;
            }
        }
        // M개의 치킨집 조합을 구하기 위해 방문체크 배열 선언
        visited = new boolean[chicken.size()];
        comb(0, 0, M);

        System.out.println(answer);

        // 메모리: 15560KB, 시간: 216ms
    }

    // M개의 치킨집 뽑기
    static void comb(int start, int depth, int M) {
        // 조합을 구했으면 계산하고, 반환된 값이 최솟값인지 체크 후 갱신
        if(depth == M) {
            int tmp = count();
            if(tmp < answer) {
                answer = tmp;
            }
            return;
        }

        for(int i= start; i<visited.length; i++) {
            visited[i] = true;
            comb(i+1, depth+1, M);
            visited[i] = false;
        }

    }

    // 치킨 거리 계산
    static int count() {
        int total = 0;

        // 가정집이 어느 치킨집과 가장 가까운지 알 수 없음
        // 따라서 한 가정집과 모든 치킨집과의 거리를 계산한 후 최솟값을 사용
        
        // 가정집 하나 추출
        for(int[] h: house) {
            int distance = Integer.MAX_VALUE;
            
            // 모든 치킨집 마다 해당 가정집과의 거리 계산
            for(int i=0; i<visited.length; i++) {
                if(visited[i]) {
                    int tmp = Math.abs(chicken.get(i)[0]-h[0]) + Math.abs(chicken.get(i)[1]-h[1]);
                    // 가정집과 치킨집의 거리가 가장 가까운 값을 치킨집과 가정집 사이 거리로 사용
                    if(tmp < distance) {
                        distance = tmp;
                    }
                }
            }
            total += distance;
        }
        return total;
    }
}
