import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, W;
    static int[][] e;
    static int[] inside, outside, bothside;
    static int inf = Integer.MAX_VALUE/2;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while(T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            // 원타곤에 배치된 적의 수를 나타냅니다.
            // e[0][i] : i번째 열의 안쪽 원에 배치된 적의 수
            // e[1][i] : i번째 열의 바깥쪽 원에 배치된 적의 수
            e = new int[2][N+1];

            for (int i = 0 ; i <= 1 ; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 1 ; j <= N ; j++)
                    e[i][j] = Integer.parseInt(st.nextToken());
            }

            // 각각 원의 안쪽, 바깥쪽, 양쪽을 점령하는데 필요한 최소 소대의 수를 나타냅니다.
            // 편의상 특수 소대를 구역에 투입한 경우를 점령하는 것으로 표현합니다.
            // inside[i] : 0 ~ i-1 열의 양쪽 모두 점령하고 i번 열의 안쪽을 점령하는데 필요한 최소 소대의 수
            // outside[i] : 0 ~ i-1 열의 양쪽 모두 점령하고 i번 열의 바깥쪽을 점령하는데 필요한 최소 소대의 수
            // bothside[i] : 0 ~ i 열의 양쪽 모두 점령하는데 필요한 최소 소대의 수
            inside = new int[N+1];
            outside = new int[N+1];
            bothside = new int[N+1];

            // 문제에서 요구하는 최소 소대의 수가 저장됩니다.
            // 나올 수 없는 큰 값으로 초기화합니다.
            int answer = inf;

            // case 1. 첫번째열과 마지막 열을 고려하지 않는 경우
            // 시작 초기값은 안쪽과 바깥쪽을 각각 점령하는데 1개 소대의 투입이 필요합니다.
            // 양쪽을 점령하는데 필요한 소대는 양쪽의 적 수가 W이하 라면 1개의 소대로 점령이 가능하며 아닌 경우 2개의 소대로 점령해야합니다.
            inside[1] = 1;
            outside[1] = 1;
            bothside[1] = e[0][1]+e[1][1] <= W ? 1 : 2;
            calc();

            answer = Math.min(answer, bothside[N]);

            // N이 1인 경우 case1의 답이 정답입니다.
            if (N != 1) {
                // calc함수에서 case 3-4를 계산하는 경우 존재할 수 없는 bothside[0]에 접근하므로 이 경우 최소 값이 되지 않도록 나올수 없는 최대값으로 초기화합니다.
                bothside[0] = inf;

                // case 2. 안쪽 원의 첫번째열과 마지막열의 적 수가 W이하 라면 한 번의 투입으로 점령이 가능합니다.
                if (e[0][1]+e[0][N] <= W) {
                    inside[1] = inf;
                    outside[1] = 1;
                    bothside[1] = 1;
                    calc();

                    // 안쪽 원의 첫번째열과 마지막열을 점령(투입 소대 수 +1)하였으므로 마지막열의 바깥쪽 원을 점령하는데 필요한 최소 소대의 수를 구합니다.
                    answer = Math.min(answer, outside[N]+1);
                }

                // case 3. 바깥쪽 원의 첫번째열과 마지막열의 적 수가 W이하 라면 한 번의 투입으로 점령이 가능합니다.
                if (e[1][1]+e[1][N] <= W) {
                    inside[1] = 1;
                    outside[1] = inf;
                    bothside[1] = 1;
                    calc();

                    // 위와 마찬가지로 마지막열의 안쪽 원을 점령하는데 필요한 최소 소대의 수에 +1(첫번째열과 마지막열의 바깥쪽을 점령하는 소대)합니다.
                    answer = Math.min(answer, inside[N]+1);
                }

                // case 4. 안쪽 원과 바깥쪽 원 모두 첫번째열과 마지막열의 적 수가 W이하 라면 두 번의 투입으로 점령이 가능합니다.
                if (e[0][1]+e[0][N] <= W && e[1][1]+e[1][N] <= W) {
                    inside[1] = inf;
                    outside[1] = inf;
                    bothside[1] = 0;
                    calc();

                    // 마지막 전(N-2)까지 모두 점령하는데 필요한 최소 소대의 수에 +2합니다.
                    answer = Math.min(answer, bothside[N-1]+2);
                }
            }

            System.out.println(answer);
        }
    }

    // 원의 안쪽, 바깥쪽, 양쪽을 점령하는데 필요한 최소 소대의 수를 계산하는 함수입니다.
    public static void calc() {
        for (int i = 2 ; i <= N ; i++) {
            // case 1-1. i번 안쪽 구역까지 점령하는데 필요한 최소 소대수
            inside[i] = bothside[i-1]+1;

            // case 1-2. i번 안쪽 구역의 붙어있는 두 구역의 적들이 W보다 작다면 한번의 침투로 가능합니다. (인접한 구역으로 한번 더 침투가 가능하므로)
            if (e[0][i]+e[0][i-1] <= W) {
                inside[i] = Math.min(inside[i], outside[i-1]+1);
            }

            // case 2-1. i번 바깥쪽 구역까지 점령하는데 필요한 최소 소대수
            outside[i] = bothside[i-1]+1;

            // case 2-2. 위와 마찬가지로 인접한 두 구역 적들이 W보다 작아 한번의 침투로 가능합니다.
            if (e[1][i]+e[1][i-1] <= W) {
                outside[i] = Math.min(outside[i], inside[i-1]+1);
            }

            // case 3-1, 3-2. i번 안쪽과 바깥쪽까지 모두 점령하는데 필요한 최소 소대수
            bothside[i] = Math.min(inside[i]+1, outside[i]+1);

            // case 3-3. i번 구역의 안쪽과 바깥쪽 적들이 W보다 작다면 한번의 침투로 가능합니다.
            if (e[0][i]+e[1][i] <= W) {
                bothside[i] = Math.min(bothside[i], bothside[i-1]+1);
            }

            // case 3-4. case 1-2와 case 2-2가 동시에 존재하여 i-2번째 구역까지 점령한 소대수에서 2개 소대를 더하면 i번째 구역까지 점령이 가능합니다.
            if (e[0][i]+e[0][i-1] <= W && e[1][i]+e[1][i-1] <= W) {
                bothside[i] = Math.min(bothside[i], bothside[i-2]+2);
            }
        }
    }
}

/*
도넛 형태로 구성된 건물은 2*N 형태의 배열로 표현합니다.
e[0]은 안쪽 원을, e[1]은 바깥쪽 원을 나타냅니다.

i번째 열에서 안쪽과 바깥쪽 중 한쪽만 특수소대를 침투시키거나 둘 모두 침투하는 경우 총 3가지 경우를 고려해야합니다.
3가지 경우를 각각 inside, outside, bothside로 표현합니다.
inside[i] : i번째 열의 안쪽 원을 점령하는데 드는 최소 특수소대의 수
outside[i] : i번째 열의 바깥쪽 원을 점령하는데 드는 최소 특수소대의 수
bothside[i] : i번째 열을 점령하는데 드는 최소 특수소대의 수

1. 안쪽을 침투하는 경우(inside[i])
    1-1. i번째 열의 안쪽을 침투한다면 i-1번째 열에서 안쪽과 바깥쪽 모두 침투한 상황에서 e[0][i]를 침투하는 방법이 있습니다.
         따라서, inside[i] = bothside[i-1]+1이 됩니다.
    1-2. e[0][i]와 e[0][i-1]의 합이 특수 소대원의 수(W)보다 작다면 한번 투입으로 두 구역을 침투할 수 있습니다.
         즉, i-1번째 열에서 바깥쪽을 점령하는데 드는 최소 특수소대의 수에 1을 더하는 결과가 됩니다.
         if((e[0][i] + e[0][i-1]) <= W) inside[i] = outside[i-1]+1
    따라서 (e[0][i] + e[0][i-1]) <= W 인 경우 위 두 경우 중 더 작은 값이 inside[i]가 됩니다.

2. 바깥쪽을 침투하는 경우(outside[i])
    2-1. 1-1과 유사하며 outside[i] = bothside[i-1]+1 입니다.
    2-2. if((e[1][i] + e[1][i-1]) <= W) outside[i] = inside[i-1]+1
    (e[1][i] + e[1][i-1]) <= W 인 경우 위 두 경우 중 더 작은 값이 outside[i]가 됩니다.

3. 양쪽을 침투하는 경우(bothside[i])
    3-1. i번째 열의 안쪽을 침투한 상황에서 바깥쪽(e[1][i])을 침투하는 방법이 있습니다.
         따라서, bothside[i] = inside[i]+1
    3-2. i번째 열의 바깥쪽을 침투한 상황에서 안쪽(e[0][i])을 침투하는 방법이 있습니다.
         따라서, bothside[i] = outside[i]+1
    3-3. i-1번째 열의 안쪽과 바깥족 모두 침투한 상황에서 i번째 열의 안쪽 바깥족을 모두 침투하는 방법이 있습니다.
         단, i번째 열의 안쪽과 바깥쪽 적의 합이 특수소대원의 수보다 작은 경우에만 가능합니다. (e[0][i] + e[1][i] <= W)
         따라서, bothside[i] = bothside[i-1]+1
    3-4.

0번째열부터 N-1번째 열까지 계산하므로 0번째열에서의 초기값을 저장해야합니다.
문제는 주어진 구조가 도넛 형태로 배열의 선형구조가 아닌 원형구조라는 점입니다.
구역의 0번째 열과 마지막 N-1번째 열이 붙어있으므로 두 열안의 적의 수를 보고 초기값을 다르게 저장합니다.
1. 0번째 열과 N-1번째 열을 고려하지 않고 0번째부터 시작하는 경우
    1-1. 0번째 열의 안쪽을 점령하는데 필요한 최소 소대 수는 1입니다.
    insidie[0] = 1
    1-2. 0번째 열의 바깥쪽을 점령하는데 필요한 최소 소대 수는 1입니다.
    outside[0] = 1
    1-3. 0번째 열 전체를 점령하는데 필요한 최소 소대 수는 0번째 열에 있는 적의 수가 W이하라면 1개 소대로 가능하며 그 외에는 2개 소대가 필요합니다.
    bothside[0] = if(e[0][0]+e[1][0] <= W) 1 else 2

2. 0번째 열과 N-1번째 열의 안쪽 원을 한번의 침투로 가능한 경우
    e[0][0]+e[0][N-1] <= W 인 경우 한번의 침투로 가능합니다. 초기에 이 두 구역을 침투하므로 두 구역을 없는 구역으로 생각하여 나머지 구역을 계산하면 됩니다.
    2-1. 0번째 열의 안쪽은 없는 것으로 취급하므로 0번째 열의 안쪽까지 점령하는데 필요한 소대는 나올 수 없는 큰 값으로 저장합니다. (1번째 열에서 계산하는 것을 방지)
    inside[0] = inf
    2-2. 0번째 열의 바깥쪽을 점령하는데 필요한 최소 소대 수는 1입니다.
    outside[0] = 1
    2-3. 0번째 열 전체를 점령하는데 필요한 최소 소대 수는 1입니다. (안쪽 열이 없으므로 바깥쪽만 점령하면 됩니다.)
    bothside[0] = 1

3. 0번째 열과 N-1번째 열의 바깥쪽 원을 한번의 침투로 가능한 경우
    e[1][0]+e[1][N-1] < W 인 경우 한번의 침투로 가능합니다. 초기에 이 두 구역을 침투하므로 두 구역을 없는 구역으로 생각하여 나머지 구역을 계산하면 됩니다.
    3-1. 0번째 열의 안쪽을 점령하는데 필요한 최소 소대 수는 1입니다.
    inside[0] = 1
    3-2. 0번째 열의 바깥쪽은 없는 것으로 취급하므로 0번째 열의 안쪽까지 점령하는데 필요한 소대는 나올 수 없는 큰 값으로 저장합니다.
    outside[0] = inf
    3-3. 0번째 열 전체를 점령하는데 필요한 최소 소대 수는 1입니다. (바깥쪽 열이 없으므로 안쪽만 점령하면 됩니다.)
    bothside[0] = 1

문제발생. 2와 3의 경우가 동시에 존재하는 경우를 고려하지 않았습니다.
4.  첫번째(0) 열과 마지막 열(N-1)의 안쪽 원과 바깥쪽 원 각각 1개소대를 투입하여 점령이 가능한 경우
    e[0][0]+e[0][N-1] <= W && e[1][0]+e[1][N-1] <= W 인 경우 두 소대의 투입으로 각각 두 구역씩 점령이 가능합니다. (위의 2번과 3번 방법이 같이 존재)
    4-1. 0번째 열의 안쪽은 없는 것으로 취급하므로 0번째 열의 안쪽까지 점령하는데 필요한 소대는 나올 수 없는 큰 값으로 저장합니다.
    inside[0] = inf
    4-2. 0번째 열의 바깥쪽은 없는 것으로 취급하므로 0번째 열의 안쪽까지 점령하는데 필요한 소대는 나올 수 없는 큰 값으로 저장합니다.
    outside[0] = inf
    4-3. 0번째 열 전체를 점령하는데 필요한 최소 소대 수는 0입니다. (1번째 열에서 위 두값은 계산하지 않으므로 bothside[0] 은 계산을 위해 0으로 초기화합니다.)

문제발생. N=1인 경우를 고려하지 않아 case2 ~ case4를 계산하여 올바른 정답이 안나왔습니다.

문제발생. calc함수 내에서 2와 3의 경우가 동시에 존재하는 경루를 고려하지 않았습니다.



*/