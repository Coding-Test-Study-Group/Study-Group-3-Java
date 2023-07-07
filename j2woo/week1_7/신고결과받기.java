package j2woo.week1_7;
import java.util.*;
public class 신고결과받기 {
    public int[] solution(String[] id_list, String[] report, int k) {
        // 유저 수
        int len = id_list.length;
        int[] answer = new int[len];
        // 유저의 인덱스
        HashMap <String, Integer> userIdx = new HashMap<>();
        // key: 신고 당한 user, value: 신고한 Id 해시셋
        HashMap <String, HashSet<String>> userList = new HashMap<>();
        // userIdx에 인덱스를 넣고, userList에 빈 해시 셋을 넣는다.
        for (int i = 0; i < len; i++) {
            userIdx.put(id_list[i], i);
            userList.put(id_list[i], new HashSet<>());
        }


        // 신고 당한 횟수
        int [] cnt = new int [len];
        // 정지 해시 셋
        HashSet<String> stop = new HashSet<>();

        // user: 신고한 사람, reportUser: 신고당한 사람
        // userList에는 해당 유저를 신고한 사람을 담는 해시 셋을 value로 가지고 있다.
        // 그러므로 user가 그 set에 없다면
        // user를 set에 넣어서 userList에 다시 넣어주고,
        // 신고당한 사람의 신고 횟수 또한 늘려준다.
        // 여기서 cnt(신고당한 횟수)가 k 이상이면 stop(정지한 사람 해시 셋)에 stopUser를 넣어준다.
        int reportLen = report.length;
        for (int i = 0; i < reportLen; i++) {
            String user = report[i].split(" ")[0];
            String reportUser = report[i].split(" ")[1];
            HashSet<String> set = userList.get(reportUser);
            if (!set.contains(user)) {
                int reportUserIdx = userIdx.get(reportUser);
                cnt[reportUserIdx]++;
                set.add(user);
                userList.put(reportUser, set);
                if (cnt[reportUserIdx] >= k) stop.add(reportUser);
            }
        }

        // 정지당한 사람의 신고한 리스트를 찾아서
        // 리스트에 있는 사람들의 메일을 받는 횟수를 늘려준다.
        // answer[idx]++
        for (String stopUser : stop) {
            HashSet <String> user = userList.get(stopUser);
            for (String u : user) {
                int idx = userIdx.get(u);
                answer[idx]++;
            }
        }
        return answer;
    }
}
