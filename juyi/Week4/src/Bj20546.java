import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 *  Bj20546 : 기적의 매매법
 *  준현 - 살 수 있을 때 가능한 만큼 매수
 *  성민 - 3일 연속 하락했을 때 전량 매수, 3일 연속 상승했을 때 전량 매도, 전일과 오늘의 주가가 동일하다면 상승, 하락 x
 *  
 *  누가 더 수익을 냈는지 출력
 */
public class Bj20546 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	// 빠른 입력을 위한 BufferedReader 선언
		int moneyJ = Integer.parseInt(br.readLine());	// 주어진 현금 입력 받기
		int moneyS = moneyJ;	// 성민이도 준현이와 동일
		
		int[] price = new int[14];	// 주가 저장 배열
		StringTokenizer st = new StringTokenizer(br.readLine());	// 공백으로 분리하기 위해 StringTokenizer 선언
		for(int i = 0; i < 14; i++) {	// 주가 크기만큼 반복 14번
			price[i] = Integer.parseInt(st.nextToken());	// 주가 입력 받기
		}
		
		int countJ = 0, countS = 0;	// 준현이와 성민이가 가지고 있는 주식의 수를 저장하는 변수 선언
		int up = 0, down = 0;	//	주가의 상승, 하락을 확인할 변수 선언
		
		// 준현
		for(int i = 0; i < 14; i++) {	// 14번 반복
			if(moneyJ >= price[i]) {	// 현재 가지고 있는 돈이 주가보다 많으면
				countJ += moneyJ / price[i];	// 살 수 있는 만큼 매수
				moneyJ %= price[i];	// 남은 돈 계산
			}
		}
		
		// 성민
		for(int i = 0; i < 13; i++) {	// 다음날과 비교하기 위해서 13번만 반복
			if(price[i] > price[i + 1]) {	// 오늘 주가가 다음날 주가보다 클 때 -> 하락
				up = 0;	// 상승 횟수 초기화
				down++;	// 하락 횟수 증가
			}
			else if(price[i] < price[i + 1]) {	// 오늘 주가가 다음날 주가보다 작을 때 -> 상승
				up++;	// 상승 횟수 증가
				down = 0;	// 하락 횟수 초기화
			}
			else {	// 오늘 주가와 다음날 주가가 동일할 때 -> 상승도 하락도 x
				up = 0;	// 상승 횟수 초기화
				down = 0;	// 하락 횟수 초기화
			}
			
			// 매수
			if(down == 3) {	// 하락 횟수가 3이 되면 3일 연속 하락한 것
				down = 0;	// 하락 횟수는 초기화
				countS += moneyS / price[i + 1];	// 살 수 있을 만큼 매수
				moneyS %= price[i + 1];	// 남은 돈 계산
			}
			
			// 매도
			if(up == 3) {	// 상승 횟수가 3이 되면 3일 연속 상승한 것
				up = 0;	// 상승 횟수 초기화
				moneyS += price[i + 1] * countS;	// 가지고 있는 주식 전량 매도
				countS = 0;	// 가지고 있는 주식 수 0
			}
			
		}
		
		int resultJ = moneyJ + price[13] * countJ;	// 마지막날 기준 준현이의 자산
		int resultS = moneyS + price[13] * countS;	// 마지막날 기준 성민이의 자산

		if(resultJ > resultS) System.out.println("BNP");	// 준현이가 클 때 BNP 출력
		else if(resultJ < resultS) System.out.println("TIMING");	// 성민이가 클 때 TIMING 출력
		else System.out.println("SAMESAME");	// 같을 때 SAMESAME 출력
	}

}
