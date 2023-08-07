import java.io.*;
import java.util.*;

/**
 * 괄호제거
 * 문제 : 괄호가 포함된 수식이 주어질 때, 괄호를 제거하여 나올 수 있는 수식을 모두 출력하시오.
 */
public class Bj2800 {
    static int N;
    static boolean[] visited;
    static List<int[]> bracketPos = new ArrayList<>();
    static char[] charArr;
    static Set<String> answer = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
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
        visited = new boolean[N];

        comb(0);
        
        List<String> tmp = new ArrayList<>(answer);
        Collections.sort(tmp);
        for(String a : tmp){
            sb.append(a).append('\n');
        }
        System.out.println(sb);
    }
    
    static void comb(int depth){
        if(depth == visited.length){
            // 이번에 제외할 괄호의 인덱스를 check 리스트에 저장한다.
            List<Integer> check = new ArrayList<>();
            int cnt = 0;
            for(int i=0; i<visited.length; i++) {
            	if(visited[i]) {
            		check.add(bracketPos.get(i)[0]);
                    check.add(bracketPos.get(i)[1]);
                    cnt++;
            	}
            }
            if(cnt == 0) {
            	return;
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
        visited[depth] = true;
        comb(depth + 1);
        visited[depth] = false;
        comb(depth + 1);
    }

    // 메모리 : 68172KB, 시간 : 308ms
    // java11 로 제출하면
    // 메모리 : 28472KB, 시간 : 300ms
}
