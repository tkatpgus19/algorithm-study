import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// 정수제곱근.
// long is oK?
public class Bj2417 {
    static long binarySearch(long str, long end, long target){
        if(target == 0) return 0;
        if(str>end) return str;
        long mid = (str + end) / 2;

        if(Math.pow(mid,2) == target) return mid;
        else{
            if(Math.pow(mid,2) > target){
                return binarySearch(str,mid -1,target);
            } else{
                return binarySearch(mid+1,end,target);
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //StringTokenizer st = new StringTokenizer(br.readLine());

        String s = br.readLine();
        long l = Long.parseLong(s);

        // 5-6자리 수 -> 3자리 수의 제곱. -> 100 ~ 999 사이.
        int len = s.length();
        long str = (long)Math.pow(10,(len-1) / 2);
        long end = str * 10;

        long ans = binarySearch(str,end,l);

        System.out.println(ans);

    }
}
