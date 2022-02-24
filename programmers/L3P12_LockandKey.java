// 자물쇠와 열쇠
// https://programmers.co.kr/learn/courses/30/lessons/60059?language=java

class Solution {
    public boolean solution(int[][] key, int[][] lock) {
        boolean answer = false;
        int m = key.length;
        int n = lock.length;
        
        // 열쇠는 정사각형 형태이므로 시계방향으로 90도씩 3번까지 회전할 수 있습니다.
        int rotateCount = 0;
        while(rotateCount <= 3) {
            // 열쇠는 x축 y축으로 각각 m-1+n번 이동할 수 있습니다.
            for(int x = 0 ; x < m-1+n ; x++) {
                for(int y = 0 ; y < m-1+n ; y++) {
                    // 자물쇠의 크기를 열쇠가 전부 들어갈 수 있도록 확장합니다.
                    int[][] expandedLock = expandLock(lock, m);
                    // 자물쇠에 열쇠를 맞추어봅니다.
                    matchKey(expandedLock, key, x, y);
                    // 자물쇠가 열렸는지 확인합니다.
                    if(checkOpen(expandedLock, n, m)) {
                        return true;
                    }
                }
            }
            // 열쇠를 회전합니다.
            key = rotateKey(key);
            rotateCount++;
        }
        
        return answer;
    }
    
    // 열쇠를 시계방향으로 90도 회전하는 함수입니다.
    public int[][] rotateKey(int[][] key) {
        int m = key.length;
        int[][] nKey = new int[m][m];
        for(int r = 0 ; r < m ; r++) {
            for(int c = 0 ; c < m ; c++) {
                nKey[r][c] = key[m-c-1][r];
            }
        }
        return nKey;
    }
    
    // 자물쇠의 크기를 확장하는 함수입니다.
    // 자물쇠와 열쇠의 크기를 입력받습니다.
    public int[][] expandLock(int[][] lock, int m) {
        int n = lock.length;
        // 자물쇠의 크기는 열쇠의 네 꼭짓점이 걸리는 크기입니다.
        int newN = 2*m+n-2;
        int[][] nLock = new int[newN][newN];
        for(int r = 0 ; r < n ; r++) {
            for(int c = 0 ; c < n ; c++) {
                nLock[m+r-1][m+c-1] = lock[r][c];
            }
        }
        return nLock;
    }
    
    // 자물쇠에 열쇠를 맞추는 함수입니다.
    // 자물쇠와 열쇠, 열쇠의 x축, y축 이동거리를 입력받습니다.
    public void matchKey(int[][] lock, int[][] key, int x, int y) {
        int m = key.length;
        for(int r = 0 ; r < m ; r++) {
            for(int c = 0 ; c < m ; c++) {
                lock[r+x][c+y] += key[r][c];
            }
        }
    }
    
    // 자물쇠가 열렸는지 확인하는 함수입니다.
    // 확장된 자물쇠와 자물쇠의 크기, 열쇠의 크기를 입력받습니다.
    public boolean checkOpen(int[][] expandedLock, int n, int m) {
        for(int r = 0 ; r < n ; r++) {
            for(int c = 0 ; c < n ; c++) {
                // 자물쇠의 값이 1인 경우에만 열립니다.
                // 열쇠의 돌기와 자물쇠의 돌기가 겹쳤을 때 값은 2가 나옵니다.
                // 자물쇠에 열쇠의 돌기가 맞지 않았을 때 값은 0이 나옵니다.
                if(expandedLock[r+m-1][c+m-1] != 1) {
                    return false;
                }
            }
        }
        return true;
    }
}
/*
k k k
k k k
k k x l l
    l l l
    l l x k k
        k k k
        k k k
        
k k k k k k k
k k k k k k k
k k k k k k k
k k k l k k k
    l l l k k


*/