// 교점에 별 만들기
// https://programmers.co.kr/learn/courses/30/lessons/87377?language=java

import java.util.*;

class Solution {
    public String[] solution(int[][] line) {
        String[] answer = {};
        // x와 y의 좌표를 담을 List입니다.
        ArrayList<Integer> xlist = new ArrayList<>();
        ArrayList<Integer> ylist = new ArrayList<>();
        
        // 직선들의 교점을 구합니다. 한 직선의 교점을 구하면 다음 직선은 이전 직선에서의 교점을 구할 필요가 없습니다.
        for(int i = 0 ; i < line.length-1 ; i++) {
            for(int j = i+1 ; j < line.length ; j++) {
                getCrossPoint(line[i], line[j], xlist, ylist);
            }
        }

        // 모든 별(교점 중 정수로 표현되는 좌표)을 포함하는 최소한의 크기로 나타내야 하므로
        // 너비는 x좌표의 최대값-최소값+1, 높이는 y좌표의 최대값-최소값+1입니다.
        int width = Collections.max(xlist)-Collections.min(xlist)+1;
        int height = Collections.max(ylist)-Collections.min(ylist)+1;
        int xmin = Collections.min(xlist);
        int ymax = Collections.max(ylist);
        
        // String배열 answer의 행을 y좌표, 문자열의 위치를 x좌표로 나타내야합니다.
        // answer의 첫 글자가 0, 0이어야 하므로 교점들을 x축과 y축을 x의 최소값, y의 최대값만큼 움직여줍니다.
        // y축으로 y의 최대값을 빼주면 음수가 나오므로 -을 곱하여 양수로 바꿔줍니다. (ylist.get(i)-ymax)*-1
        for(int i = 0 ; i < xlist.size() ; i++) {
            xlist.set(i, xlist.get(i)-xmin);
            ylist.set(i, ymax-ylist.get(i));
        }
        
        answer = new String[height];

        // 너비만큼 "." 으로 채웁니다.
        for(int i = 0 ; i < answer.length ; i++) {
            answer[i] = ".".repeat(width);
        }

        // "."으로 채워진 격자판에 교점의 위치에 해당하는 지점을 "*"로 바꿔줍니다.
        for(int i = 0 ; i < xlist.size() ; i++) {
            StringBuilder a = new StringBuilder(answer[ylist.get(i)]);
            a.setCharAt(xlist.get(i), '*');
            answer[Math.abs(ylist.get(i))] = a.toString();
        }
        
        return answer;
    }
    
    // 교점을 구하는 함수입니다.
    public void getCrossPoint(int[] line1, int[] line2, ArrayList<Integer> xlist, ArrayList<Integer> ylist) {
        long a1 = line1[0];
        long b1 = line1[1];
        long c1 = line1[2];
        long a2 = line2[0];
        long b2 = line2[1];
        long c2 = line2[2];
        
        long delta = a1 * b2 - a2 * b1;
        long x = b1 * c2 - b2 * c1;
        long y = a2 * c1 - a1 * c2;
        // delta가 0인 경우 두 직선이 평행하므로 교점이 없습니다.
        if(delta == 0) 
            return ;
        // delta로 나누어 떨어지지 않는다면 정수가 아닙니다.
        if(x % delta != 0 || y % delta != 0) {
            return ;
        }
        else {
            xlist.add((int)(x / delta));
            ylist.add((int)(y / delta));
        }
    }
}