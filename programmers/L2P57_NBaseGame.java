// n진수 게임
// https://programmers.co.kr/learn/courses/30/lessons/17687?language=java

class Solution {
    public String solution(int n, int t, int m, int p) {
        String answer = "";
        String order = "";
        int number = 0;
        // 미리 구할 숫자의 개수는 t개 입니다. 
        // 한바퀴(m개) 마다 말해야할 숫자는 하나이므로 t바퀴째 까지 말할 숫자를 구하게 됩니다.
        // 모든 사람이 말해야 하는 숫자의 순서를 order라고 한다면 order의 길이가 m*t를 넘어야 미리 구할 숫자의 개수 t개를 알 수 있게됩니다.
        while(order.length() < m*t) {
            order += Integer.toString(number, n).toUpperCase();
            number++;
        }
        
        int index = 0;
        while(answer.length() < t) {
            // index를 m으로 나눈 나머지 +1 이 튜브의 순서 p와 같을때 튜브가 말해야 하는 숫자가 됩니다.
            if((index % m)+1 == p) {
                answer += Character.toString(order.charAt(index));
            }
            index++;
        }

        return answer;
    }
}