// 외벽 점검
// https://programmers.co.kr/learn/courses/30/lessons/60062?language=java

import java.util.*;

class Solution {
    int answer;
    public int solution(int n, int[] weak, int[] dist) {
        // 초기값으로 최대인원보다 큰 수를 저장합니다.
        answer = dist.length+1;
        int[][] ex_weak = new int[weak.length][weak.length];
        boolean[] visited = new boolean[dist.length];
        
        makeExtendedWeak(weak, n, ex_weak);
        dfs(0, new int[dist.length], n, ex_weak, dist, visited);
        
        // 모든 인원을 투입해도 취약 지점을 전부 점검할 수 없는 경우 -1을 리턴합니다.
        if(answer == dist.length+1) {
            return -1;
        }
        
        return answer;
    }
    
    // 투입할 친구의 순열을 구하며, 구한 순열에 대해 취약점을 점검하는 함수입니다.
    // depth : 깊이, result : 순열이 저장되는 공간
    public void dfs(int depth, int[] result, int n, int[][] ex_weak, int[] dist, boolean[] visited) {
        // 순열이 구하면 취약점을 점검합니다.
        if(depth == dist.length) {
            // 취약점을 순차방문하여 모든 취약점을 방문합니다.
            for(int[] weak : ex_weak) {
                check(result, n, weak);    
            }
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
    
    // 취약점을 점검하는 함수입니다.
    public void check(int[] result, int n, int[] weak) {
        // 취약점을 가리키는 커서입니다.
        int cursor = 0;
        // 점검을 보낸 사람의 수입니다.
        int count = 0;
        // 점검이 확인된 지점을 저장하는 set입니다.
        HashSet<Integer> set = new HashSet<>();
        
        for(int d : result) {
            // 보낸 사람이 점검을 시작해서 종료하는 지점입니다.
            int end = weak[cursor] + d;
            // 외벽의 취약점을 순회하며 종료지점안에 포함되는 취약점은 점검합니다.
            for(int i = cursor ; i < weak.length ; i++) {
                // 취약점을 점검하면 다음 취약점으로 커서를 이동합니다.
                if(weak[i] <= end) {
                    set.add(weak[i]%n);
                    cursor++;
                }
                // 종료지점을 벗어나는 지점은 점검할 수 없으므로 점검을 종료합니다.
                else {
                    break;
                }
            }
            // 점검을 종료하면 점검한 사람의 수를 증가시킵니다.
            count++;
            // 점검을 모두 했다면 중단합니다.
            if(set.size() == weak.length) {
                answer = Math.min(answer, count);
                break;
            }
        }
    }
    
    // 취약점 배열의 순서를 하나씩 바꾸어 저장하는 함수입니다.
    public void makeExtendedWeak(int[] weak, int n, int[][] ex_weak) {
        // ex_weak[i]는 i-1번째 취약점이 가장 마지막에 배치되는 경우입니다. (단, i >= 1)
        ex_weak[0] = weak.clone();
        for(int i = 1 ; i < ex_weak.length ; i++) {
            int temp = weak[0];
            for(int j = 0 ; j < weak.length-1 ; j++) {
                weak[j] = weak[j+1];
            }
            weak[weak.length-1] = temp+n;
            ex_weak[i] = weak.clone();
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
[1,2,3,4] ,[2,3,4,13], [3,4,13,14], [4,13,14,15]
n개의 원소를 가지는 weak배열은 n개의 한방향으로 순서만 뒤바꾼 배열로 바꿀 수 있다.
한방향으로 계산이 가능

dist의 원소의 순열에 따라 순차적으로 진행
ex) dist = [1, 2, 3, 4]
1234, 1324, 1423 등 다양한 순열
순열 순으로 weak를 방문하여 체크
*/