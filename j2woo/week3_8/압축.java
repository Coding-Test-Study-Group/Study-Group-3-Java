package j2woo.week3_8;

import java.util.*;

public class 압축 {
    public int[] solution(String msg) {
        // map을 사전처럼 사용
        // key : 단어, value : 인덱스
        HashMap<String, Integer> map = new
            HashMap<>();

        // 사전에 해당하는  인덱스
        int idx = 1;

        // A - Z까지의 인덱스를 map에 입력
        for (char c = 'A'; c <= 'Z'; c++) {
            map.put(c + "", idx++);
        }

        int len = msg.length();

        // list에는 answer에 해당하는 색인 번호 출력
        ArrayList <Integer> list = new ArrayList<>();

        // msg를 단어 탐색
        for (int i = 0; i < len; i++) {
            // 현재 사전에서 찾을 수 있는 가장 긴 문자열 w
            String now = msg.charAt(i) + "";

            // 현재 문자에 다음 문자를 붙이면서 map에 들어있는지 확인한다.
            // 들어있더라면 현재 now에 다음 문자를 붙여주고
            // 아니라면 map에 다음 문자까지 붙여서 문자와 인덱스를 넣어준다. 반복문 종료
            // 현재 인덱스가 끝일 경우도 생각해준다.
            while (true) {
                if (i != len - 1 && map.containsKey(now + msg.charAt(i + 1))) {
                    now += msg.charAt(++i);
                } else if (i != len - 1) {
                    map.put(now + msg.charAt(i + 1) , idx++);
                    break;
                } else break;
            }

            // 현재 찾은 w의 색인 번호를 리스트에 넣어준다.
            list.add(map.get(now));


        }

        // 리스트에 값들을 answer 배열에 넣어준다.
        int answerIdx = 0;
        int[] answer = new int [list.size()];
        for (Integer i : list) {
            answer[answerIdx++] = i;
        }
        return answer;
    }
}
