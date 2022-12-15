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
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0 ; i < n ; i++) {
            int number = Integer.parseInt(st.nextToken());
            if (map.putIfAbsent(number, 1) == null) {
                priorityQueue.add(number);
                continue;
            }
            map.compute(number, (k, v)-> v+1);
        }

        


        System.out.println();

    }
}


/*
맵에 숫자를 key로, 나온 횟수를 value로 저장합니다.
우선순위큐에 각 숫자를 넣습니다.
우선순위큐에서 숫자가 작은것부터 차례로 뽑으며 이전 숫자와 연속하는지 확인합니다.
연속하는 경우 다음으로 작은 숫자를 뽑아 출력하며 이전에 뽑은 숫자는 도로 큐에 넣습니다.
연속하지 않는 경우 뽑은 숫자를 횟수만큼 출력합니다.
우선순위큐의 크기가 2인 경우 큰 수를 먼저 출력합니다. 

 */