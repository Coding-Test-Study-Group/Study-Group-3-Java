package soojik.week8.쿼드트리;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  static int N;
  static int[][] map;
  static StringBuilder answer = new StringBuilder();
  public static void main(String[] args) {

    try {
      System.setIn(new FileInputStream("soojik/week8/쿼드트리/input.txt"));
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

      N = Integer.parseInt(br.readLine());
      map = new int[N][N];
      for (int i=0;i<N;i++) {
        String input_str = br.readLine();
        for (int j = 0; j < N; j++) {
          map[i][j] = input_str.charAt(j) - '0';
        }
      }

      partCheck(0, 0, N);

      System.out.println(answer);

    } catch (IOException e) {
      System.out.println(e.getLocalizedMessage());
    }
  }

  static void partCheck(int x, int y, int size) {
    // [x, y] 부터 [x+size, y+size] 사이의 영역이 모두 같은 영역이라면
    if (isPossible(x, y, size)) {
      // 바로 답에 압축된 결과 추가하고
      answer.append(map[x][y]);
      // 해당 영역 메서드는 종료
      return;
    }

    // 그 이외에는 다시 네 구역으로 나눠 나온 결과를 괄호로 묶어준다.
    answer.append("(");

    // 영역이 4개로 나뉘었으니 체크할 구역의 한 변의 길이는 1/2 로 줄어든다.
    size /= 2;

    // 차례대로 왼쪽 위, 오른쪽 위, 왼쪽 아래, 오른쪽 아래를 탐색해준다.
    partCheck(x, y, size);
    partCheck(x, y + size, size);
    partCheck(x + size, y, size);
    partCheck(x + size, y + size, size);

    answer.append(")");
  }

  // [x, y] 부터 [x+size, y+size] 사이의 영역이 모두 같은 색인지 체크
  static boolean isPossible(int x, int y, int size) {
    int start = map[x][y];
    for (int i = x; i < x+size; i++) {
      for (int j = y; j < y+size; j++) {
        if (map[i][j] != start) return false;
      }
    }

    return true;
  }
}
