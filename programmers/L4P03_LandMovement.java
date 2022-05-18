// 지형 이동
// https://programmers.co.kr/learn/courses/30/lessons/62050?language=java

import java.util.*;

class Solution {
    int n;
    int[] dr = {-1, 1, 0, 0};
    int[] dc = {0, 0, -1, 1};
    public int solution(int[][] land, int height) {
        int answer = 0;
        n = land.length;
        
        boolean[][] visited = new boolean[n][n];
        boolean[][][][] ladder = new boolean[300][300][300][300];
        Queue<int[]> q = new LinkedList <>();
        
        q.add(new int[] {0, 0});
        visited[0][0] = true;
        
        while(!q.isEmpty()) {
            int[] rc = q.poll();
            int pr = rc[0];
            int pc = rc[1];
            
            for(int i = 0 ; i < 4 ; i++) {
                int nr = pr+dr[i];
                int nc = pc+dc[i];
                
                if(!inRange(nr, nc) || visited[nr][nc]) continue;
                if(Math.abs(land[pr][pc] - land[nr][nc]) <= height) {
                    visited[nr][nc] = true;
                    q.add(new int[] {nr, nc});
                }
                else {
                    
                }
            }
            
        }
        
        
        return answer;
    }
    
    // public 
    
    public boolean inRange(int r, int c) {
        return 0 <= r && r < n && 0 <= c && c < n;
    }
    
    
    class Ladder {
        int r1, c1, r2, c2;
    }
}

/*
bfs로 상하좌우를 비교하며 방문
visited에 기록
height 범위 내의 이동이 가능한 영역을 모두 방문한다.
height 범위를 벗어난 이동이 필요한 경우 필요한 비용 중 최소 비용을 저장함
height 범위 내의 이동을 모두 마친 후 저장한 최소 비용을 더함

land의 모든 좌표를 방문 > height범위 이동 가능한 영역을 나눔 > 영역별 최소 비용 사다리 설치가 가능한 곳에 설치 (사다리가 중복 설치 되지않도록함)

*/