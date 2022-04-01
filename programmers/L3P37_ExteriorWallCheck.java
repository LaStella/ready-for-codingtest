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
        dfs(0, new int[dist.length], n, ex_weak, dist, visited);
        
        System.out.println(Arrays.toString(ex_weak));
        
        return answer;
    }
    
    public void dfs(int depth, int[] result, int n, int[] ex_weak, int[] dist, boolean[] visited) {
        if(depth == dist.length) {
            System.out.println(Arrays.toString(result));
            check(result, n, ex_weak);
        }
        else {
            for(int i = 0 ; i < dist.length ; i++) {
                if(!visited[i]) {
                    visited[i] = true;
                    result[depth] = dist[i];
                    dfs(depth+1, result, n, ex_weak, dist, visited);
                    visited[i] = false;
                }
            }
        }
    }
    
    public void check(int[] result, int n, int[] ex_weak) {
        // 취약점 시작 지점입니다.
        int s_index = 0;
        // 점검을 보낸 사람의 수입니다.
        int count = 0;
        // 점검이 확인된 지점을 저장하는 set입니다.
        HashSet<Integer> set = new HashSet<>();
        
        for(int d : result) {
            // 보낸 사람이 점검을 시작해서 종료하는 지점입니다.
            int e_point = ex_weak[s_index] + d;
            // 외벽의 취약점을 순회하며 종료지점안에 포함되는 취약점은 점검합니다.
            for(int i = s_index ; i < ex_weak.length ; i++) {
                if(ex_weak[i] <= e_point) {
                    System.out.print("!");
                    set.add(ex_weak[i]%n);
                    s_index++;
                }
                // 종료지점을 벗어나는 지점은 점검할 수 없으므로 점검을 종료합니다.
                else {
                    break;
                }
            }
            System.out.println();
            // 점검을 종료하면 점검한 사람의 수를 증가시킵니다.
            count++;
            // 점검을 모두 했다면 중단합니다.
            if(set.size() == ex_weak.length/2) {
                System.out.println(count);
                break;
            }
        }
    }
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
*/
