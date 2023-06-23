package soojik.week7;

import java.util.PriorityQueue;
import java.util.Collections;

class 이중우선순위큐 {
  /*
  문제에서 주어지는 세가지 명령어는
  1. I N: N 삽입
  2. D 1: 최댓값 삭제
  3. D -1: 최솟값 삭제
   */
  public int[] solution(String[] operations) {
    int size = operations.length;

    // pq_asc: 오름차순으로 정렬
    PriorityQueue<Integer> pq_asc = new PriorityQueue<>();

    // pq_desc: 내림차순으로 정렬
    PriorityQueue<Integer> pq_desc = new PriorityQueue<>(Collections.reverseOrder());

    // 주어진 operations 배열 순회하며 명령어(cmd) 와 이어지는 숫자(num) 분리
    int idx = 0;
    while (idx < size) {
      String[] arr = operations[idx].split(" ");
      String cmd = arr[0];
      int num = Integer.parseInt(arr[1]);

      idx++;

      // I(Insert)는 뒤에 오는 숫자 (num) 을 큐에 넣으라는 의미
      if (cmd.equals("I")) {
        pq_asc.add(num);
        pq_desc.add(num);
      }
      // 이외에 올 D(Delete)는 최댓값, 또는 최솟값을 빼는 명령어
      else {
        // 빼야할 때 우선순위 큐에 값이 없으면 삭제명령은 무시되므로 continue
        // 둘 중 하나만 검사해도 되는 이유는 삭제 과정에서 삭제될 숫자가 pq_asc, pq_desc 두 큐에서 모두 삭제되기 때문이다.
        if (pq_asc.isEmpty()) continue;

        // 최솟값 삭제
        if (num == -1) {
          //  먼저 오름차순 정렬된 pq_asc 에서 하나 꺼내 min 에 저장하고
          int min = pq_asc.poll();
          // 해당 숫자를 pq_desc 에서도 함께 삭제한다.
          pq_desc.remove(min);
        }
        // 최댓값 삭제
        else if (num == 1) {
          //  먼저 내림차순 정렬된 pq_desc 에서 하나 꺼내 max 에 저장하고
          int max = pq_desc.poll();
          // 해당 숫자를 pq_asc 에서도 함께 삭제한다.
          pq_asc.remove(max);
        }

        // 이렇게 최솟값, 최댓값을 찾을 수 있는 우선순위 큐를 두개를 둔 다음, 하나씩 꺼내 상대 큐에서도 삭제해주며 같은 상태를 유지해준다.
      }
    }

    // 만약 pq_asc 가 비었다면 모든 숫자가 비었단 의미로 [0, 0] 출력
    // pq_desc 와 같은 상태로 유지되므로 pq_desc 는 따로 검사하지 않음
    if (pq_asc.isEmpty()) {
      return new int[]{0, 0};
    }
    // 그렇지 않은 경우 두 큐에서 최댓값, 최솟값 하나씩 뽑아 출력
    else {
      return new int[]{pq_desc.peek(), pq_asc.peek()};
    }
  }
}