package j2woo.week1_6;
import java.util.*;

public class 리코쳇로봇 {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static char [][] map;
    static int h;
    static int w;

    public int solution(String[] board) {
        // 게임판의 높이와 너비
        h = board.length;
        w = board[0].length();

        // 방문 확인
        boolean [][] visited = new boolean[h][w];

        // BFS를 사용하기 위한 말이 이동할 위치를 넣을 Queue
        Queue<Move> que = new LinkedList<>();

        // 게임판 초기화
        map = new char[h][w];
        // map에 게임판의 상태를 넣어주면서 시작 위치를 찾는다면 que에 넣어준다.
        for (int i = 0; i < h; i++) {
            map[i] = board[i].toCharArray();
            if (board[i].contains("R")) {
                que.add(new Move(i, board[i].indexOf("R"), 0));
                visited[i][board[i].indexOf("R")] = true;
            }
        }

        // BFS
        // 현재 위치부터 네방향으로 이동해준다.
        // 메서드 stop을 사용하여 장애물이나 맨 끝에 부딪힐 때까지의 위치를 구해준다.
        // stop으로 멈춘 위치(next)가 G 목표지점이면 이동 횟수를 출력해준다.(BFS이므로 최소를 구할 수 있음)
        // 멈춘 위치(next)가 G가 아니고 방문을 안했다면 que에 넣어준다.
        while (!que.isEmpty()) {
            Move now = que.poll();
            for (int i = 0; i < 4; i++) {
                Move next = stop(now, i);
                if(map[next.x][next.y] == 'G') return next.cnt;
                else if (!visited[next.x][next.y]) {
                    que.add(next);
                    visited[next.x][next.y] = true;
                }
            }
        }

        // 목표지점을 찾지 못했다면 -1 출력
        return -1;
    }

    private static Move stop(Move move, int direc) {
        int nx = move.x;
        int ny = move.y;

        while (true) {

            nx += dx[direc];
            ny += dy[direc];

            if (nx >= 0 && nx < h && ny >= 0 && ny < w) {
                if(map[nx][ny] == 'D') break;
            } else break;
        }

        return new Move(nx - dx[direc], ny - dy[direc], move.cnt + 1);
    }

}

// 움직이는 위치 객체
// x: x값, y: y값, cnt: 움직인 횟수
class Move {
    int x;
    int y;
    int cnt;

    public Move(int x, int y, int cnt) {
        this.x = x;
        this.y = y;
        this.cnt = cnt;
    }
}
