package soojik.week8;

public class 등굣길 {

  public static void main(String[] args) {
    System.out.println(solution(4, 3, new int[][]{{2, 2}}));
  }

  static int solution(int m, int n, int[][] puddles) {
    // 1,1 부터 m,n
    // isPuddle[][]: 해당 자리에 물이 있는지 확인하기 위함
    // map[][]: 해당 위치까지 올 수 있는 방법 카운트하기 위함
    boolean[][] isPuddle = new boolean[n + 1][m + 1];
    int[][] map = new int[n + 1][m + 1];

    // 물있는 곳 체크
    for (int[] puddle : puddles) {
      isPuddle[puddle[1]][puddle[0]] = true;
    }

    // 첫 시작 [1, 1]은 갈 수 있는 경로 1로 체크
    map[1][1] = 1;

    // 1,1 부터 m,n 까지 배열 순회하며
    for (int i=1;i<=n;i++) {
      for (int j = 1; j <= m; j++) {
        // 물이 있다면 그 자리는 카운트하지 않고 지나감으로써 0으로 체크
        if (isPuddle[i][j]) continue;
        // 그 외의 경우에는 기본적으로 방향이 왼쪽, 상에서 오른쪽, 하로 내려오는 우하향 이니까
        // 위, 또는 왼쪽의 카운트를 합해 조건 중 하나인 1000000007 의 나머지를 구한 값을 배열에 넣어준다.
        map[i][j] += (map[i - 1][j] + map[i][j - 1]) % 1000000007;
      }
    }

    return map[n][m];
  }
}
