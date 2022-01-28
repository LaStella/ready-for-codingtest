// 다음 큰 숫자
// https://programmers.co.kr/learn/courses/30/lessons/12911?language=java

class Solution {
    public int solution(int n) {
        int answer = 0;
        int count = countOne(n);
        while(true) {
            n++;
            if(count == countOne(n)) {
                break;
            }
        }
        
        answer = n;
        
        return answer;
    }
    
    
    public int countOne(int i) {
        int count = 0;
        String s = Integer.toBinaryString(i).replaceAll("0", "");
        
        return s.length();
    }
}


/*
1   2   4   8
1   10  100 1000

3   5   6   9
11  101 110 1001


7     11    13      14
111   1011  1101    1110



1001110     끝이 0일때, 왼쪽이 0인 1을 찾음, 왼쪽을 1로 바꾸고 남은 1들을 끝에서부터 채움
1010011

11100       끝이 0일때, 왼쪽이 0인 1을 못찾음, 0의 갯수+1, 끝에서부터 남은 1들을 채움
100011

1011100
1100011

1010101     끝이 1일때, 왼쪽이 0인 1을 찾음, 왼쪽을 1로 바꾸고 현재위치를 0으로바꿈
1010110

1111        끝이 1일때, 왼쪽이 0인 1을 못찾음, 0의 갯수+1, 끝에서부터 남은 1들을 채움
10111

*/