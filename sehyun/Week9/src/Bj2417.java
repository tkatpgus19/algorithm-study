import java.io.*;

/**
 * [2417번: 정수 제곱근]
 * 문제: 주어진 정수의 제급근을 구하시오.
 * 해결: 1. Math.sqrt 메서드를 이용해 소숫점이 포함된 제급근을 구하고, 최대한 비슷한 근사값을 찾는다.
 *      2. 이분탐색을 통해 값을 제곱해가며 주어진 정수보다 커지기 시작하는 값을 찾는다.
 */
//public class Bj2417{
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//        long n = Long.parseLong(br.readLine());
//        long sqrt = (long)Math.sqrt(n);
//        if(sqrt*sqrt >= n){
//            System.out.println(sqrt);
//        }
//        else {
//            System.out.println(sqrt+1);
//        }
//    }
//}

public class Bj2417{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long n = Long.parseLong(br.readLine());

        long left = 0;
        long right = n;
        long answer = 0;

        // 근사값을 찾을 때까지 이분 탐색
        while(left <= right){
            long mid = (left + right) / 2;

            // mid를 제곱했을 때 주어진 정수보다 크거나 같으면 좌측으로 이동
            if(Math.pow(mid, 2) >= n){
                answer = mid;
                right = mid-1;
            }
            // 더 작으면 우측으로 이동
            else{
                left = mid+1;
            }
        }

        System.out.println(answer);
        // 메모리: 14396KB, 시간: 124ms
    }
}