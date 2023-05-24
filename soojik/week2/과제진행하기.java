package soojik.week2;

import java.lang.reflect.Array;
import java.util.*;

public class 과제진행하기 {

  public static void main(String[] args) {
    System.out.println(Arrays.toString(solution(new String[][]{{"korean", "11:40", "30"}, {"english", "12:10", "20"}, {"math", "12:30", "40"}})));
    System.out.println(Arrays.toString(solution(new String[][]{{"science", "12:40", "50"}, {"music", "12:20", "40"}, {"history", "14:00", "30"}, {"computer", "12:30", "100"}})));
  }

  public static String[] solution(String[][] plans) {
    int N = plans.length;

    String[] answer = new String[N];
    int idx_ans = 0;

    // 주어지는 과제를 Subject 객체에 담아 리스트로 관리
    List<Subject> subjects = new ArrayList<>();
    for (int i=0;i<N;i++) {
      String name_tmp = plans[i][0];
      String[] tmp = plans[i][1].split(":");
      // 이때, 시작 시각은 00:00 을 기준으로 지난 분을 계산해서 넣어준다.
      int time_tmp = Integer.parseInt(tmp[0]) * 60 + Integer.parseInt(tmp[1]);
      int playtime_tmp = Integer.parseInt(plans[i][2]);

      subjects.add(new Subject(name_tmp, time_tmp, playtime_tmp));
    }

    // 시작시간을 기준으로 정렬
    subjects.sort(Comparator.comparingInt(Subject::getStart));

    // 가장 먼저 시작할 subject 객체를 last 가 참조
    // last 는 바로 이전에 했던 subject 를 참조하게 할 것
    Subject last = subjects.get(0);

    // stack 에는 시작했지만 다른 과목의 인터셉트로 인해 끝내지 못한 과목을 넣어놓을 공간
    Stack<Subject> stack = new Stack<>();

    // 그 이외 subjects 를 순회
    for (int i=1;i<N;i++) {
      // 다가온 차례의 subject
      Subject curr = subjects.get(i);

      // 만약 직전에 하고있던 subject 가 끝났다면
      if (last.start + last.playtime <= curr.start) {
        // 끝난 과목이니까 answer 배열에 넣어준다.
        answer[idx_ans++] = last.name;

        int 남은시간 = curr.start - (last.start + last.playtime);

        // 만약 남은 시간 (직전~현재 과목의 갭) 이 0 이상,
        while (남은시간 != 0) {
          // stack 에 앞서 끝내지 못한 과목이 있다면
          if (!stack.empty()) {
            // 가장 위에 있는 과목의
            Subject tmp = stack.pop();

            // 남은 활동 시간이 이 갭 타임을 넘어선다면
            if (tmp.playtime > 남은시간) {
              // 남은 활동 시간에서 갭 타임을 빼준 후 다시 넣어준다.
              tmp.setPlaytime(tmp.playtime - 남은시간);
              stack.push(tmp);
              // 그리고 더 이상 남은 갭타임(남은 시간)이 없으니까 while 문 종료
              break;
            }
            // 만약 남은 활동 시간과 갭 타임이 같다면
            else if (tmp.playtime == 남은시간) {
              // 정답 배열에 넣어준다.
              // 이미 끝난 과목이니까 stack 에는 안넣어줘도 된다.
              answer[idx_ans++] = tmp.name;
              // 마찬가지로 while 문 종료
              break;
            }
            // 남은 활동 시간이 갭 타임보다 적다면
            else {
              // 끝난 과목이니까 정답배열에 넣어주고
              answer[idx_ans++] = tmp.name;
              // 갭 타임에서 이전 과목을 위해 쓴 시간을 빼준다.
              남은시간 -= tmp.playtime;
              // 남은시간이 있으므로 다시 while 이 돌것
            }
          }
          // 만약 stack 에 과목이 없다면 while 문 종료
          else {
            break;
          }
        }

      }
      // 직전에 하던 과목을 아직 끝내지 못했다면
      else {
        // 활동 시간을 다시 계산해서
        last.setPlaytime(last.playtime - (curr.start - last.start));
        // stack 에 넣어준다
        stack.push(last);
      }

      // 다음 반복문을 위해 현재 과목을 직전 과목이 참조하도록
      last = curr;
    }

    // 마지막으로 남은 과목은 뒤에 걸리는 것이 없으니까 바로 정답 배열에 넣어준다.
    answer[idx_ans++] = subjects.get(N - 1).name;

    // stack 에 남아있던 과목들도 모두 빼서 정답 배열에 넣어준다.
    while (!stack.empty()) {
      answer[idx_ans++] = stack.pop().name;
    }

    return answer;
  }
}

// Subject 라는 이름으로 주어지는 과제 이름, 시작 시각, 활동 시간을 넣어줄 클래스 정의
class Subject {
  String name;
  int start;
  int playtime;

  public int getStart() {
    return this.start;
  }

  public void setPlaytime(int playtime) {
    this.playtime = playtime;
  }

  public Subject(String name, int start, int playtime) {
    this.name = name;
    this.start = start;
    this.playtime = playtime;
  }

  public String toString() {
    return name+" "+start+" "+playtime;
  }
}
