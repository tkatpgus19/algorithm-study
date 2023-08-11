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
        // 양수 힙, 음수 힙 두 개 생성.
        PriorityQueue<Integer> pqPlus = new PriorityQueue();
        PriorityQueue<Integer> pqMinus = new PriorityQueue();

        for(int i=0;i<N;i++){
              int x = Integer.parseInt(br.readLine());
              if(x>0){
                  pqPlus.add(x); // 양수면 양수 힙에 넣기 
              } else if ( x< 0){
                  pqMinus.add(-x); // 음수면 음수 힙에 넣기 
              } else{
            	  // 0일 때 
                  if(pqPlus.isEmpty()&& pqMinus.isEmpty()){  // 둘 다 비었으면 0 출력 
                      System.out.println(0);
                      continue;
                  } else if(pqMinus.isEmpty()){
                      System.out.println(pqPlus.remove()); // 음수 힙이 비었으면 양수 힙에서 빼내기 
                      continue;
                  } else if(pqPlus.isEmpty()){
                      System.out.println(pqMinus.remove()*-1); // 양수 힙이 비었으면 음수 힙에서 빼내기 
                      continue;
                  }
                  // 양수, 음수 힙에서 작은쪽 빼내기.
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
