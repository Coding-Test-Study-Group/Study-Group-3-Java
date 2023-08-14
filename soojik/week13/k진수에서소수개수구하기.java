package soojik.week13;

public class k진수에서소수개수구하기 {

  public static void main(String[] args) {

  }

  /*
  n의 k진수 값을 구하며 동시에 0이 나올 때마다 누적된 값들이 소수에 해당하는지 검사한다.
   */
  static int solution(int n, int k) {
    int answer = 0;

    StringBuilder sb = new StringBuilder();

    // k진수 구하눈 과정
    while (n > 0) {
      // 나머지를 미리 저장해놓고
      long 나머지 = n % k;
      // 나머지 값에 상관 없이 다음 k진수 다음 숫자 구하려면 나누기
      n /= k;
      // 나머지가 0이라면
      if (나머지 == 0) {
        // 나누기로 진수값 구하는 것은 맨 뒤에서부터 구하는 것이기 떄문에 누적된 진수값을 뒤집어준다.
        sb.reverse();
        // 만약 연속해서 0이 나오지 않았고(누적된 값이 있고)
        // isPrime 메서드에 충족한다면(소수라면)
        if (sb.length() > 0 && isPrime(Long.parseLong(sb.toString()))) {
          // 답 1 증가
          ++answer;
        }
        // 새롭게 소수로 판별할 수 있게 sb 초기화
        sb = new StringBuilder();
        // 0은 따로 sb에 넣지 않는다.
        continue;
      }
      // 나머지가 0이 아니라면 sb에 누적
      sb.append(나머지);
    }

    // 마지막으로 sb에 남은 값이 있다면 위와 똑같이 검사해준다.
    sb.reverse();
    if (sb.length() > 0 && isPrime(Long.parseLong(sb.toString()))) ++answer;

    return answer;
  }

  // 소수 판별하는 메서드
  static boolean isPrime(long num) {
    // 주어진 num이 2보다 작으면 false 반환
    if (num < 2) return false;
    // 이외의 경우는 2부터 num의 제곱근까지 num을 나눠보며
    for (int i = 2; i <= Math.sqrt(num); i++) {
      // 만약 num이 i로 딱 맞아떨어진다면 소수가 아닌 것으로 판단
      if (num % i == 0) return false;
    }
    // 위에서 걸리지 않았다면 true
    return true;
  }
}