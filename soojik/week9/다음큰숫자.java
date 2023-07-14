package soojik.week9;

public class 다음큰숫자 {

  public static void main(String[] args) {
    System.out.println(solution(78));
    System.out.println(solution(15));
  }

  static int solution(int n) {
    // Integer.bitCount(n) -> n을 이진수로 변환했을 때 1의 갯수를 반환하는 메서드
    // 주어진 n을 이진수로 변환했을 때 1의 갯수를 cnt 에 저장해주었다.
    int cnt = Integer.bitCount(n);

    // cnt 보다 크면서 이진수 변환 후 1의 갯수가 같은 수 중 가장 작은 수를 찾기 위한 반복문
    while (true) {
      // n을 1씩 증가시키며
      ++n;

      // n에 대해 다시 1의 갯수를 new_cnt 에 저장
      int new_cnt = Integer.bitCount(n);

      // cnt 와 new_cnt 가 같다면 반복문 종료 후
      if (cnt == new_cnt) {
        break;
      }
    }

    // 답 출력
    return n;
  }
}
