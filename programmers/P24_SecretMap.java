class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        
        // 지도는 한 변의 길이가 n인 정사각형 배열 형태로, 각 칸은 "공백"(" ") 또는 "벽"("#") 두 종류로 이루어져 있다.
        // 전체 지도는 두 장의 지도를 겹쳐서 얻을 수 있다.
        // 지도 1 또는 지도 2 중 어느 하나라도 벽인 부분은 전체 지도에서도 벽이다. 지도 1과 지도 2에서 모두 공백인 부분은 전체 지도에서도 공백이다.
        // "지도 1"과 "지도 2"는 각각 정수 배열로 암호화되어 있다.
        // 암호화된 배열은 지도의 각 가로줄에서 벽 부분을 1, 공백 부분을 0으로 부호화했을 때 얻어지는 이진수에 해당하는 값의 배열이다.

        // 입력으로 지도의 한 변 크기 n 과 2개의 정수 배열 arr1, arr2가 들어온다.
        // 원래의 비밀지도를 해독하여 '#', 공백으로 구성된 문자열 배열로 출력하라.

        for(int i = 0 ; i < n ; i++) {
            //각 배열의 이진수 값을 OR연산 하여 answer배열에 저장
            answer[i] = Integer.toBinaryString(arr1[i] | arr2[i]);

            //이때 01001과 같이 앞자리가 0인경우 사라지게되므로
            //부족한 자릿수만큼 문자열의 앞에 "0"을 채워준다.
            if(answer[i].length() != n) {
                answer[i] = "0".repeat(n - answer[i].length()) + answer[i];
            }
        }
        
        for(int i = 0 ; i < n ; i++) {
            //완성된 이진수 배열 지도를 0은 공백으로, 1은 #으로 치환해준다.
            answer[i] = answer[i].replaceAll("0", " ");
            answer[i] = answer[i].replaceAll("1", "#");
        }

        return answer;
    }
}