import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static long N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Long.parseLong(br.readLine());


        long start = 0;
        long end = N ;
        long mid = 0;

        long ans = 0;

        while(start <= end) {
            mid = (start+end)/2;

            double k =Math.floor(Math.pow(mid, 2));

            if(Math.pow(mid, 2)>=N) {
                ans = mid;
                end = mid-1;
            }else {
                start = mid+1;
            }
        }

        System.out.println(ans);

    }

}