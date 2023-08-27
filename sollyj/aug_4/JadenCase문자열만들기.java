package sollyj.aug_4;

public class JadenCase문자열만들기 {
	public static void main(String[] args) {
		// System.out.println(solution("3people unFollowed me"));
		// System.out.println(solution("for the last week"));
		System.out.println(solution("for  the first time "));
	}

	private static String solution(String s) {
		StringBuilder answer = new StringBuilder();
		String[] words = s.split(" ");

		for (String word : words) {
			if (word.length() != 0) {
				answer.append(toJaden(word));
			}
			answer.append(" ");
		}

		// 원래 문자열의 마지막에 공백이 있다면 마지막 공백 안지워도 된다.
		if (s.endsWith(" ")) {
			return answer.toString();

		}

		answer.deleteCharAt(answer.length() - 1);    // 마지막 공백 지우기

		return answer.toString();
	}

	private static String toJaden(String str) {
		String result = str.toLowerCase();
		char first = result.charAt(0);    // 첫글자

		if (48 <= first && first <= 57) {    // 숫자면
			return result;
		}

		first = (char)(first - 32);    // 첫글자 소문자를 대문자로 바꿔준다.
		result = first + result.substring(1);

		return result;
	}
}
