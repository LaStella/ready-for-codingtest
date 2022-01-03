// 피로도
// https://programmers.co.kr/learn/courses/30/lessons/87946?language=java

import java.util.*;

class Solution {
    public int solution(int k, int[][] dungeons) {
        int answer = 0;
    
        boolean[] visited = new boolean[dungeons.length];
        
        answer = dfs(k, dungeons, 0, visited);
        
        return answer;
    }
    
    // 깊이 우선 탐색(Depth First Search)를 이용하여 던전을 탐험하는 모든 경우를 찾으며 탐험할 수 있는 최대 던전 수를 구하는 함수입니다.
    public int dfs(int k, int[][] dungeons, int count, boolean[] visited) {
        // 탐험한 최대 던전 수입니다.
        int max = count;
        
        // 던전을 하나 탐험할 때 마다 count를 1씩 증가시키고
        // 탐험한 던전은 visited로 표시합니다.
        for(int i = 0 ; i < dungeons.length ; i++) {
            if(!visited[i]) {
                if(k >= dungeons[i][0]) {
                    visited[i] = true;
                    // 탐험한 최대 던전 수를 갱신합니다.
                    max = Math.max(max, dfs(k-dungeons[i][1], dungeons, count+1, visited));
                    visited[i] = false;
                }
            }
        }

        return max;
    }
}