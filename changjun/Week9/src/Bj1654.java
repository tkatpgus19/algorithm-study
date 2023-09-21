import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
// 랜선 자르기
// str가 아닌 end 출력
// 탐색 0->1부터 시작.
// 개수가 같거나 클 때.
// 자 료 형. 계산 과정에서 int 범위 넘을 것 같으면 바로 long
public class Bj1654 {
    static int[] lan;
    static int K;
    static int binarySearch(long str, long end, int target){
        if(end<str) return (int)end; // 최대값이니 end
        long mid = (str+end) / 2;

        long cnt = 0;
        // 전선 개수 구하기
        for(int i=0;i<K;i++){
            cnt+= lan[i] / mid;
        }

        // 같거나 크다라는 조건 때문에 이렇게 조건문 형성.
        if(cnt >= target){
                return binarySearch(mid+1,end,target);
            } else{
                return binarySearch(str,mid-1,target);
            }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        lan = new int[K];

        for(int i=0;i<K;i++){
            st = new StringTokenizer(br.readLine());
            lan[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(lan); // 정렬.

        // 1부터 제일 긴 랜선 길이까지 이분탐색.
        int ans = binarySearch(1,lan[K-1],N);
        System.out.println(ans);

    }
}
