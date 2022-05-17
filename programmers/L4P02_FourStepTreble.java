// 4단 고음
// https://programmers.co.kr/learn/courses/30/lessons/1831?language=java

import java.util.*;

class Solution {
    public int solution(int n) {
        int answer = 0;
        answer = getCount(n, 0);
        return answer;
    }

    // n을 더하기의 개수만큼 빼며 남은 n과 더하기의 개수를 비교합니다.
    public int getCount(int n, int plus) {
        int count = 0;
        // 남은 n이 3이고 남은 더하기가 2개라면 올바른 문자열이므로 1을 반환합니다.
        if(n == 3 && plus == 2) {
            return 1;
        }
        // 남은 더하기가 없거나, 남은 n이 2인 경우 문자열로 될 수 없는 고음으로 0을 반환합니다.
        else if(n == 3 || n == 2) {
            return 0;
        }

        // 남은 더하기의 개수에 따른 곱하기의 개수의 최소값이 n보다 크다면 불가능합니다.
        // 더하기 개수/2 = 곱하기의 개수, 곱하기 연산의 최소값 = 곱하기를 연달아서 연산하는 경우
        // 3^(plus/2)가 곧 곱하기 연산의 최소값이 됩니다.
        if(Math.pow(3, plus/2) > n) {
            return 0;
        }

        // 남은 n이 3으로 나누어 떨어지면서 더하기의 개수가 2개 이상이라면 고음 한번(*++)을 제거할 수 있습니다.
        if(n % 3 == 0 && plus >= 2) {
            count += getCount(n/3, plus-2);
        }
        // n을 하나씩 줄이며 가능한 경우의 수를 더합니다.
        count += getCount(n-1, plus+1);

        return count;
    }
}

/*

*++

*++(*++) (3x+2)3+2 = 9x+8
*+(*++)+ (3x+1)3+3 = 9x+6
*(*++)++ (3x)3+4 = 9x+4
--------------------------
*++h
*+h+
*h++

n=41
5(h) = 41 x
4(h)+1 = 41 -> h = 10 o
3(h)+2 = 41 -> h = 13 o
--------------------------
시작은 항상 *
끝은 항상 ++
앞에서부터 계산한 결과이므로
결과를 뒤에서부터 계산하는것이 용이

*의 개수 = +의 개수/2
k번 고음을 한다면 *은 k개, +는 2k개

n = 41
n-2 = 39 *count = 1
n-4 = 37 count = 2
n-6 = 35 count = 3



*/