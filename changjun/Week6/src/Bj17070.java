import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// 파이프 이동하기
// 파이브는 벽을 긁으면 안된다. -> 주위에 벽이 있다면 대각선은 안 된다.
// 각 도달한 방향별 개수를 따로.
public class Bj17070 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][N];
        int[][][] mapD = new int[N][N][3];

        int dx[] = {0,1,1};
        int dy[] = {1,0,1};

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        mapD[0][1][0] = 1; // 초기 상태. 가로

        for(int i=0;i<N;i++){
            for(int j=2;j<N;j++){
                if(map[i][j] == 1) continue;
                for(int d=0;d<3;d++){
                    int bx = i - dx[d];
                    int by = j - dy[d];

                    if(bx<0||bx>=N||by<0||by>=N) continue;
                    if(d==2 && (map[i-1][j] == 1 || map[i][j-1] == 1)) continue;

                    mapD[i][j][d] = mapD[bx][by][0] + mapD[bx][by][1] + mapD[bx][by][2];
                    if(d!=2) mapD[i][j][d] -= mapD[bx][by][(d+1)%2];
//                    if(d==2 && map[i-1][j] == 1) mapD[i][j][d] -= mapD[bx][by][0];
//                    if(d==2 && map[i][j-1] == 1) mapD[i][j][d] -= mapD[bx][by][1];
                }
            }
        }
//        for(int d=0;d<3;d++){
//            for(int i=0;i<N;i++){
//                for(int j=0;j<N;j++){
//                    System.out.print(mapD[i][j][d]+" ");
//                }
//                System.out.println();
//            }
//
//            System.out.println("----------");
//        }

        System.out.println(mapD[N-1][N-1][0] + mapD[N-1][N-1][1] + mapD[N-1][N-1][2]);

    }
}
