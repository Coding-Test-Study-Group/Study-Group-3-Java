package soojik.week13;

public class n2배열자르기 {

  public static void main(String[] args) {
    System.out.println(solution(3, 2, 5));
    System.out.println(solution(4, 7, 14));
  }

  static int[] solution(int n, long left, long right) {

    // 답 넣을 answer 배열은 left번째 인덱스부터 right번째 인덱스까지 넣을만큼의 크기로
    long len = right - left + 1;
    int[] answer = new int[(int) len];

    // left부터 right번째 인덱스까지 값 채워주기
    for (long i=left;i<=right;i++) {
      // 규칙은 각 행(몫), 열(나머지) 값의 최댓값에 1을 더한 값이 들어가는 것
      int 몫 = (int) (i / n);
      int 나머지 = (int) (i % n);

      answer[(int) (i - left)] = Math.max(몫, 나머지) + 1;
    }

    return answer;
  }
}
