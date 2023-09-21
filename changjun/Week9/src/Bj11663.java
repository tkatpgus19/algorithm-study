import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
// 선분 위의 점
// 문제에 써 있지 않으면 정렬 해야함.
public class Bj11663 {
    static int[] dot;
    static int[][] line;

    static int binarySearch(int str, int end, int target){
        if(str>end) return str;
        int mid = (str+end) / 2;

        if(dot[mid] == target) return mid;
        else{
            if(dot[mid] > target){
                return binarySearch(str,mid-1,target);
            } else{
                return binarySearch(mid+1,end,target);
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        dot = new int[N];
        line = new int[M][2];

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            dot[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(dot); // 정렬해야 이분탐색 가능

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            line[i][0] = Integer.parseInt(st.nextToken()); // 선분 시작
            line[i][1] = Integer.parseInt(st.nextToken()); // 선분 끝
        }

        for(int i=0;i<M;i++){
            int strDot = binarySearch(0,N-1,line[i][0]); // 시작 점.
            int endDot = binarySearch(0,N-1,line[i][1]); // 끝 점.

            int cnt = endDot - strDot; // 차이 구하기.
            if(endDot!=N && dot[endDot] == line[i][1]) cnt++; // 끝 점이 선분 위의 점이면 1개 추가.
            sb.append(cnt+"\n");
        }

        System.out.println(sb);

    }
}
