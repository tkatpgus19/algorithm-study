import java.io.*;
import java.util.*;

/**
 * [14719번 빗물]
 * 문제: 블록이 주어질 때, 고이는 빗물의 총량을 구하시오.
 * 해결: 결국 좌, 우 최댓값들에 둘러쌓인 부분이 빗물이 고이는 부분이다. 블록을 한칸씩 이동하며 좌 최댓값, 우 최댓값을 갱신하고, 둘 중 작은 값과 블록의 높이를 뺀 값을 더해간다.
 */
public class Bj14719 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int[] blocks = new int[W];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<W; i++) {
            blocks[i] = Integer.parseInt(st.nextToken());
        }

        int answer = 0;

        // 좌 최댓값(초기는 0번째 블록), 우 최댓값 저장을 위한 변수
        int leftKing = blocks[0];
        int rightKing = 0;

        // 양 끝단은 빗물이 고일 수 없으므로 제외하고 탐색
        for(int i=1; i<W-1; i++) {
            // 왼쪽 최댓값을 계속해서 갱신
            for(int j=0; j<i; j++) {
                // 왼쪽 최댓값이 새로 갱신되었다는 말은 오른쪽으로 탐색을 진행하며 오른쪽 최댓값이 왼쪽 최댓값이 되었다는 뜻이므로 오른쪽 최댓값 0으로 갱신
                if(blocks[j] >= leftKing) {
                    leftKing = blocks[j];
                    rightKing = 0;
                }
            }
            // 오른쪽 최댓값을 계속해서 갱신
            for(int j=i; j<W; j++) {
                if(blocks[j] >= rightKing) {
                    rightKing = blocks[j];
                }
            }
            
            // 빗물은 좌, 우 최댓값 중 두번째로 큰 최댓값과 블록을 뺀 만큼 고이게 됨
            int value = Math.min(leftKing, rightKing) - blocks[i];

            // 이상한 경우의 수 사전 차단(value가 음수가 되는 경우 더하지 않음)
            if(value >= 0) {
                answer += value;
            }
        }
        System.out.println(answer);

        // 메모리: 14280KB, 시간: 136ms
    }
}