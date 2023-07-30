package sollyj.july_4;

import java.util.Arrays;
import java.util.HashSet;

public class 불량사용자 {
	static HashSet<String> restrictedIds;    // 제재 아이디들을 조합한 문자열을 넣을 set
	static boolean[] visited;

	public static void main(String[] args) {
		String[] user_id = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
		String[] banned_id1 = {"fr*d*", "abc1**"};
		String[] banned_id2 = {"*rodo", "*rodo", "******"};
		String[] banned_id3 = {"fr*d*", "*rodo", "******", "******"};

		System.out.println(solution(user_id, banned_id1));
		System.out.println(solution(user_id, banned_id2));
		System.out.println(solution(user_id, banned_id3));
	}

	private static int solution(String[] user_id, String[] banned_id) {
		restrictedIds = new HashSet<>();
		visited = new boolean[user_id.length];

		// 정규표현식 문법상 *를 .으로 바꿔준다.
		String[] bannedIdRegex = new String[banned_id.length];

		for (int i = 0; i < banned_id.length; i++) {
			bannedIdRegex[i] = banned_id[i].replace("*", ".");
		}

		combination(user_id, bannedIdRegex, 0, "");

		return restrictedIds.size();
	}

	// 전체 아이디, 불량 아이디, 현재 탐색하고 있는 인덱스, 제재 아이디 조합 결과
	private static void combination(String[] userIds, String[] bannedIds, int idx, String result) {
		// bannedIds의 마지막 인덱스까지 탐색 마쳤으면 조합한 결과 set에 add
		if (idx == bannedIds.length) {
			String[] restrictedIdArr = result.split(",");
			Arrays.sort(restrictedIdArr);

			String restrictedIdStr = "";

			for (int i = 0; i < restrictedIdArr.length; i++) {
				restrictedIdStr = restrictedIdStr.concat(restrictedIdArr[i]);
			}

			restrictedIds.add(restrictedIdStr);

			return;
		}

		for (int i = 0; i < userIds.length; i++) {
			if (!visited[i] && userIds[i].matches(bannedIds[idx])) {
				visited[i] = true;
				combination(userIds, bannedIds, idx + 1, result + "," + userIds[i]);    // 조합에 추가하며 더 깊이 탐색
				visited[i] = false;    // 탐색 끝났으면 방문배열 초기화하고 Back tracking
			}
		}
	}
}
