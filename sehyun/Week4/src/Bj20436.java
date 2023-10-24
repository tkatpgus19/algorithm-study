import java.io.*;
import java.util.*;

/**
 * [20436번 ZOAC 3]
 * 문제: 왼손은 한글 자음, 오른손은 한글 모음을 칠 수 있을 때, 주어진 영단어를 타이핑하는데 걸리는 최소시간을 구하시오.
 * 해결: 주어진 타이핑 계산 공식을 따르는 것이 최소의 시간이 걸리는 방법이므로 단순 구현한다.
 */
public class Bj20436 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 각 키보드 좌표값 초기화
        char[][] keyboard = new char[3][10];
        keyboard[0][0] = 'q';
        keyboard[0][1] = 'w';
        keyboard[0][2] = 'e';
        keyboard[0][3] = 'r';
        keyboard[0][4] = 't';
        keyboard[0][5] = 'y';
        keyboard[0][6] = 'u';
        keyboard[0][7] = 'i';
        keyboard[0][8] = 'o';
        keyboard[0][9] = 'p';

        keyboard[1][0] = 'a';
        keyboard[1][1] = 's';
        keyboard[1][2] = 'd';
        keyboard[1][3] = 'f';
        keyboard[1][4] = 'g';
        keyboard[1][5] = 'h';
        keyboard[1][6] = 'j';
        keyboard[1][7] = 'k';
        keyboard[1][8] = 'l';

        keyboard[2][0] = 'z';
        keyboard[2][1] = 'x';
        keyboard[2][2] = 'c';
        keyboard[2][3] = 'v';
        keyboard[2][4] = 'b';
        keyboard[2][5] = 'n';
        keyboard[2][6] = 'm';

        // 각 손가락 초기 위치 입력
        st = new StringTokenizer(br.readLine());
        char left = st.nextToken().charAt(0);
        char right = st.nextToken().charAt(0);

        // 영단어 입력
        char[] charArr = br.readLine().toCharArray();

        // 손가락 초기 위치값 탐색
        int lx = 0; int ly = 0;
        int rx = 0; int ry = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 10; j++) {
                if(keyboard[i][j] == left){
                    lx = i;
                    ly = j;
                }
                if(keyboard[i][j] == right){
                    rx = i;
                    ry = j;
                }
            }
        }

        // 입력할 문자가 한글 모음 위치인지, 자음 위치인지 체크 후 거리 계산
        int answer = 0;
        for(int k=0; k<charArr.length; k++) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 10; j++) {
                    if(charArr[k] == keyboard[i][j]){
                        if("qwertasdfgzxcv".contains(""+charArr[k])){
                            answer += Math.abs(lx-i) + Math.abs(ly-j);
                            lx = i;
                            ly = j;
                        }
                        else{
                            answer += Math.abs(rx-i) + Math.abs(ry-j);
                            rx = i;
                            ry = j;
                        }
                    }
                }
            }
        }
        System.out.println(answer + charArr.length);

        // 메모리: 14212KB, 시간: 128ms
    }
}