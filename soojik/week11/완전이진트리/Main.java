package soojik.week11.완전이진트리;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Main {
  static int K;
  static int[] order_arr;
  static StringBuilder[] tree_arr;
  public static void main(String[] args) {
    try {
      System.setIn(new FileInputStream("soojik/week11/완전이진트리/input.txt"));
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

      K = Integer.parseInt(br.readLine());

      // order_arr: 주어진 전위 순회일 때 방문 순서 배열
      order_arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

      // tree_arr: K 깊이(레벨)만큼의 완전 이진 트리이고, 조건에 맞게 출력하도록 StringBuilder[K] 배열 사용
      tree_arr = IntStream.range(0, K).mapToObj(StringBuilder::new).toArray(StringBuilder[]::new);

      // e.g.) 레벨이 3인 완전 이진 트리의 원소 개수는 2^3 - 1
      int end = (int) Math.pow(2, K) - 1;

      setNode(0, end, 0);

      for (StringBuilder sb : tree_arr) {
        System.out.println(sb);
      }

    } catch (IOException e) {
      System.out.println(e.getLocalizedMessage());
    }
  }


  /*
  setNode: 재귀함수로서 전위(왼쪽노드그룹 - 중간노드 - 오른쪽노드그룹) 순회임을 이용해 가운데 mid(start+end/2) 노드를 맨 윗레벨, 루트노드부터 채운다.
  start: 시작 위치
  end: 끝위치
  level: 현재 채우고있는 노드 깊이(레벨)
   */
  static void setNode(int start, int end, int level) {
    // 만약 K만큼 level 이 도달했다면 재귀 종료
    if (level == K) return;

    // 중간노드부터 채워주는데,
    int mid = (start + end) / 2;
    // tree_arr 배열의 level 인덱스에 하나씩 더해준다.
    tree_arr[level].append(order_arr[mid]).append(" ");

    // 다음 레벨도 채우기
    setNode(start, mid - 1, level + 1);
    setNode(mid + 1, end, level + 1);
  }
}
