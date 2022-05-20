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
        
        bfs(0, 0, visited, land, height);
        
        while(true) {
            Ladder l = findLadderLocation(visited, land);
            // 사다리를 설치할 격자칸의 좌표가 (0, 0) 초기값으로 나온다면 모든 격자칸을 방문하였으므로 종료합니다.
            if(l.location[0] == 0 && l.location[1] == 0) {
                break;
            }
            // 사다리를 설치하고 설치한 격자칸 좌표를 시작으로 bfs탐색을 합니다.
            else {
                answer += l.cost;
                bfs(l.location[0], l.location[1], visited, land, height);
            }
        }
        
        return answer;
    }
    
    
    // (r, c)에서 부터 상, 하, 좌, 우로 높이차가 height 이하인 격자를 탐색하는 함수입니다.
    public void bfs(int r, int c, boolean[][] visited, int[][] land, int height) {
        Queue<int[]> q = new LinkedList <>();
        q.add(new int[] {r, c});
        visited[r][c] = true;
        
        // 사다리 설치에 드는 최소 비용과 위치를 저장합니다.
        int min_cost = Integer.MAX_VALUE;
        int mr = 0, mc = 0;
        
        while(!q.isEmpty()) {
            int[] location = q.poll();
            int pr = location[0];
            int pc = location[1];
            
            // 큐에서 뽑은 위치를 기준으로 상, 하, 좌, 우를 탐색합니다.
            for(int i = 0 ; i < 4 ; i++) {
                int nr = pr+dr[i];
                int nc = pc+dc[i];
                
                // 격자 범위를 벗어나거나 이미 방문한 격자는 넘어갑니다.
                if(!inRange(nr, nc) || visited[nr][nc]) continue;
                
                // 두 격자 칸의 높이차가 height보다 작으면 탐색할 수 있습니다.
                if(Math.abs(land[pr][pc] - land[nr][nc]) <= height) {
                    visited[nr][nc] = true;
                    q.add(new int[] {nr, nc});
                }
            }
        }
    }
    
    // 방문하지 않는 격자칸 중 사다리를 설치하는 비용이 가장 적게드는 격자칸을 찾는 함수입니다.
    public Ladder findLadderLocation(boolean[][] visited, int[][] land) {
        Queue<int[]> q = new LinkedList <>();
        q.add(new int[] {0, 0});
        boolean[][] ladder_visited = new boolean[n][n];
        ladder_visited[0][0] = true;
        
        // 사다리 설치에 드는 최소 비용과 위치를 저장합니다.
        int min_cost = Integer.MAX_VALUE;
        int mr = 0, mc = 0;
        
        while(!q.isEmpty()) {
            int[] rc = q.poll();
            int pr = rc[0];
            int pc = rc[1];
            
            for(int i = 0 ; i < 4 ; i++) {
                int nr = pr+dr[i];
                int nc = pc+dc[i];
                
                // 격자 범위를 벗어나거나 현재 함수에서 이미 방문한 격자라면 넘어갑니다.
                if(!inRange(nr, nc) || ladder_visited[nr][nc]) continue;
                
                // 새로운 좌표에 대해 방문 처리를 합니다.
                ladder_visited[nr][nc] = true;
                
                // 이미 탐색한 격자칸이라면 큐에 넣어 방문하지 않는 격자칸을 찾습니다.
                if(visited[nr][nc]) {
                    q.add(new int[] {nr, nc});
                }
                // 방문하지 않은 격자칸이라면 사다리 설치 비용을 비교합니다.
                else {
                    // 사다리를 설치하는 비용은 두 격자 칸의 높이차입니다.
                    int cost = Math.abs(land[pr][pc] - land[nr][nc]);
                    if(cost < min_cost) {
                        min_cost = cost;
                        mr = nr;
                        mc = nc;
                    }
                }
            }
        }
        
        return new Ladder(new int[] {mr, mc}, min_cost);
    }
    
    // 주어진 좌표가 격자를 벗어나는지 확인하는 함수입니다.
    public boolean inRange(int r, int c) {
        return 0 <= r && r < n && 0 <= c && c < n;
    }
    
    // 설치 좌표와 설치 비용이 저장되는 사다리 객체입니다.
    class Ladder {
        int[] location;
        int cost;
        public Ladder(int[] location, int cost) {
            this.location = location;
            this.cost = cost;
        }
    }
}

/*
bfs로 상하좌우를 비교하며 방문
visited에 기록
height 범위 내의 이동이 가능한 영역을 모두 방문한다.
height 범위를 벗어난 이동이 필요한 경우 필요한 비용 중 최소 비용을 저장함
height 범위 내의 이동을 모두 마친 후 저장한 최소 비용을 더함

land의 모든 좌표를 방문 > height범위 이동 가능한 영역을 나눔 > 영역별 최소 비용 사다리 설치가 가능한 곳에 설치 (사다리가 중복 설치 되지않도록함)

------------------------------------------------
bfs를 재귀
bfs를 이용하여 상하좌우를 방문
height 범위 내의 이동이 가능한 영역을 모두 방문한다.
height 범위를 벗어난 이동이 필요한 경우 필요한 비용 중 최소 비용을 저장함
최소 비용으로 저장된 좌표를 다시 bfs


*/