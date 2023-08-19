import java.io.*;
import java.util.*;

/**
 * [12933번 오리]
 * 문제: 'quack'으로 우는 오리 녹음본이 문자열로 주어질 때, 최소 몇마리의 오리가 존재하는지 구하시오.
 * 해결: 울음은 항상 'q'로 시작하므로 'q'를 찾은 시점부터 나머지 'uack'를 탐색해 '0'으로 바꿔가며, 탐색 도중 새로운 'q'를 만나면 오리 수를 1 더해준다.
 */
public class Bj12933 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 탐색을 위한 'quack' 문자 배열 선언
        char[] quack = {'q', 'u', 'a', 'c', 'k'};
        char[] charArr = br.readLine().toCharArray();
        int answer = 0;

        // q를 발견했을 때 바로 나머지 문자 탐색
        // 탐색 중 새로운 q 등장 시 오리 수 추가
        // 마무리 문자를 찾았을 때 탈출하고 새로운 q 연산 시작

        for(int i=0; i<charArr.length; i++){
            // 'q'를 찾았을 때
            if(charArr[i] == quack[0]){
                int idx = 0;
                int j = 0;
                int cnt = 1;

                // 나머지 요소들 탐색 시작
                while(true){
                    // 'quack'을 모두 찾았거나, 주어진 녹음본 끝까지 탐색했을 때 종료
                    if(j == 5 || idx == charArr.length){
                        break;
                    }
                    // 'quack'중 하나를 찾았을 때 해당 문자를 '0'으로 치환하고, 다음 'quack'문자 탐색
                    if(charArr[idx] == quack[j]){
                        charArr[idx] = '0';
                        j++;
                    }
                    // 탐색 시작 계기가 된 'q'가 아닌 다른 'q'가 발견 되었을 때(uack 탐색 중 발견) 오리 수 1 추가
                    if(j != 0 && charArr[idx] == quack[0]){
                        cnt++;
                    }
                    idx++;
                }
                // 정상적으로 탐색을 마쳤다면 quack 배열 탐색에 사용된 j가 5여야 함
                // 5가 아니면 비정상 종료(quack 순서가 올바르지 않음)이므로 -1 출력을 위해 answer에 -1 저장 후 break
                if(j != 5){
                    answer = -1;
                    break;
                }

                // 이전까지의 오리 수와 탐색으로 찾게 된 새로운 오리 수를 비교해 갱신
                answer = Math.max(answer, cnt);
            }
        }

        // 혹시라도 모든 탐색이 끝난 후 '0'으로 치환되지 않은 문자가 있으면 비정상 종료이므로 answer에 -1 저장
        for(char c: charArr){
            if(c != '0'){
                answer = -1;
                break;
            }
        }
        System.out.println(answer);

        // 메모리: 14360KB, 시간: 144ms
    }
}