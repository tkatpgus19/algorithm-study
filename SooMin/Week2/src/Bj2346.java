import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Bj2346 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 풍선이 들어있는 q
        Deque<Balloon> balloons = new ArrayDeque<>();

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 결과 담는 통
        ArrayList<Integer> list = new ArrayList<>();



        for (int i = 0; i < N; i++) {
            // Balloon 객체에 index 및 값 집어넣기
            balloons.add(new Balloon(i+1,Integer.parseInt(st.nextToken())));
        }

//        for(Balloon b: balloons) {
//        	System.out.print(b.value + " ");
//        }

        while(true){

            if(balloons.size() ==1) {
                Balloon last = balloons.poll();

                list.add(last.index);
                break;
            }

            Balloon b = balloons.poll();
            list.add(b.index);
            if(b.value<0) {
                for (int i = 0; i < Math.abs(b.value); i++) {
                    Balloon bb = balloons.removeLast();
                    balloons.addFirst(bb);
                }
            }else {
                for(int i=0; i < b.value-1; i++) {
                    Balloon bb = balloons.removeFirst();
                    balloons.addLast(bb);
                }
            }
        }

        for(int a: list) {
            System.out.print(a+ " ");
        }



    }
}

class Balloon {
    // 초기 인덱스
    int index;
    int value;

    Balloon(int i,int v){
        index = i;
        value = v;
    }
}