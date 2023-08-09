import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
//문자열 집합
public class Bj14425 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Set<String> S = new HashSet<String>();
        ArrayList check = new ArrayList();

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            S.add(st.nextToken());
        }
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            check.add(st.nextToken());
        }
        check.retainAll(S);
        System.out.println(check.size());
    }
}
