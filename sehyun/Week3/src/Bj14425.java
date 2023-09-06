import java.io.*;
import java.util.*;

/**
 * [14425번 문자열 집합]
 * 문제: 저장될 문자열이 주어지고, 이후에 주어지는 문자열 중 몇개가 저장된 문자열과 일치하는지 출력하시오.
 * 해결: 입력이 까다롭지 않아 단순히 set에 저장하고 입력받는 문자열이 있는지만 체크해서 수를 세면 된다.
 */
public class Bj14425 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        Set<String> set = new HashSet<>();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        for(int i=0; i<N; i++){
            set.add(br.readLine());
        }

        int answer = 0;
        for(int i=0; i<M; i++){
            if(set.contains(br.readLine())){
                answer++;
            }
        }
        System.out.println(answer);

        // 메모리: 31248KB, 시간: 412ms
    }
}
