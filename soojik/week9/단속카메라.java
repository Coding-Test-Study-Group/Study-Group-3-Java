package soojik.week9;

import java.util.Arrays;
import java.util.Comparator;

public class 단속카메라 {

  public static void main(String[] args) {
    System.out.println(solution(new int[][]{{-20,-15}, {-14,-5}, {-18,-13}, {-5,-3}}));
  }

  static int solution(int[][] routes) {
    /*
    각 차의 진입-진출 지점이 주어지는데 이 지점이 겹치는 부분에 카메라를 설치하게 된다.
    예시를 들면
    [-20, -15] 가 있을 때 [-14, -5] 는 겹치는 부분이 없으므로 새로운 카메라를 설치한다.
    [-18, -13] 는 가장 처음 [-20, -15] 와 겹치는 부분 [-18, -15] 이 생기게 된다.
    [-5, -3] 는 위와 마찬가지로 [-14, -5] 와 [-5] 지점이 겹친다.

    이처럼 겹치는 부분에 대한, 카메라가 놓일 수 있는 범위를 따로 관리해도 되지만
    미리 주어지는 routes 를 <진출 지점>을 기준으로 내림차순으로 정렬해서
    마지막 카메라가 있는 위치를 lastCamera 라고 저장해 이 값과 현재의 진입 지점을 비교해서,
    현재 진입 지점이 작거나 같다면(이미 진출 지점을 기준으로 내림차순 정렬했으므로 현재 진입지점 <= lastCamera <= 현재 진출지점 을 만족한다.)
    겹치는 부분이 있고, 한 카메라로 n명을 동시에 단속할 수 있음을 알 수 있다.
    만약 현재 진입 지점이 lastCamera 보다 크다면 새로운 카메라를 설치해야 하므로
    카메라 수(answer)와 lastCamera 를 현재 진출지점으로 재설정 해주어 다음 차를 단속할 수 있는지 판단한다.
     */
    int answer = 0;
    Arrays.sort(routes, Comparator.comparingInt((int[] a) -> a[1]));

    // 차의 이동 범위는 -30000 ~ 30000
    int lastCamera = -30001;
    for (int[] route : routes) {
      // 이미 설치된 지점, lastCamera 와 겹치므로 따로 작업이 필요 없다.
      if (route[0] <= lastCamera) {
        continue;
      }

      // 그 외의 경우는 현재 진출 지점에 새롭게 카메라르 세우고
      lastCamera = route[1];
      // 카메라 수를 1만큼 증가시킨다.
      ++answer;
    }

    return answer;
  }
}