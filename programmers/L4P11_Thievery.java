// 도둑질
// https://programmers.co.kr/learn/courses/30/lessons/42897?language=java

class Solution {
    public int solution(int[] money) {
        int answer = 0;
        int n = money.length;
        
        int[] first_max = new int[n];
        int[] max = new int[n];
        
        // 첫번째 집을 터는 경우와 그렇지 않은 경우로 나누어서 계산합니다.
        // 첫번째 집을 터는 경우 첫번째 집의 인접한 집은 경보가 울리기 때문에 털 수 없습니다.
        first_max[0] = money[0];
        first_max[1] = first_max[0];
        // 첫번째 집을 털으므로 마지막 집을 털 수 없습니다,(money[n-1])
        for(int i = 2 ; i < n-1 ; i++) {
            first_max[i] = Math.max(first_max[i-1], first_max[i-2]+money[i]);
        }
        
        // 첫번째 집을 털지 않는 경우로 첫번째 집에서 훔치는 돈의 최대값은 0, 두번째 집은 두번째 집의 금액으로 시작합니다.
        max[0] = 0;
        max[1] = money[1];
        // 첫번째 집을 털지 않으므로 마지막 집을 털 수 있습니다.
        for(int i = 2 ; i < n ; i++) {
            max[i] = Math.max(max[i-1], max[i-2]+money[i]);
        }
        
        // 위의 두 경우에서 마지막 집에서 훔칠 수 있는 돈의 최대값을 비교합니다.
        answer = Math.max(first_max[n-2], max[n-1]);
        
        return answer;
    }
}