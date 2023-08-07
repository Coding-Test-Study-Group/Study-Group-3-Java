package j2woo.week1_8;

import java.util.*;
public class 캐시 {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        ArrayList<String> list = new ArrayList<>();
        int len = cities.length;

        for (int i = 0; i < len; i++) {
            // 현재 탐색 도시 대문자
            String city = cities[i].toUpperCase();
            // 캐시 사이즈가 0이면 모두 미스
            if (cacheSize == 0) {
                answer += 5;
                continue;
            }
            // 현재 캐시 리스트에 없다면 miss이므로 +5
            if (!list.contains(city)) answer += 5;

            // 캐시에 시티가 있더라면 +1을 해주고
            // 현재 탐색 도시의 인덱스를 찾아주고 그 인덱스 값을 제거해준다.(맨 앞에 넣으려고)
            if (list.contains(city)) {
                answer += 1;

                int idx = list.indexOf(city);
                list.remove(idx);
            }
            // 현재 캐시 리스트의 사이즈가 캐시 사이즈와 같다면 리스트의 마지막 값을 제거한다.
            if (list.size() == cacheSize) {
                list.remove(cacheSize - 1);
            }
            // 현재 탐색 도시를 리스트 맨 앞에 넣어준다.
            list.add(0, city);
        }
        return answer;
    }

}
