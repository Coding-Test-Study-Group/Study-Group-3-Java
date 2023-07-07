package j2woo.week1_7;
import java.util.*;
public class 쿼드트리 {
    static int [][] map;
    static String answer = "";
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        map = new int[N][N];
        for(int i = 0; i < N; i++) {
            String str = sc.next();

            for(int j = 0; j < N; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        dfs(0, 0, N);
        System.out.println(answer);
    }

    public static void dfs (int x, int y, int size) {
        if (size == 0) return;
        int sum = 0;

        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                sum += map[i][j];
            }
        }

        // 더한 값으로 모두 1 또는 0으로만 되어있는지 확인
        if (sum == size * size) answer += "1";
        else if (sum == 0) answer += "0";
        else {
            int nextSize = size / 2;
            answer += "(";
            dfs(x, y, nextSize);
            dfs(x, y + nextSize, nextSize);
            dfs(x + nextSize, y, nextSize);
            dfs(x + nextSize, y + nextSize, nextSize);
            answer += ")";
        }
    }
}
