import java.io.*;
import java.util.*;

/**
 * [2578번 빙고]
 * 문제: 숫자가 채워진 5x5 빙고판이 주어지고 25개의 정수가 주어진다. 몇 번째 정수에 빙고 3줄이 완성되는지 구하시오.
 * 해결: 불리는 숫자들을 모두 0으로 바꾸고, 각 행과 열, 대각선의 합을 구해 0인지(빙고인지) 체크한다.
 */
public class Bj2578 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int[][] numArr = new int[5][5];
        int[][] calledNum = new int[5][5];

        // 빙고판 입력
        for(int i=0; i<5; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<5; j++){
                numArr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 불릴 정수 입력
        for(int i=0; i<5; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<5; j++){
                calledNum[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 불리는 정수 개수 카운트
        int answer = 0;

        // 정수를 차례로 하나씩 호출
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                answer++;

                // 빙고판을 탐색해 불린 정수를 찾아 0으로 치환
                for(int x=0; x<5; x++){
                    for(int y=0; y<5; y++){
                        if(numArr[x][y] == calledNum[i][j]){
                            numArr[x][y] = 0;
                            break;
                        }
                    }
                }
                // 가장 운이 좋은 경우 12개가 불렸을 때 빙고 달성
                // 전까지는 비교 메소드 호출 금지
                if(answer < 12){
                    continue;
                }
                // 빙고 체크
                if(checkBingo(numArr)){
                    System.out.println(answer);
                    return;
                }
            }
        }
        // 메모리: 14088KB, 시간: 120ms
    }

    // 빙고인지 체크하는 메소드
    static boolean checkBingo(int[][] numArr){

        // 빙고 개수 카운트
        int cnt = 0;

        // 좌 대각, 우 대각 체크
        int[] check = new int[2];
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                // 좌 대각
                if(i == j){
                    check[0] += numArr[i][j];
                }
                // 우 대각
                if(i+j == 4){
                    check[1] += numArr[i][j];
                }
            }
        }
        // 각 대각 요소들의 합이 0(빙고)일때 카운트
        if(check[0] == 0){
            cnt++;
        }
        if(check[1] == 0){
            cnt++;
        }
        check[0] = 0;
        check[1] = 0;

        // 가로, 세로 체크
        for(int i=0; i<5; i++){
            if(cnt >= 3){
                break;
            }
            for(int j=0; j<5; j++){
                check[0] += numArr[i][j];
                check[1] += numArr[j][i];
            }
            if(check[0] == 0){
                cnt++;
            }
            if(check[1] == 0){
                cnt++;
            }
            check[0] = 0;
            check[1] = 0;
        }

        // 빙고가 3개 이상
        if(cnt >= 3){
            return true;
        }

        return false;
    }
}