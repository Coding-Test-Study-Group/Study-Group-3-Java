package soojik.week5.대칭차집합;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) {

    /*
    대칭 차집합은 A에 대한 B의 대한 차집합 (A - B), B에 대한 A의 차집합 (B - A) 의 합집합을 의미한다.
    e.g.) A = { 1, 2, 4 } 이고, B = { 2, 3, 4, 5, 6 } 일 때
    A - B = { 1 }
    B - A = { 3, 5, 6 }

    (A - B) U (B - A) = { 1, 3, 5, 6 }

    그림을 그려보면 대칭 차집합은 A와 B의 합집합에 대한 (A U B) A와 B의 교집합(A ∩ B) 의 차집합 이다.
    e.g.) A U B = { 1, 2, 3, 4, 5, 6 }
    A ∩ B = { 2, 4 }
    (A U B) - (A ∩ B) = { 1, 3, 5, 6 }

     */
    try {
      System.setIn(new FileInputStream("soojik/week5/대칭차집합/input.txt"));
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

      // 원소의 순서는 상관없고, 원소 검색만 용이하면 되므로 검색 속도가 빠른 HashSet 을 이용할 수 있었다.
      // 다만 여기서 걱정되었던 점은 집합 안에 중복되는 원소가 있다는 것이었는데 여기 예제에는 해당되지 않은 것 같다.
      HashSet<Integer> set = new HashSet<>();

      StringTokenizer st = new StringTokenizer(br.readLine());
      int A = Integer.parseInt(st.nextToken());
      int B = Integer.parseInt(st.nextToken());

      // 처음엔 A 집합을 모두 set 에 넣어준다.
      st = new StringTokenizer(br.readLine());
      for (int i = 0; i < A; i++) {
        set.add(Integer.parseInt(st.nextToken()));
      }

      // B 집합은 입력받으며
      st = new StringTokenizer(br.readLine());
      for (int i = 0; i < B; i++) {
        int num = Integer.parseInt(st.nextToken());

        // set 에 이미 있다면 교집합에 들어갈 원소이므로 삭제해주고
        if (set.contains(num)) {
          set.remove(num);
        }
        // 없다면 넣어줘야 하니까 더해준다.
        else {
          set.add(num);
        }
      }

      System.out.println(set.size());
    } catch (IOException e) {
      System.out.println(e.getLocalizedMessage());
    }
  }
}
