package soojik.week2.NBA농구;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) throws IOException {
    System.setIn(new FileInputStream("soojik/week2/NBA농구/input.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());
    Point[] pointList = new Point[N];

    int[] score = new int[2];
    int[] answer = new int[2];

    StringTokenizer st;
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      int who_tmp = Integer.parseInt(st.nextToken());
      String[] tmp = st.nextToken().split(":");
      int when_tmp = Integer.parseInt(tmp[0]) * 60 + Integer.parseInt(tmp[1]);

      pointList[i] = new Point(who_tmp, when_tmp);
    }

    ++score[pointList[0].who - 1];
    int last_time = pointList[0].when;

    for (int i = 1; i < N; i++) {
      int who_tmp = pointList[i].who;
      int when_tmp = pointList[i].when;

      if (score[0] > score[1]) {
        answer[0] += when_tmp - last_time;
      }
      if (score[0] < score[1]) {
        answer[1] += when_tmp - last_time;
      }
      ++score[who_tmp - 1];
      last_time = when_tmp;
    }

    if (score[0] > score[1]) {
      answer[0] += (48 * 60 - last_time);
    }
    if (score[0] < score[1]) {
      answer[1] += (48 * 60 - last_time);
    }

    for (int time : answer) {
      int mm = time / 60;
      int ss = time % 60;

      System.out.println(String.format("%02d", mm) + ":" + String.format("%02d", ss));
    }

  }
}

class Point {
  int who;
  int when;

  public Point(int who, int when) {
    this.who = who;
    this.when = when;
  }

  @Override
  public String toString() {
    return "Point{" +
            "who=" + who +
            ", when=" + when +
            '}';
  }
}
