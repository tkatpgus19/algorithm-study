import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Bj10799 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        Stack<Character> s = new Stack<>();
        int result = 0;
        
        for(int i = 0; i < input.length(); i++) {
            if(input.charAt(i) == '(') {
                s.push(input.charAt(i));
                continue;
            }
            if(input.charAt(i) == ')') {
                s.pop();
                
                if(input.charAt(i-1) == '(') {
                    result += s.size();
                }
                else result++;
            }
            
        }
        
        System.out.println(result);

    }

}