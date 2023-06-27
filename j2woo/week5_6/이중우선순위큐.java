package j2woo.week5_6;

import java.util.*;
public class 이중우선순위큐 {
    public int[] solution(String[] operations) {
        int[] answer = {};
        ArrayList<Integer> list = new ArrayList<>();

        int len = operations.length;

        for (int i = 0; i < len; i++) {
            String [] oper = operations[i].split(" ");

            // I 일 때는 리스트에 넣어준다.
            // 리스트 오름차순 정렬
            if (oper[0].equals("I")) {
                list.add(Integer.parseInt(oper[1]));
                list.sort(null);
            }

            // I가 아니고 큐가 비지 않았더라면
            else if (!list.isEmpty()) {
                // 1이면 최댓값 삭제 (오름차순 정렬) 맨 뒤 삭제
                if (oper[1].equals("1")) {
                    list.remove(list.size() -1);
                }
                // -1이면 최솟값 삭제 (오름차순 정렬) 맨 앞 삭제
                else if (oper[1].equals("-1")) {
                    list.remove(0);
                }
            }

        }


        if (list.isEmpty()) {
            answer = new int[] {0,0};
        } else {
            answer = new int[] {list.get(list.size() - 1), list.get(0)};
        }
        return answer;
    }
}
