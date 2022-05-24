// 사칙연산
// https://programmers.co.kr/learn/courses/30/lessons/1843?language=java

import java.util.*;

class Solution {
    int answer;
    public int solution(String arr[]) {
        answer = Integer.MIN_VALUE;

        
        return answer;
    }
}

/*
숫자 연산자가 번갈아 존재
0번 연산자는 0,1번 숫자
1번 연산자는 1,2번 숫자
2번 연산자는 2,3번 숫자
n번 연산자는 n,n+1번 숫자

연산결과는 n번자리에 넣는다.
숫자와 연산자들을 각각 리스트에 넣고 연산자를 기준으로 dfs
시간초과
-------------------------------------------------
-연산자 뒤의 값이 최소가 되어야 최대값이 나옴
+연산다 뒤의 값이 최대가 되어야 최대값이 나옴
이를 이용하여 DP로 접근

arr[0]~arr[x] + arr[x+1]~arr[end]
arr[0]~arr[x] - arr[x+1]~arr[end] 
의 형태로 나누어서 위의 연산자에 따른 최대값을 계산하여 저장

*/