// 외벽 점검
// https://programmers.co.kr/learn/courses/30/lessons/60062?language=java

import java.util.*;

class Solution {
    public int solution(int n, int[] weak, int[] dist) {
        int answer = 0;
        int[] ex_weak = new int[weak.length*2];
        
        for(int i = 0 ; i < ex_weak.length ; i++) {
            ex_weak[i] = weak[i%weak.length]+n*(i/weak.length);
        }
        
        boolean[] visited = new boolean[dist.length];
        dfs(n, ex_weak, dist, visited);
        
        System.out.println(Arrays.toString(ex_weak));
        
        return answer;
    }
    
    public void dfs(int depth, int[] result, int n, int[] ex_weak, int[] dist, boolean[] visited) {
        if(depth == dist.length) {
            
        }
    }
    
    public void dfs2(int)
}

/*
임의의 지점 a,b 사이의 거리
ex) a = 1, b = 10 , n = 12
거리는 3
1-(10-12) = 3
13-10 = 3
1 = 1+n
weak배열을 확장 [1,2,3,..,i] -> [1,2,3,..,1+n,2+n,...i+n]
한방향으로 계산이 가능

dist의 원소의 순열에 따라 순차적으로 진행
ex) dist = [1, 2, 3, 4]
1234, 1324, 1423 등 다양한 순열
순열 순으로 weak를 방문하여 체크

weak방문 순열
ex) dist순열 1324
weak = 1,5,6,10
weak[0] 부터할지 weak[1]부터할지 순열


*/
