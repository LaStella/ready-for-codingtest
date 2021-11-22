import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;

        //ArrayList로 변환
        ArrayList<Integer> lostList = new ArrayList<>();
        ArrayList<Integer> reserveList = new ArrayList<>();

        for(int temp : lost) {
            lostList.add(temp);
        }
        for(int temp : reserve) {
            reserveList.add(temp);
        }
        
        // lost [4, 2], reserve [3, 5] 와 같은 입력이 들어올경우 문제가 되므로 정렬
        lostList.sort(Comparator.naturalOrder());
        reserveList.sort(Comparator.naturalOrder());
        
        //체육복을 도난 당한사람들 중 여벌의 체육복을 가진경우 제거
        for(Iterator<Integer> lostIt = lostList.iterator(); lostIt.hasNext();) {
            int lostNum = lostIt.next();
            for(Iterator<Integer> reserveIt = reserveList.iterator(); reserveIt.hasNext();) {
                int reserveNum = reserveIt.next();
                if(lostNum == reserveNum) {
                    lostIt.remove();
                    reserveIt.remove();
                }
            }
        }
        
        //체육복을 도난당한 학생의 앞번호나 뒷번호가 여벌의 체육복이 있을 경우 빌려줌
        for(Iterator<Integer> it = lostList.iterator(); it.hasNext();) {
            answer--;
            int itNum = it.next();
            if(reserveList.contains(itNum-1)) {
                    answer++;
                    reserveList.remove(reserveList.indexOf(itNum-1));
                    it.remove();
            }
            else if(reserveList.contains(itNum+1)) {
                    answer++;
                    reserveList.remove(reserveList.indexOf(itNum+1));
                    it.remove();
            }
        }
        
        answer += n;
    
        return answer;
    }
}