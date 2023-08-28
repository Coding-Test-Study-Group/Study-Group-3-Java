package soojik.week14;

public class n진수게임 {
  // allNum: 0부터 임의의 수까지 n진수로 변환 후 모두 저장
  static StringBuilder allNum = new StringBuilder();
  // answer: 튜브가 말해야할 t개의 숫자 담을 변수
  static StringBuilder answer = new StringBuilder();
  public static void main(String[] args) {
    System.out.println(solution(2, 4, 2, 1));
    System.out.println(solution(16, 16, 2, 1));
    System.out.println(solution(16, 16, 2, 2));
  }
  static String solution(int n, int t, int m, int p) {
        /* num을 0부터 allNum의 길이가 참여인원 * 미리 구할 숫자의 개수보다 작을 동안,
        임의의 수까지 1씩 증가시키며 n진수로 변환해 allNum에 더해준다.
        m * t인 이유?
        튜브가 t개를 말하기 위해서는 적어도 t만큼의 게임이 진행되어야하기때문에
        */
    int num = 0;
    while (allNum.length() <= m*t) {
      addNum(num++, n);
    }

    // idx 는 allNum에서 주어진 p를 참고해 튜브의 차례에서만 뽑아내기 위함
    int idx = 0;

    // t만큼 반복
    while (idx < t) {
      // answer 에 allNum에서 튜브 차례에 대한 숫자를 뽑아준다.
      answer.append(allNum.charAt(idx * m + p - 1));
      ++idx;
    }

    return answer.toString();
  }

  static void addNum(int num, int n) {
    StringBuilder sb = new StringBuilder();

    // 주어진 수가 0 이면 바로 0을 더하고 메서드 종료
    if (num == 0) {
      allNum.append(0);
      return;
    }
    // num에 대한 n진수 값 구해서 sb에 더해주기
    while (0 < num) {
      // 10에서 15사이의 수는 각 A~F로 출력필요
      if (10 <= num % n && num % n <= 15) {
        sb.append((char) (num%n+55));
        num /= n;
        continue;
      }
      sb.append(num%n);
      num /= n;
    }

    // 그렇게 구한 sb를 allNum에 뒤집어 더해준다.
    allNum.append(sb.reverse());
  }
}
