package j2woo.week1_6;

import java.util.*;

public class 영어끝말잇기 {
    public int[] solution(int n, String[] words) {
        int[] answer = new int[2];

        // words 배열의 크기
        int len = words.length;

        // 단어들을 두번째부터 탐색한다.(첫째 단어는 상관없으므로)
        // 1. 이전에 등장했던 단어 해결
        // 배열을 리스트로 변환하여 현재 단어의 인덱스가 지금 인덱스보다 전에 있을 때
        // 2. 앞사람이 말한 단어의 마지막 문자로 시작하는 단어 해결
        // 전에 문자 끝과 지금 단어의 시작 비교
        // 3. 한글자 체크
        // 탈락한 사람: 단어의 순서를 n으로 나눈 나머지에 1을 더해줌
        // 몇 번째 차례: 단어의 순서를 n으로 나눈 몫에 1을 더해줌
        for (int i = 1; i < len; i++) {
            // 현재 단어
            String now = words[i];
            // 전 단어의 마지막 문자
            char before = words[i-1].charAt(words[i-1].length() -1);

            if (Arrays.asList(words).indexOf(now) < i || before != now.charAt(0) || now.length() == 1) {
                answer = new int[]{i % n + 1, i / n + 1};
                break;
            }
        }

        return answer;
    }
}
