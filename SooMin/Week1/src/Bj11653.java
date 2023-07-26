import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Bj11653 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int i =2;

        int primeFactor = 1;

        while(primeFactor != N){
            if(N%i == 0){
                System.out.println(i);
                N /= i;
            }else{
                i++;
            }
        }

    }
}
