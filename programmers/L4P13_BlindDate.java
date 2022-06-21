// 튜브의 소개팅
// https://programmers.co.kr/learn/courses/30/lessons/1839?language=java

import java.util.*;

class Solution {
    public int[] solution(int m, int n, int s, int[][] time_map) {
        int[] answer = new int[2];
        
        // 각 칸의 모든 이동 횟수를 2500으로 초기화합니다.
        int mn = m*n;
        int[][][] board = new int[m][n][mn];
        for(int r = 0 ; r < m ; r++) {
            for(int c = 0 ; c < n ; c++) {
                Arrays.fill(board[r][c], Integer.MAX_VALUE);
            }
        }
        
        board[0][0][0] = 0;
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        
        for(int d = 0 ; d < mn ; d++) {
            for(int r = 0 ; r < m ; r++) {
                for(int c = 0 ; c < n ; c++) {
                    for(int i = 0 ; i < 4 ; i++) {
                        int nr = r+dr[i];
                        int nc = c+dc[i];
                        // 범위를 벗어나는 좌표나 테이블인 좌표는 넘어갑니다.
                        if(!inRange(nr, nc, m, n) || time_map[nr][nc] == -1) continue;
                        board[nr][nc][d+1] = Math.min(board[nr][nc][d+1], board[r][c][d] + time_map[nr][nc])
                    }
                }
            }
        }
        
        
        
      
        return answer;
    }
    
    public boolean inRange(int r, int c, int m, int n) {
        return 0 <= r && r < m && 0 <= c && c < n;
    }
}

/*
노드에 좌표, 이동 횟수, 대화시간을 저장하여 이를 bfs를 이용하여 탐색합니다.
큐에서 뽑은 노드의 좌표가 도착지점이면 종료합니다.
큐에서 뽑은 노드에 해당하는 이동 횟수와 대화 시간이 기존보다 더 작은지 확인합니다.
작다면 보드를 수정하고, 상,하,좌,우로 움직인 4개의 좌표를 큐에 넣습니다.
작지 않으면 넘어갑니다.
문제발생> 큐에서 뽑은 노드가 도착지점이면 종료하였는데, 이후로 더 적은 대화시간을 가진 노드가 도착지에 도착할 수 있습니다.
따라서 종료 조건을 없애고, 큐에 더 이상 노드가 존재하지 않을 때까지 반복합니다.

문제발생> 테스트 실패, 이동 횟수를 첫번째로 비교하고 두번째로 대화시간을 비교하므로 대화시간이 더 적지만 이동횟수가 많은 노드가 제외되는 문제가 발생한것으로 생각됩니다.
노드를 우선순위큐를 이용하여 정렬하며, 정렬 조건의 최우선은 이동횟수, 이동횟수가 같다면 대화시간을 비교하여 정렬하도록 하였습니다.

문제발생> 테스트케이스 시간초과


시간이 너무 오래걸리는 문제로 dp로 접근하였습니다.
board[r][c][d] = t,  d = 이동횟수 t = 대화시간
board[0][0][0] = 0으로 시작
board[1][0][1], board[0][1][1] 에 대화시간의 최소값을 저장 Math.min(board[1][0][1], board[0][0][0] + time_map[1][0])


*/