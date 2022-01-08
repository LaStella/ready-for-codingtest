// 주식 가격
// https://programmers.co.kr/learn/courses/30/lessons/42584?language=java#

import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        Stack<Integer> stack = new Stack<>();
        
        // stack에 주식 가격 index를 넣습니다.
        // 단, stack에 저장되어 있는 주식 가격이 앞으로 들어올 주식 가격보다 작다면 stack에서 제거합니다.
        // prices배열은 초 단위로 기록된 주식 가격이므로, index의 차이는 그 가격이 유지된 시간을 뜻합니다.
        for(int i = 0 ; i < prices.length ; i++) {
            // stack이 비어있지 않으며 현재 주식의 가격이 이전 주식 가격보다 작은 경우 stack에서 제거하며
            // prices[작은 경우 index]의 가격이 유지된 시간은 '현재 index - 작은 경우 index' 입니다.
            while(!stack.isEmpty() && prices[i] < prices[stack.peek()]) {
                answer[stack.peek()] = i-stack.pop();
            }
            stack.push(i);
        }
        
        // stack에 남아있는 index들은 stack에 들어간 순간부터 끝까지 유지되었습니다.
        // 들어간 순간 = index, 끝 = prices.length-1 입니다.
        // index의 차이만큼 유지된 시간이므로 prices[들어온 순간 index]가 유지된 시간은 prices.length-index-1 입니다.
        while(!stack.isEmpty()) {
            answer[stack.peek()] = prices.length-stack.pop()-1;
        }
        
        return answer;
    }
}