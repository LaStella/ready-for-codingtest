// 기둥과 보 설치
// https://programmers.co.kr/learn/courses/30/lessons/60061?language=java

class Solution {
    // 설치한 기둥과 보의 개수를 나타낼 변수입니다.
    int frame_count = 0;
    public int[][] solution(int n, int[][] build_frame) {
        int[][] answer = {};
        
        // 보와 기둥을 설치할 3차원 배열입니다.
        // [x좌표][y좌표][보와 기둥]
        // build_frame에서 x,y좌표는 n까지 주어지며 x+1, y+1좌표로 접근이 용이하도록(board[n+1][n+1] 에 접근이 가능하도록) n+2크기의 배열을 만듭니다.
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
        
        // 설치한 구조물의 개수만큼 배열을 만듭니다.
        answer = new int[frame_count][3];
        int index = 0;

        // answer배열에 설치된 구조물의 좌표와 구조물의 종류를 저장합니다.
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
    
    // 기둥이 존재할 수 있는지 확인하는 함수입니다.
    public boolean checkPossiblePillar(int[][][] board, int x, int y) {
        // 기둥이 바닥위에 있으면 존재할 수 있습니다.
        if(y == 0) return true;
        else {
            // 기둥이 보의 한쪽 끝 부분 위에 있으면 존재할 수 있습니다.
            if((x-1 >= 0 && board[x-1][y][1] == 1) || board[x][y][1] == 1) return true;
            // 기둥이 다른 기둥 위에 있으면 존재할 수 있습니다.
            if(board[x][y-1][0] == 1) return true;
        }
        
        return false;
    }
    
    // 보가 존재할 수 있는지 확인하는 함수입니다.
    public boolean checkPossibleFloor(int[][][] board, int x, int y) {
        // 보의 한 끝 부분이 기둥위에 있으면 존재할 수 있습니다.
        if(board[x][y-1][0] == 1 || board[x+1][y-1][0] == 1) return true;
        // 보의 양쪽 끝 부분이 다른 보와 동시에 연결되면 존재할 수 있습니다.
        if((x-1 >= 0 && board[x-1][y][1] == 1) && board[x+1][y][1] == 1) return true;
        
        return false;
    }
    
    // 기둥을 설치하는 함수입니다.
    public void addPillar(int[][][] board, int x, int y) {
        // 기둥이 존재할 수 있는지 확인하여 가능하면 설치합니다.
        if(checkPossiblePillar(board, x, y)) {
            board[x][y][0] = 1;
            frame_count++;
        }
    }
    
    // 기둥을 삭제하는 함수입니다.
    public void removePillar(int[][][] board, int x, int y) {
        // 기둥을 삭제합니다.
        board[x][y][0] = 0;
        
        // 삭제한 기둥 위에 기둥이 있을 경우
        if(board[x][y+1][0] == 1) {
            // (x, y+1)기둥이 존재할 수 있는지 확인하여 존재할 수 없다면 (x, y)기둥을 복구하고, 삭제를 하지 않습니다.
            if(!checkPossiblePillar(board, x, y+1)) {
                board[x][y][0] = 1;
                return;
            }
        }
        
        // 삭제한 기둥 위에 오른쪽으로 보가 있을 경우
        if(board[x][y+1][1] == 1) {
            // 보가 존재할 수 있는지 확인하여 존재할 수 없다면 기둥을 복구하고, 삭제를 하지 않습니다.
            if(!checkPossibleFloor(board, x, y+1)) {
                board[x][y][0] = 1;
                return;
            }
        }
        
        // 삭제한 기둥 위에 왼쪽으로 보가 있을 경우
        if(x-1 >= 0 && board[x-1][y+1][1] == 1) {
            // 위와 동일합니다.
            if(!checkPossibleFloor(board, x-1, y+1)) {
                board[x][y][0] = 1;
                return;
            }
        }
        
        frame_count--;
    }
    
    // 보를 설치하는 함수입니다.
    public void addFloor(int[][][] board, int x, int y) {
        // 보가 존재할 수 있는지 확인하여 가능하면 설치합니다.
        if(checkPossibleFloor(board, x, y)) {
            board[x][y][1] = 1;
            frame_count++;    
        }
    }
    
    // 보를 삭제하는 함수입니다.
    public void removeFloor(int[][][] board, int x, int y) {
        // 보를 삭제합니다.
        board[x][y][1] = 0;
        
        // 삭제한 보의 왼쪽으로 보가 있을 경우
        if(x-1 >= 0 && board[x-1][y][1] == 1) {
            if(!checkPossibleFloor(board, x-1, y)) {
                board[x][y][1] = 1;
                return;
            }
        }
        
        // 삭제한 보의 오른쪽으로 보가 있을 경우
        if(board[x+1][y][1] == 1) {
            if(!checkPossibleFloor(board, x+1, y)) {
                board[x][y][1] = 1;
                return;
            }
        }
        
        // 삭제한 보의 왼쪽 끝에 기둥이 있을 경우
        if(board[x][y][0] == 1) {
            if(!checkPossiblePillar(board, x, y)) {
                board[x][y][1] = 1;
                return;
            }
        }
        
        // 삭제한 보의 오른쪽 끝에 기둥이 있을 경우
        if(board[x+1][y][0] == 1) {
            if(!checkPossiblePillar(board, x+1, y)) {
                board[x][y][1] = 1;
                return;
            }
        }
        
        frame_count--;
    }
}