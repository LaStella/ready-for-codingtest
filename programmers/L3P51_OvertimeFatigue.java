// 야근 피로도
// https://programmers.co.kr/learn/courses/30/lessons/12927?language=java

// 핵심 : 우선순위큐

import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        
        PriorityQueue<Integer> q = new PriorityQueue<>(Collections.reverseOrder());
        
        // 모든 작업을 우선순위큐에 넣습니다. (내림차순)
        for(int work : works) {
            q.add(work);
        }
        
        // 야근 피로도를 최소화 하려면 작업량이 가장 많은 작업을 줄여야 합니다.
        // 우선순위큐에서 가장 큰 작업량을 가진 작업을 줄이고, 줄인 작업량을 다시 우선순위큐에 넣습니다.
        // 남은 시간(n)을 줄입니다.
        // 우선순위큐에서 0이 나올 경우 남아있는 모든 작업량이 0이므로, 야근 피로도는 0입니다.
        while(n > 0) {
            if(q.peek() == 0) {
                return answer = 0;
            }
            q.add(q.poll()-1);
            n--;
        }
        
        // 남은 작업량을 제곱하여 더하면 야근 피로도가 나옵니다.
        for(int work : q) {
            answer += work*work;
        }
        
        return answer;
    }
}