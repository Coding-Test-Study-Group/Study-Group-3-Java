package soojik.week11;

import java.util.HashSet;

public class 불량사용자 {
  static int answer = 0;
  static HashSet<HashSet<String>> possibleSet = new HashSet();
  public static void main(String[] args) {
    System.out.println(solution(new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"}, new String[]{"fr*d*", "abc1**"}));
    System.out.println(solution(new String[]{"fr*d*", "abc1**"}, new String[]{"*rodo", "*rodo", "******"}));
    System.out.println(solution(new String[]{"fr*d*", "abc1**"}, new String[]{"fr*d*", "*rodo", "******", "******"}));
  }

  static int solution(String[] user_id, String[] banned_id) {
    dfs(user_id, banned_id, new HashSet<String>(), 0);

    // 결과적으로 possibleSet 에 남은 조합들이 나올 수 있는 모든 경우이므로 size 를 반환한다.
    return possibleSet.size();
  }

  // user 문자열과 banned 문자열 비교할 메서드
  static boolean isValid(String user, String banned) {
    int id_len = user.length();

    for (int i=0;i<id_len;i++) {
      if (banned.charAt(i) != '*' && user.charAt(i) != banned.charAt(i)) return false;
    }

    return true;
  }

  /*
  user_id: 주어진 응모 유저 아이디
  banned_id: 주어진 제재 유저 아이디
  visit: 여러 경우의 제재 후보조합을 구성하기 위한 HashSet
  index: banned_id 배열을 순회할 index
   */
  static void dfs(String[] user_id, String[] banned_id, HashSet<String> visit, int index) {
    // index 가 banned_id의 길이와 같아졌다면 즉, banned_id 의 순회를 마쳤을 경우
    if (banned_id.length == index) {
      // 해당 재귀함수로 만들어낸 제재 후보조합을 possibleSet 에 넣어준다.
      // HashSet<HashSet<String>> possibleSet: 후보조합은 순서상관없이 중복되어선 안되므로 다음과 같이 관리한다.
      possibleSet.add(new HashSet(visit));
      return;
    }

    // user_id(응모 유저 아이디) 배열을 순회하며
    int id_len = user_id.length;
    for (int i=0;i<id_len;i++) {
      // user_id[i] 가
      // 1. 만약 이전 제재 아이디 후보로 후보 조합에 오르지 않을때
      // 2. 현재 검사 중인 banned_id[index] 와 길이가 같을때
      // 3. user_id[i] 가 banned_id[index] 와의 유사도 검사(isValid)에서 true 로 검증되었을 때
      if (!visit.contains(user_id[i]) && user_id[i].length() == banned_id[index].length() && isValid(user_id[i], banned_id[index])) {
        // 현재 후보 조합에 추가한 후
        visit.add(user_id[i]);
        // 다음 banned_id 에 대한 후보를 추가하기 위해 재귀호출한다.
        dfs(user_id, banned_id, visit, index+1);
        // 다시 후보 조합에서 제거해주고 다음 user_id[i+1] 에 대해 검사한다.
        visit.remove(user_id[i]);
      }
    }
  }
}
