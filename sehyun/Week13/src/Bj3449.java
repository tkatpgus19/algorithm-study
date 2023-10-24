import java.io.*;

public class Bj3449{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int t=0; t<T; t++){
            char[] str1 = br.readLine().toCharArray();
            char[] str2 = br.readLine().toCharArray();
            int answer = 0;
            for(int i=0; i<str1.length; i++){
                if(str1[i] != str2[i]) {
                    answer++;
                }
            }
            sb.append("Hamming distance is ").append(answer).append('.').append('\n');
        }
        System.out.println(sb);
    }
}