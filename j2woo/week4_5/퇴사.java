package j2woo.week4_5;

import java.util.*;
import java.io.*;
public class 퇴사 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        // i날까지의 최대 수익
        // N + 1인 이유는 마지막 날 수업이 하루 걸릴 때를 위하여
        int [] D = new int[N + 1];

        // 해당 날짜의 상담 정보
        // plan[i][0] 은 상담시간, plan[i][1] 상담료
        int [][] plan = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            plan[i][0] = Integer.parseInt(st.nextToken());
            plan[i][1] = Integer.parseInt(st.nextToken());
        }

        // D[i] 찾아주기
        // 1) D[i]은  어제와 오늘의 최대 수익 비교해서 넣어주기, D[i] = Math.max(D[i-1], D[i])
        // 2) i날짜의 상담을 들을 수 있다면 (i + 상담에 걸리는 시간(plan[i][0]) < N + 1
        // 오늘부터 i날짜에 걸리는 시간을 더한 날짜를 또 비교해준다. i날 상담을 들었을 때와 안들었을 때
        // D[i + plan[i][0]] = D[i + plan[i][0]] = Math.max(D[i + plan[i][0]], D[i] + plan[i][1]);
        if (plan[0][0] <= N) D[plan[0][0]] = plan[0][1];

        for (int i = 1; i < N; i++) {
            D[i] = Math.max(D[i-1], D[i]);
            if (i + plan[i][0] <= N) {
                D[i + plan[i][0]] = Math.max(D[i + plan[i][0]], D[i] + plan[i][1]);
            }
        }
        // N인 날은 전날과 따로 비교해준다.
        D[N] = Math.max(D[N-1], D[N]);
        System.out.println(D[N]);
    }
}
