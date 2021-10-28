import java.util.ArrayList;

class Solution {
    public int solution(int[] nums) {
        int answer = 0;
        ArrayList<Integer> myPocket = new ArrayList<>();
        
        //nums에서 종류별로 하나씩만 뽑아 저장한다.
        for(int p : nums) {
            if(!myPocket.contains(p)) {
                myPocket.add(p);
                //하나씩만 저장하여도 최대로 선택할 수 있는 폰켓몬은 N/2마리 이므로
                if(myPocket.size()==nums.length/2) {
                    break;
                }
            }
        }
        
        //최종적으로 myPocket의 크기가 N/2보다 작더라도 
        //남은 폰켓몬은 결국 중복되는 폰켓몬들을 고르게 되므로
        //return값인 폰켓몬 종류 번호의 갯수에는 변함이 없다.
        answer = myPocket.size();        
        
        return answer;
    }
}