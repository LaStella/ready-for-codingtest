class Solution {
    public String solution(int[] numbers, String hand) {
        String answer = "";
        //* = 10, 0 = 11, # = 12로 정의하여 위치계산
        int currentL = 10;  //현재 왼손엄지위치
        int currentR = 12;  //현재 오른손엄지위치
        int disL = 0;   //왼손엄지부터의 거리
        int disR = 0;   //오른손엄지부터의 거리

        for(int n : numbers) {
            //1, 4, 7 의 입력시 왼손
            if(n == 1 || n == 4 || n == 7){
                answer += "L";
                currentL = n;
            }
            //3, 6, 9 의 입력시 오른손
            else if(n == 3 || n == 6 || n == 9){
                answer += "R";
                currentR = n;
            }
            //2, 5, 8, 0 의 입력시 거리계산
            else {
                //n이 0일경우 11로 변경
                if(n == 0) {
                    n = 11;
                }
                disL = Math.abs(currentL-n)/3+Math.abs(currentL-n)%3; //왼손엄지로부터의 거리계산 
                disR = Math.abs(currentR-n)/3+Math.abs(currentR-n)%3; //오른손엄지로부터의 거리계산
                if(disL < disR) {
                    answer += "L";
                    currentL = n;
                }
                else if(disL > disR) {
                    answer += "R";
                    currentR = n;
                }
                else {
                    if(hand.equals("left")) {
                        answer += "L";
                        currentL = n;
                    }
                    else{
                        answer += "R";
                        currentR = n;
                    }
                }
            }
        } 
        return answer;
    }
}