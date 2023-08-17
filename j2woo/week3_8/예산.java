package j2woo.week3_8;

import java.util.*;
import java.io.*;
public class 예산 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int [] num = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        // 숫자 받기
        for (int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        // 배열 오름차순 정렬(큰 수 찾기 위해서)
        Arrays.sort(num);

        // 국가예산의 총액
        int M = Integer.parseInt(br.readLine());

        // left = 0, right는 가장 큰 금액
        int left = 0;
        int right = num[N - 1];

        int mid = (left + right) / 2;

        while (left <= right) {
            int value = 0;

            // mid는 예산
            mid = (left + right) / 2;

            // 예산보다 작거나 같으면 요청 금액을 더하고, 예산보다 크면 예산을 더한다.
            for (int i = 0; i < N; i++) {
                if (num[i] <= mid) {
                    value += num[i];
                } else {
                    value += mid;
                }
            }

            if (value > M) right = mid - 1;
            else left = mid + 1;

        }

        System.out.println(right);

    }
}
