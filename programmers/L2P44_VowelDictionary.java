// 모음 사전
// https://programmers.co.kr/learn/courses/30/lessons/84512?language=java

class Solution {
    public int solution(String word) {
        int answer = 0;
        for(int i = 0 ; i < word.length() ; i++) {
            answer += getValue(word.charAt(i))*getWeight(i)+1;
        }
        return answer;
    }
    
    // 글자의 위치별 가중치를 구하는 함수입니다.
    // 마지막자리는 모음순(AIEOU)으로 바뀔때 마다 1만큼 증가하는 가중치를 가지고 있습니다.
    // 4번째 자리는 6만큼(1*5 + 1) 증가하는 가중치를 가지고 있습니다.
    // 3번째 자린은 31만큼(6*5 + 1) 증가하는 가중치를 가지고 있습니다.
    // n번째 자리는 (n-1의 가중치)*5+1 증가하는 가중치를 가지게 됩니다.
    public int getWeight(int i) {
        int weight = 1;
        int repeat = 5-(i+1);
        while(repeat>0) {
            weight *= 5;
            weight++;
            repeat--;
        }
        return weight;
    }

    // 글자의 순서를 가져오는 함수입니다.
    public int getValue(char c) {
        switch(c) {
            case 'A': return 0;
            case 'E': return 1;
            case 'I': return 2;
            case 'O': return 3;
        }
        return 4;
    }
}