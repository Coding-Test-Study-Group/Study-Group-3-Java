package j2woo.week5_6;

import java.io.*;
import java.util.*;

public class 용돈관리 {
    public static void main (String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        // N일 동안 사용한 금액 배열
        int [] money = new int[N];

        // 이분탐색으로 금액 K를 찾을 것이다.
        // start는 N일 사용할 금액 중 가장 큰 금액
        // end는 N일 동안 사용한 총 금액
        // money 배열에 값을 넣어주며 start와 end를 찾아준다.
        int end = 0;
        int start = 0;
        for (int i = 0; i < N; i++) {
            money[i] = Integer.parseInt(br.readLine());
            start = Math.max(start, money[i]);
            end += money[i];
        }

        // 이분탐색
        int mid = 0;
        while (start < end) {
            // 인출 금액은 중간값
            mid = (start + end) / 2;
            // now는 인출한 금액 cnt는 인출 횟수
            int cnt = 1;
            int now = mid;
            // 하루 금액을 확인해주며 now가 오늘 사용할 금액보다 적다면 새로 인출해준다.
            for (int i = 0; i < N; i++) {
                if (now < money[i]) {
                    cnt++;
                    now = mid;
                }
                now -= money[i];
            }
            // 인출한 횟수 cnt가 M보다 크다면 인출 금액을 늘려준다.
            if (cnt > M) {
                start = mid + 1;
            }
            // 인출한 횟수 cnt가 M보다 작거나 같다면 인출 금액을 줄여준다.
            else {
                end = mid;
            }
        }
        // 최소 금액이므로 end 출력
        System.out.println(end);
    }
}
