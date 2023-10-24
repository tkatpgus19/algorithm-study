import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;


/*  2579 계단 오르기
 *  점프는 이단 점프(N-2번 계단에서 N번 계단으로)와 1단 점프(N-1 -> N) 으로가 존재
 *  점프의 조건은 연속된 3개의 계단을 밟을 수 없다는 것 (1,2,3 차례로 못 밟음)
 *  계단마다 가치가 정해져있고, 맨 마지막 계단은 꼭 밟아야 할 때,
 *  마지막 계단으로 왔을 시, 얻을 수 있는 최대 가치는?
 */

/*  문제 풀이 방법
 *  dp[N]을 N번 계단 까지 고려했을 때 최대 가치라고 정하고 DP 배열의 맨 마지막 값 dp[N-1]을 구한다.
 *  <<주의점!!>>
 *  dp[N]을 bottom-up 방식으로 구하기 위해 이전 값들을 이용하려고 할때 dp[N-1]을 이용하면 안된다.
 *  왜냐하면 N-1까지의 최대 가치가 N-2번 계단을 밟고 나온 값인지, 안 밟고 나온 값인지 알 수  없기 때문이다.
 *
 *  따라서 N번째 최대 가치 dp[N]을 구하기 위해서는 밑의 두 후보를 고려해야 하는데,
 * (1) dp[N-2] + 계단 N의 가치 (-> 이단 점프로 바로 온 경우)
 * (2) dp[N-3] + 계단 N-1의 가치 + 계단 N의 가치 (-> N-2를 밟지 않고 1단 점프로 온 경우 중 최대 가치)
 *  이 둘을 고려하면 된다.
 */

public class b2579 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        // 배열은 모두 실제 계단의 인덱스와 맞추기 위해 N+1 한다.
        // 계단 값 자체를 저장할 배열
        int [] stair = new int[N+1];
        // i번 계단까지 왔을 때의 최대가치
        int [] dp = new int[N+1];



        for (int i = 1; i <= N; i++) {
            stair[i] = Integer.parseInt(br.readLine());
        }

        // index == 0은 계단 없는 평지, 1번은 최대가치는 자기 가치 스스로이니까 그 값 저장 (문제에서 음수 가치는 없다고 함.)
        // index == 2는 평지에서 이단 점프, 1단점프씩 온 경우 중 최대값 저장(2가지 경우만 고려하면 됨. / 평지는 계단으로 생각하지 않음.)
        dp[0] = 0;
        dp[1] = stair[1];


        //-------------------------* 에러 처리 *--------------------------------
        // 계단이 1이하이면 밑의 dp를 적용할 방법이 없음. 이건 위의 계산된 해를 그대로 출력
        if(N <2){
            System.out.println(dp[N]);
            return;
        }

        dp[2] = Math.max(dp[0]+stair[2], dp[1]+stair[2]);

        // 계단이 2이하이면 밑의 dp를 적용할 방법이 없음. 이건 위의 계산된 해를 그대로 출력
        if(N <3){
            System.out.println(dp[N]);
            return;
        }


        //-------------------------* 진짜 DP *--------------------------------
        // 맨 위 문제 풀이에서 설명한 내용
        // 이단 점프 뛰었을 때의 최대값 vs N-2 계단 안 밝고 N-1에서 1단 점프 뛰었을 때의 최대값
        for (int i = 3; i <= N; i++) {
            dp[i] = Math.max(dp[i-2] + stair[i], dp[i-3]+stair[i-1]+ stair[i]);
        }

        System.out.println(dp[N]);
    }
}