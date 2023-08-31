import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;


public class Bj14501 {

    static int sum, N;
    static ArrayList<int[]> list = new ArrayList<>();
    static boolean [] flag;
    static int max = 0;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        list.add(0, null);
        flag = new boolean[N+1];

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int remainedDay = N-i;
            int Time = Integer.parseInt(st.nextToken());
            int Pay = Integer.parseInt(st.nextToken());

            if(remainedDay - Time+1 < 0) {
                int [] pair = {-1, 0};
                list.add(pair);
                continue;
            };

            int [] pair = {Time,Pay};
            list.add(pair);

        }

        for (int i = 1; i < list.size(); i++) {
            if(list.get(i)[0] == -1){
                flag[i] = true;
            }
        }

        calcul(1);
        System.out.println(max);
    }

    // true - 방문, false - 미방문
    public static void calcul(int index){

        if(index >= list.size()){
            max = Math.max(max, sum);
            return;
        }

        if(list.get(index)[0] == -1){
            calcul(index+1);
            return;
        }


        for (int i = index; i < list.size(); i++) {

            if(flag[i]) continue;

            for (int j = i; j < list.get(i)[0]+i; j++) {
                if(list.get(j)[0] == -1) continue;
                flag[j] = true;
            }
            sum += list.get(i)[1];
            calcul(list.get(i)[0]+i);

            for (int j = i; j < list.get(i)[0]+i; j++) {
                if(list.get(j)[0] == -1) continue;
                flag[j] = false;
            }
            sum -=list.get(i)[1];
            calcul(i+1);
            return;
        }
    }
}
