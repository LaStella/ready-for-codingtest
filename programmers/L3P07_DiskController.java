// 디스크 컨트롤러
// https://programmers.co.kr/learn/courses/30/lessons/42627?language=java#

import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;
        
        // 각 작업들을 요청시간순서에 따라 정렬합니다.
        // Arrays.sort(jobs, (o1, o2) -> o1[0] - o2[0]);
        Arrays.sort(jobs, new Comparator<int[]>() {
           @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        
        int time = 0;
        int count = 0;
        int index = 0;
        // 작업을 소요시간이 작은순으로 정렬하기위해 우선순위큐를 사용합니다.
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        // 작업의 개수만큼 반복합니다.
        while(count < jobs.length) {
            // 큐에 작업이 없으며 현재시간보다 작은 요청시간을 가진 작업이 없다면
            // 현재시간을 다음 작업시간으로 맞춰줍니다.
            // ex) [1, 3], [5, 9], [5, 7]
            if(pq.isEmpty() && time < jobs[index][0]) {
                time = jobs[index][0];
            }
            // 현재시간보다 작은 요청시간을 가진 작업들을 우선순위큐에 넣습니다.
            while(index < jobs.length && jobs[index][0] <= time) {
                pq.add(jobs[index++]);
            }
            
            // 작업 소요시간이 가장 작은 작업을 처리합니다.
            time += pq.peek()[1];
            // answer에 작업이 요청된 순간에서 종료되기까지 걸린 시간을 더해줍니다.
            answer += time - pq.poll()[0];
            count++;
        }
        
        // 작업의 개수로 나누어주면 평균이 됩니다. 정수형 자료타입의 나누기이므로 소수점이하는 버려집니다.
        answer /= jobs.length;
        
        return answer;
    }
}

/*
작업시간 = 이전작업시간 + 작업소요시간
작업시간보다 작은 요청시간을 가진 작업들 중 최소소요시간을 뽑습니다.
뽑힌 작업을 처리합니다.
이 과정을 작업이 더이상 없을때까지 반복합니다.
*/