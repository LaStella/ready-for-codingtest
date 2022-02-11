// N으로 표현
// https://programmers.co.kr/learn/courses/30/lessons/42895?language=java

class Solution {
    // 재귀함수를 통해 결과가 나오므로 전역변수를 사용하여 결과값을 저장하기 편하게 하였습니다.
    int answer = -1;
    public int solution(int N, int number) {
        dfs(N, number, 0, 0);
        return answer;
    }

    // 깊이 우선 탐색 알고리즘을 사용하여 사칙연산을 수행하는 함수입니다.
    // count = N이 사용된 횟수, result = N을 가지고 사칙연산을 수행한 결과값을 말합니다.
    public void dfs(int N, int number, int count, int result) {
        // 사칙연산 결과값이 number와 같다면 사용된 N의 개수 count를 answer로 저장합니다.
        // answer의 초기값은 -1이므로 Math.min을 사용하면 초기값으로 유지되기 때문에 (count는 양수이기에) 초기값을 예외로 처리합니다.
        if(result == number) {
            if(answer == -1) {
                answer = count;
            }
            else {
                answer = Math.min(answer, count);
            }
        }
        // 사칙연산에서 N을 8번 초과하여 사용하였다면 중단합니다.
        // answer의 초기값이 -1이므로 N을 1번부터 8번까지 사용하는 모든 경우에서 number와 같은 값이 안나온다면 answer는 -1로 유지됩니다.
        else if(count > 8) {
            return;
        }
        // 위의 두 경우(정답이 나오거나, N을 8번 초과하여 사용하거나)가 아닌 경우 사칙연산을 계속하게 됩니다.
        else {
            // 사칙연산을 하게될 피연산자를 나타냅니다.
            int operand = N;
            // 피연산자는 최소 1번부터 8-count번까지 사용할 수 있습니다.
            // ex)count가 0일 경우 피연산자는 N, NN, NNN, ..., NNNNNNNN까지 사칙연산의 피연산자로 사용할 수 있습니다.
            for(int i = 1 ; i <= 8-count ; i++) {
                dfs(N, number, count+i, result+operand);
                dfs(N, number, count+i, result-operand);
                dfs(N, number, count+i, result*operand);
                dfs(N, number, count+i, result/operand);
                // 피연산자는 붙이는 연산입니다. N이 n번 반복될 경우 = (N이 n-1번 반복될 경우)*10+N 입니다.
                operand = operand*10+N;
            }
        }
    }
}


/*
1.
5+5*5+5-5/5
dfs알고리즘을 사용한다면 괄호연산을 하는 것과 동일
사칙연산의 값이 바로 result로 계산 = 괄호를 먼저 연산하는것과 동일한 연산

2.
5       5
55      5*10+5
555     5*100+5*10+5 = (5*10+5)*10+5
5555    5*1000+5*100+5*10+5 = ((5*10+5)*10+5)*10+5
5가 n번 반복되는 경우 = (5가 n-1번 반복되는 경우)*10+5

3.
피연산자를 붙여쓸경우는 8번까지 가능하므로 (8보다 클경우 -1return)
깊이우선탐색시 결과값에 사칙연산할 피연산자는 N부터 시작하여 NNNNNNNN까지 사칙연산할수있다.
현재 count가 0이라면 8까지, 1이라면 7까지 ... n이라면 8-n까지
*/

