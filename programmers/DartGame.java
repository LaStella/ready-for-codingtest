import java.lang.Math;

class Solution {
    public int solution(String dartResult) {
        int answer = 0;
        String[] dartArry;
        int[] resultArry = new int[4];
        int arryCnt = 0;
        
        dartResult = dartResult.replaceAll("\\d+", "&$0");
        dartArry = dartResult.split("&");
        
        for(String s : dartArry) {
            String numb = "";
            for(int i = 0 ; i < s.length() ; i++) {
                if(s.charAt(i) == 'S') {
                    resultArry[arryCnt] = (int) Math.pow(Integer.parseInt(numb), 1);
                }
                else if(s.charAt(i) == 'D') {
                    resultArry[arryCnt] = (int) Math.pow(Integer.parseInt(numb), 2);
                }
                else if(s.charAt(i) == 'T') {
                    resultArry[arryCnt] = (int) Math.pow(Integer.parseInt(numb), 3);
                }
                else if(s.charAt(i) == '*') {
                    resultArry[arryCnt] *= 2;
                    resultArry[arryCnt-1] *= 2;
                }
                else if(s.charAt(i) == '#') {
                    resultArry[arryCnt] *= -1;
                }
                else {
                    numb += s.charAt(i);
                }
            }
            arryCnt++;
        }
        
        for(int i = 1 ; i < resultArry.length ; i++) {
            answer += resultArry[i];
        }
        
        return answer;
    }
}