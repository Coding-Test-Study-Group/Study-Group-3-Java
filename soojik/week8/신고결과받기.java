package soojik.week8;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class 신고결과받기 {

  public static void main(String[] args) {
    System.out.println(Arrays.toString(solution(new String[]{"muzi", "frodo", "apeach", "neo"}, new String[]{"muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"}, 2)));
    System.out.println(Arrays.toString(solution(new String[]{"con", "ryan"}, new String[]{"ryan con", "ryan con", "ryan con", "ryan con"}, 3)));
  }

  static int[] solution(String[] id_list, String[] report, int k) {
    int len = id_list.length;
    int[] answer = new int[len];

    // reported: key-value 값이 <신고 받은 사람, 신고한 사람 집합> 으로 들어가 후에 신고한 사람 집합의 크기가 k 보다 크면 집합을 순회하며 각 회원이 받을 메일을 카운트하게 된다.
    HashMap<String, HashSet> reported = new HashMap<>();

    // email: key-value 값이 <신고한 사람, 받을 메일 갯수>
    HashMap<String, Integer> email = new HashMap<>();

    // 먼저 reported, email 두 hashmap 에 회원 모두를 넣어준다.
    for (String id : id_list) {
      reported.put(id, new HashSet<Integer>());
      email.put(id, 0);
    }

    // from: 신고한 사람
    // to: 신고 당한 사람
    for (String r : report) {
      String from = r.split(" ")[0];
      String to = r.split(" ")[1];

      // reported 신고 목록에 <to, set.add(from)> 신고한 회원을 추가해준다.
      reported.get(to).add(from);
    }

    // 신고 기록 map 을 돌며
    for (String key : reported.keySet()) {
      // 해당 멤버에 신고한 사람 집합을 각 reporters 라는 이름으로 뽑아낸다.
      HashSet<String> reporters = reported.get(key);

      // reporters 크기가 k 보다 크다면
      if (reporters.size() >= k) {
        // 신고한 사람이 받을 메일 횟수에 1씩 증가시켜준다.
        for (String reporter : reporters) {
          email.put(reporter, email.get(reporter) + 1);
        }
      }
    }

    // id_list 순서와 answer 순서를 맞춰줘야하므로 id_list 를 순회하며 메일 받을 횟수를 answer 배열에 넣어준다.
    for (int i=0;i<len;i++) {
      answer[i] = email.get(id_list[i]);
    }

    return answer;
  }
}
