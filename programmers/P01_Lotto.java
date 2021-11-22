class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = {};
        int same = 0;
        int unrecog = 0;
        
        for (int i=0; i<lottos.length; i++) {
            if(lottos[i]==0){
                unrecog++;
            }
            else {
                for (int j=0; j<win_nums.length; j++) {
                if(lottos[i]==win_nums[j]) {
                    same++;
                    }
                }
            }
        }

        unrecog = 7-same-unrecog;
        same = 7-same;
        if (same == 7) {
            same = 6;
        }
        if (unrecog == 7) {
            unrecog = 6;
        }

              
        answer = new int[] {unrecog, same};

        return answer;
    }
}