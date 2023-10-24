import java.io.*;
import java.util.*;

/**
 * [19637번: IF문 좀 대신 써줘]
 * 문제: 전투력이 주어질 때 전투력에 해당하는 칭호를 출력하시오.
 * 해결: 주어진 전투력 기준들을 정렬해 일차원 그래프의 좌표로 보고 이분 탐색을 수행하며 어디에 속하는지를 찾고 출력한다.
 */
public class Bj19637{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 특정 전투력에 해당하는 칭호 입력을 위한 배열 선언
        String[] powerName = new String[N];
        int[] powerNum = new int[N];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            powerName[i] = st.nextToken();
            powerNum[i] = Integer.parseInt(st.nextToken());
        }

        // 전투력을 하나씩 입력받아 이분탐색 후 어느 구간에 속하는지 출력
        for(int i=0; i<M; i++){
            int power = Integer.parseInt(br.readLine());
            int left = 0;
            int right = N;
            int answer = 0;

            while(left <= right){
                int mid = (left+right)/2;
                
                // 해당 전투력이 칭호 기준보다 작은 경우 좌측으로 이동
                if(power <= powerNum[mid]){
                    right = mid-1;
                    answer = mid;
                }
                // 전투력이 칭호 기준보다 큰 경우
                else{
                    left = mid+1;
                }
            }
            // 해당 인덱스의 전투력 칭호를 저장
            sb.append(powerName[answer]).append('\n');
        }
        System.out.println(sb);
        // 메모리: 54012KB, 시간: 532ms
    }
}