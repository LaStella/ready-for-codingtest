// 거스름돈
// https://programmers.co.kr/learn/courses/30/lessons/12907?language=java

import java.util.*;

class Solution {
    public int solution(int n, int[] money) {
        int answer = 0;
        
        long[] d = new long[n+1];
        Arrays.sort(money);
        
        // money[0]으로 거스름돈을 줄 수 있는지 확인합니다.
        for(int i = 0 ; i <= n ; i++) {
            if(i%money[0] == 0) d[i] = 1;
        }
        
        // money[i]로 거스름돈을 줄 수 있는지 확인합니다.
        // d[j]를 j원을 거스름돈으로 주는 경우의 수 라고 할때, 
        // j원을 주는 경우의 수는 j원에서 동전(money[i])를 뺀 거스름돈을 주는 경우의 수 들의 합이 됩니다.
        // d[j] = d[j] + d[j-money[i]] (i = 0인 경우는 위에서 초기화 했으므로, i는 1부터 money.length-1까지)
        // j-money[i] 가 음수가 된다면 money[i]로 거스름돈을 지불할 수 없는 경우입니다.
        // 따라서, j는 money[i]보다 같거나 큰 금액이어야 합니다.
        for(int i = 1 ; i < money.length ; i++) {
            for(int j = money[i] ; j <= n ; j++) {
                d[j] += d[j-money[i]];
            }
        }
        
        answer = (int)(d[n]%1000000007);
        
        return answer;
    }
}

/*
동전 1,2,5 (money[0]~money[2])
d[n]을 n원을 주는 방법의 수 라고하면
d[n] = d[n-money[i]]
d[5] = d[5-money[0]]+d[5-money[1]]+d[5-money[2]]
        = d[4]+d[3]+d[0]
d[4] = d[3]+d[2]+d[-1] 음수발생

초기화를 위한 기준이 필요
money[0]을 기준으로 사용한다
d[n] = (n%money[0] == 0) d[n] = 1
money[0]으로만 지불이 가능한 경우 1로 초기화, 그렇지 않은 경우 0

money[0]으로만 지불 가능한 경우를 계산했으므로 money[1]~money[2]까지 추가하며 계산한다
n원을 지불하는 경우는 기존 money[0]으로 지불하는 방법 + n-money[i]원을 지불하는 방법이 된다.
d[n] = d[n] + d[n-money[i]]
d[1] = d[1] + d[1-money[1]]
d[1] = d[1] + d[1-money[2]]

d[2] = d[2] + d[2-money[1]]
d[2] = d[2] + d[2-money[2]]
n-money[i]가 음수가 되는 경우는 money[i]동전으로 거스름돈을 줄 수 없는 경우이다.
따라서 음수가 되는 경우를 제외하기 위해 n은 항상 money[i]보다 같거나 커야한다.
즉 money[i] ~ n원까지 계산하게된다.
for(money[i]) i=1부터
for(j = money[i] ; j <= n ; j++)

*/