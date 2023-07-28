package soojik.week12;

import java.util.LinkedList;
import java.util.Queue;

public class 캐시 {
  public static void main(String[] args) {
    System.out.println(solution(3, new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"}));
    System.out.println(solution(3, new String[]{"Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul"}));
    System.out.println(solution(2, new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"}));
    System.out.println(solution(5, new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"}));
    System.out.println(solution(2, new String[]{"Jeju", "Pangyo", "NewYork", "newyork"}));
    System.out.println(solution(0, new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA"}));
  }

  static int solution(int cacheSize, String[] cities) {
    int answer = 0;

    // LRU(Least Recently Used) 캐시 교체 알고리즘을 사용하니까 가장 먼저 들어간(오래 전에 쓰인) 요소가 가장 먼저 나오는 FIFO 구조의 Queue 를 사용해 캐시 저장소를 관리한다.
    Queue<String> cache = new LinkedList<>();

    // 만약 cacheSize 가 0이라면 매번 요청이 cache miss 이기 때문에 조회 수에 5를 곱해 바로 반환한다.
    if (cacheSize == 0) return cities.length * 5;

    // 그 외에는 cities 배열을 순회하며
    for (String city : cities) {
      // 대소문가 구분 안하므로 모두 소문자로 맞춰주고
      city = city.toLowerCase();
      // cache 에 city 가 있는지 먼저 확인 후
      // 1. 있다면
      if (cache.contains(city)) {
        // cache hit 으로 조회시간(answer) 1 증가 시켜주고
        ++answer;
        // cache 에서 city 삭제(remove) 후 가장 앞으로 옮긴다(add).
        cache.remove(city);
        cache.add(city);
        continue;
      }
      // 2. 없다면 새롭게 cache 에 저장해야하는데
      // 만약 cache 가 찼다면
      if (cache.size() >= cacheSize) {
        // 가장 오래 안쓴, 맨 앞에 위치한 요소를 빼주고
        cache.poll();
      }
      // 현재 city 를 뒤에 더해준다.
      cache.add(city);
      // cache miss 이니까 조회시간(answer) 에 5를 더해준다.
      answer += 5;
    }

    return answer;
  }
}
