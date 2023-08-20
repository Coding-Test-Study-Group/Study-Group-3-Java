package soojik.week13;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class 압축 {

  static HashMap<String, Integer> dict = new HashMap();
  static int len;
  static int idx = 27; // 마지막 사전 번호
  static List<Integer> answer = new ArrayList();
  public static void main(String[] args) {
    System.out.println(solution("KAKAO"));
    System.out.println(solution("TOBEORNOTTOBEORTOBEORNOT"));
    System.out.println(solution("ABABABABABABABAB"));
  }

  static int[] solution(String msg) {

    // 1. 사전 초기화
    for (int i=1;i<=26;i++) {
      dict.put(Character.toString((char)(64 + i)), i);
    }

    len = msg.length();

    // 2. 없는 글자는 뒤에 27번부터 차례로 넣어주기
    int searching_idx = 0;
    while (searching_idx < len) {
      // 다음 탐색할 인덱스 번호 구하기
      int next = addIdx(msg, searching_idx);
      searching_idx += next;
    }

    return answer.stream().mapToInt(Integer::intValue).toArray();
  }

  // 현재 searching_idx 에서 시작된 단어 탐색이 끝난 후 다음으로 탐색을 진행할 단어의 첫글자 인덱스가 얼마나 떨어져있는지를 반환한다.
  static int addIdx(String msg, int start_idx) {
    // next는 start_idx에 더할 값으로 1씩 증가시키면서 글자들이 사전에 있는지 탐색한다.
    int next = 0;
    // 색인번호 저장할 변수
    int last_idx = 0;
    // w에 현재 입력할 단어 업데이트
    StringBuilder w = new StringBuilder();
    // next 를 1씩 증가시키면서
    while (start_idx + next < len) {
      // start_idx 부터 글자 하나씩 더해준다.
      w.append(msg.charAt(start_idx + next));
      // w가 사전에 있다면
      if (dict.containsKey(w.toString())) {
        // 색인번호에 업데이트 해준다.
        last_idx = dict.get(w.toString());
        // 다음 글자 더해서 탐색 진행하도록
        ++next;
      }
      // 없는 단어를 찾으면 사전에 추가해준다.
      else {
        dict.put(w.toString(), idx);
        // 다음에 새로운 단어 찾으면 매길 번호
        ++idx;
        break;
      }
    }

    // 마지막으로 업데이트된 색인 번호를 answer에 더해준다.
    answer.add(last_idx);
    // 다음으로 탐색할 글자는 마지막으로 존재하는 단어를 찾은 후의 글자부터 시작이다.
    return next;
  }
}
