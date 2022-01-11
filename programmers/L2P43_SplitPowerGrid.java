// 전력망 둘로 나누기
// https://programmers.co.kr/learn/courses/30/lessons/86971?language=java

import java.util.*;

class Solution {
    public int solution(int n, int[][] wires) {
        int answer = n;
        
        // 각 간선을 하나씩 잘라 송전탑 개수의 차이를 비교합니다.
        for(int i = 0 ; i < wires.length ; i++) {
            // 간선을 잘라 나누어진 두 전력망은 각각 getCount(wires, i)개의 송전탑과 n-getCount(wires, i)개의 송전탑을 가지게됩니다.
            // 두 전력망의 송전탑 개수의 차이는 getCount(wires, i)-(n-getCount(wires, i)) 이므로 2*getCount(wires, i)-n)이 됩니다.
            answer = Math.min(answer, Math.abs(2*getCount(wires, i)-n));
        }
        
        return answer;
    }
    
    // 주어딘 간선을 제외한 나머지 간선으로 송전탑의 개수를 구하는 함수입니다.
    public int getCount(int[][] wires, int k) {
        Stack<Integer> stack = new Stack<>();
        // 각 간선의 방문 여부를 나타냅니다.
        boolean[] visited = new boolean[wires.length];
        // wires[k]는 잘라낸 간선이므로 방문처리를 해줍니다.
        visited[k] = true;
        // 잘라낸 간선의 시작 송전탑을 기준으로 연결된 모든 송전탑을 찾습니다.
        stack.push(wires[k][0]);
        int count = 1;
        while(!stack.isEmpty()) {
            int t = stack.pop();
            for(int i = 0 ; i < wires.length ; i++) {
                if(!visited[i]) {
                    // 연결된 송전탑의 번호를 stack에 저장하여 다시 해당 송전탑으로 연결된 모든 간선을 찾습니다.
                    if(wires[i][0] == t) {
                        visited[i] = true;
                        count++;
                        stack.push(wires[i][1]);
                    }
                    if(wires[i][1] == t) {
                        visited[i] = true;
                        count++;
                        stack.push(wires[i][0]);
                    }
                }
            }
        }
        
        return count;
    }
}