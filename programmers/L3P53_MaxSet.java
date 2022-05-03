// 최고의 집합
// https://programmers.co.kr/learn/courses/30/lessons/12938?language=java

import java.util.*;

class Solution {
    public int[] solution(int n, int s) {
        int[] answer = {};
        
        // 자연수의 개수 n이 합 s보다 크다면 1보다 작은 자연수는 존재하지 않으므로 최고의 집합이 존재할 수 없습니다.
        if(n > s) {
            answer = new int[] {-1};
            return answer;
        }
        // 자연수의 합 s를 n으로 나눈 값을 배열에 채웁니다.
        // 나머지는 배열의 끝에서부터 하나씩 채워줍니다.
        else {
            answer = new int[n];
            Arrays.fill(answer, s/n);
            if(s%n != 0) {
                int remain = s%n;
                int index = n-1;
                while(remain > 0) {
                    answer[index--] += 1;
                    remain--;
                }
                
            }
        }
        
        return answer;
    }
}

/*
n개의 자연수 합 s
1을 고를 경우 나머지 수는 n-1개이며 합은 s-1
3 9
1 1 7 = d[2]*7
1 2 6 = d[3]*6
1 3 5 = d[4]*5
1 4 4 = d[5]*4
2 2 5 = d[4]*5
2 3 4 = d[5]*4
3 3 3 = d[6]*3
3 4 2 = d[7]*2

d[i][j]를 자연수 i개, 합 j일때 최대곱이라고 하자.
d[1][1] = 1
d[1][2] = 2
d[2][2] = 1
d[2][3] = 2 = d[1][1]*(3-1) d[1][2]*(3-2)
d[2][4] = d[1][1]*(4-1) d[1][2]*(4-2) d[1][3]*(4-3)
d[3][3] = 1
d[3][6] = d[2][2]*(6-2)  d[2][3]*(6-3)  d[2][4]*(6-4)  d[2][5]*(6-5)
--------------------------------------------------------------------------
n개의 자연수 합 s

s를 n으로 나눈 값으로만 이루어진 집합
{s/n, s/n, s/n ...}




*/