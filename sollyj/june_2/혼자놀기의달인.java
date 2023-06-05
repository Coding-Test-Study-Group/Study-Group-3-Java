package sollyj.june_2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class 혼자놀기의달인 {
	static boolean[] visited;
	static List<Integer> cardList;    // 주어진 cards을 리스트로 바꿔서 변환
	static int count;

	public static void main(String[] args) {
		System.out.println(solution(new int[] {8, 6, 3, 7, 2, 5, 1, 4}));
	}

	private static int solution(int[] cards) {
		int answer = 0;

		int len = cards.length;
		visited = new boolean[len + 1];
		cardList = new ArrayList<>(len + 1);
		List<Integer> boxCount = new ArrayList<>();    // n번째 상자를 선택했을때 열려있는 상자 수

		cardList.add(0);    // 인덱스 1부터 카드번호를 삽입할 것이므로 0번째엔 더미를 넣어준다.

		for (int i = 0; i < len; i++) {
			cardList.add(cards[i]);
		}

		for (int i = 1; i <= len; i++) {
			count = 1;
			dfs(i);

			if (count == len) {
				boxCount.add(0);
				boxCount.add(0);
				break;
			} else {
				boxCount.add(count);
			}
		}

		Collections.sort(boxCount, Collections.reverseOrder());
		answer = boxCount.get(0) * boxCount.get(1);
		return answer;
	}

	private static void dfs(int card) {
		visited[card] = true;

		if (!visited[cardList.get(card)]) {
			count += 1;
			dfs(cardList.get(card));
		}
	}
}
