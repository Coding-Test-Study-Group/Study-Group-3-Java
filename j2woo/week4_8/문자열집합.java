package j2woo.week4_8;

import java.io.*;
import java.util.*;
public class 문자열집합 {
    public static void main (String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // set에 집합S에 해당하는 문자열을 넣었다.
        HashSet<String> set = new HashSet<>();

        // 정답의 개수
        int answer = 0;

        // N개의 문자열을 set에 넣어준다.
        for (int i = 0; i < N; i++) {
            set.add(br.readLine());
        }

        // M개의 문자열을 set에 들어있는지 확인한다.
        for (int i = 0; i < M; i++) {
            // set에 포함한다면 answer++
            if (set.contains(br.readLine())) answer++;
        }
        System.out.println(answer);
    }
}
