import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int prisonSize = Integer.parseInt(br.readLine());
        int[] prison = new int[prisonSize];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0 ; i < prison.length ; i++) {
            prison[i] = Integer.parseInt(st.nextToken());
        }

        int lighterSize = Integer.parseInt(br.readLine());
        int[] lighter = new int[lighterSize];
        st = new StringTokenizer(br.readLine());
        for (int i = 0 ; i < lighter.length ; i++) {
            lighter[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(findCorrectBrightness(prison, lighter));
    }

    private static int findCorrectBrightness(int[] prison, int[] lighter) {
        int answer = -1;
        int start = 1;
        int end = 100000;
        int mid;
        while (start <= end) {
            mid = (start+end) / 2;
            if (checkPrison(prison, lighter, mid)) {
                answer = mid;
                end = mid-1;
                continue;
            }
            start = mid+1;
        }
        return answer;
    }

    private static boolean checkPrison(int[] prison, int[] lighter, int brightness) {
        for (int p : prison) {
            boolean flag = false;
            for (int l : lighter) {
                if (p <= l + brightness && p >= l - brightness) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                return false;
            }
        }

        return true;
    }
}