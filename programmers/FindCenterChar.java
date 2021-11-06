class Solution {
    public String solution(String s) {
        String answer = "";

        // 단어 s의 가운데 글자를 반환하는 함수를 만드시오.
        // 단어의 길이가 짝수라면 가운데 두글자를 반환.
        

        // substring(start, end)메소드는 index가 start부터 end-1까지 자르게됨
        // 단어 s의 길이가 홀수일 경우
        // 시작 index는 (길이-1)/2 또는 길이/2가 된다. int형의 나눗셈은 소수점을 버리기때문.
        // 끝 index는 길이/2 + 1이 된다.
        // 길이가 짝수일 경우
        // 시작 index는 (길이-2)/2 또는 (길이-1)/2 이며, 끝 index는 길이/2 + 1이 된다.
        // 따라서 다음과 같은 substring은 홀수, 짝수 상관없이 가운데 글자를 가져올 수 있다.
        
        answer += s.substring((s.length()-1)/2, s.length()/2+1);
        return answer;
    }
}