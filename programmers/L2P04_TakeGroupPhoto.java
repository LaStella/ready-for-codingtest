class Solution {
    public int solution(int n, String[] data) {
        int answer = 0;
        String[] person = {"A", "C", "F", "J", "M", "N", "R", "T"};
        String seat = "";
        boolean[] visited = new boolean[8];
        
        answer = getPermutationCount(person, seat, visited, data);

        return answer;
    }
    
    public int getPermutationCount(String[] person, String seat, boolean[] visited, String[] data) {
        int count = 0;
        if(seat.length() == 8) { // 8명의 프렌즈가 모두 자리에 앉은 경우
            if(isCheckData(data, seat)) { // 해당 순열이 조건에 맞는지 판별
                count++;
            }
        }
        // 8명의 프렌즈들의 순열을 구한다.
        // A는 0번, C는 1번 과 같이 각 프렌즈들은 번호가 있으며
        // 프렌즈가 순열에 들어갈 경우 visited배열의 해당하는 번호가 true가 된다.
        String temp = seat; // 프렌즈가 들어가기전 자리를 저장
        for(int i = 0 ; i < 8 ; i++) {
            if(!visited[i]) {
                visited[i] = true;
                seat += person[i]; // 자리에 프렌즈를 배치
                count += getPermutationCount(person, seat, visited, data); // 다음 자리를 채우기 위해 다시 순열함수에 들어가게된다.
                seat = temp; // 프렌즈를 배치하기 전의 자리로 되돌림
                visited[i] = false; // 프렌즈를 배치하기 전으로 돌렸으므로 false가 된다.
            }
        }
        
        return count;
    }
    
    public boolean isCheckData(String[] data, String seat) {
        int a, b, interval;
        char operator;
        for(String s : data) {
            // 입력형식에 따라 각 프렌즈들의 글자에 해당하는 자리의 위치를 찾아 a, b에 저장한다.
            // 연산자는 operator에 저장
            // 간격은 interval 저장하며 1을 더해주는 것은 a-b의 절대값이 1만큼 더 크기때문이다.
            a = seat.indexOf(String.valueOf(s.charAt(0)));
            b = seat.indexOf(String.valueOf(s.charAt(2)));
            operator = s.charAt(3);
            interval = s.charAt(4) - '0' + 1;
            if(operator == '=') {
                if(Math.abs(a-b) != interval) {
                    return false;                    
                }
            }
            else if(operator == '<') {
                if(Math.abs(a-b) >= interval) {
                    return false;                    
                }
            }
            else {
                if(Math.abs(a-b) <= interval) {
                    return false;                    
                }
            }
        }
        return true;     
    }
    
}