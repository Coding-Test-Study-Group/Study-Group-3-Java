package soojik.week3.교수가된현우;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

  public static void main(String[] args) throws IOException {
    System.setIn(new FileInputStream("soojik/week3/교수가된현우/input.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int T = Integer.parseInt(br.readLine());

    for (int i = 0; i < T; i++) {
      long N = Integer.parseInt(br.readLine());

      int cnt = 0;
      for (long j = 5; j <= N; j *= 5) {
        cnt += N / j;
      }

      System.out.println(cnt);

      /*
      원래는 2, 5 갯수를 모두 세고 둘 중 작은 수를 반환하려고 했다.

      int cnt_2 = 0;
      int cnt_5 = 0;
      for (long j = 2; j <= N; j++) {
        long tmp = j;
        while (tmp > 1 && tmp % 2 == 0) {
          tmp /= 2;
          ++cnt_2;
        }
        while (tmp > 1 && tmp % 5 == 0) {
          tmp /= 5;
          ++cnt_5;
        }
      }

      System.out.println(Math.min(cnt_2, cnt_5));
       */
    }
  }
}
