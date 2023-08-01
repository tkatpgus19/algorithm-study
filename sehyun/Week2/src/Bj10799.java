import java.io.*;
import java.util.*;

/**
 * 쇠막대기
 * 문제: 괄호로 쇠막대기와 레이저의 위치가 주어질 때 생성되는 쇠막대기의 개수를 구하시오.
 */
public class Bj10799 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Deque<Character> stack = new ArrayDeque<>();
        char[] arr = br.readLine().toCharArray();

        int answer = 0;
        char last = ' ';
        for(Character s : arr){
            if(s == ')'){
                // 괄호의 짝을 맞추면 스택에서 제거
                stack.removeLast();
                if(last == ')'){
                    // 이전 문자가 ')'일 경우 단순히 막대기를 표현한 것으로 한개 +
                    answer++;
                } else{
                    // 이전 문자가 '('일 경우 레이저를 표현한 것으로 스택에 쌓인 '(' 수 만큼 +
                    answer += stack.size();
                }
            } else{
                // '(' 인 경우 추가
                stack.addLast(s);
            }
            last = s;
        }
        System.out.println(answer);
        // 메모리 : 14756KB, 시간 : 116ms
    }
}
