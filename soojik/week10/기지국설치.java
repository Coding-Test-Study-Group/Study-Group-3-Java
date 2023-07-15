package soojik.week10;

public class 기지국설치 {

  public static void main(String[] args) {
    System.out.println(solution(11, new int[]{4, 11}, 1));
    System.out.println(solution(16, new int[]{9}, 2));
  }

  static int solution(int n, int[] stations, int w) {
    int answer = 0;

    // 5g 기지국이 영향끼치는 범위
    int area_width = 2 * w + 1;

    // last5g: 5g가 되는 직전의 마지막 영역
    int last5g = 0;
    // 기지국 위치 배열 순회
    for (int station : stations) {
      // 만약 직전의 5g 영역이 현재 기지국이 끼치는 영역과 닿지 않는다면 (둘 사이 틈이 있다면)
      if (last5g < station - w) {
        // stations - w - last5g - 1 : 현재 기지국이 영향끼치는 범위의 시작점과 직전 기지국의 끝점 사이의 영역 갯수 (남는영역이라고 하자)
        // 위 값을 area_width 로 나눴을 때 딱 맞아떨어지지 않는 이상 [남는영역 / area_width] 보다 하나가 더 필요하다는 삼항연산자
        answer += (station - w - last5g - 1) % area_width == 0 ? (station - w - last5g - 1) / area_width : (station - w - last5g - 1) / area_width + 1;
      }
      // last5g 업데이트
      last5g = station + w;
    }

    // last5g 가 n보다 작다는 뜻은 아직 last5g ~ n 사이에 틈이 있다는 것
    if (last5g < n) {
      // 위 공식과 마찬가지로 answer 에 [남는영역 / area_width] 또는 [남는영역 / area_width + 1] 로 기지국 개수를 더해준다.
      answer += (n - last5g) % area_width == 0 ? (n - last5g) / area_width : (n - last5g) / area_width + 1;
    }

    return answer;
  }
}
