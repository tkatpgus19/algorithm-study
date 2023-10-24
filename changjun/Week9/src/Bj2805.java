import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// ㄴㅏ무 자르기
// 나무 높이들을 기준으로 이진탐색 대략 선택하고
// 한 번 더 이진탐색해서 정확한 답 구함.
// int -> long
public class Bj2805 {
    static int[] tree;
    static int N;
    static int M;
    static int binaryTreeSearch(int str, int end, long target){
        if(end<str) return str;

        int mid= (str+end) /2;
        long sum = 0;

        for(int i=mid;i<N;i++){
            sum+=(tree[i]-tree[mid]);
        }

        if(sum==target) return mid;

        if(sum < target){
            return binaryTreeSearch(str,mid-1,target);
        } else{
            return binaryTreeSearch(mid+1,end,target);
        }
    }

    static int binarySearch(int str, int end, long target, int selectTree){
        if(end<str) return end; // 최대값이니 end 반환.

        int mid= (str+end) /2;
        // 높이를 하나 내릴 때마다 선택 나무 뒤 나무 숫자 만큼 길이가 늘어남.
        long sum = (long)(tree[selectTree] - mid) * (N-selectTree);

        if(sum==target) return mid;

        if(sum < target){
            return binarySearch(str,mid-1,target,selectTree);
        } else{
            return binarySearch(mid+1,end,target,selectTree);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        tree = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            tree[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(tree); // 정렬

        // 답이 어디 나무 사이에 있는 지 대략 구함.
        int treeSelect = binaryTreeSearch(0,N-1,M);
        int heightSelect;
        long sum = 0;

        // 구한 나무 뒤로 쭉 더해서
        for(int i=treeSelect+1;i<N;i++){
            sum+=(tree[i]-tree[treeSelect]);
        }

        // 타겟 값 - 쭉 더한 값을 이진 탐색으로 구함.
        if(treeSelect==0){
             heightSelect = binarySearch(0,tree[treeSelect],M-sum,treeSelect);
        } else{
            heightSelect = binarySearch(tree[treeSelect-1],tree[treeSelect],M-sum,treeSelect);
        }

        System.out.println(heightSelect);
    }
}
