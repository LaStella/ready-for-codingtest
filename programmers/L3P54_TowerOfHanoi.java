// 하노이의 탑
// https://programmers.co.kr/learn/courses/30/lessons/12946?language=java

import java.util.*;

class Solution {
    public int[][] solution(int n) {
        int[][] answer = {};
        
        int[] location = new int[n+1];
        Arrays.fill(location, 1);
        
        if(n % 2 == 0) {
            
        }
        
        else {
            
        }
        
        return answer;
    }
}

/*
n 짝수
홀수번째는 오른쪽으로
짝수번째는 왼쪽으로 이동

1 2
2 3
1 3
3 2
1 1
2 2
1 2
4 3
1 3
2 1
1 1
3 3
1 2
2 3
1 3

옮기는 순서 121312141213121

하노이(n, 3)을 n개의 원반을 3번 기둥으로 옮긴다고 한다면, 
하노이(n-1, 2)를 해야한다


*/