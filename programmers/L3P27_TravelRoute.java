// 여행경로
// https://programmers.co.kr/learn/courses/30/lessons/43164?language=java

import java.util.*;

class Solution {
    public String[] solution(String[][] tickets) {
        String[] answer = new String[tickets.length+1];
        
        // 티켓들을 알파벳 순으로 정렬합니다.
        Arrays.sort(tickets, new Comparator<String[]>() {
            @Override
                public int compare(String[] o1, String[] o2) {
                    if(o1[0].equals(o2[0])) {
                        return o1[1].compareTo(o2[1]);
                    }
                    else {
                        return o1[0].compareTo(o2[0]);
                    }
                }
        });
        
        // 티켓의 사용 여부를 저장할 배열입니다.
        boolean[] used = new boolean[tickets.length];
        
        // ICN에서 출발하며 DFS알고리즘을 통해 경로를 찾습니다.
        answer[0] = "ICN";
        dfs(0, answer, tickets, used);
        
        return answer;
    }
    
    // 모든 티켓을 사용하는 경우를 DFS알고리즘을 통해 찾는 함수입니다.
    public boolean dfs(int depth, String[] answer, String[][] tickets, boolean[] used) {
        // 모든 티켓을 다 사용하였다면 true를 리턴합니다.
        if(depth == tickets.length) {
            return true;
        }
        // 모든 티켓을 다 사용할때 까지 반복합니다.
        else {
            for(int i = 0 ; i < tickets.length ; i++) {
                // 아직 사용하지 않은 티켓이며, 출발 공항이 이전 도착 공항인 경우 티켓을 사용합니다.
                if(!used[i] && tickets[i][0].equals(answer[depth])) {
                    // 티켓을 사용함으로 변경합니다.
                    used[i] = true;
                    // 다음 도착 공항을 저장합니다.
                    answer[depth+1] = tickets[i][1];
                    // 처음으로 모든 티켓을 다 사용한 경우를 찾는다면 중단합니다.
                    if(dfs(depth + 1, answer, tickets, used)) return true;
                    // 티켓을 사용하지 않고 넘어갑니다.
                    used[i] = false;
                }
            }
        }
        
        return false;
    }
}