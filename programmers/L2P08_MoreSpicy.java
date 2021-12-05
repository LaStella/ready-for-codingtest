import java.util.*;
import java.util.stream.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        List<Integer> scovList = new ArrayList<>();
        scovList = Arrays.stream(scoville).boxed().collect(Collectors.toList());
        PriorityQueue<Integer> scovQueue = new PriorityQueue<>(scovList);
        // stream사용하지 않고 for문
        // for(int i : scoville) {
        //     scovQueue.add(i);
        // }
        
        while(scovQueue.peek() < K) {
            if(scovQueue.size() < 2) {
                answer = -1;
                break;
            }
            else {
                scovQueue.add(scovQueue.poll()+scovQueue.poll()*2);
                answer++;
            }
        }
        
        return answer;
    }
}