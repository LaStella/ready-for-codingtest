class Solution {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        int[] basket = new int[board.length*board[0].length];   //board의 가로 * 세로의 값
        int basketTop = 0;
        
        for(int m : moves) {
            for(int i = 0 ; i < board[0].length ; i++) {
                if(board[i][m-1]!=0) {
                    basket[basketTop] = board[i][m-1];
                    board[i][m-1] = 0;
                    
                    if(basketTop!=0) {     //바구니에 처음 인형을 쌓는 경우가 아닐경우에만
                        if(basket[basketTop-1]==basket[basketTop]) {
                            answer += 2;
                            basketTop--;
                        }
                        else {
                            basketTop++;
                        }
                    }
                    else {
                        basketTop++;
                    }
                    
                    break;
                }
            }
        }
        
        return answer;
    }
}