package j2woo.week4_7;

import java.util.*;
public class 미로탈출 {
    public int solution(String[] maps) {
        int answer = -1;
        int r = maps.length;
        int c = maps[0].length();

        char [][] map = new char[r][c];

        // 시작 위치
        int sx = 0;
        int sy = 0;

        // 이차원 배열에 넣기
        for (int i = 0; i < r; i++) {
            map[i] = maps[i].toCharArray();
            // 시작 위치 찾기
            if (maps[i].contains("S")) {
                sx = i;
                sy = maps[i].indexOf("S");
            }
        }

        // 방문 확인 이차원 배열
        boolean [][] checked = new boolean[r][c];
        Queue<Node> que = new LinkedList<>();
        que.add(new Node(sx, sy, 0, false));
        checked[sx][sy] = true;

        // 방향
        int [] dx = {-1, 1, 0, 0};
        int [] dy = {0, 0, -1, 1};

        // 레버를 찾았는지 여부
        boolean findL = false;
        // BFS
        // 벽이 아닌 이동할 수 있는 칸으로 이동하며 BFS를 사용해 최소 시간을 찾아준다.
        // S -> L 까지 찾고, L -> E 까지 찾기
        loop :
        while (!que.isEmpty()) {
            Node now = que.poll();
            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                try {
                    // 레버 처음 찾았을 때
                    // 레버를 찾은 상황으로 바꿔주기 (findL, 현재 노드의 findL)
                    // 레버를 찾았으므로 방문을 초기화해주고 레버의 위치부터 E까지의 최소위치 찾기위해
                    if (!findL && map[nx][ny] == 'L') {
                        findL = true;
                        checked = new boolean[r][c];
                        que.add(new Node(nx, ny, now.cnt + 1, true));
                        break;
                    }

                    else if (now.findL && findL && map[nx][ny] == 'E') {
                        answer = now.cnt + 1;
                        break loop;
                    }

                    // 레버 찾기와 E 찾기 분리 이유: que에 들어온 노드가 레버를 찾았을 때 노드인지 E 찾기 위한 노드인지 확인하기 위해 (now.findL)
                    // 레버 찾기 위한 탐색
                    else if (!findL && !checked[nx][ny] && map[nx][ny] != 'X') {

                        checked[nx][ny] = true;
                        que.add(new Node(nx, ny, now.cnt + 1, false));
                    }
                    // E 찾기 위한 탐색
                    else if (findL && now.findL && !checked[nx][ny] && map[nx][ny] != 'X') {
                        checked[nx][ny] = true;
                        que.add(new Node(nx, ny, now.cnt + 1, true));
                    }
                } catch(Exception e) {

                }
            }
        }
        return answer;
    }
}

/**
 x , y: 위치
 cnt : 이동 시간
 findL : 레버를 찾았는지
 **/
class Node {
    int x;
    int y;
    int cnt;
    boolean findL;

    Node (int x, int y, int cnt, boolean findL) {
        this.x = x;
        this.y = y;
        this.cnt = cnt;
        this.findL = findL;
    }
}
