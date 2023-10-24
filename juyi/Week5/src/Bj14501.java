import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
 *  Bj14501 : 퇴사
 *  퇴사 하기 전까지 최대 수익 찾기
 *  
 *  1. 끝나는 시간 오름차순 정렬, 같으면 수익 내림차순 정렬해서 앞에서부터 겹치지 않게 선택하기 -> 다음 날 이익이 더 큰 경우 해결 x
 *  	반례 : 예제 4
 *  2. 수익 내림차순 정렬, 같으면 끝나는 시간 오름차순 정렬, 같으면 시작 시간 내림차순 정렬 -> 다음 날 이익이 더 큰 경우 해결 x
 *  	반례 : 3
 *  		 3 100
 *  		 1 99
 *  		 1 2
 *  
 *  동적 프로그래밍 이용 -> 뒤에서부터 계산하기
 */

public class Bj14501 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	// 빠른 입력 BufferedReader
		
		int N = Integer.parseInt(br.readLine());	// N 입력 받기
		
		Data[] data = new Data[N + 1];	// 상담 일정표 저장 배열
		
		for(int i = 1; i < N + 1; i++) {	// 1부터 N까지 반복
			StringTokenizer st = new StringTokenizer(br.readLine());	// 한 줄 입력 받고 공백으로 나누기
			int temp = Integer.parseInt(st.nextToken());	// 상담 소요 시간 입력 받기
			int benefit = Integer.parseInt(st.nextToken());	// 받을 수 있는 금액 입력 받기
			
			data[i] = new Data(temp, benefit);	// Data 객체 생성
		}
		
		int[] result = new int[N + 6];	// i까지의 수익을 저장할 배열 -> DP
		
		for(int i = N; i > 0; i--) {	// 뒤부터 탐색
			if(i + data[i].temp - 1 > N) {	// i일째에 상담 끝나는 일자가 N일보다 크면 상담 진행 x
				result[i] = result[i + 1];	// 그 이후까지(i+1일째)의 이익을 얻음.
			}
			else result[i] = Math.max(data[i].benefit + result[i + data[i].temp], result[i + 1]);	// i일째 상담 진행 이익 + 상담 종료일 이후의 이익 값과 i+1일째 값(i일째 상담을 진행하지 않았을 때의 이익) 중 큰 값이 result에 저장  
			
		}
		
		System.out.println(result[1]);	// 1일째에 저장된 값이 최대 수익. 출력
		
	}
	
	static class Data{	// 상담 일정표의 소요 시간과 이익을 함께 관리하기 위해 클래스 생성
		int temp;	// 소요 시간
		int benefit;	// 이익
		
		public Data(int temp, int benefit) {	// 생성자
			super();
			this.temp = temp;
			this.benefit = benefit;
		}
		
	}
}
/*public class Bj14501 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		Data[] data = new Data[N];
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int temp = Integer.parseInt(st.nextToken());
			int benefit = Integer.parseInt(st.nextToken());
			
			data[i] = new Data(i + 1, i + temp, benefit);
		}
	
		for(int i = 0; i < N; i++) {
			if(data[i].endDay > N) {
				data[i].benefit = 0;
			}
		}
		
		Arrays.sort(data);
		
		boolean[] selected = new boolean[N + 1];
		int result = data[0].benefit;
		int select = 0;
		
		for(int i = data[0].startDay; i <= data[0].endDay; i++) {
			selected[i] = true;
		}
		
		for(int i = 1; i < N; i++) {
			if(data[i].endDay > N) continue;
			
			boolean check = true;
			for(int j = data[i].startDay; j <= data[i].endDay; j++) {
				if(selected[j]) {
					check = false;
					break;
				}
			}

			if(check) {
				result += data[i].benefit;
				select = i;
				for(int j = data[i].startDay; j <= data[i].endDay; j++) {
					selected[j] = true;
				}
			}
		}
		
		System.out.println(result);

	}

	static class Data implements Comparable<Data>{
		int startDay;
		int endDay;
		int benefit;
		public Data(int startDay, int endDay, int benefit) {
			super();
			this.startDay = startDay;
			this.endDay = endDay;
			this.benefit = benefit;
		}
		@Override
		public int compareTo(Data o) {
			if(this.benefit == o.benefit) {
				if(this.endDay == o.endDay) {
					return -Integer.compare(this.startDay, o.startDay);
				}
				return Integer.compare(this.endDay, o.endDay);
			}
			return -Integer.compare(this.benefit, o.benefit);
		}
		@Override
		public String toString() {
			return "Data [startDay=" + startDay + ", endDay=" + endDay + ", benefit=" + benefit + "]";
		}
		
	}
}*/
