package j2woo.week4_8;

public class JadenCase문자열만들기 {
    public String solution(String s) {
        String answer = "";
        int len = s.length();

        // 반복문을 통해 공백을 찾아주며 단어를 조건에 맞게 변환한다.
        for (int i = 0; i < len; i++) {
            // 현재 인덱스에 해당하는 문자
            char c = s.charAt(i);

            // 공백 전까지의 문자열
            String w = "";

            // s에서 i인덱스부터의 공백에 해당하는 인덱스
            int idx = s.indexOf(" ", i);

            // i와 idx가 같다면 현재가 공백이므로 answer에 공백 추가
            if (i == idx) {
                answer += " ";
                continue;
            }
            // 현재 공백이 없더라면 w에 s의 인덱스 i부터 끝까지의 문자열을 넣어준다. i는 끝으로 두어서 반복문 끝나게
            else if (idx == -1) {
                w = s.substring(i);
                i = len;
            }
            // 공백이 있더라면 w에 s의 i부터 idx 전까지의 문자열을 넣어주고 i를 공백 전까지 이동한다.
            else {
                w = s.substring(i, idx);
                i = idx - 1;
            }

            // 맨 앞 문자가 소문자라면 맨 앞 문자를 대문자로 바꿔주고 뒤에 문자들은 소문자로 바꿔준다.
            if (c >= 'a' && c <= 'z') {
                w = String.valueOf(c).toUpperCase() + w.substring(1).toLowerCase();
            }
            // 소문자가 아니라면 맨 앞 문자는 그대로 두고 뒤에 오는 문자들은 소문자로 바꿔준다.
            else {
                w = String.valueOf(c) + w.substring(1).toLowerCase();
            }

            // 변형한 문자열을 답에 붙여준다.
            answer += w;

        }

        return answer;
    }
}
