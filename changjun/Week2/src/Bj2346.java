import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Bj2346 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        Deque<Integer> bln = new ArrayDeque<>();
        Deque<Integer> blnNum = new ArrayDeque<>();

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            bln.addLast(i+1);
            blnNum.addLast(Integer.parseInt(st.nextToken()));
        }

        for(int i=0;i<N;i++){
            int curNum = blnNum.peekFirst();
            System.out.printf("%d ",bln.peekFirst());
            bln.removeFirst();
            blnNum.removeFirst();

            if(bln.isEmpty())
                break;

            if(curNum>0){
                for(int j=0;j<curNum-1;j++){
                    bln.addLast(bln.pollFirst());
                    blnNum.addLast(blnNum.pollFirst());
                }

            } else {
                for(int j=0;j>curNum;j--){
                    bln.addFirst(bln.pollLast());
                    blnNum.addFirst(blnNum.pollLast());
                }

            }
        }
    }
}
