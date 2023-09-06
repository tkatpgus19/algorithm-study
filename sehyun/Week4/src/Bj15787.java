import java.io.*;
import java.util.*;

/**
 * [15787번 기차가 어둠을 헤치고 은하수를]
 * 문제: 20개 좌석을 가진 N개의 기차가 일련의 연산을 수행했을 때, 좌석 현황이 중복되지 않는 기차의 개수를 구하시오.
 * 해결: 단순 배열로 구현해도 되지만, 비트 연산이 역시 채고시다.
 */
public class Bj15787 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // N개의 기차를 모두 0으로 초기화
        int[] trains = new int[N];

        int command;
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            command = Integer.parseInt(st.nextToken());

            // add 명령
            if(command == 1) {
                add(st, trains);
            }
            // remove 명령
            else if(command == 2) {
                remove(st, trains);
            }
            // 뒤로 밀기 명령
            else if(command == 3) {
                pushBackward(st, trains);
            }
            // 앞으로 밀기 명령
            else {
                pushForward(st, trains);
            }
        }

        // 기차의 중복을 제거
        Set<Integer> removeDup = new HashSet<>();
        for(int train: trains) {
            removeDup.add(train);
        }
        System.out.println(removeDup.size());

        // 메모리: 40712KB, 시간: 400ms
        // 배열 사용시
        // 메모리: 123100KB, 시간: 1000ms
    }

    static void add(StringTokenizer st, int[] trains) {
        int trainNo = Integer.parseInt(st.nextToken())-1;
        int seatNo = Integer.parseInt(st.nextToken())-1;
        trains[trainNo] |= 1<<seatNo;
    }
    static void remove(StringTokenizer st, int[] trains) {
        int trainNo = Integer.parseInt(st.nextToken())-1;
        int seatNo = Integer.parseInt(st.nextToken())-1;
        trains[trainNo] &= ~(1<<seatNo);
    }

    // 비트를 왼쪽으로 밀고, 최상위 비트를 제거
    static void pushBackward(StringTokenizer st, int[] trains) {
        int trainNo = Integer.parseInt(st.nextToken())-1;
        trains[trainNo] <<= 1;
        trains[trainNo] &= ~(1<<20);
    }
    static void pushForward(StringTokenizer st, int[] trains) {
        int trainNo = Integer.parseInt(st.nextToken())-1;
        trains[trainNo] >>= 1;
    }
}