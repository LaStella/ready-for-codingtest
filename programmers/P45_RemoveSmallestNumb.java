import java.util.*;

class Solution {
    public int[] solution(int[] arr) {
        int[] answer = {};
        ArrayList<Integer> arrList = new ArrayList<>();
        
        if(arr.length == 1) {
            arr[0] = -1;
            answer = arr;    
        }
        else {
            // List로 만들어 최소값 삭제
            for(int i : arr) {
                arrList.add(i);
            }
            arrList.remove(Collections.min(arrList));

            // List를 배열로 변환
            answer = new int[arrList.size()];
            int i = 0;
            for(int temp : arrList) {
                answer[i++] = temp;
            }
            // List를 사용하고 stream을 이용하여 List -> 배열로 변환
            // answer = arrList.stream().mapToInt(i -> i).toArray();
            // List를 사용하고 stream을 이용하여 최소값 삭제, List -> 배열로 변환
            // answer = arrList.stream().filter(i -> i > arrList.stream().min(Integer::compare).orElse(-1)).mapToInt(i -> i).toArray();
            // List를 사용하지 않고 stream을 이용하여 최소값 삭제
            // answer = Arrays.stream(arr).filter(i -> i > Arrays.stream(arr).min().getAsInt()).toArray();
            
        }        
        
        return answer;
    }
}