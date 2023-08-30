import java.io.*;
import java.util.*;

/**
 * [14888번 연산자 끼워넣기]
 * 문제: 숫자들과 연산자들이 주어질 때, 이들로 만든 계산식의 연산 결과의 최대, 최솟값을 구하시오.
 * 해결: 모든 경우의 수식을 만들어 계산해 결과값의 최대, 최솟값을 찾는다.
 */
public class Bj14888 {
    static int[] operators = new int[4];
    static int[] nums;
    static int MAX = Integer.MIN_VALUE;
    static int MIN = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        nums = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<4; i++) {
            operators[i] = Integer.parseInt(st.nextToken());
        }

        // 재귀함수 호출
        calculate(nums[0], 1);
        sb.append(MAX).append('\n').append(MIN);
        System.out.println(sb);

        // 메모리: 15612KB, 시간: 132ms
    }

    // 조합을 구하고 계산해 결과값으로 최대, 최솟값을 갱신하는 메소드
    static void calculate(int num, int idx) {
        // 모든 피연산자를 사용한 결과값이 도출 되었을 때, 최댓값 갱신
        if(idx == nums.length) {
            MAX = Math.max(MAX, num);
            MIN = Math.min(MIN, num);
            return;
        }
        for(int i=0; i<4; i++) {
            // 연산자가 하나 이상 있을 때 하나를 소모해 연산 수행
            if(operators[i] > 0) {
                operators[i]--;
                switch(i) {
                    case 0:
                        calculate(num+nums[idx], idx+1);
                        break;
                    case 1:
                        calculate(num-nums[idx], idx+1);
                        break;
                    case 2:
                        calculate(num*nums[idx], idx+1);
                        break;
                    case 3:
                        calculate(num/nums[idx], idx+1);
                        break;
                }
                // 백트래킹을 위해 뺐던 연산자 수 다시 복구
                operators[i]++;
            }
        }
    }
}