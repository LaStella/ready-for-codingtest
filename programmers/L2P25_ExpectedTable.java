// 예상 대진표
// https://programmers.co.kr/learn/courses/30/lessons/12985?language=java

class Solution
{
    public int solution(int n, int a, int b)
    {
        int answer = 0;
        
        // a와 b를 각각 2로 나눈 올림(반올림) 값이 같으면 서로 같은 라운드에서 만난 것입니다.
        while(a != b) {
            a = (int) Math.ceil((double) a/2);
            b = (int) Math.ceil((double) b/2);
            answer++;
        }

        return answer;
    }
}