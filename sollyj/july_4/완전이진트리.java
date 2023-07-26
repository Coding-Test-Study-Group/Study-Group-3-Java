package sollyj.july_4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 완전이진트리 {
	static int K;    // 레벨
	static List<Integer> nodeList;    // 방문한 노드 번호 순서대로 넣을 리스트
	static List<Integer>[] tree;    // tree[i]에는 i레벨에 해당하는 노드들 넣어줌

	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			K = Integer.parseInt(br.readLine());
			nodeList = new ArrayList<>();
			tree = new ArrayList[K + 1];

			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < Math.pow(2, K) - 1; i++) {
				nodeList.add(Integer.parseInt(st.nextToken()));
			}

			for (int i = 1; i <= K; i++) {
				tree[i] = new ArrayList<>();
			}

			recurrence(0, nodeList.size() - 1, 1);

			for (int i = 1; i <= K; i++) {
				for (int node : tree[i]) {
					System.out.print(node + " ");
				}
				System.out.println();
			}
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
	}

	private static void recurrence(int start, int end, int level) {
		if (level > K) {
			return;
		}

		// 현재 level의 루트 노드 인덱스
		int mid = (start + end) / 2;

		tree[level].add(nodeList.get(mid));

		recurrence(start, mid - 1, level + 1);    // 왼쪽 재귀
		recurrence(mid + 1, end, level + 1);    // 오른쪽 재귀
	}
}
