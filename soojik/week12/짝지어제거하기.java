package soojik.week12;

import java.util.Stack;

public class 짝지어제거하기 {
  public static void main(String[] args) {
    System.out.println(solution("baabaa"));
    System.out.println(solution("cdcd"));
  }

  static int solution(String s) {
    // stack의 후입선출 구조를 이용해 가장 나중에 들어간 문자(중간에 있었더라도 짝지어 나간 후)를 들어갈 문자와 검사한다.
    Stack<Character> stack = new Stack();

    char[] arr = s.toCharArray();

    // 문자 하나하나 순회
    for (char c : arr) {
      // stack 이 비었다면 짝지을 문자가 없기때문에 그냥 현재 문자(c) push
      if (stack.isEmpty()) {
        stack.push(c);
        continue;
      }

      // stack 에 값이 있을 경우 가장 맨 윗, 나중에 들어간 문자가 현재 문자(c)와 같다면
      if (stack.peek() == c) {
        // 짝지어졌으니까 뽑아낸다.
        stack.pop();
      }
      // 문자가 다르다면
      else {
        // 일단 stack 에 쌓기
        stack.push(c);
      }
    }

    // for 문에서 짝지어질 수 있는 문자는 모두 나갔기 때문에 남은 문자가 있다면 조건 만족하지 못한다는 것으로 0 반환, 그 외는 1 반환
    return stack.isEmpty() ? 1 : 0;
  }
}
