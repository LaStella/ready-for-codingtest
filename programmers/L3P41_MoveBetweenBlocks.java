// 블록 이동하기
// https://programmers.co.kr/learn/courses/30/lessons/60063?language=java

import java.util.*;

class Solution {
    int n;
    public int solution(int[][] board) {
        int answer = 0;
        n = board.length;
        
        int[][] next_value = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        Queue<Robot> q = new LinkedList<>();
        boolean[][][][] visited = new boolean[n][n][n][n];
        
        // 시작지점 (0, 0), (0, 1)로부터 시작합니다.
        q.add(new Robot(0, 0, 0, 0, 1));
        visited[0][0][0][1] = true;
        
        while(!q.isEmpty()) {
            Robot p_robot = q.poll();
            int t = p_robot.t;
            int r1 = p_robot.r1;
            int c1 = p_robot.c1;
            int r2 = p_robot.r2;
            int c2 = p_robot.c2;            
            
            // 로봇이 (n-1, n-1)에 도달하면 걸린 시간을 정답으로 저장합니다.
            if(r2 == n-1 && c2 == n-1) {
                answer = t;
                break;
            }
            else {
                // 로봇이 상, 하, 좌, 우 이동합니다.
                for(int i = 0 ; i < 4 ; i++) {
                    int nr1 = p_robot.r1+next_value[i][0];
                    int nc1 = p_robot.c1+next_value[i][1];
                    int nr2 = p_robot.r2+next_value[i][0];
                    int nc2 = p_robot.c2+next_value[i][1];
                    if(isOutOfBoard(nr1) || isOutOfBoard(nc1) || isOutOfBoard(nr2) || isOutOfBoard(nc2)) {
                        continue;
                    }
                    if(board[nr1][nc1] != 1 && board[nr2][nc2] != 1 && !visited[nr1][nc1][nr2][nc2]) {
                        visited[nr1][nc1][nr2][nc2] = true;
                        q.add(new Robot(t+1, nr1, nc1, nr2, nc2));
                    }
                }
                
                // 로봇이 회전합니다.
                // 로봇이 가로로 배치된 경우 로봇의 위치에서 위쪽 두 칸 또는 아래쪽 두 칸이 비어있어야 회전이 가능합니다.
                // 로봇이 세로로 배치된 경우 로봇의 위치에서 왼쪽 두 칸 또는 오른쪽 두 칸이 비어있어야 회전이 가능합니다.
                // 축이 되는 칸에 따라 회전 방향이 다릅니다.
                // 로봇의 두 칸의 좌표는 항상 r1 <= r2, c1 <=c2 를 만족해야합니다.
                if(r1 == r2) {
                    if(!isOutOfBoard(r1-1) && board[r1-1][c1] != 1 && board[r2-1][c2] != 1) {
                        // (r1, c1)이 축이 되어 회전합니다.
                        if(!visited[r1-1][c1][r1][c1]) {
                            visited[r1-1][c1][r1][c1] = true;
                            q.add(new Robot(t+1, r1-1, c1, r1, c1));
                        }
                        // (r2, c2)이 축이 되어 회전합니다.
                        if(!visited[r2-1][c2][r2][c2]) {
                            visited[r2-1][c2][r2][c2] = true;
                            q.add(new Robot(t+1, r2-1, c2, r2, c2));
                        }
                    }
                    if(!isOutOfBoard(r1+1) && board[r1+1][c1] != 1 && board[r2+1][c2] != 1) {
                        if(!visited[r1][c1][r1+1][c1]) {
                            visited[r1][c1][r1+1][c1] = true;
                            q.add(new Robot(t+1, r1, c1, r1+1, c1));
                        }
                        if(!visited[r2][c2][r2+1][c2]) {
                            visited[r2][c2][r2+1][c2] = true;
                            q.add(new Robot(t+1, r2, c2, r2+1, c2));
                        }
                    }
                }
                else {
                    if(!isOutOfBoard(c1-1) && board[r1][c1-1] != 1 && board[r2][c2-1] != 1) {
                        if(!visited[r1][c1-1][r1][c1]) {
                            visited[r1][c1-1][r1][c1] = true;
                            q.add(new Robot(t+1, r1, c1-1, r1, c1));
                        }
                        
                        if(!visited[r2][c2-1][r2][c2]) {
                            visited[r2][c2-1][r2][c2] = true;
                            q.add(new Robot(t+1, r2, c2-1, r2, c2));
                        }
                    }
                    if(!isOutOfBoard(c1+1) && board[r1][c1+1] != 1 && board[r2][c2+1] != 1) {
                        if(!visited[r1][c1][r1][c1+1]) {
                            visited[r1][c1][r1][c1+1] = true;
                            q.add(new Robot(t+1, r1, c1, r1, c1+1));
                        }
                        if(!visited[r2][c2][r2][c2+1]) {
                            visited[r2][c2][r2][c2+1] = true;
                            q.add(new Robot(t+1, r2, c2, r2, c2+1));
                        }
                    }
                }
            }
        }
        
        return answer;
    }
    
    public boolean isOutOfBoard(int l) {
        if(l < 0 || l >= n) return true;
        else return false;
    }
}

class Robot {
    int t;
    int r1;
    int c1;
    int r2;
    int c2;
    Robot(int t, int r1, int c1, int r2, int c2) {
        this.t = t;
        this.r1 = r1;
        this.c1 = c1;
        this.r2 = r2;
        this.c2 = c2;
    }
}

/*
bfs를 이용 - 상하좌우 이동, 회전
로봇의 두 좌표는 오름차순 정렬
*/