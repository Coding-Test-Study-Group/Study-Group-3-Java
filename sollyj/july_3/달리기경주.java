package sollyj.july_3;

import java.util.Arrays;
import java.util.HashMap;

public class 달리기경주 {
	public static void main(String[] args) {
		String[] players = {"mumu", "soe", "poe", "kai", "mine"};
		String[] callings = {"kai", "kai", "mine", "mine"};
		System.out.println(Arrays.toString(solution(players, callings)));
	}

	private static String[] solution(String[] players, String[] callings) {
		int len = players.length;
		String[] answer = new String[len];

		HashMap<String, Integer> map1 = new HashMap<>();    // <선수 이름, 현재 등수>
		HashMap<Integer, String> map2 = new HashMap<>();    // <현재 등수, 선수 이름>

		for (int i = 0; i < len; i++) {
			map1.put(players[i], i + 1);
			map2.put(i + 1, players[i]);
		}

		for (String c : callings) {
			int rank = map1.get(c);    // 불린 선수의 현재 등수
			String 추월한선수 = map2.get(rank - 1);    // 추월한 선수 이름

			// map1, map2에 둘이 swap한것을 put 해줌
			map1.put(c, rank - 1);
			map1.put(추월한선수, rank);
			map2.put(rank - 1, c);
			map2.put(rank, 추월한선수);
		}

		// map2의 key값(등수)을 차례대로 get하여 answer에 넣어준다.
		for (int i = 0; i < len; i++) {
			answer[i] = map2.get(i + 1);
		}

		/* values를 순회하며 답을 넣어줘도 된다.
		int idx = 0;
		for (String player : map2.values()) {
			answer[idx++] = player;
		} */

		return answer;
	}
}
