package soojik.week14.문자열집합;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
  /*
  HashSet에 모든 문자열을 넣어주고 검사할 문자열은 set에 있는지 확인 후, 있다면 answer를 1씩 증가시킨다.
   */
  public static void main(String[] args) throws IOException {
    System.setIn(new FileInputStream("soojik/week14/문자열집합/input.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    HashSet<String> set = new HashSet<>();
    for (int i = 0; i < N; i++) {
      set.add(br.readLine());
    }

    int answer = 0;
    for (int i = 0; i < M; i++) {
      if (set.contains(br.readLine())) {
        ++answer;
      }
    }

    System.out.println(answer);
  }
}
