import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

/*
 * Bj11663 : 선분 위의 점
 */
public class Bj11663 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));    // 빠른 입력
        StringBuilder sb = new StringBuilder();    // 빠른 출력
        StringTokenizer st = new StringTokenizer(br.readLine());    // 공백으로 나누기

        int N = Integer.parseInt(st.nextToken());    // N 입력 받기
        int M = Integer.parseInt(st.nextToken());    // M 입력 받기
        
        int[] dot = new int[N];    // 좌표 입력 받기
        st = new StringTokenizer(br.readLine());    // 공백으로 나누기
        for(int i = 0; i < N; i++) {
            dot[i] = Integer.parseInt(st.nextToken());    // 입력 받기
        }
        Arrays.sort(dot);    // 이분 탐색하기 위해 정렬
        for(int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());    // 공백 나누기
            int start = Integer.parseInt(st.nextToken());    // 시작점 입력 받기
            int end = Integer.parseInt(st.nextToken());    // 끝점 입력 받기
            
            int i = 0, j = N - 1, startIdx = 0;    // 이분 탐색의 시작 인덱스, 끝 인덱스, 중간값 startIdx
            while(i <= j) {    // 시작 인덱스가 끝 인덱스보다 작을 때까지 반복
                startIdx = (j - i) / 2 + i;    // 중간값
                if(start > dot[startIdx]) i = startIdx + 1;    // 시작점이 더 크면 시작 인덱스는 중간값 + 1
                else if (start < dot[startIdx]) j = startIdx - 1;    // 시작점이 더 작으면 끝 인덱스는 중간값 - 1
                else break;    // 같으면 반복문 종료
            }
            if(startIdx >= 0 && startIdx < N && start > dot[startIdx]) startIdx++;    // 중간값이 좌표 안쪽인데 시작점보다 작으면 중간값 증가
            
            i = 0;    // 시작 인덱스 초기화
            j = N - 1;    // 끝 인덱스 초기화
            int endIdx = 0;    // 중간값
            while(i <= j) {     // 시작 인덱스가 끝 인덱스보다 작을 때까지 반복
                endIdx = (j - i) / 2 + i;    // 중간값
                if(end > dot[endIdx]) i = endIdx + 1;    // 끝점이 더 크면 끝 인덱스는 중간값 + 1
                else if (end < dot[endIdx]) j = endIdx - 1;    // 끝점이 더 작으면 끝 인덱스는 중간값 - 1
                else break;    // 같으면 반복문 종료
            }
            
            if(endIdx >= 0 && endIdx < N && end < dot[endIdx]) endIdx--;    // 중간값이 좌표 안쪽인데 끝점보다 작으면 중간값 감소
            
            if(endIdx < startIdx) sb.append(0);    // 계산된 끝 인덱스가 시작 인덱스보다 작으면 0 출력
            else sb.append(endIdx - startIdx + 1);    // 아니면 개수 계산해서 출력
            
            sb.append("\n");    // 줄바꿈
        }
        
        System.out.print(sb);    // sb 출력
    }

}