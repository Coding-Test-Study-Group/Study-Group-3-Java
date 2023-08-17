package sollyj.aug_3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class 압축 {
	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution("KAKAO")));
		System.out.println(Arrays.toString(solution("TOBEORNOTTOBEORTOBEORNOT")));
		System.out.println(Arrays.toString(solution("ABABABABABABABAB")));
	}

	private static int[] solution(String msg) {
		List<Integer> answer_list = new ArrayList<>();
		HashMap<String, Integer> dict = new HashMap<>();    // <단어, 색인 번호>

		// A ~ Z까지 사전 초기화
		int dictIdx = 1;
		for (int i = 'A'; i <= 'Z'; i++) {
			dict.put(String.valueOf((char)i), dictIdx++);
		}

		int i = 0;
		while (i < msg.length()) {
			String temp = msg.substring(i, i + 1);
			boolean flag = false;

			// 한글자씩 늘리면서 사전에 없을때까지 반복
			while (dict.containsKey(temp)) {
				i++;

				// 마지막 글자면 종료
				if (i == msg.length()) {
					answer_list.add(dict.get(temp));
					flag = true;
					break;
				}

				temp += msg.substring(i, i + 1);
			}

			if (flag)
				break;

			// 현재 temp는 사전에 없는 문자이므로 사전에 등록해준다.
			answer_list.add(dict.get(temp.substring(0, temp.length() - 1)));
			dict.put(temp, dictIdx++);
		}

		return answer_list.stream().mapToInt(Integer::intValue).toArray();
	}
}
