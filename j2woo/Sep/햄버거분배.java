package j2woo.Sep;

import java.util.*;
import java.io.*;
public class 햄버거분배 {
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // 햄버거가 먹혔거나 사람이 먹었는지 확인하는 배열
        boolean [] checked = new boolean[N];

        // 햄버거와 사람 ex) HHPHPPHHPPHPPPHPHPHP
        String str = br.readLine();

        // 정답, 햄버거를 먹을 수 있는 최대 사람 수
        int answer = 0;

        // str을 확인하면서 햄버거와 사람이 K거리 안에 있는지 확인
        for (int i = 0; i < N; i++) {
            // 현재 인덱스의 햄버거 or 사람
            char c = str.charAt(i);

            // 먹혔거나 먹은 것이라면 다음 인덱스로 넘어감
            if (checked[i]) continue;

            // K거리를 확인할 때 끝보다 뒤일 경우를 주의하기 위하여
            int max = Math.min(i + K + 1, N);

            // K거리까지 확인한다.
            // 해당 인덱스가 먹히거나 먹지 않은 무엇이고 자기 자신과 다른 것이라면
            // 햄버거를 먹을 수 있거나 먹힐 수 있기 때문에 정답을 늘리고 i와 j에 해당하는 boolean 배열에 확인처리를 해준다.
            for (int j = i + 1; j < max; j++) {
                if (checked[j] == false && str.charAt(j) != c) {
                    answer++;
                    checked[i] = true;
                    checked[j] = true;
                    break;
                }
            }
        }

        System.out.println(answer);
    }
}
