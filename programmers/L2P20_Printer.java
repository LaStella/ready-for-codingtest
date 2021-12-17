// 프린터
// https://programmers.co.kr/learn/courses/30/lessons/42587?language=java

import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        
        HashMap<Integer, Integer> map = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();
        
        // 인쇄 대기목록의 순서대로 고유번호와 중요도를 저장합니다.
        // 대기목록을 queue로 만들어 순서대로 저장합니다.
        for(int i = 0 ; i < priorities.length ; i++) {
            map.put(i, priorities[i]);
            queue.add(i);
        }
        
        // 대기목록(queue)에 요청한 문서(location)가 없을 때까지 반복합니다.
        while(queue.contains(location)) {
            // 가장 앞에 있는 문서(J)를 꺼냅니다.
            int j = queue.poll();
            
            // J의 중요도 보다 높은 문서가 존재할 경우 대기목록의 가장 마지막에 넣습니다.
            if(map.get(j) < Collections.max(map.values())) {
                queue.add(j);
            }
            // 그렇지 않을 경우 J를 인쇄합니다.
            // 인쇄된 경우 순서가 증가합니다.
            else {
                map.remove(j);
                answer++;
            }
        }
        
        return answer;
    }
}