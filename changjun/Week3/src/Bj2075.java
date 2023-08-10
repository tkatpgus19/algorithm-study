import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
//N번째 큰 수
public class Bj2075 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[][] arr = new int[N][N];
        int[] arrIdx = new int[N]; // 열의 각 선택된 횟수 기록.

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int max = -1_000_000_000;
        //N번째 큰 수 찾기
        for(int i=0;i<N;i++){
            max = -1_000_000_000;
            int maxIdx = 0;
            // 각 열의 가장 아래 위치한 숫자들만 비교 
            for(int j=0;j<N;j++){
                int tmp = arr[N-1-arrIdx[j]][j];
                if(tmp>max){
                    max = tmp;
                    maxIdx = j;
                }
            }
            arrIdx[maxIdx]++; // 선택한 열 인덱스 증가.
        }
        System.out.println(max);
    }
}
