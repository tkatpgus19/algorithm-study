import java.io.*;
import java.util.*;

/**
 * 괄호의 값
 * 문제 : 괄호로 이루어진 식이 주어질 때, 식을 계산한 결과값을 출력하시오.
 * 예외 케이스 : '()]()', '(((()'
 */
public class Bj2504 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Deque<Character> stack = new ArrayDeque<>();

        char[] arr = br.readLine().toCharArray();

        // 현재 값을 저장할 mul(multiple), 모든 수의 합을 저장할 answer 선언.
        int mul = 1;
        int answer = 0;
        char tmp = ' ';

        for (char c : arr) {
            // 여는 괄호일 때 괄호가 한번 더 씌워지므로 해당하는 값을 곱한다.
            if (c == '(' || c == '[') {
                if (c == '(') {
                    mul *= 2;
                } else {
                    mul *= 3;
                }
                stack.addLast(c);
            } else {
                if (c == ')') {
                    // 짝이 맞는 괄호를 닫을 때,
                    if (stack.peekLast() != null && stack.peekLast() == '(') {
                        // 직전 문자가 일치하는 괄호일 때만 값을 저장한다.
                        if (tmp == '(') {
                            answer += mul;
                        }
                        // 괄호를 벗겼으니 곱했던 값도 나눠준다.
                        mul /= 2;
                        stack.removeLast();
                    } else {
                        // 짝이 맞지 않는 이상한 괄호를 닫을 때 전까지 저장한 값을 초기화 후 종료.
                        answer = 0;
                        break;
                    }
                } else {
                    if (stack.peekLast() != null && stack.peekLast() == '[') {
                        if (tmp == '[') {
                            answer += mul;
                        }
                        mul /= 3;
                        stack.removeLast();
                    } else {
                        answer = 0;
                        break;
                    }
                }
            }
            tmp = c;
        }

        // 비정상적으로 종료되어 스택에 값이 남아있을 때 0 출력
        if(!stack.isEmpty()){
            System.out.println(0);
        } else{
            System.out.println(answer);
        }
        // 메모리 : 11572KB, 시간 : 76ms
    }
}
