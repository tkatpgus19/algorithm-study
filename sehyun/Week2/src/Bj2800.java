import java.io.*;
import java.util.*;

/**
 * 괄호제거
 * 문제 : 괄호가 포함된 수식이 주어질 때, 괄호를 제거하여 나올 수 있는 수식을 모두 출력하시오.
 */
public class Bj2800 {
    static int N;
    static boolean[] visited;
    static int[] numArr;
    static int[] output;
    static char[] charArr;
    static List<int[]> bracketPos = new ArrayList<>();
    static List<String> answer = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Deque<Integer> stack = new ArrayDeque<>();
        charArr = br.readLine().toCharArray();

        // 괄호 쌍의 인덱스를 저장한다.
        for(int i=0; i<charArr.length; i++){
            if(charArr[i] == '('){
                stack.addLast(i);
            } else if(charArr[i] == ')'){
                bracketPos.add(new int[]{i, stack.removeLast()});
            }
        }

        // 조합 구현을 위한 준비
        N = bracketPos.size();
        numArr = new int[N];
        for(int i=0; i<N; i++){
            numArr[i] = i+1;
        }
        visited = new boolean[N];
        output = new int[N];

        // 각각 1개, 2개, ... N개의 조합을 구한다.
        for(int i=0; i<N; i++){
            comb(0, 0, i+1);
        }

        // 중복제거
        Set<String> tmp = new HashSet<>(answer);
        answer = new ArrayList<>(tmp);
        Collections.sort(answer);

        for(String a : answer){
            System.out.println(a);
        }
    }

    // 조합
    static void comb(int cnt, int start, int max){
        if(cnt == max){
            // 이번에 제외할 괄호의 인덱스를 check 리스트에 저장한다.
            List<Integer> check = new ArrayList<>();
            for(int i : output){
                if(i != 0){
                    check.add(bracketPos.get(i-1)[0]);
                    check.add(bracketPos.get(i-1)[1]);
                }
            }
            String result = "";
            // check 리스트에 존재하는 인덱스 요소는 스킵하며 저장한다.
            for(int i=0; i<charArr.length; i++){
                if(check.contains(i)){
                    continue;
                }
                result += charArr[i];
            }
            answer.add(result);
            return;
        }
        for(int i=start; i<N; i++){
            visited[i] = true;
            output[cnt] = numArr[i];
            comb(cnt+1, i+1, max);
            visited[i] = false;
        }
    }

    // 메모리 : 68172KB, 시간 : 308ms
}
