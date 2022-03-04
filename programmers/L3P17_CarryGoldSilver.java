// 금과 은 운반하기
// https://programmers.co.kr/learn/courses/30/lessons/86053?language=java


class Solution {
    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        long answer = -1;
        int length = g.length;
        
        long start = 0;
        // 금과 은의 양이 최대로 많으며, 편도시간이 최대로 긴 경우가 최대 시간이 됩니다.
        long end = (long)(1e9 * 2 * 1e5 * 2);
        
        while(start <= end) {
            long time = (start + end) / 2;
            long gold_amount = 0;
            long silver_amount = 0;
            long possible_amount = 0;
            for(int i = 0 ; i < length ; i++) {
                // 운반 횟수를 나타냅니다. 주어진 시간 time을 왕복시간으로 나누어 반올림하면 운반 횟수가 나옵니다.
                // 반올림하는 경우는 마지막에 편도로 운반이 가능한 경우를 나타냅니다.
                long count = Math.round((double)time / (t[i] * 2));
                // 트럭이 운반 가능한 총량을 나타냅니다.
                long truck_amount = w[i]*count;
                // 금의 양에 해당 도시가 가지고 있는 전체 금의 양과 트럭이 운반 가능한 양을 비교하여 더합니다.
                // 도시가 가지고 있는 금의 양이 많아도 주어진 시간안에 운반할 수 있는 양은 한계가 있으며
                // 반대로 운반할 수 있는 양이 많아도 도시가 가지고 있는 금의 양은 한계가 있습니다.
                gold_amount += Math.min(g[i], truck_amount);
                // 위와 동일합니다.
                silver_amount += Math.min(s[i], truck_amount);
                // 트럭이 가져올 수 있는 총 금과 은의 양을 나타냅니다.
                // 금을 실은 트럭에 여유가 있다면 은을 실을 수 있습니다.(반대도 가능합니다.)
                possible_amount += Math.min(g[i]+s[i], truck_amount);
            }
            // 주어진 시간이 충분하여 필요한 금과 은의 양을 운반할 수 있다면 시간을 줄입니다.
            if(gold_amount >= a && silver_amount >= b && possible_amount >= a+b) {
                end = time-1;
                answer = time;
            }
            // 주어진 시간이 부족하였다면 시간을 늘립니다.
            else {
                start = time+1;
            }
        }
        
        
        return answer;
    }
}

/*
시간을 이분탐색
주어진 시간동안 운반하는 금, 은의 양을 계산
금, 은의양이 부족할경우 시간을 늘림
충분할 경우 시간을 줄임

ex) 1   2   3
금 300 300 300
은 100 50  100
트 450 100  400
a  700
b  250
편도 가능시간이 주어질때
금 가능 총량 700
은 가능 총량 250
트럭 가능 총량 950
실제 가능 총량 400+100+400 = 900

*/