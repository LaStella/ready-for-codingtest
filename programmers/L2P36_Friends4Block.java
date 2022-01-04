// 프렌즈4블록
// https://programmers.co.kr/learn/courses/30/lessons/17679?language=java

class Solution {
    public int solution(int m, int n, String[] board) {
        int answer = 0;
        // 제거될 블록을 나타내는 bool배열입니다.
        boolean[][] delete = new boolean[m][n];
        String[][] nboard = new String[m][n];

        // 문자열 배열 board를 2차 문자열 배열 nboard로 변환합니다.
        for(int i = 0 ; i < m ; i++) {
            nboard[i] = board[i].split("");
        }
        
        // 제거할 수 있는 2x2 블록이 없을 때까지 반복합니다.
        while(true) {
            // nboard의 각 블록이 2x2형태로 같은지 확인합니다.
            // 마지막 row와 column은 2x2형태가 될 수 없으므로 그 전까지만 확인합니다.
            for(int r = 0 ; r < m-1 ; r++) {
                for(int c = 0 ; c < n-1 ; c++) {
                    checkTwobyTwo(r, c, delete, nboard);
                }
            }
            int deleteCount = getDeleteCount(delete, nboard);
            // 더이상 제거될 블록이 없다면 반복을 중단합니다.
            if(deleteCount == 0) {
                break;
            }
            
            answer += deleteCount;
        }
        
        return answer;
    }
    
    // 2x2블록인지 확인하는 함수입니다.
    public void checkTwobyTwo(int r, int c, boolean[][] delete, String[][] board) {
        int count = 0;
        // 오른쪽, 아래쪽, 오른아래쪽을 나타내는 가중치입니다.
        int[][] nextval = {{0, 1}, {1, 0}, {1, 1}};

        // 현재 블록이 비어있을 경우 return합니다.
        if(board[r][c].equals("0")) {
            return;
        }
        
        // 현재 블록과 오른쪽, 아래쪽, 오른아래쪽의 블록이 같은지 확인합니다.
        for(int[] n : nextval) {
            int nr = r+n[0];
            int nc = c+n[1];
            if(board[r][c].equals(board[nr][nc])) {
                count++;
            }
            else {
                break;
            }
        }

        // count가 3이 되면 사라질 수 있는 2x2블록 형태이므로 제거될 대상을 나타내는 delete배열의 값을 true로 바꿔줍니다.
        if(count == 3) {
            delete[r][c] = true;
            for(int[] n : nextval) {
                int nr = r+n[0];
                int nc = c+n[1];
                delete[nr][nc] = true;
            }
        }
    }
    
    // 블록을 제거하고 제거된 블록의 개수를 return하는 함수입니다.
    public int getDeleteCount(boolean[][] delete, String[][] board) {
        int count = 0;

        for(int c = 0 ; c < board[0].length ; c++) {
            for(int r = 0 ; r < board.length ; r++) {
                if(delete[r][c]) {
                    count++;
                    delete[r][c] = false;
                    // 블록이 지워지면 빈 공간이 되므로
                    // 현재 row보다 위에 있는 블록들을 아래로 채웁니다.
                    for(int i = r ; i > 0 ; i--) {
                        // 위에서 부터 순서대로 지우기 때문에
                        // 위에 있는 블록이 없다면 중단합니다.
                        if(board[i-1][c].equals("0")) {
                            break;
                        }
                        // 위에 있는 블록을 한칸 아래로 내리고
                        board[i][c] = board[i-1][c];
                        // 위에 있는 블록은 빈 공간으로 표시해줍니다.
                        board[i-1][c] = "0";
                    }
                }
            }
        }
        
        return count;
    }
}