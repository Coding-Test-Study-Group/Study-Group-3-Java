package soojik.week5;

import java.util.Arrays;

public class 구명보트 {

  public static void main(String[] args) {
    System.out.println(solution(new int[]{70, 50, 80, 50}, 100));
    System.out.println(solution(new int[]{70, 80, 50}, 100));
  }
  static int solution(int[] people, int limit) {

    /*
    [70kg, 50kg, 80kg, 50kg]
    와 같은 사람들이 있을때
    limit 을 넘지 않으며 최대 2명까지 탑승할 수 있는 보트 개수의 최솟값을 구하는 문제이다.

    따라서 오름차순으로 정렬하고
    50, 50, 70, 80
    투포인터를 이용해

    각 양쪽부터 안탄 사람들 중


     */

    int answer = 0;

    Arrays.sort(people);

    int p1 = 0;
    int p2 = people.length - 1;

    // 이 과정을 p1이 p2를 넘어설 때까지 반복하며 (p1 이 p2 를 넘어서는 경우는 직전에 인접한 두명을 모두 태웠을 경우이다.)
    while (p1 <= p2) {
      ++answer;

      // 중간에 p1 이 p2 를 만나게되면 마지막 사람을 가리키므로 바로 answer 을 반환하도록 해주었다.
      if (p1 == p2) return answer;

      // 각 양쪽부터 안탄 사람들 중
      // 가장 가벼운 사람 + 가장 무거운 사람을 체크해 최대 수용 무게를 초과한다면 ( < limit)
      if (people[p1] + people[p2] > limit) {
        // 가장 무거운 사람만 태우고 (--p2)
        --p2;
      }
      // 2명이 보트를 탈 수 있다면 모두 태운다. (++p1, --p2)
      else {
        ++p1;
        --p2;
      }
    }

    return answer;
  }
}