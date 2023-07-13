package j2woo.week2_7;

public class 공원산책 {
    public int[] solution(String[] park, String[] routes) {

        int w = park[0].length();
        int h = park.length;
        char [][] map = new char [h][w];
        // 시작위치 s_x, s_y
        int s_x = 0;
        int s_y = 0;
        // map을 초기화하면서 S 시작위치를 찾아준다.
        for (int i = 0; i < h; i++) {
            map[i] = park[i].toCharArray();
            if (park[i].contains("S")) {
                s_x = i;
                s_y = park[i].indexOf("S");
            }
        }

        // 수행할 명령어를 확인
        for (String r : routes) {
            String[] rt = r.split(" ");
            // 이동할 칸의 수
            int cnt = Integer.parseInt(rt[1]);

            // 메서드 move를 이용해 방향 찾아준다.
            int [] next = move(rt[0]);

            // 주어진 방향으로 이동할 때 공원을 벗어나는지 확인
            // 이동했을 때 위치
            int n_x = s_x + next[0] * cnt;
            int n_y = s_y + next[1] * cnt;

            // 장애물 있는지 확인하기 위하여
            boolean checked = true;

            // 주어진 방향으로 이동할 때 공원을 벗어나는지 확인
            if (n_x >= 0 && n_x < h && n_y < w && n_y >= 0) {
                // 이동하면서 장애물이 있는지 확인
                for (int i = 1; i <= Integer.parseInt(rt[1]); i++) {
                    if (map[s_x + next[0] * i][s_y + next[1] * i] == 'X') {
                        checked = false;
                        break;
                    }
                }
                // 장애물이 없다면 이동
                if (checked) {
                    s_x = n_x;
                    s_y = n_y;
                }
            } else continue;
        }
        // 현재 위치 출력
        return new int[]{s_x, s_y};
    }

    public static int[] move (String dir) {
        if (dir.equals("E")) {
            return new int[]{0, 1};
        } else  if (dir.equals("W")) {
            return new int[]{0, -1};
        } else  if (dir.equals("S")) {
            return new int[]{1, 0};
        } else {
            return new int[]{-1, 0};
        }
    }
}
