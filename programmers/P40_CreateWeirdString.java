class Solution {
    public String solution(String s) {
        String answer = "";
        // 빈칸도 예외없이 나누기 위해 -1
        String[] strArry = s.split(" ", -1);

        for(int i = 0 ; i < strArry.length ; i++) {
            String temp = "";
            for(int j = 0 ; j < strArry[i].length() ; j++) {
                if(j % 2 != 0) {
                    temp += Character.toLowerCase(strArry[i].charAt(j));
                }
                else {
                    temp += Character.toUpperCase(strArry[i].charAt(j));
                }
            }
            strArry[i] = temp;
        }
        
        answer = String.join(" ", strArry);
        
        return answer;
    }
}