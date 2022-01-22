// 가장 큰 정사각형 찾기
// https://programmers.co.kr/learn/courses/30/lessons/12905?language=java

class Solution {
    public int solution(int [][]board) {
        int answer = 0;
        int length = 0;
        
        for(int r = 0 ; r < board.length ; r++) {
            for(int c = 0 ; c < board[0].length ; c++) {
                if(board[r][c] == 1) {
                    // System.out.println("r: "+r+" c: "+c);
                    int m = getMaxLength(r, c, board);
                    // System.out.println("r: "+r+"c:"+c);
                    // System.out.println(m);
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
                    // checkSqure(r, c, board, m);
                    // System.out.println(checkSqure(r, c, board, m));
                }
            }
        }
        

        return answer;
    }
    
    public boolean checkSqure(int rStart, int cStart, int[][] board, int m) {
        // for(int m = m ; m > 0 ; m--) {
        // System.out.println("start");
        if(rStart+m > board.length) {
            // System.out.println("!");
            return false;
        }
        
        for(int r = rStart+1 ; r < rStart+m ; r++) {
            for(int c = cStart ; c < cStart+m ; c++) {
                if(board[r][c] != 1) return false;
                // else System.out.println("----r: "+r+" c: "+c);
                // System.out.println("r : "+r+" c : "+c);
                // System.out.print(board[r][c]+" ");
            }
            // System.out.println();
        }
        // System.out.println("r: "+rStart+" c: "+cStart);
        return true;
        // }
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