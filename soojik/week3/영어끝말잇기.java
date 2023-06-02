package soojik.week3;

import java.util.Arrays;
import java.util.HashSet;

public class 영어끝말잇기 {
  public static void main(String[] args) {
    System.out.println(Arrays.toString(solution(3, new String[]{"tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank"})));
    System.out.println(Arrays.toString(solution(5, new String[]{"hello", "observe", "effect", "take", "either", "recognize", "encourage", "ensure", "establish", "hang", "gather", "refer", "reference", "estimate", "executive"})));
    System.out.println(Arrays.toString(solution(2, new String[]{"hello", "one", "even", "never", "now", "world", "draw"})));
  }

  static int[] solution(int n, String[] words) {
    int len = words.length;

    // 단어 중복체크를 위해 HashSet 사용
    HashSet<String> set = new HashSet<>();
    // 1번 인덱스부터 순회하기에 0번은 미리 set 에 넣어준다.
    set.add(words[0]);

    // 1부터 순회하며 전 단어 마지막 글자와 비교한다.
    for (int i=1;i<len;i++) {
      // 만약 중복단어라면 바로 현재 차례에서 답을 반환한다.
      if (set.contains(words[i])) {
        return new int[]{i%n + 1, i/n + 1};
      }
      // 만약 앞 단어와 현재 단어가 끝말잇기에 충족하지 못한다면 바로 답을 반환한다.
      if (words[i].charAt(0) != words[i-1].charAt(words[i-1].length()-1)) {
        return new int[]{i%n+1, i/n + 1};
      }

      // 그 이외의 경우에는 set 에 현재 단어를 넣어준다.
      set.add(words[i]);
    }

    // 순회가 끝나고도 반환이 안되었다면 모든 단어가 끝말잇기를 충족했다는 뜻이므로 [0,0]을 반환해준다.
    return new int[]{0, 0};
  }
}
