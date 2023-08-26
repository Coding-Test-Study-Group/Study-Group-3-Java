package soojik.week14;

public class JadenCase문자열만들기 {
  public static void main(String[] args) {
    System.out.println(solution("3people unFollowed me"));
    System.out.println(solution("for the last week"));
  }

  static String solution(String s) {

    // 빈칸 기준으로 나눠 배열로 만들기
    char[] char_arr = s.toCharArray();

    StringBuilder answer = new StringBuilder();
    // 단어의 첫 단어인지 판별하기 위한 변수
    boolean isFirst = true;
    // 나눈 문자열 배열 순회
    for (char c : char_arr) {
      // 현 단어 c가 공백이라면
      if (c == ' ') {
        // 그대로 answer에 더해주고
        answer.append(' ');
        // isFirst는 true로 설정
        isFirst = true;
        continue;
      }

      // 만약 첫 단어라면
      if (isFirst) {
        // 대문자로 변경 후 answer에 더해주고
        answer.append(Character.toUpperCase(c));
        // isFirst는 false로 설정
        isFirst = false;
        continue;
      }
      // 첫 단어가 아니라면 소문자로 변경 후 answer에 더해주기
      answer.append(Character.toLowerCase(c));
    }

    return answer.toString();
  }
}