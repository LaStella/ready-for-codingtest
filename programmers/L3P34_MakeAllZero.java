// 모두 0으로 만들기
// https://programmers.co.kr/learn/courses/30/lessons/76503?language=java#

class Solution {
    long answer = -1;
    public long solution(int[] a, int[][] edges) {
        boolean[] visited = new boolean[edges.length];
        
        dfs(a, edges, 0, 0, visited, "");
        
        return answer;
    }
    
    public void dfs(int[] a, int[][] edges, int depth, int result, boolean[] visited, String test) {
        if(depth == edges.length) {
            for(int i = 0 ; i < a.length ; i++) {
                if(a[i] != 0) return;
            }
            if(answer == -1) {
                answer = result;
            }
            if(result < answer) answer = result;
            System.out.println(test);
        }
        else {
            for(int i = 0 ; i < edges.length ; i++) {
                if(!visited[i]) {
                    visited[i] = true;
                    int u = edges[i][0];
                    int v = edges[i][1];
                    int au = a[u];
                    int av = a[v];
                    int auv = a[u]+a[v];
                    if(au == 0 && av == 0) {
                        dfs(a, edges, depth+1, result, visited, test+i);
                    }
                    else if(auv == 0) {
                        a[u] = 0;
                        a[v] = 0;
                        dfs(a, edges, depth+1, result+Math.abs(av), visited, test+i+"n");
                    }
                    else {
                        // u의 가중치 증가, v의 가중치 감소
                        a[u] = auv;
                        a[v] = 0;
                        dfs(a, edges, depth+1, result+Math.abs(av), visited, test+i+"u");
                        // u의 가중치 감소, v의 가중치 증가
                        a[u] = 0;
                        a[v] = auv;
                        dfs(a, edges, depth+1, result+Math.abs(au), visited, test+i+"v");
                    }
                    a[u] = au;
                    a[v] = av;
                    visited[i] = false;
                }
            }
        }
        
    }
}