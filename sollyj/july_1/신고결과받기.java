package sollyj.july_1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class 신고결과받기 {
	public static void main(String[] args) {
		//String[] idList = {"muzi", "frodo", "apeach", "neo"};
		String[] idList = {"con", "ryan"};

		//String[] report = {"muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi"};
		String[] report = {"ryan con", "ryan con", "ryan con", "ryan con"};

		//int k = 2;
		int k = 3;

		System.out.println(Arrays.toString(solution(idList, report, k)));
	}

	private static int[] solution(String[] id_list, String[] report, int k) {
		int[] answer = new int[id_list.length];

		// report를 중복 없이 저장할 set
		HashSet<String> report_set = new HashSet<>();
		// 유저당 신고 받은 횟수를 저장할 map <id, 신고받은횟수>
		HashMap<String, Integer> report_map = new HashMap<>();

		// 한 유저가 같은 유저를 여러번 신고하면 하나로 치므로 중복 없이 저장한다.
		for (String r : report) {
			report_set.add(r);
		}

		// iterator를 이용하여 map에 저장
		Iterator<String> it = report_set.iterator();
		while (it.hasNext()) {
			String 신고받은id = it.next().split(" ")[1];

			if (report_map.containsKey(신고받은id)) {
				int value = report_map.get(신고받은id);

				report_map.put(신고받은id, value + 1);
			} else {
				report_map.put(신고받은id, 1);
			}
		}

		// map을 순회하며 정답 도출
		report_map.forEach((key, value) -> {
			if (value >= k) {
				// i번째 유저의 처리 결과 메일 받은 횟수 계산
				for (int i = 0; i < id_list.length; i++) {
					String id = id_list[i];    // i번째 유저

					String temp = id + " " + key;    // report_set에서 찾을 임시 문자열

					if (report_set.contains(temp)) {    // 신고한 적이 있다면 메일 보낸 횟수++
						answer[i]++;
					}
				}
			}
		});

		return answer;
	}
}
