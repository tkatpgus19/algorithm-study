import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// 치킨 거리
public class Bj15686 {
    static int N,M,H,C;
    static int[][] addH;
    static int[][] addC;
    static int[][] distance;
    static int[] shutDownChicken;
    static int minCityDistance = Integer.MAX_VALUE;
    static void shutDown(int dep){
        if(dep == M){
            int cityDistance = 0; // 도시 치킨거리

            // 만약 폐점 시킬 가계가 없다면.
            if(C == M){
                for(int i=0;i<H;i++) {
                    int minDistance = 100; // 갱신이 안 된다.
                    for (int j = 0; j < C; j++) {
                        if (distance[i][j] < minDistance) {
                            minDistance = distance[i][j];
                        }
                    }
                    cityDistance += minDistance;
                }
                minCityDistance = cityDistance;
                return;
            }

            // 남은 치킨거리 중 최소값 선택.
            for(int i=0;i<H;i++){
                int minDistance = 100; // 갱신이 안 된다.
                for(int j:shutDownChicken){
                    if(distance[i][j] < minDistance){
                        minDistance = distance[i][j];
                    }
                }
                cityDistance+=minDistance;
            }
            if(cityDistance < minCityDistance)
                minCityDistance = cityDistance;
            return;
        }

        for(int i=0;i<C;i++){
            // 순열 조건.
            if(dep!=0 && shutDownChicken[dep-1] >= i){
                continue;
            }

            shutDownChicken[dep] = i;
            shutDown(dep+1);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 맵 크기
        M = Integer.parseInt(st.nextToken()); // 남길 치킨집 수

        H = 0; // 집
        C = 0; // 치킨집

        int[][] map = new int[N][N];

        addH = new int[100][2]; // 집 주소
        addC = new int[13][2]; // 치킨집 주소

        // 입력
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                int tmp =  Integer.parseInt(st.nextToken());
                // 집 / 치킨집 개수 세기.
                if(tmp == 1){
                    addH[H][0] = i;
                    addH[H][1] = j;
                    H++;
                } else if (tmp == 2){
                    addC[C][0] = i;
                    addC[C][1] = j;
                    C++;
                }
                map[i][j] = tmp;
            }
        }

        distance = new int[H][C]; // 치킨 거리.
        shutDownChicken = new int[M]; // 남길 치킨집들.

        // 치킨 거리 계산.
        for(int i=0;i<H;i++){
            for(int j=0;j<C;j++){
                distance[i][j] += Math.abs(addH[i][0] - addC[j][0]);
                distance[i][j] += Math.abs(addH[i][1] - addC[j][1]);
            }
        }

        shutDown(0);
        System.out.println(minCityDistance);
    }
}
