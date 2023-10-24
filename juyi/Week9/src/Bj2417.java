import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
* Bj2417 : 정수 제곱근
* 주어진 정수의 제곱근 구하기
*/
public class Bj2417 {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));    // 빠른 입력
        
        long n = Long.parseLong(br.readLine());    // 2^63까지 입력되므로 long으로 선언
        
        long start = 0, end = n;    // 이분탐색을 위한 시작 인덱스와 끝 인덱스
        while(start <= end) {    // 시작 인덱스가 끝 인덱스보다 작을 때까지 반복
            long mid = (end - start) / 2 + start;    // 중간 값 구하기
            // mid * mid를 하면 오버플로우 될 수도 있으므로 Math.pow 사용
            if(Math.pow(mid, 2) < n) start = mid + 1;    // 제곱값이 n보다 작으면 시작 인덱스를 중간 값 다음 값으로 변경
            else if(Math.pow(mid, 2) > n) end = mid - 1;    // 제곱값이 n보다 크면  끝 인덱스를 중간 값 이전 값으로 변경
            else {    // 제곱값과 n이 같으면
                start = mid;    // 시작 값을 중간 값으로 변경
                break;    // while문 종료
            }
        }
        
        System.out.println(start);    // 시작값 출력

    }

}