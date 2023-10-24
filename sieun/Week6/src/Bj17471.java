/*
 List : 메모리가 허용하는 한 계속해서 추가할 수 있도록 만든 자료형 클래스 
 */
import java.util.*;
import java.io.*;

public class Bj17471 {
	static int N ; //지역 개수
	static int peoples[]; //구역별 인구 수 
	static List<ArrayList<Integer>> graph;
	static boolean selected[]; //부분 집합 만들 때 사용
	static boolean visited[]; 
	static int res; //인구 차이 -> 정답
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(bf.readLine()); //지역 개수 
		res = Integer.MAX_VALUE; //인구 차이 (정답) initialize
		peoples = new int[N]; //지역별 인구 수 
		selected = new boolean[N]; //집합 만들 때 선택되엇는지 확인하는 변수
		
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		//지역별 사람수 입력 받기
		for(int i=0; i<N; i++) {
			peoples[i] = Integer.parseInt(st.nextToken()); 
		}
		
		//그래프 생성
		graph = new ArrayList<>(); 
		
		//그래프에 ArrayList 를 N만큼 추가 -> N개의 마을에 연결되어 있는 정보를 이중으로 받는다.
		for(int i=0; i<N; i++) {
			graph.add(new ArrayList<Integer>());  
		}
		
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(bf.readLine());
			int cnt = Integer.parseInt(st.nextToken());  //i 마을에 인접한 구역 수가 몇개 있는지 입력 받음 
			
			for(int j = 0; j<cnt; j++) { //cnt 만큼 반복하면서 인접한 마을 입력 받음
				int num = Integer.parseInt(st.nextToken()); 
				graph.get(i).add(num-1); //그래프의 i 번째에 인접한  graph에 정보를 추가 해준다. num-1 하는 이유는 0번 부터 시작하기 때문
			}
		}
		
		//지역 나누기
		divide(0);
		
		if(res == Integer.MAX_VALUE)
			res = -1;
		System.out.println(res);
	}

	//지역 나누는 method -> 조합 사용
	private static void divide(int idx) {
		//기저 조건
		if(idx == N) {
			
			List<Integer> aList = new ArrayList<>();//구역 1
			List<Integer> bList = new ArrayList<>();//구역 2
			
			for(int i=0; i<N; i++) {
				if(selected[i]) aList.add(i); //선택되었으면 aList에 추가
				else bList.add(i); //선택 안되었으면 bList에 추가
			}
			
			if(aList.size() == 0 || bList.size() == 0) //한쪽 지역에 몰빵 (둘중 하나라도 아무것도 선택 안되어 있음) -> x
				return;
			
			if(check(aList) && check(bList)) { //두 구역이 연결되어 있는지 확인
				//구역 1이랑 구역 2랑 둘다 연결되어 있으면 인구차를 구해서 res 에 REUTN 해줌
				res = getPeopleDiff(); //인구 차이 구하기
			}
			return;
		}
	
		//조합해서 나눔
		selected[idx] = true; //idx번째를 선택함
		divide(idx +1); //개수 증가해서 체크해보기
		selected[idx] = false; //idx를 선택안함
		divide(idx +1) ; //개수 증가헤서 체크해보기
	}

	//차이 구하기
	private static int getPeopleDiff() {
		//PA: 구역 1 사람 수 
		//PB : 구역 2 사람 수 
		int PA = 0, PB = 0;
		
		//N번 돌면서 체크
		for(int i=0; i<N; i++) {
			if(selected[i]) //선택되었으면 
				PA += peoples[i]; // 구역 1 사람
			else //선택 안되었으면
				PB += peoples[i]; //구역 2 사람
		}
		//차이 구해서 절대값 씌우기
		int diff = Math.abs(PA - PB);
		//최소 값 return 
		return Math.min(res, diff);
		
	}

	//서로 연결되어 있는지 확인하는 method -> BFS
	private static boolean check(List<Integer> list) {
		
		Queue <Integer> q = new LinkedList<>(); //BFS해서 연결되어 있는지 확인하기 위한 q
		visited = new boolean[N]; //방문했는지 체크 
		
		visited[list.get(0)] = true; //0번 방문 처리
		q.offer(list.get(0)); //0번을 q에 추가
		
		int count = 1; //방문한 지역 개수
		while(!q.isEmpty()) { //q를 팝하면서 계속 방문
			int cur = q.poll(); //큐에서 팝 해서 현재 지역으로 설정
			for(int i=0; i<graph.get(cur).size(); i++) {
				int y = graph.get(cur).get(i); 
				if(list.contains(y) && !visited[y]) { 
					q.offer(y);
					visited[y] = true;//방문 처리
					count ++; //count 증가 
				}
			}
		}
		
		if(count == list.size()) return true;
		else return false;
	}
	
}