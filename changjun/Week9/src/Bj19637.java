import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// IF문 좀 대신 써줘
// StringBuilder 써야 시간초과 안 남.
public class Bj19637 {
    static String[] title;
    static int[] titleValue;
    static int[] power;

    static int binarySearch(int str, int end, int target){
        int mid = (str+end) / 2;
        if(end < str) return str; // ~까지 칭호를 주니까 str

        if(titleValue[mid] == target) return mid;
        else{
            if(titleValue[mid]>target){
                return binarySearch(str,mid-1,target);
            } else {
                return binarySearch(mid+1, end, target);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        title = new String[N];
        titleValue = new int[N];
        power = new int[M];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            title[i] = st.nextToken();
            titleValue[i] = Integer.parseInt(st.nextToken());
            // 값이 앞과 같을 시 타이틀 통일.
            if(i>0 && titleValue[i-1] == titleValue[i]) title[i] = title[i-1];
        }

        for(int i=0;i<M;i++){
            power[i] = Integer.parseInt(br.readLine());
        }

        for(int i=0;i<M;i++){
            //System.out.println(binarySearch(0,N-1,power[i]));
            sb.append(title[binarySearch(0,N-1,power[i])]+"\n");
        }
        System.out.println(sb);
    }
}
