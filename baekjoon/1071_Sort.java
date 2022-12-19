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

        List<Integer> list = new ArrayList<>();

        for (int i = 0 ; i < n ; i++) {
            int number = Integer.parseInt(st.nextToken());
            list.add(number);
        }

        sortList(list);
        printList(list);
    }

    private static void printList(List<Integer> list) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0 ; i < list.size() ; i++) {
            int number = list.get(i);
            sb.append(number + " ");
        }
        System.out.println(sb);
    }

    private static void sortList(List<Integer> list) {
        Collections.sort(list);
        int prevIndex = 0;
        for (int i = 1 ; i < list.size()-1 ; i++) {
            // 연속되는 경우
            if (list.get(prevIndex) + 1 == list.get(i)) {
                int index = getBackIndex(i, list);
                if (index == -1) {
                    index = getFrontIndex(prevIndex, list);
                }
                swapNumber(i, index, list);
            }
            prevIndex = i;
        }
    }

    private static int getFrontIndex(int start, List<Integer> list) {
        for (int i = start - 1 ; i >= 0 ; i--) {
            if (list.get(i) != list.get(start)) {
                return i+1;
            }
        }

        return 0;
    }

    private static int getBackIndex(int start, List<Integer> list) {
        for (int i = start + 1 ; i < list.size() ; i++) {
            if (list.get(i) != list.get(start)) {
                return i;
            }
        }

        return -1;
    }

    private static void swapNumber(int index1, int index2, List<Integer> list) {
        int number = list.get(index1);
        list.set(index1, list.get(index2));
        list.set(index2, number);
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
 */