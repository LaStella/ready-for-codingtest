class Solution {
    public String solution(String new_id) {
        String answer = "";
        
        //1단계
        new_id = new_id.toLowerCase();
        
        //2단계
        new_id = new_id.replaceAll("[^0-9a-z-_.]", "");
        
        //3단계
        new_id = new_id.replaceAll("\\.{2,}", "."); // \\. 이 마침표를 의미
        
        //4단계        
        new_id = new_id.replaceAll("^\\.|\\.$", ""); // ^\\. 마침표로 시작하거나 \\.$ 마침표로 끝나거나
        
        //5단계
        if(new_id.length()==0) {
            new_id = "a";
        }
        
        //6단계
        if(new_id.length()>=16) {
            new_id = new_id.substring(0,15);    //substring으로 index 0부터 14까지 자른다. (15까지 자르는게 아니다.)
        }
        new_id = new_id.replaceAll("\\.$", "");
        
        //7단계
        while(new_id.length()<=2) {
            new_id+=new_id.charAt(new_id.length()-1);
        }
        answer = new_id;
        
        return answer;
    }
}