// 디스크 컨트롤러
// https://programmers.co.kr/learn/courses/30/lessons/42627?language=java#

import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;
        ArrayList<int[]> jobsList = new ArrayList<>();
        
        for(int[] job : jobs) {
            jobsList.add(job);
        }
        // 각 작업들을 요청시간순서에 따라 정렬합니다.
        Collections.sort(jobsList, new Comparator<int[]>() {
           @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] == o2[0]) {
                    return o1[1] - o2[1];
                }
                else {
                    return o1[0] - o2[0];
                }
            }
        });
        
        int t = 0;
        while(jobsList.size() > 0) {
            
        }
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