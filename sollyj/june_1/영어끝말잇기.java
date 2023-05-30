package sollyj.june_1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 영어끝말잇기 {
	public static void main(String[] args) {
		int n = 3;
		String[] words = {"tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank"};
		System.out.println(Arrays.toString(solution(n, words)));

		// int n = 5;
		// String[] words = {"hello", "observe", "effect", "take", "either", "recognize", "encourage", "ensure",
		// 	"establish", "hang", "gather", "refer", "reference", "estimate", "executive"};
		// System.out.println(Arrays.toString(solution(n, words)));

		// int n = 2;
		// String[] words = {"hello", "one", "even", "never", "now", "world", "draw"};
		// System.out.println(Arrays.toString(solution(n, words)));
	}

	private static int[] solution(int n, String[] words) {
		int[] answer = {0, 0};

		List<String> pass = new ArrayList<>();    // 이미 통과한 단어들
		int turn = 1;    // 턴 몇번 돌았는지
		int t = 1;    // 순서

		for (int i = 0; i < words.length; i++) {
			if (pass.size() == 0) {
				pass.add(words[i]);
			} else if (!pass.contains(words[i])) {    // 이전에 단어가 나온 적이 없는 경우
				String before = pass.get(i - 1);    // 직전 단어

				// 직전 단어의 끝글자와 현재 단어의 첫글자가 같은지 확인
				if (before.substring(before.length() - 1).equals(words[i].substring(0, 1))) {
					pass.add(words[i]);
				} else {    // 다르면 끝말잇기 끝
					answer[0] = t;
					answer[1] = turn;
					break;
				}
			} else {    // 이전에 단어가 나온 적이 있는 경우 끝말잇기 끝
				answer[0] = t;
				answer[1] = turn;
				break;
			}

			if (t % n == 0) {
				turn++;
				t = 1;
				continue;
			}

			t++;
		}

		return answer;
	}
}
