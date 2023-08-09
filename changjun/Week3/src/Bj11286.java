import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
//절대값 힙.
public class Bj11286 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        PriorityQueue<Integer> pqPlus = new PriorityQueue();
        PriorityQueue<Integer> pqMinus = new PriorityQueue();

        for(int i=0;i<N;i++){
              int x = Integer.parseInt(br.readLine());
              if(x>0){
                  pqPlus.add(x);
              } else if ( x< 0){
                  pqMinus.add(-x);
              } else{
                  if(pqPlus.isEmpty()&& pqMinus.isEmpty()){
                      System.out.println(0);
                      continue;
                  } else if(pqMinus.isEmpty()){
                      System.out.println(pqPlus.remove());
                      continue;
                  } else if(pqPlus.isEmpty()){
                      System.out.println(pqMinus.remove()*-1);
                      continue;
                  }
                  int minPlus = pqPlus.peek();
                  int minMinus = pqMinus.peek();
                  if(minMinus<=minPlus){
                      System.out.println(pqMinus.remove()*-1);
                  } else{
                      System.out.println(pqPlus.remove());
                  }

              }
        }

    }
}
