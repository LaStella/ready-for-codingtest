// 단속카메라
// https://programmers.co.kr/learn/courses/30/lessons/42884?language=java

import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        int answer = 1;
        
        // 경로를 진입 지점순으로 정렬합니다.
        Arrays.sort(routes, (o1, o2) -> o1[0] - o2[0]);
        
        // end는 경로들이 겹치는 구간의 종료지점입니다.
        // 초기값으로 첫번째 경로의 진출지점을 저장합니다.
        int end = routes[0][1];
        
        // route의 진입지점이 end보다 작으면 route는 이전 경로들과 겹치는 구간이 존재합니다.
        // route의 진출지점과 end를 비교하여 겹치는 구간의 종료지점을 수정합니다.
        // route와 진입지점이 end보다 크면 route는 이전 경로들과 겹치는 구간이 없으므로, 새로운 카메라를 설치하며 겹치는 구간의 종료지점을 수정합니다.
        for(int[] route : routes) {
            if(route[0] <= end) {
                end = Math.min(end, route[1]);
            }
            else {
                answer++;
                end = route[1];
            }
        }
        
        return answer;
    }
}


/*
------------
 --------
   ----
    ---------------
      ---------------
         ------------------------
             ----- 
               --- 
                 ---------------
                       ----------
                         --------
                          ------------- 
                           ------
                             --------
                              ----
                             
route를 정렬한다
route를 차례로 방문하여 겹치는 구간이 존재하면 겹치는 구간을 최신화
겹치는 구간이 존재하지 않으면 카메라를 설치, 새로운 구간을 생성

*/