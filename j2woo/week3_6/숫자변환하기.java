package j2woo.week3_6;
import java.util.*;
public class 숫자변환하기 {
    public int solution(int x, int y, int n) {
        int answer = 0;
        // D[i] = i로 변환하기 위해 필요한 최소 연산 횟수
        int D[] = new int[y + 1];
        // 해당 숫자가 가능한지 확인하기 위하여 (방문과 같이)
        // -1이면 연산 불가능한 수
        Arrays.fill(D, -1);
        D[x] = 0;

        // for문을 돌며 *2 나 *3 이나 +n으로 가능할 때와 전에 계산 안했을 때와 비교하여 최소를 구해준다.
        for (int i = x; i <= y; i++) {
            if (D[i] == -1) continue;
            if (2 * i <= y) {

                D[2 * i] = (D[2 * i] == -1) ? (D[i] + 1) : Math.min(D[2 * i], D[i] + 1);
            }
            if (3 * i <= y) {
                D[3 * i] = (D[3 * i] == -1) ? (D[i] + 1) : Math.min(D[3 * i], D[i] + 1);
            }
            if (i + n <= y) {
                D[i + n] = (D[i + n] == -1) ? (D[i] + 1) : Math.min(D[i + n], D[i] + 1);
            }
        }

        // D[y]가 있으면 출력 없으면 -1 출력
        if (D[y] > 0 || x == y) return D[y];

        return -1;
    }
}

