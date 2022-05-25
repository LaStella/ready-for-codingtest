// 사칙연산
// https://programmers.co.kr/learn/courses/30/lessons/1843?language=java

import java.util.*;

class Solution {
    int[][] max;
    int[][] min;
    // 제한 사항(101개 이하, 1000이하)에 따라 최대값이 될 수 없는 유효하지 않는 수입니다.
    int invalid_number = 102*1001;
    public int solution(String arr[]) {
        int answer = -1;
        
        int n = arr.length;
        // arr[start] ~ arr[end] 까지 최대값, 최소값을 저장하는 배열입니다.
        // ex) max[2][6]는 arr[2]~arr[6]까지 연산 순서에 따라 구할 수 있는 최대값을 나타냅니다.
        max = new int[n][n];
        min = new int[n][n];
        
        // 최대값과 최소값이 저장되는 배열을 유효하지 않는 수로 초기화합니다.
        for(int i = 0 ; i < n ; i++) {
            Arrays.fill(max[i], invalid_number);
            Arrays.fill(min[i], invalid_number);
        }
        
        // arr배열의 처음(0)부터 끝(n-1)까지 연산순서에 따른 최대값을 가져옵니다.
        answer = getMax(0, n-1, arr);
        
        return answer;
    }
    
    // arr[start]부터 arr[end]까지 연산 순서에 따른 최대값을 구하는 함수입니다.
    public int getMax(int start, int end, String[] arr) {
        // 시작지점과 종료지점이 동일하면 해당자리에 있는 숫자를 반환합니다.
        if(start == end) {
            return max[start][end] = Integer.parseInt(arr[start]);
        }
        
        // 이전에 찾은 값이라면(초기값이 아니므로) 찾았던 값을 그대로 반환합니다.
        if(max[start][end] != invalid_number) {
            return max[start][end];
        }
        
        int result = Integer.MIN_VALUE;
        
        // arr[start] ~ arr[end]의 범위는 arr[start] ~ arr[mid], arr[mid+2] ~ arr[end] 두 구간으로 나누어 계산할 수 있습니다.
        // arr배열의 짝수엔은 숫자가, 홀수에는 연산자가 들어있으며, start, end, mid는 모두 짝수로 숫자입니다.
        for(int mid = start ; mid < end ; mid+=2) {
            // 나누어진 두 구간의 합이 최대값이라면 두 구간 모두 최대값이어야 합 또한 최대값이 될 수 있습니다.
            if(arr[mid+1].equals("+")) {
                result = Math.max(result, getMax(start, mid, arr)+getMax(mid+2, end, arr));
            }
            // 나누어진 두 구간의 차가 최대값이라면 앞구간의 최대값에서 뒷구간의 최소값을 빼는것이 최대값이 될 수 있습니다.
            else {
                result = Math.max(result, getMax(start, mid, arr)-getMin(mid+2, end, arr));
            }
        }
        
        return max[start][end] = result;
    }
    
    // arr[start]부터 arr[end]까지 연산 순서에 따른 최소값을 구하는 함수입니다.
    public int getMin(int start, int end, String[] arr) {
        if(start == end) {
            return min[start][end] = Integer.parseInt(arr[start]);
        }
        
        if(min[start][end] != invalid_number) {
            return min[start][end];
        }
        
        int result = Integer.MAX_VALUE;
        
        for(int mid = start ; mid < end ; mid+=2) {
            // 나누어진 두 구간의 합이 최소값이라면 두 구간 모두 최소값이어야 합 또한 최소값이 될 수 있습니다.
            if(arr[mid+1].equals("+")) {
                result = Math.min(result, getMin(start, mid, arr)+getMin(mid+2, end, arr));
            }
            // 나누어진 두 구간의 차가 최소값이라면 앞구간의 최소값에서 뒷구간의 최대값을 빼는것이 최소값이 될 수 있습니다.
            else {
                result = Math.min(result, getMin(start, mid, arr)-getMax(mid+2, end, arr));
            }
        }
        
        return min[start][end] = result;
    }
}

/*
숫자 연산자가 번갈아 존재
0번 연산자는 0,1번 숫자
1번 연산자는 1,2번 숫자
2번 연산자는 2,3번 숫자
n번 연산자는 n,n+1번 숫자

연산결과는 n번자리에 넣는다.
숫자와 연산자들을 각각 리스트에 넣고 연산자를 기준으로 dfs
시간초과
-------------------------------------------------
-연산자 뒤의 값이 최소가 되어야 최대값이 나옴
+연산자 뒤의 값이 최대가 되어야 최대값이 나옴
이를 이용하여 DP로 접근

arr[0]~arr[x] + arr[x+1]~arr[end]
arr[0]~arr[x] - arr[x+1]~arr[end] 
의 형태로 나누어서 위의 연산자에 따른 최대값을 계산하여 저장

max배열과 min배열로 나누어 저장
max[a][b] 를 arr[a]~arr[b]까지 최대
min[a][b] 를 arr[a]~arr[b]까지 최소
ex) 1+2-3+4
1 + 2-3+4 = 1+max[2][6]
max[2][6] = 2-min[4][6]
min[4][6] = 3-4

1+2-3-4
1+최대
1+2-최소
1+2-3-최소


1-3+5-8
1-min[2][6]
min[2][6] = 3+min[4][6]

*/