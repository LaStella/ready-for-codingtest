// 공 이동 시뮬레이션
// https://programmers.co.kr/learn/courses/30/lessons/87391?language=java

import java.util.*;

class Solution {
    public long solution(int n, int m, int x, int y, int[][] queries) {
        long answer = 0;
        
        int r_start = x;
        int c_start = y;
        int r_end = x;
        int c_end = y;
        
        // 목적지 (x, y)부터 시작하여 쿼리를 역순으로 반대의 쿼리를 실행하게되면 (x, y)로 도착할 수 있는 r과 c의 값을 알 수 있습니다.
        // 목적지 (x, y)로 가면서 도착하는 경유지 (a, b)가 있다면, (a, b)로 가기위한 경유지 (c, d)가 존재하게되므로
        // 경유지들의 범위를 쿼리의 역순으로 반대 방향으로 이동하면 구할 수 있습니다.
        // 단, 격자의 끝에 위치한 경우 될 수 있는 r과 c가 여러개 될 수 있습니다.
        // ex) 문제의 예시와 같을 경우 마지막 쿼리인 [2, 1]을 실행하여 (0, 0)에 도착하는 경우는 (0, 0)과 (1, 0)이 됩니다.
        for(int i = queries.length-1 ; i >= 0 ; i--) {
            int command = queries[i][0];
            int dx = queries[i][1];
            
            if(command == 0) {
                if(c_start != 0) {
                    c_start += dx;
                }
                c_end = Math.min(m-1, c_end + dx);
            }
            else if(command == 1) {
                if(c_end != m-1) {
                    c_end -= dx;
                }
                c_start = Math.max(0, c_start - dx);
            }
            else if(command == 2) {
                if(r_start != 0) {
                    r_start += dx;
                }
                r_end = Math.min(n-1, r_end + dx);
            }
            else {
                if(r_end != n-1) {
                    r_end -= dx;
                }
                r_start = Math.max(0, r_start - dx);
            }
            // 격자 범위를 벗어나 쿼리를 실행해도 (x, y)로 도달할 수 없는 경우 정답은 0이 됩니다.
            if(c_start >= m || c_end < 0 || r_start >= n || r_end < 0) {
                return answer = 0;
            }
        }
        
        answer = ((long)r_end-r_start+1)*((long)c_end-c_start+1);
        
        return answer;
    }
}


/*
공의 도착지인 (x, y)에서 역으로 쿼리를 실행하면 출발지가 나온다.
이를 이용하여 출발지의 범위를 계산한다.
ex) n=6, m=6일때
x=3, y=1 이라면
마지막 쿼리가 [2, 2] 일경우
마지막 쿼리의 출발 위치는 (5, 1)이어야 한다.
x=0, y=1 이라면
(2, 1) (1, 1) (0, 1)이 범위가 된다.
x~x+dx가 r의 범위가 된다.

x=3, y=1
마지막 쿼리가 [3, 2] 일경우
마지막 쿼리의 출발 위치는 (1, 1)이어야 한다.
x-dx가 r의 범위가 된다.
x=5, y=1
(5, 1) (4, 1) (3, 1)이 범위가 된다.
x-dx~x가 r의 범위가 된다.

*/