// 카펫
// https://programmers.co.kr/learn/courses/30/lessons/42842?language=java

class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        
        // 근의 공식으로 해결하는 방법입니다.
        // ax^2+bx+c = 0 (단 a != 0)
        int b = -(brown+4)/2;
        int c = yellow+brown;
        answer[0] = (int) (-b+Math.sqrt(b*b-4*c))/2;
        answer[1] = (int) (-b-Math.sqrt(b*b-4*c))/2;

        // for문으로 해결하는 방법입니다.
        // int xy = brown+yellow;
        // for(int i = xy ; i > 0 ; i--) {
        //     if(xy % i == 0) {
        //         int x = i;
        //         int y = xy/i;
        //         if(yellow == (x-2)*(y-2)) {
        //             answer[0] = x;
        //             answer[1] = y;
        //             break;
        //         }
        //     }
        // }

        return answer;
    }
}


/*
가로 x 세로 y
yellow = (x-2)(y-2)
       = xy-(2x+2y)+4
brown = 2(x-2)+2(y-2)+4
      = 2x-4+2y-4+4
      = 2x+2y-4
2x+2y = brown+4

yellow = xy-(brown+4)+4
       = xy-brown
       
yellow+brown = xy
------위의 식으로부터 자연수 x, y쌍을 for문으로 찾을 수 있습니다.

brown = 2x+2y-4
2y = -2x+brown+4
y = -x + (brown+4)/2

yl+br = -x^2+(br+4)/2x
0 = -x^2+(brown+4)/2x-(yellow+brown)
0 = x^2-(brown+4)/2x+(yellow+brown)
int b = -(brown+4)/2
int c = yellow+brown
------ax^2+bx+c 형태이므로 근의 공식으로 답을 찾을 수 있습니다.
x = -b+Math.sqrt(b*b+4(yellow+brown))/2
*/