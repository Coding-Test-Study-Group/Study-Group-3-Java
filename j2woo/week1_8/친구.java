package j2woo.week1_8;
import java.util.*;


public class 친구 {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        sc.nextLine();
        // 현재 친구 리스트 배열 (1:1) 친구
        ArrayList<Integer> list [] = new ArrayList[N];

        // key : 사람, value : 2-친구들
        HashMap<Integer, HashSet<Integer>> map = new HashMap<>();

        // 현재 직속 친구인 사람을
        // set에 담아 map에 넣고
        // list에도 넣는다.
        for (int i = 0; i < N; i++) {
            list [i] = new ArrayList<>();
            String s = sc.nextLine();
            HashSet<Integer> set =  new HashSet<>();
            for (int j = 0; j < N; j++) {
                if (s.charAt(j) == 'Y') {
                    set.add(j);
                    list[i].add(j);
                }
            }
            map.put(i, set);
        }

        int max = 0;
        // 2-친구를 찾기 위해
        // 현재 i의 일대일 친구 리스트들에
        // 리스트 친구들에 들어있는 다른 친구들이 2-친구이므로 set에 넣어준다.
        for (int i = 0; i < N; i++) {
            for (int num : list[i]) {
                HashSet<Integer> set = map.get(num);
                for (int num2 : list[i]) {
                    if (num != num2) {
                        set.add(num2);
                    }
                }
                map.put(num, set);
                max = Math.max(max, set.size());
            }
        }

        System.out.println(max);
    }
}
