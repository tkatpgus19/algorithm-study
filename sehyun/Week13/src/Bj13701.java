import java.util.*;
import java.io.*;

public class Bj13701 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        BitSet bitSet = new BitSet();
        st = new StringTokenizer(br.readLine());

        while(st.hasMoreTokens()) {
            int num = Integer.parseInt(st.nextToken());
            if (!bitSet.get(num)) {
                bitSet.set(num);
                sb.append(num).append(' ');
            }
        }
        System.out.println(sb);
    }
}
