package soojik.week11;

import java.util.Queue;
import java.util.LinkedList;

public class 미로탈출 {

  static int[] dr = {0, -1, 0, 1};
  static int[] dc = {-1, 0, 1, 0};
  static int r_len, c_len;
  static char[][] map;

  public static void main(String[] args) {
    System.out.println(solution(new String[]{"SOOOL","XXXXO","OOOOO","OXXXX","OOOOE"}));
    System.out.println(solution(new String[]{"LOOXS","OOOOX","OOOOO","OOOOO","EOOOO"}));
  }

  static int solution(String[] maps) {
    r_len = maps.length;
    c_len = maps[0].length();

    map = new char[r_len][c_len];

    // bfs 메서드에서 시작정보(startAt) 는 Point 인스턴스로 주어지므로
    // 각 S -> L, L -> E 최소거리 탐색을 위해 startPoint, leverPoint 를 선언한다.
    Point startPoint = null;
    Point leverPoint = null;
    // 주어진 String[] maps 를 좀 더 쉽게, 빠르게(래퍼 클래스보단 기본 타입이 처리 속도가 빠르기때문에) 다루기위해 char[][] map 으로 변환해준다.
    for (int i=0;i<r_len*c_len;i++) {
      int r = i/c_len;
      int c = i%c_len;
      map[r][c] = maps[r].charAt(c);
      // 해당 과정에서 startPoint 와 leverPoint 를 정의해준다..
      if (map[r][c] == 'S') {
        startPoint = new Point(r, c, 0);
      }
      if (map[r][c] == 'L') {
        leverPoint = new Point(r, c, 0);
      }
    }

    // a1는 S -> L 에 대한 최소거리(시간)
    int a1 = bfs(startPoint, 'L');
    // a1 값이 초기값(Integer.MAX_VALUE) 로 그대로라면 바로 -1 반환해준다.
    if (a1 == Integer.MAX_VALUE) return -1;
    // 위와 같다.
    int a2 = bfs(leverPoint, 'E');
    if (a2 == Integer.MAX_VALUE) return -1;

    // 갈 수 없는 경우라면 위에서 모두 걸렀으니까 바로 a1 + a2 를 반환한다.
    return a1 + a2;
  }

  static int bfs(Point startAt, char endAt) {
    Queue<Point> q = new LinkedList();
    // 시작점을 먼저 저장해준다.
    q.add(startAt);

    // 방문체크할 배열
    // 갔던 길을 다시 갈 수 있다는 언급은 S -> L 에서 경로와 L -> E 의 경로(또는 칸)이 겹칠 수 있다는 의미로 받아들였다.
    boolean[][] visit = new boolean[r_len][c_len];

    // startAt -> endAt 걸리는 최소 시간을 업데이트할 변수
    int min = Integer.MAX_VALUE;

    // q 에 있는 Point 인스턴스를 하나씩 꺼내서 넓이 우선 탐색
    while (!q.isEmpty()) {
      Point curr = q.poll();
      int cr = curr.r;
      int cc = curr.c;

      // 지금 위치가 E(출구)라면 이때까지 걸렸던 시간 curr(Point).cnt 를 min 값과 비교해 업데이트 해준다.
      if (map[cr][cc] == endAt) {
        min = Math.min(curr.cnt, min);
        // 목적지에 도착했기 때문에 여기서 더 탐색은 멈춘다. 이때는 return 이 아니라 탐색했던 경로에 대한 것만 멈추니까 continue
        continue;
      }

      // 4방향 탐색
      for (int i=0;i<4;i++) {
        int nr = cr + dr[i];
        int nc = cc + dc[i];

        // 범위 안에 있으면서
        if (0 <= nr && nr < r_len && 0 <= nc && nc < c_len) {
          // 갈 수 있고(X가 아니며), 아직 방문하기 전인 곳
          if (map[cr][cc] == 'X' || visit[nr][nc]) continue;
          // 에 대해서 방문체크를 미리 해둔다.
          /*
          !!! 여기서 시간초과가 어마어마하게 발생했다.
          생각해보면 다음에 갈 수 있는 곳을 미리 방문체크 해두면 나중에 또 [nr][nc] 에 접근할 일이 있을 때 어차피 같은 과정을 반복할테니 중복과정을 (테스트케이스가 크다면) 엄청나게 줄일 수 있다.
           */
          visit[nr][nc] = true;
          // 후에 다음 [nr][nc] 에 대한 탐색을 위해 q 에 추가한다.
          q.add(new Point(nr, nc, curr.cnt + 1));
        }
      }
    }

    // 갈 수 있는 곳에 대한 탐색이 모두 끝난 후 최종 min 을 반환한다.
    return min;
  }
}

// Point 객체는 r, c 와 같은 위치, 그리고 해당 위치까지 얼마나 걸렸는지에 대한 cnt 값을 가진다.
class Point {
  int r, c, cnt;

  public Point (int r, int c, int cnt) {
    this.r = r;
    this.c = c;
    this.cnt = cnt;
  }
}