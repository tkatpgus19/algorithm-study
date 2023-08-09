import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
// 나는야 포켓몬 마스터 이다솜
public class Bj1620 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Map<Integer, String> pokemon = new HashMap<>();
        Map<String,Integer> pokemonR = new HashMap<>();


        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            pokemon.put(i,s);
            pokemonR.put(s,i);
        }

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            char c = s.charAt(0);
            if((c>='a'&&c<='z')||(c>='A'&&c<='Z')){
                System.out.println(pokemonR.get(s)+1);
            } else{
                System.out.println(pokemon.get(Integer.parseInt(s)-1));
            }
        }
        //entry로
    }
}
