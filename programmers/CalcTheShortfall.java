class Solution {
    public long solution(int price, int money, int count) {
        long answer = -1;
        long totalPayment = 0;

        // 놀이기구의 이용로는 n번 째 탈 이용한다면 원래 이용료의의 n배 (ex. 처음 100일 경우 2번째 200, 3번째 300)
        // 놀이기구 이용료 price, 소지한 금액 money, 타고싶은 횟수 count 일때
        // 부족한 금액을 return하라.

        // 제한사항
        // 놀이기구의 이용료 price : 1 ≤ price ≤ 2,500, price는 자연수
        // 처음 가지고 있던 금액 money : 1 ≤ money ≤ 1,000,000,000, money는 자연수
        // 놀이기구의 이용 횟수 count : 1 ≤ count ≤ 2,500, count는 자연수

        
        for(int i = 1 ; i <= count ; i++) {
            totalPayment += price*i;
        }
        
        answer = totalPayment - money;
        
        return (answer < 0) ? 0 : answer;
    }
}