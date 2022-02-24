// 자물쇠와 열쇠
// https://programmers.co.kr/learn/courses/30/lessons/60059?language=java

class Solution {
    public boolean solution(int[][] key, int[][] lock) {
        boolean answer = true;
        
        
        // key = rotate(key);
        // for(int[] arr : key) {
        //     for(int i : arr) {
        //         System.out.print(i);
        //     }
        //     System.out.println();
        // }
        printa(expandLock(lock, key.length));
        
        return answer;
    }
    
    public int[][] rotate(int[][] key) {
        int m = key.length;
        int[][] nkey = new int[m][m];
        for(int r = 0 ; r < m ; r++) {
            for(int c = 0 ; c < m ; c++) {
                nkey[r][c] = key[m-c-1][r];
                // System.out.println(key[m-c-1][r]);
            }
        }
        return nkey;
    }
    
    public int[][] expandLock(int[][] lock, int keyLength) {
        int n = lock.length;
        int newN = 2*keyLength+n-2;
        int[][] nLock = new int[newN][newN];
        for(int r = 0 ; r < n ; r++) {
            for(int c = 0 ; c < n ; c++) {
                nLock[keyLength+r-1][keyLength+c-1] = lock[r][c];
            }
        }
        return nLock;
    }
    
    public void printa(int[][] arr) {
        for(int[] a : arr) {
            for(int i : a) {
                System.out.print(i+" ");
            }
            System.out.println();
        }
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

