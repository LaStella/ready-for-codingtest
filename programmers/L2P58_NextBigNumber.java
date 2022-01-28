// 다음 큰 숫자
// https://programmers.co.kr/learn/courses/30/lessons/12911?language=java

class Solution {
    public int solution(int n) {
        int answer = 0;
        // bitCount메소드는 Integer형 숫자의 비트데이터에서 1의 개수를 return하는 메소드입니다.
        int count = Integer.bitCount(n);
        
        // n을 1씩 증가시키며 1의 개수가 같은 n을 찾습니다.
        while(true) {
            n++;
            if(count == Integer.bitCount(n)) {
                break;
            }
        }
        
        answer = n;
        
        return answer;
    }
}