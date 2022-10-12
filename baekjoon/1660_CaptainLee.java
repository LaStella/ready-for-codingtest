// 캡틴 이다솜
// https://www.acmicpc.net/problem/1660

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        // dp1.get(i) : 길이가 i인 정삼각형을 이루는 대포알의 개수  
        List<Integer> dp1 = new ArrayList<>();
        // dp2.get(i) : i번 사면체를 이루는 대포알의 개수
        List<Integer> dp2 = new ArrayList<>();
        dp1.add(1);
        dp2.add(1);
        int index = 0;
        int val = 2;
        // 사면체를 이루는 대포알의 개수는 최대 N개입니다.
        // 따라서 N개를 초과할때까지 사면체를 계산합니다.
        while (dp2.get(index) < N) {
            dp1.add(dp1.get(index) + val);
            dp2.add(dp2.get(index) + dp1.get(index+1));
            index++;
            val++;
        }

        // dp3[i] : i개의 대포알로 만들 수 있는 최소 사면체의 개수
        int[] dp3 = new int[N+1];
        // 나올수 없는 되채값으로 초기화하며, 0개의 대포알로는 0개의 사면체를, 1개의 대포알로는 1개의 사면체를 만들 수 있습니다.
        Arrays.fill(dp3, Integer.MAX_VALUE);
        dp3[0] = 0;
        dp3[1] = 1;

        // 대포알의 개수를 늘려가며 만들 수 있는 최소 사면체의 개수를 계산합니다.
        for (int i = 2 ; i <= N ; i++) {
            // dp2에는 각 사면체를 이루는 대포알의 개수가 저장되어있습니다.
            // 임의의 j번 사면체를 만든다면 남은 대포알은 i-dp2.get(j) 가 됩니다.
            // 남은 대포알로 만들 수 있는 최소 사면체의 개수를 가져와 +1(j번 사면체를 만들었으므로) 한 것과 비교하여 최소값을 저장합니다.
            for (int j = 0 ; j < dp2.size() ; j++) {
                if (dp2.get(j) > i) break;
                dp3[i] = Math.min(dp3[i], dp3[i-dp2.get(j)] +1);
            }
        }

        System.out.println(dp3[N]);

    }
}


/*
문제에서 말하는 사면체는 길이가 N인 정삼각형에서 길이를 1씩 줄여나가며 정삼각형을 위에 얹는 것을 말합니다.
정삼각형을 이루는 대포알의 개수를 저장하고, 정삼각형으로 만드는 사면체를 이루는 대포알의 개수를 저장해야합니다. (메모이제이션)

길이가 n인 정삼각형을 이루는 대포알의 개수는 길이가 N-1인 정삼각형을 이루는 대포알의 개수에 n을 더하는 것입니다.
dp1[n] = dp1[n-1] + n

n번 사면체를 이루는 대포알의 개수는 n-1번 사면체를 이루는 대포알의 개수에 N길이의 정삼각형을 이루는 대포알의 개수를 더하는 것입니다.
dp2[n] = dp2[n-1] + dp1[n]

마지막으로 문제에서 주어지는 N을 이용하여 결과를 계산해야합니다.
dp3[n]을 대포알 n개로 만들 수 있는 사면체의 최소 개수라고 합니다.
위의 dp2에서 0번~m번까지 각 사면체를 만드는데 필요한 대포알의 수가 저장되어있으므로
n개에서 dp2[m]개를 뺀 나머지 대포알로 만들 수 있는 최소 사면체의 개수에 +1(사면체를 하나 만들었으므로)한 것 중 최소 개수를 저장하면 됩니다.
dp3[n] = Math.min(dp3[n], dp3[n-dp2[m]] + 1)

 */