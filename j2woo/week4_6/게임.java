package j2woo.week4_6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 게임 {
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long X = Long.parseLong(st.nextToken());
        long Y = Long.parseLong(st.nextToken());

        long Z = Y * 100 / X;

        if (Z >= 99) {
            System.out.println(-1);
        } else {
            long start = 0;
            long end = X;
            long mid = 0;
            long now_Z = 0;
            while (start < end) {
                mid = (start + end) / 2;
                now_Z = (Y + mid) * 100 / (X + mid);
                if (now_Z > Z) {
                    end = mid;
                } else {
                    start = mid + 1;
                }
            }

            System.out.println(start);
        }
    }
}
