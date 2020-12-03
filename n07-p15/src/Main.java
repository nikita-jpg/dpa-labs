public class Main {
    public static int work(int[] arr) {
        int n = arr.length - 1;
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; ++i) {
            dp[i][i] = 0;
        }

        for (int l = 1; l < n; ++l) {
            for (int i = 0; i < n - l; ++i) {
                int j = i + l;
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; ++k) {
                    dp[i][j] = Math.min(dp[i][j],
                            dp[i][k] + dp[k + 1][j] + arr[i] * arr[k + 1] * arr[j + 1]);
                }
            }
        }
        return dp[0][n - 1];
    }

    //Асимптотика решения - O(N^3) время и O(N^2) память.
    public static void main(String[] args) {
        int[] test = { 5, 10, 3, 12, 5, 50, 6};
        System.out.println("минимальное количество скалярных умножений, необходимое для решения: " + Main.work(test));
    }
}