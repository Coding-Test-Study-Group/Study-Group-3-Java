package soojik.week10;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class 시소짝꿍 {

  public static void main(String[] args) {
    System.out.println(solution(new int[]{100,180,360,100,270}));
  }

  static long solution(int[] weights) {
    long answer = 0;

    // 실수 계산이 있어 key 값을 double 로 설정했다.
    HashMap<Double, Integer> map = new HashMap<>();

    // 모든 weight 값을 map 에 넣어 카운트 해주었다.
    for(int weight: weights) {
      map.put((double) weight, map.getOrDefault((double) weight, 0) + 1);
    }

    // key 값을 기준으로 map 순회
    List<Double> keys = new ArrayList(map.keySet());
    for (double weight : keys) {
      // ==: 같은 거리에서 마주보고 앉는 경우는 (map.get(weight))C2 이다.
      answer += (long) map.get(weight) * (map.get(weight) - 1) / 2;

      // 2, 3: [weight : 상대 = 2 : 3] 일 때 상대 무게의 사람 수를 찾아 계산
      answer += (long) map.get(weight) * map.getOrDefault(weight * 3.0 / 2, 0);

      // 2, 4: 이 경우는 나(weight) 기준으로 무게가 2배인 사람 수를 찾아 계산
      answer += (long) map.get(weight) * map.getOrDefault(weight * 2.0, 0);

      // 3, 4: [weight : 상대 = 4 : 3] 일 때 상대 무게의 사람 수를 찾아 계산
      answer += (long) map.get(weight) * map.getOrDefault(weight * 3.0 / 4, 0);
    }

    // 주의할 점은 answer 가 long 이라고 해서 오버플로우가 일어나지 않는것은 아니다.
    // 위 for 문 안에서 계산과정 도중 일어날 수도 있어 long 으로 형번환해 answer 에 넣어주었다.
    return answer;
  }
}
