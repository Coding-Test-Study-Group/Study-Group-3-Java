package soojik.week10;

import java.util.HashMap;

public class 달리기경주 {

  public static void main(String[] args) {

  }

  /*
  HashMap<String, Integer> prize: [선수명 - 바뀐 등수]
  answer[등수] = 선수명
  callings 을 순회하며 answer 와, HashMap 의 [선수 - 등수] 정보를 계속해서 업데이트 해준다.
   */
  static String[] solution(String[] players, String[] callings) {
    int len = players.length;
    String[] answer = new String[len];

    // 선수명 - 바뀐 등수 를 계속 반영할 HashMap
    HashMap<String, Integer> prize = new HashMap<>();
    // players 정보를 모두 prize 에 넣어준다.
    int idx = 0;
    for (String player : players) {
      prize.put(player, idx);
      answer[idx++] = player;
    }

    // callings 배열을 순회하면서
    for (String calling : callings) {
      // 불린사람은 [현재 prize-1]로 prize 업데이트
      int called_prize = prize.get(calling);
      prize.put(calling, called_prize - 1);
      // 바뀐 등수에 누가 있었는지 answer 에서 가져오기
      String who = answer[called_prize - 1];

      // answer 에서 자리 바꾸기
      answer[called_prize - 1] = calling;
      answer[called_prize] = who;

      // 추월 당해 바뀐 선수도 prize 에 업데이트
      prize.put(who, called_prize);
    }

    return answer;
  }
}
