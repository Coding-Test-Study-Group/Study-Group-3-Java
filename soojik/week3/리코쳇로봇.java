package soojik.week3;

import java.util.LinkedList;
import java.util.Queue;

public class 리코쳇로봇 {
  static int[] dx = {-1, 0, 1, 0};
  static int[] dy = {0, 1, 0, -1};
  static boolean[][] visit;
  static int r_len;
  static int c_len;

  public static void main(String[] args) {
    System.out.println(solution(new String[]{"...D..R", ".D.G...", "....D.D", "D....D.", "..D...."}));
    System.out.println(solution(new String[]{".D.R", "....", ".G..", "...D"}));

  }
  static int solution(String[] board) {
    r_len = board.length;
    c_len = board[0].length();

    // 막다른 길에 막혀 이동 시작하는 지점에 대한 방문 여부 체크할 배열
    visit = new boolean[r_len][c_len];

    // q 에 다음 시작점 저장
    Queue<Info> q = new LinkedList<>();

    // 배열 순회하며 시작점 R 을 찾아 q 에 넣어주고 방문 true 로 체크해준다.
    for (int i=0;i<r_len*c_len;i++) {
      if(board[i/c_len].charAt(i%c_len) == 'R') {
        q.add(new Info(i/c_len, i%c_len, 0));
        visit[i / c_len][i % c_len] = true;
        break;
      }
    }

    // q 하나씩 꺼내서 확인
    while(!q.isEmpty()) {
      Info curr = q.poll();

      // 상하좌우 방향으로
      for (int i=0;i<4;i++) {
        int next_x = curr.x;
        int next_y = curr.y;

        // i 방향으로 끝까지 갔을 때 막힌 지점을 참조할 next
        Info next;

        // 정해진 i 방향으로 이동
        while (true) {
          next_x += dx[i];
          next_y += dy[i];

          // 다음 지점이 배열 안에 존재하면서
          if (0 <= next_x && next_x < r_len && 0 <= next_y && next_y < c_len) {
            // D 를 만났다면
            if (board[next_x].charAt(next_y) == 'D') {
              // 현재 위치 [next_x - dx[i], next_y - dy[i]] 를 depth + 1 로 이동 횟수를 하나 늘려 next 가 참조하도록 한 후 break
              next = new Info(next_x - dx[i], next_y - dy[i], curr.depth + 1);
              break;
            }
          }
          else {
            // 다음 지점이 배열을 벗어났다면 이 또한 막다른 곳이므로
            // 현재 위치 [next_x - dx[i], next_y - dy[i]] 를 depth + 1 로 이동 횟수를 하나 늘려 next 가 참조하도록 한 후 break
            next = new Info(next_x - dx[i], next_y - dy[i], curr.depth + 1);
            break;
          }
        }

        // 막다른 길에 위치해있을 때, 그 곳이 골인지점이라면
        if (board[next.x].charAt(next.y) == 'G') {
          // 바로 이동 횟수를 반환한다.
          return next.depth;
        }
        // 골인 지점이 아니면서
        // 아직 해당 지점에서 이동을 해본 적 없다면 q 에 추가한다.
        else if (!visit[next.x][next.y]) {
          q.add(next);
          visit[next.x][next.y] = true;
        }
      }
    }

    // 이전에 반환되지 못했다면 골인지점으로 갈 수 없다는 뜻이므로 -1 을 반환한다.
    return -1;
  }

  /* 처음에 bfs 로 안풀려서 dfs 로도 풀어보려고했던 흔적 ..
  static void dfs(int x, int y, int depth, String[] board, boolean[][] visit) {
    if (visit[x][y]) return;
    System.out.println("1: " + x + " " + y + " " + depth + " " + board[x].charAt(y));

    visit[x][y] = true;

    if (board[x].charAt(y) == 'G') {
      answer = Math.min(answer, depth);
    }

    for (int i = 0; i < 4; i++) {
      int next_x = x;
      int next_y = y;

      while (true) {
        next_x += dx[i];
        next_y += dy[i];

        if (0 <= next_x && next_x < r_len && 0 <= next_y && next_y < c_len) {
          if (next_x == 0 || next_x == r_len - 1 || next_y == 0 || next_y == c_len - 1) {
            dfs(next_x, next_y, depth + 1, board, visit);
          }
          else if (board[next_x].charAt(next_y) == 'D') {
            dfs(next_x - dx[i], next_y - dy[i], depth + 1, board, visit);
          }
        }
        else break;
      }
    }
  }
   */
}

class Info {
  int x;
  int y;
  int depth;

  public Info(int x, int y, int depth) {
    this.x = x;
    this.y = y;
    this.depth = depth;
  }
}