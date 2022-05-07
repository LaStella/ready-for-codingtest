// N-Queen
// https://programmers.co.kr/learn/courses/30/lessons/12952?language=java

import java.util.*;

class Solution {
    int answer;
    public int solution(int n) {
        answer = 0;
        
        // n개의 퀸이 n행에서 배치되는 열을 저장합니다. (ex. board[3] = 1 이라면, 3번 행퀸은 1열에 배치되었음을 의미합니다.)
        int[] board = new int[n];
        
        dfs(0, n, board);
        
        return answer;
    }
    
    // k번째 행에 퀸을 배치하는 함수입니다.
    public void dfs(int k, int n, int[] board) {
        // n개의 퀸을 모두 배치했다면 정답을 늘려줍니다.
        if(k == n) {
            answer++;
            return;
        }
        else {
            // 0번째 열부터 n-1번째 열까지 확인합니다.
            for(int i = 0 ; i < n ; i++) {
                boolean able = true;
                // 각 열마다 이미 배치된 퀸들과 같은 열에 있는지, 대각선에 있는지 확인합니다.
                for(int j = 0 ; j < k ; j++) {
                    if(board[j] == i || Math.abs(board[j]-i) == Math.abs(j-k)) {
                        able = false;
                        break;
                    }
                }
                
                // 이미 배치되어있는 퀸들(board)과 세로, 대각선으로 공격할 수 없다면 해당 열(i)에 퀸을 배치하고 다음 퀸을 배치합니다.
                if(able) {
                    board[k] = i;
                    dfs(k+1, n, board);
                }
            }
        }
    }
}

/*
한 열, 한 행에는 퀸이 하나만 존재할 수 있다
열 또는 행을 기준으로 퀸을 배치하며 맨 마지막 열 또는 행에 도달 가능한지 확인한다.
행을 기준으로 잡는다.
0번째 행에서 0~n-1까지 퀸을 배치하며, 퀸을 배치하였으면 재귀를 통해 다음 퀸을 배치

다음퀸은 (0+1)번째 행에서 배치, 행을 기준으로 하기때문에 행이 같을 수 없다.(퀸이 가로 이동으로 공격할 수 없다.)
같은 열인 경우 세로이동으로 공격할 수 있으므로 배치된 퀸의 열을 저장해야한다.
대각선 공격을 할 수 없게 해야하므로 대각선위치에 있는지 확인해야한다.
배치한 퀸들의 행,열을 통해 대각선을 판단(대각선은 기울기의 절대값이 1인 경우로 가로차이 절대값 == 세로차이 절대값 인 경우가 대각선이다.)



*/