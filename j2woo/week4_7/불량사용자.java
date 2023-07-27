package j2woo.week4_7;
import java.util.*;
public class 불량사용자 {
    public static HashMap<Integer, LinkedList<Integer>> bIdMap;
    public static HashSet<HashSet<Integer>> set;
    static int bIdCnt;
    static int answer;
    public int solution(String[] user_id, String[] banned_id) {
        answer = 0;

        bIdMap = new HashMap<>();
        set = new HashSet<>();
        int userCnt = user_id.length;
        bIdCnt = banned_id.length;
        for (int j = 0; j < bIdCnt; j++) {
            for (int i = 0; i < userCnt; i++) {
                if (banned_id[j].length() == user_id[i].length() && sameId(user_id[i].toCharArray(), banned_id[j].toCharArray())) {
                    LinkedList<Integer> list = bIdMap.getOrDefault(j, new LinkedList<>());
                    list.add(i);
                    bIdMap.put(j, list);
                }
            }
        }


        boolean [] checked = new boolean[userCnt];
        DFS(0,  new HashSet<Integer>());

        return set.size();
    }

    public void DFS(int idx, HashSet<Integer> hset) {
        LinkedList<Integer> list = bIdMap.get(idx);
        for (Integer num : list) {
            if (!hset.contains(num)) {

                hset.add(num);
                if (idx == bIdCnt - 1) {
                    set.add(hset);
                } else {

                    DFS(idx + 1, hset);
                }
                hset.remove(num);
            }
        }
    }


    public boolean sameId (char[] id, char[] bId) {
        int len = id.length;
        for (int i = 0; i < len; i++) {
            if (bId[i] == '*') continue;
            else if (id[i] != bId[i]) {
                return false;
            }
        }
        return true;
    }
}
