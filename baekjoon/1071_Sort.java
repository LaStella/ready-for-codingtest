// 소트
// https://www.acmicpc.net/problem/1071

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        // 숫자를 key로 숫자가 나온 횟수를 value로 가지는 맵입니다.
        Map<Integer, Integer> map = new HashMap<>();

        // 맵에 숫자를 넣고 처음 들어가는 숫자는 우선순위큐에 넣습니다.
        for (int i = 0 ; i < n ; i++) {
            int number = Integer.parseInt(st.nextToken());
            if (map.putIfAbsent(number, 1) == null) {
                priorityQueue.add(number);
                continue;
            }
            map.compute(number, (k, v)-> v+1);
        }

        printNumber(priorityQueue, map);
    }

    private static void printNumber(PriorityQueue<Integer> priorityQueue, Map<Integer, Integer> map) {
        StringBuilder sb = new StringBuilder();
        int prevNumb = -2;

        // 1개의 숫자만 입력된 경우
        if (priorityQueue.size() == 1) {
            int nowNumb = priorityQueue.poll();
            sb.append(getRepeatNumber(nowNumb, map));
            System.out.println(sb);
            return;
        }

        // 큐에 남은 숫자가 2개가 될 때까지 반복합니다.
        while (priorityQueue.size() > 2) {
            int nowNumb = priorityQueue.poll();

            // 이전 숫자와 현재 숫자가 연속된 숫자라면 다음 숫자를 뽑아 먼저 출력합니다.
            if (prevNumb + 1 == nowNumb) {
                int nextNumb = priorityQueue.peek();
                sb.append(nextNumb + " ");
                map.compute(nextNumb, (k, v)-> v-1);
                prevNumb = nextNumb;
                // 남은 숫자가 없는 경우 우선순위큐에서 제거합니다.
                if (map.get(nextNumb) <= 0) {
                    priorityQueue.poll();
                }
                // 현재 숫자를 다시 큐에 넣습니다.
                priorityQueue.add(nowNumb);
                continue;
            }

            // 현재 숫자를 현재 가지고 있는 만큼 반복해서 출력합니다.
            sb.append(getRepeatNumber(nowNumb, map));
            prevNumb = nowNumb;
        }

        // 남은 두 숫자가 연속된 숫자인 경우 큰 수를 먼저 출력합니다.
        int smallNumb = priorityQueue.poll();
        int bigNumb = priorityQueue.poll();

        if (smallNumb + 1 == bigNumb) {
            sb.append(getRepeatNumber(bigNumb, map));
            sb.append(getRepeatNumber(smallNumb, map));
            System.out.println(sb);
            return;
        }

        // 이전 숫자와 작은 숫자가 연속된 숫자인 경우 큰 수를 1번 출력 후 나머지를 출력합니다.
        if (prevNumb + 1 == smallNumb) {
            sb.append(bigNumb + " ");
            map.compute(bigNumb, (k, v)-> v-1);
        }
        sb.append(getRepeatNumber(smallNumb, map));
        sb.append(getRepeatNumber(bigNumb, map));
        System.out.println(sb);
    }

    // 매개변수로 받은 number를 현재 미출력한 개수만큼 반복한 문자열을 리턴하는 함수입니다.
    private static String getRepeatNumber(int number, Map<Integer, Integer> map) {
        return (number + " ").repeat(map.get(number));
    }
}


/*
맵에 숫자를 key로, 나온 횟수를 value로 저장합니다.
우선순위큐에 각 숫자를 넣습니다.
우선순위큐에서 숫자가 작은것부터 차례로 뽑으며 이전 숫자와 연속하는지 확인합니다.
연속하는 경우 다음으로 작은 숫자를 뽑아 출력하며 이전에 뽑은 숫자는 도로 큐에 넣습니다.
연속하지 않는 경우 뽑은 숫자를 횟수만큼 출력합니다.
우선순위큐의 크기가 2인 경우 큰 수를 먼저 출력합니다.

문제발생1 >  반례존재 1 2 3 3 4 4 5

---------------------------------------------------------------------------------

숫자들을 오름차순 정렬합니다.
앞에서부터 차례로 연속된 수인지 확인합니다.
(이전 숫자 + 1 == 현재 숫자) 인 경우 연속된 수입니다.
연속된 수인경우 현재 숫자 다음으로 큰 수를 찾아 위치를 서로 바꿉니다.
다음으로 큰 수를 찾지 못하는 경우 이전 숫자와 위치를 바꿉니다.

문제발생2 > 반례존재 1 1 1 2 2 2 3 3 3 4 4 4

(수정) 다음으로 큰 수를 찾지 못하는 경우 이전 숫자들 중 연속되지 않는 숫자를 찾아 바꿉니다.

---------------------------------------------------------------------------------

맵에 숫자를 key로, 나온 횟수를 value로 저장합니다.
우선순위큐에 각 숫자를 넣습니다. 같은 숫자가 여러개 존재하여도 하나만 넣습니다.
우선순위큐에서 숫자가 작은것부터 차례로 뽑으며 이전 숫자와 연속하는지 확인합니다.
연속하는 경우 다음으로 작은 숫자를 뽑아 출력하며 이전에 뽑은 숫자는 도로 큐에 넣습니다.
연속하지 않는 경우 뽑은 숫자를 횟수만큼 출력합니다.
우선순위큐에 남은 숫자가 2개라면 연속된 숫자인지 확인하여 출력 순서를 정합니다.

문제발생3 > 마지막에 남은 두 숫자가 연속된 숫자가 아닌 경우 작은 숫자부터 출력하였습니다.
          이때 이전에 출력한 숫자와 연속되는 경우가 존재하므로 이를 수정하였습니다.
 */