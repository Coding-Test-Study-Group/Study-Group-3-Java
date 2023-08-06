package j2woo.week1_8;

import java.util.*;
public class 짝지어제거하기 {
    public int solution(String s)
    {
        // s를 문자 배열로 변환
        char [] c = s.toCharArray();

        // 문자를 담아서 최근에 들어온 문자와 비교하여 짝인지 확인하기 위해 스택 사용
        Stack<Character> sk = new Stack<>();
        // 문자열 길이
        int len = s.length();
        // 문자를 하나씩 넣으며
        // 스택이 비었다면 비교할 문자가 없기 때문에 넣고
        // 스택이 비어있지 않다면 최근에 넣었던 문자가 현재 비교할 문자와 같다면 pop하고
        // 같지 않더라면 현재 문자를 스택에 넣는다.
        for (int i = 0; i < len; i++) {
            if (sk.isEmpty()) {
                sk.push(c[i]);
            } else if (sk.peek() == c[i]) {
                sk.pop();
            } else {
                sk.push(c[i]);
            }
        }

        // 스택이 비었더라면 모두 짝지었기 때문에 성공, 아니라면 실패
        int answer = sk.isEmpty() ? 1 : 0;

        return answer;
    }
}
