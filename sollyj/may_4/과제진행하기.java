package sollyj.may_4;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Stack;

public class 과제진행하기 {
	public static void main(String[] args) {
		// String[] answer = solution(
		// 	new String[][] {{"korean", "11:40", "30"}, {"english", "12:10", "20"}, {"math", "12:30", "40"}});
		String[] answer = solution(
			new String[][] {{"science", "12:40", "50"}, {"music", "12:20", "40"}, {"history", "14:00", "30"},
				{"computer", "12:30", "100"}});
		// String[] answer = solution(
		// 	new String[][] {{"aaa", "12:00", "20"}, {"bbb", "12:10", "30"}, {"ccc", "12:40", "10"}});

		System.out.println(Arrays.toString(answer));
	}

	private static String[] solution(String[][] plans) {
		String[] answer = {};

		// 과제 시작 순으로 진행하므로 우선순위큐에 과제를 넣는다.
		PriorityQueue<Work> pq = new PriorityQueue<>(
			(o1, o2) -> (o1.start_time - o2.start_time)
		);

		for (int i = 0; i < plans.length; i++) {
			String name = plans[i][0];

			int h = Integer.parseInt(plans[i][1].split(":")[0]);
			int m = Integer.parseInt(plans[i][1].split(":")[1]);
			int start_time = h * 60 + m;

			int take_time = Integer.parseInt(plans[i][2]);

			pq.add(new Work(name, start_time, take_time));
		}

		// 잠시 멈춘 과제 저장하기 위한 스택
		Stack<Work> remaining = new Stack<>();

		while (!pq.isEmpty()) {
			Work cur = pq.poll();

			String curName = cur.name;
			int curStartTime = cur.start_time;
			int curTakeTime = cur.take_time;

			int curTime = curStartTime;    // 현재 시각

			if (!pq.isEmpty()) {    // 다음 과제가 남아있는 경우
				Work next = pq.peek();

				// 경우 1. 과제 마무리 되었고 다음 과제할 시간도 되었을때
				if (curStartTime + curTakeTime == next.start_time) {
					// answer에 추가
					answer = Arrays.copyOf(answer, answer.length + 1);
					answer[answer.length - 1] = curName;
				}

				// 경우2. 다음 과제 할 시간이 되었는데 과제 마무리 못햇을때
				else if (curStartTime + curTakeTime > next.start_time) {
					int t = curTakeTime - (next.start_time - curTime);
					remaining.push(new Work(curName, t));
				}

				// 경우3. 과제는 끝났는데 다음 과제 시작 시간까지 남았을 때
				else if (curStartTime + curTakeTime < next.start_time) {
					// answer에 추가
					answer = Arrays.copyOf(answer, answer.length + 1);
					answer[answer.length - 1] = curName;

					curTime += curTakeTime;

					// 가장 최근에 멈춘 과제 다시 시작
					while (!remaining.isEmpty()) {
						Work rem = remaining.pop();

						if (curTime + rem.take_time <= next.start_time) {    // 다음 과제 시작 전까지 끝낼 수 있는 경우
							curTime += rem.take_time;

							// answer에 추가
							answer = Arrays.copyOf(answer, answer.length + 1);
							answer[answer.length - 1] = rem.name;
						} else {    // 다음 과제 시작 전까지 못 끝내는 경우
							int t = rem.take_time - (next.start_time - curTime);    // 과제 남은 시간 갱신
							remaining.push(new Work(rem.name, t));    // 다시 멈춘 과제 스택에 추가
							break;
						}
					}
				}
			} else {    // 마지막 과제인 경우
				// 경우1. 마지막 과제 끝내고 스택이 비어있는 경우 (모든 과제 완료)
				// 경우2. 마지막 과제 끝냈는데 스택에 있는 경우 (미완료 과제 차례대로 완료)
				answer = Arrays.copyOf(answer, answer.length + 1);
				answer[answer.length - 1] = curName;

				while (!remaining.isEmpty()) {
					Work rem = remaining.pop();

					answer = Arrays.copyOf(answer, answer.length + 1);
					answer[answer.length - 1] = rem.name;
				}
			}
		}

		return answer;
	}
}

class Work {
	String name;
	int start_time;
	int take_time;

	public Work(String name, int start_time, int take_time) {
		this.name = name;
		this.start_time = start_time;
		this.take_time = take_time;
	}

	public Work(String name, int take_time) {
		this.name = name;
		this.take_time = take_time;
	}
}
