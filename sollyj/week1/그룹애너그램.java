package sollyj.week1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class 그룹애너그램 {
	public static void main(String[] args) {
		System.out.println(groupAnagrams(new String[] {"eat", "tea", "tan", "ate", "nat", "bat"}));
	}

	private static ArrayList<ArrayList> groupAnagrams(String[] strs) {
		// 같은 문자로 이루어져 있는 단어들을 그룹화 시킬때 필요한 HashMap
		// key는 단어, value는 그룹의 인덱스를 의미한다.
		HashMap<String, Integer> map = new HashMap<>();

		ArrayList<ArrayList> result = new ArrayList<>();	// 결과 리스트

		int ri = 0;	// 결과리스트의 인덱스

		for (String str : strs) {
			char[] strToChars = str.toCharArray();    // 문자열을 char배열로 변환
			Arrays.sort(strToChars);    // 오름차순 정렬
			String key = new String(strToChars);    // 다시 문자열로 바꾼다.

			if (map.get(key) == null) {	// 단어가 map에 없으면 새로 그룹을 만들어준다.
				map.put(key, ri++);	// map에 key, value를 할당해준다.

				ArrayList<String> group = new ArrayList<>();
				group.add(str);
				result.add(group);
			} else {	// 문자열이 map에 있으면 group에 추가
				int gi = map.get(key);	// group의 인덱스
				ArrayList<String> group = result.get(gi);
				group.add(str);
				result.set(gi, group);
			}
		}

		return result;
	}
}
