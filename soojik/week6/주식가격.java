package soojik.week6;

import java.util.Stack;

public class 주식가격 {

  public static void main(String[] args) {
    solution(new int[]{1, 2, 3, 2, 3});
  }

  static int[] solution(int[] prices) {

    int size = prices.length;

    int[] answer = new int[size];

    // 스택에 차례대로 각 초마다의 Price(시간, 가격) 정보를 넣어준다.
    // 이때 시간은 1, 2, ... 가 아니라 0 부터 시작하도록 한다.
    Stack<Price> stack = new Stack<>();

    for (int i=0;i<size;i++) {
      // 지금 가격은 now_price 라고 할 때
      int now_price = prices[i];

      // stack 에 값이 존재하면서, 가장 위(최근)의 Price 값이 now_price 보다 크다면 이떄보다 떨어졌다는 뜻이니까
      while (!stack.isEmpty() && stack.peek().value > now_price) {
        // 비교한 Price 인스턴스를 꺼내 p 가 참조하도록 하고
        Price p = stack.pop();

        // answer 배열에 p.second 에 해당하는 위치에 현재 시간(i)과 p.second 의 차를 넣어준다.
        answer[p.second] = i - p.second;
      }

      // 만약 stack 에 값이 없거나 가장 위에 있는 Price 값이 now_price 보다 작다면 현재 Price 정보를 stack 에 추가해준다.
      stack.add(new Price(i, now_price));
    }

    // 모든 순회가 끝나면 stack 에 남은 Price 정보들은 각자의 second  초 이후로 한번도 안떨어졌다는 뜻이니까
    // 하나씩 꺼내서 answer 배열에 각자의 second 위치에 size - 1 - p,second 값을 넣어준다.
    // size - 1 인 이유는 앞서 초 카운트를 0부터 시작해서
    while (!stack.isEmpty()) {
      Price p = stack.pop();
      answer[p.second] = size - 1 - p.second;
    }

    return answer;
  }
}

// n초의 가격 정보를 담을 클래스
class Price {
  int second;
  int value;

  public Price(int second, int value) {
    this.second = second;
    this.value = value;
  }
}
