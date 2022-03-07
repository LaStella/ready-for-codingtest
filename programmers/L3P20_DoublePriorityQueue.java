// 이중우선순위큐
// https://programmers.co.kr/learn/courses/30/lessons/42628?language=java

import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        int[] answer = new int[2];
        // 작은 숫자가 우선순위가 높은 큐와 큰 숫자가 우선순위가 높은 큐를 생성합니다.
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        PriorityQueue<Integer> rev_pq = new PriorityQueue<>(Collections.reverseOrder());
        
        for(String operation : operations) {
            String[] oper = operation.split(" ");
            // 두 큐에 같은 숫자를 삽입합니다.
            if(oper[0].equals("I")) {
                pq.add(Integer.parseInt(oper[1]));
                rev_pq.add(Integer.parseInt(oper[1]));
            }
            else {
                // 최댓값을 삭제하므로 큰 숫자가 우선순위가 높은 rev_pq에서 값을 뽑고, 뽑힌 값과 같은 값을 pq에서 제거합니다.
                if(oper[1].equals("1")) {
                    pq.remove(rev_pq.poll());
                }
                // 위와 동일하며 pq에서 값을 뽑아 rev_pq에서 같은 값을 제거합니다.
                else {
                    rev_pq.remove(pq.poll());
                }
            }
        }
        
        // 큐가 비어 있다면 [0,0]을 리턴합니다.
        if(pq.size() == 0) {
            answer[0] = answer[1] = 0;
        }
        // 비어있지 않으면 [최댓값, 최솟값]을 리턴합니다.
        else {
            answer[0] = rev_pq.peek();
            answer[1] = pq.peek();
        }
        
        
        return answer;
    }
}