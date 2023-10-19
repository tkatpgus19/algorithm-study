import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*  22857 가장 긴 짝수 연속한 부분 수열(small)
 *  N개의 길이의 수열이 주어지고, K번 홀수를 삭제할 수 있는 권한이 주어진다.
 *  주어진 수열에서 만들 수 있는 가장 긴 짝수 연속 수열의 길이를 구하여라
 */

/*  문제 풀이 방법
    (1) DP로 시도 해봤는데, DP로 안 풀림 -> 투 포인터로 푼다
    (2) 투 포인터의 양 끝을 p1, p2라고 한다. 현재 포인터 내에 가지고 있는 홀수 갯수를 담는 변수를 Z라 한다.
    (3) 투 포인터 내에 홀수가 K개 이하일 때는 p2를 계속 움직임.
    (4) 만약 p2를 옮겼는데, 홀수가 K개 초과할 경우, p2를 직전의 값으로 다시 옮긴 채로 짝수의 갯수를 센다.
    (5) 그 뒤로 p1을 앞으로 옮긴다. 이때 p1이 옮겨지며 버려진 수가 짝수이면, 이제 앞으로 나아가면 못 쓰는 값이 버리고
        홀수이면, 삭제가능한 홀수의 카운트Z를 1줄인다. Z<K가 되면 이제 P2를 다시 움직인다.
    (6) 이렇게 수열 끝까지 반복해서 최대의 짝수 값을 찾아서 출력한다!

    WOW!! 이 녀석은 골드로 가야할 문제이다!
*/

public class b22857 {

    static int N, K;
    static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());

        // 값 받기
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int p1 = 0, p2 = 0, even = 0, odd = 0;

        for (int i = 0; i < N; i++) {

            // 포인터 내의 홀수 개수가 아직 K개 이하일 경우, 짝수면 값 받고, p2 늘리고,
            // 홀수면 odd 카운트 늘리고 p2 늘리고
            if (odd <= K) {

                if (arr[p2] % 2 == 0) {
                    even++;
                    p2++;
                } else {
                    odd++;
                    p2++;
                    // 한칸 더 가서 odd++ 했는데, 포인터 내의 홀수 개수가 K개 초과한 경우
                    if (odd > K) {
                        max = Math.max(even, max);

                        // 투 포인터 내의 홀수의 갯수가 다시 K개 안쪽이 될 때까지 P1을 옮긴다.
                        do {
                            if (arr[p1] % 2 == 0) {
                                even--;
                            } else {
                                odd--;
                            }
                            p1++;

                        } while (odd > K);

                    }
                }
            }

            if (p2 >= N ) {
                max = Math.max(even, max);
                break;
            }
//            System.out.println(i + "번째 loop == " + "p1: " + p1 + " p2: " + p2 + " even: " + even + " odd: " + odd + " max: "+ max);
        }

        System.out.println(max);

    }
}
