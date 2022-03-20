// 기둥과 보 설치
// https://programmers.co.kr/learn/courses/30/lessons/60061?language=java

class Solution {
    // 설치한 기둥과 보의 개수를 나타낼 변수입니다.
    int frame_count = 0;
    public int[][] solution(int n, int[][] build_frame) {
        int[][] answer = {};
        
        // 보와 기둥을 설치할 3차원 배열입니다.
        // [x좌표][y좌표][보와 기둥]
        int[][][] board = new int[n+2][n+2][2];
        
        for(int[] build : build_frame) {
            if(build[2] == 0) {
                if(build[3] == 0) {
                    removePillar(board, build[0], build[1]);
                }
                else {
                    addPillar(board, build[0], build[1]);
                }
            }
            else {
                if(build[3] == 0) {
                    removeFloor(board, build[0], build[1]);
                }
                else {
                    addFloor(board, build[0], build[1]);
                }
            }
        }
        
        answer = new int[frame_count][3];
        int index = 0;

        for(int x = 0 ; x <= n ; x++) {
            for(int y = 0 ; y <= n ; y++) {
                for(int i = 0 ; i < 2 ; i++) {
                    if(board[x][y][i] == 1) {
                        answer[index++] = new int[] {x, y, i};
                    }
                }
            }
        }
        
        return answer;
    }
    
    // 기둥을 설치하는 함수입니다.
    public void addPillar(int[][][] board, int x, int y) {
        // 기둥을 설치하려는 곳이 바닥이 아니면서
        if(y != 0) {
            // 기둥을 지지할 기둥이 없으며
            if(y-1 >= 0 && board[x][y-1][0] != 1) {
                // 기둥을 지지할 보가 없다면 설치할 수 없습니다.
                if(x-1 >= 0 && board[x-1][y][1] != 1 && board[x][y][1] !=1) {
                    return;
                }    
            }
        }
        
        board[x][y][0] = 1;
        frame_count++;
    }
    
    // 기둥을 삭제하는 함수입니다.
    public void removePillar(int[][][] board, int x, int y) {
        // 삭제하려는 기둥 위에 기둥이 있을 경우
        if(board[x][y+1][0] == 1) {
            // 위에 있는 기둥을 지지할 보가 없다면 삭제할 수 없습니다.
            if(board[x][y+1][1] != 1 && board[x-1][y+1][1] != 1) {
                return;
            }
        }
        // 삭제하려는 기둥 위에 보가 있을 경우
        if(board[x][y+1][1] == 1) {
            // 위에 있는 보를 지지할 기둥이 없고
            if(board[x+1][y][0] != 1) {
                // 위에 있는 보를 지지할 양 옆 보가 없다면 삭제할 수 없습니다.
                if(x-1 >= 0 && board[x-1][y+1][1] != 1 && board[x+1][y+1][1] != 1) {
                    return;
                }       
            }
        }
        board[x][y][0] = 0;
        frame_count--;
    }
    
    // 보를 설치하는 함수입니다.
    public void addFloor(int[][][] board, int x, int y) {
        // 보를 지지할 기둥이 없는지 확인합니다.
        if(y-1 >= 0 && board[x][y-1][0] != 1 && board[x+1][y-1][0] != 1) {
            // 보를 지지할 양 옆 보가 없는지 확인합니다.
            if(x-1 >= 0 && board[x-1][y][1] != 1 && board[x+1][y][1] != 1) {
                return;
            }
        }
        board[x][y][1] = 1;
        frame_count++;
    }
    
    // 보를 삭제하는 함수입니다.
    public void removeFloor(int[][][] board, int x, int y) {
        // 삭제하려는 보의 왼쪽에 보가 있을 경우
        if(x-1 >= 0 && board[x-1][y][1] == 1) {
            // 왼쪽에 있는 보를 지지할 기둥이 없다면 삭제할 수 없습니다.
            if(y-1 >= 0 && board[x-1][y-1][0] != 1) {
                return;
            }
        }
        // 삭제하려는 보의 오른쪽에 보가 있는 경우
        if(board[x+1][y][1] == 1) {
            // 오른쪽에 있는 보를 지지할 기둥이 없다면 삭제할 수 없습니다.
            if(y-1 >= 0 && board[x+1][y-1][0] != 1) {
                return;
            }
        }
        // 삭제하려는 보의 오른쪽에 기둥이 있는 경우
        if(board[x+1][y][0] == 1) {
            // 오른쪽에 있는 기둥을 지지할 기둥이 없고
            if(y-1 >= 0 && board[x+1][y-1][0] != 1) {
                // 오른쪽에 있는 기둥을 지지할 보가 없다면 삭제할 수 없습니다.
                if(board[x+1][y][1] != 1) {
                    return;
                }
            }
        }
        board[x][y][1] = 0;
        frame_count--;
    }
}

/*
한 좌표에 대해 기둥, 보가 존재할 수 있음
기둥 설치시(x,y,0,1) > y=0 설치,  y=0이아닐경우 (x-1, y)에 보 유무 확인
보 설치시(x,y,1,1) > (x,y-1)에 기둥 유무 확인 > (x-1,y)와 (x+1,y)에 보 유무 확인

보 삭제시(x,y,1,0) > (x-1, y)에 보가 있는지 확인> 있다면 (x-1, y-1)에 기둥 유무 확인
                    (x+1, y)에 보가 있는지 확인> 있다면 (x+1, y-1)기둥 유무 확인
                    (x+1, y)에 기둥이 있는지 확인 > 있다면 (x+1, y)에 보 유무 확인 또는 (x+1, y-1) 에 기둥 유무 확인
*/