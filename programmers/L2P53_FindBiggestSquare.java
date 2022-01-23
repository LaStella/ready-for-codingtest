// 가장 큰 정사각형 찾기
// https://programmers.co.kr/learn/courses/30/lessons/12905?language=java


class Solution {
    public int solution(int [][]board) {
        int answer = 0;
        
        for(int r = 0 ; r < board.length ; r++) {
            for(int c = 0 ; c < board[0].length ; c++) {
                if(board[r][c] == 1) {
                    // board를 한칸씩 순회하며 1일 경우 주위(위쪽, 왼쪽, 왼위대각선쪽)를 확인합니다.
                    checkAround(r, c, board);
                    // 최대값을 answer로 저장합니다. (정사각형 한 변의 길이)
                    if(board[r][c] > answer) answer = board[r][c];
                }
            }
        }
        
        // for(int[] arr : board) {
        //     for(int i : arr) {
        //         System.out.print(i+" ");
        //     }
        //     System.out.println();
        // }
        
        answer *= answer;

        return answer;
    }
    
    // 주어진 board의 좌표 r, c를 기준으로 위쪽, 왼쪽, 왼위대각선쪽을 확인하는 함수입니다.
    public void checkAround(int r, int c, int[][] board) {
        // board 범위를 넘어서는 경우 중단
        if(r-1 < 0 || c-1 < 0) {
            return;
        }
        // 위쪽, 왼쪽, 왼위대각선쪽의 값들 중 최소값을 찾습니다.
        int min = Math.min(board[r][c-1], Math.min(board[r-1][c-1], board[r-1][c]));
        // 주어진 좌표 값은 최소값보다 1이 크게됩니다. board를 한칸씩 순회하며 정사각형으로 둘러싸인 정사각형은 그 변의 길이를 1씩 늘려 저장합니다.
        board[r][c] = min+1;
    }
}

/* 효율성 실패 코드
class Solution {
    public int solution(int [][]board) {
        int answer = 0;
        int length = 0;
        
        for(int r = 0 ; r < board.length ; r++) {
            for(int c = 0 ; c < board[0].length ; c++) {
                if(board[r][c] == 1) {
                    int m = getMaxLength(r, c, board);
                    while(m > length) {
                        if(checkSqure(r, c, board, m)) {
                            length = m;
                            answer = m*m;
                            break;
                        }
                        else {
                            m--;
                        }
                    }
                }
            }
        }
        

        return answer;
    }
    
    public boolean checkSqure(int rStart, int cStart, int[][] board, int m) {
        if(rStart+m > board.length) {
            return false;
        }
        
        for(int r = rStart+1 ; r < rStart+m ; r++) {
            for(int c = cStart ; c < cStart+m ; c++) {
                if(board[r][c] != 1) return false;
            }
        }

        return true;
    }
    
    public int getMaxLength(int rStart, int cStart, int[][] board) {
        int length = 0;
        
        for(int c = cStart ; c < board[0].length ; c++) {
            if(board[rStart][c] == 1) length++;
            else break;
        }
        
        return length;
    }
}
*/