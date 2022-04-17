// 풍선 터트리기
// https://programmers.co.kr/learn/courses/30/lessons/68646?language=java

class Solution {
    public int solution(int[] a) {
        int answer = a.length;
        
        for(int i = 0 ; i < a.length ; i++) {
            if(checkLeft(i, a) && checkRight(i, a)) {
                answer--;
            }
        }
        
        
        return answer;
    }
    
    public boolean checkLeft(int index, int[] a) {
        for(int i = 0 ; i < index ; i++) {
            if(a[index] > a[i]) return true;
        }
        
        return false;
    }
    public boolean checkRight(int index, int[] a) {
        for(int i = index+1 ; i < a.length ; i++) {
            if(a[index] > a[i]) return true;
        }
        
        return false;
    }
}

/*
임의의 풍선을 선택하였을때
해당 풍선의 좌, 우에 해당 풍선보다 값이 작은 풍선이 존재한다면 해당 풍선은 최후까지 남을 수 없다.
*/