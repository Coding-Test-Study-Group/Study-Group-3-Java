package soojik.week9;

import java.util.Arrays;
import java.util.HashMap;

public class 공원산책 {

  static int r, c;
  static boolean[][] map;
  // key - value 값으로 방향명 - 이동할 방향(1칸 기준)를 갖고있는 HashMap
  static HashMap<String, int[]> directionMap = new HashMap<>();

  public static void main(String[] args) {
    System.out.println(Arrays.toString(solution(new String[]{"SOO","OOO","OOO"}, new String[]{"E 2","S 2","W 1"})));
    System.out.println(Arrays.toString(solution(new String[]{"SOO","OXX","OOO"}, new String[]{"E 2","S 2","W 1"})));
    System.out.println(Arrays.toString(solution(new String[]{"OSO","OOO","OXO","OOO"}, new String[]{"E 2","S 3","W 1"})));
  }

  static int[] solution(String[] park, String[] routes) {

    // 방향명대로 이동할 방향을 지정
    directionMap.put("E", new int[]{0, 1});
    directionMap.put("W", new int[]{0, -1});
    directionMap.put("N", new int[]{-1, 0});
    directionMap.put("S", new int[]{1, 0});

    // 지도의 크기 r, c
    r = park.length;
    c = park[0].length();
    // X 를 제외한 S, O 는 모두 갈 수 있는 곳으로 판단되므로 true 로 체크해줄 배열
    map = new boolean[r][c];

    // 개의 위치 정보 (r, c)
    int[] dog = new int[2];

    // park 순회하며 갈 수 있는 곳 체크 및 S, 개의 시작 위치 표시
    for (int i=0;i<r;i++) {
      for (int j=0;j<c;j++) {
        // O나 S면 map 에 True 표시
        if (park[i].charAt(j) == 'O')
          map[i][j] = true;
        if (park[i].charAt(j) == 'S') {
          map[i][j] = true;
          // S 는 시작위치도 표시
          dog = new int[]{i, j};
        }
      }
    }

    // 경로 배열 순회
    for (String route : routes) {
      // 방향
      String direction = route.split(" ")[0];
      // 이동할 칸 수
      int size = Integer.parseInt(route.split(" ")[1]);
      dog = nextDog(dog, direction, size);
    }

    return dog;
  }

  // 다음 개의 위치를 계산할 메서드
  // start: 시작 위치
  // direction: 방향
  // size: 이동할 칸 수
  static int[] nextDog(int[] start, String direction, int size) {

    // 시작 위치
    int start_r = start[0];
    int start_c = start[1];
    // directionMap 에서 direction 에 해당하는 value 가져와서 이동할 방향을 direc 에 저장
    int[] direc = directionMap.get(direction);

    // size 만큼 이동하며
    for (int i=0;i<size;i++) {
      // 다음 위치는 각 start_r, start_c 에 direc 을 더해주며 계산
      start_r += direc[0];
      start_c += direc[1];
      // 개가 산책할 수 있는 조건 2가지
      // 1. 지도 범위 안인가
      // 2. 장애물이 없는가
      // 둘을 판단해 조건에 부합하지 않는다면 갈 수 없다는 의미로 바로 start 를 반환한다.
      if (0 <= start_c && start_c < c && 0 <= start_r && start_r < r
              && map[start_r][start_c]) {
        continue;
      }
      return start;
    }

    // 그 이외에는 이동 완료한 start_r, start_c 를 반환한다.
    return new int[]{start_r, start_c};

  }
}
