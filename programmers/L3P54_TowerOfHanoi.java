// 하노이의 탑
// https://programmers.co.kr/learn/courses/30/lessons/12946?language=java

import java.util.*;

class Solution {
    List<int[]> list;
    public int[][] solution(int n) {
        int[][] answer = {};
        
        // 이동 순서를 담을 리스트입니다.
        list = new ArrayList<>();
        
        // n개의 원반이 1에서 3으로 2를 경유해서 이동합니다.
        hanoi(n, 1, 3, 2);
        
        answer = new int[list.size()][2];
        
        int index = 0;
        for(int[] move : list) {
            answer[index++] = move;
        }
        
        return answer;
    }
    
    // n개의 원반이 출발지(start)에서 경유지(via)를 거쳐 목적지(end)로 이동하는 함수입니다.
    // n개의 원반이 3번 기둥으로 이동하려면, n-1개의 원반이 2번 기둥으로 이동한 후 n번째 원반이 3번 기둥으로 이동해야합니다.
    // 이후 2번 기둥으로 이동한 n-1개의 원반을 3번 기둥으로 이동합니다.
    public void hanoi(int n, int start, int end, int via) {
        int[] move = {start, end};
        
        if(n == 1) {
            list.add(move);
        }
        else {
            hanoi(n-1, start, via, end);
            list.add(move);
            hanoi(n-1, via, end, start);
        }
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
------------------------------------------------
하노이(n, 3)을 n개의 원반을 3번 기둥으로 옮긴다고 한다면, 
하노이(n-1, 2)를 해야하고, n번째 원반을 3으로 이동한 후 하노이(n-1, 3)을 해야한다.


*/