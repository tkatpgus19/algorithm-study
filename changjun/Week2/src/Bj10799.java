import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Bj10799 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<Character> s = new Stack<>();

        String read = br.readLine();
        for(int i=0;i<read.length();i++){
            s.add(read.charAt(i));
        }

        int cnt = 0;
        int ans = 0;
        char lastIdx = ')'; // 직전 값 저장.

        // 뒤에서 부터 탐색.
        for(int i=0;i<read.length();i++){
            // 상단 문자가 ')' 라면
            if(s.peek()==')'){
                cnt++; // 막대 개수 증가.
                s.pop();
                lastIdx = ')';
            }
            // 상단 문자가 '(' 라면
            else if(s.peek()=='('){
                // 직전에 ')'가 들어왔으면 레이저.
                if(lastIdx == ')'){
                    ans+=--cnt;
                    s.pop();
                }
                // 직전에 '('가 들어왔다면 막대종료.
                else if (lastIdx == '(') {
                    cnt--;
                    ans++; // 자른 토막 개수이기에 종료 막대기 추가.
                    s.pop();
                }
                lastIdx = '(';
            }
        }

        System.out.println(ans);
    }
}
