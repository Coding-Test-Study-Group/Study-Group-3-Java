package j2woo.week3_6;

import java.util.*;
import java.io.*;
public class 대칭차집합 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int aCnt = Integer.parseInt(st.nextToken());
        int bCnt = Integer.parseInt(st.nextToken());

        // A 집합 넣기
        HashSet <Integer> A = new HashSet<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < aCnt; i++) {
            A.add(Integer.parseInt(st.nextToken()));
        }

        // A 집합과 B집합의 교집합의 개수 구하기
        // B를 확인하면서 A에 있는지 확인
        // 있으면 교집합의 개수 ++
        int Cnt = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < bCnt; i++) {
            int b = Integer.parseInt(st.nextToken());
            // A에 b의 원소가 존재하면
            if (A.contains((Integer)b)) Cnt++;
        }

        // 출력 ( A 원소 개수 - 교집합 개수) + (B 원소 개수 - 교집합 개수)
        System.out.println((aCnt - Cnt) + (bCnt - Cnt));
    }
}
