package j2woo.week2_8;

import java.io.*;
import java.util.*;

public class 연산자끼워넣기 {
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;
    static int [] A;
    static int N;
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        // A 배열 받기
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        // 연산자 개수 받기
        int method [] = new int[4];
        for (int i = 0; i < 4; i++) {
            method[i] = Integer.parseInt(st.nextToken());
        }

        // 재귀로 문제 풀이
        DFS(new ME(A[0], method, 1));

        System.out.println(max);
        System.out.println(min);
    }

    private static void DFS (ME m) {
        // 4가지 연산을 넣어주기위한 반복문
        for (int i = 0; i < 4; i++) {
            // m.method[i]가 0인 경우 연산자의 개수가 없으므로 넘어가기
            if (m.method[i] == 0) continue;
            // 있다면
            else {
                // result에 해당 연산을 한 결과를 getResult 메소드를 사용해서 받아준다.
                int result = getResult(m.result, A[m.cnt], i);

                // 연산이 모두 끝났다면 max와 min 값을 찾아준다.
                if (m.cnt + 1 == N) {
                    max = Math.max(max, result);
                    min = Math.min(min, result);
                    return;
                }
                // 연산이 남았더라면 재귀를 사용하여 다음 연산으로 보내준다.
                else {
                    m.method[i]--;
                    DFS(new ME(result, m.method, m.cnt + 1));
                }
                m.method[i]++;
            }
        }
    }
    private static int getResult (int a, int b, int idx) {

        if (idx == 0) return a + b;
        else if (idx == 1) return a - b;
        else if (idx == 2) return a * b;
        else return a / b;
    }

    private static class ME {
        int result;
        int method[];
        int cnt;

        ME(int result, int[] method, int cnt) {
            this.result = result;
            this.method = method;
            this.cnt = cnt;
        }
    }
}
