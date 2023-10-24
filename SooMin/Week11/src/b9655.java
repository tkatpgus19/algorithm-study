import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


/*
 *  9655 돌 게임
 *  베스킨라빈스 31같은 게임.
 *  총 돌의 개수가 주어지고 플레이어는 자신의 턴에 항상 돌을 1개 내지는 3개만 가져갈 수 있다.
 *  마지막 돌을 가져가는 사람이 WINNER
 */

/*  문제 풀이
    (1) 걍 짝수면 창영이가, 홀수면 상근이가 무조건 이김
*/

public class b9655 {

    static HashMap<String, Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        if(N%2 == 0){
            System.out.println("CY");
        }else{
            System.out.println("SK");
        }


    }
}