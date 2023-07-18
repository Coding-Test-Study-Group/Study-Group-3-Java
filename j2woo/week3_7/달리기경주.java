package j2woo.week3_7;

import java.util.*;
public class 달리기경주 {
    public String[] solution(String[] players, String[] callings) {
        // HashMap에 선수들의 등수 넣기
        int len = players.length;
        HashMap<String, Integer> idx = new HashMap<>();

        for (int i = 0; i < len; i++) {
            idx.put(players[i], i);
        }

        // calling에서 불리면 현재 등수와 앞 등수의 인덱스 바꿔주기
        for (String call : callings) {
            // 불린 선수의 인덱스 map에서 찾기
            int index = idx.get(call);

            // players의 인덱스에 따라 순서 바꿔주고, map에서 인덱스 값도 바꿔준다.
            // 앞선수 인덱스 바꾸기
            String another = players[index - 1];
            idx.put(another, index);

            // 불린 선수 인덱스 -1로 바꾸기
            idx.put(call, index - 1);

            // players의 순서도 바꾸기
            players[index] = another;
            players[index - 1] = call;
        }

        return players;
    }
}
