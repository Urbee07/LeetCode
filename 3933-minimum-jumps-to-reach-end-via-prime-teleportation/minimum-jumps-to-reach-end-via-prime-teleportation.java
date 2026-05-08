import java.util.*;

class Solution {
    
    public int minJumps(int[] nums) {
        int n = nums.length;
        
        if (n == 1) return 0;

        int maxVal = 0;
        for (int x : nums) {
            maxVal = Math.max(maxVal, x);
        }

        // Smallest Prime Factor sieve
        int[] spf = new int[maxVal + 1];
        for (int i = 2; i <= maxVal; i++) {
            if (spf[i] == 0) {
                for (int j = i; j <= maxVal; j += i) {
                    if (spf[j] == 0) {
                        spf[j] = i;
                    }
                }
            }
        }

        // bucket[p] = all indices whose value is divisible by p
        Map<Integer, List<Integer>> bucket = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int val = nums[i];
            Set<Integer> factors = getPrimeFactors(val, spf);

            for (int p : factors) {
                bucket.computeIfAbsent(p, k -> new ArrayList<>()).add(i);
            }
        }

        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[n];

        q.offer(0);
        visited[0] = true;

        int steps = 0;

        while (!q.isEmpty()) {

            int size = q.size();

            for (int s = 0; s < size; s++) {

                int i = q.poll();

                if (i == n - 1) {
                    return steps;
                }

                // Adjacent left
                if (i - 1 >= 0 && !visited[i - 1]) {
                    visited[i - 1] = true;
                    q.offer(i - 1);
                }

                // Adjacent right
                if (i + 1 < n && !visited[i + 1]) {
                    visited[i + 1] = true;
                    q.offer(i + 1);
                }

                // Prime teleportation
                if (isPrime(nums[i], spf)) {

                    int p = nums[i];

                    if (bucket.containsKey(p)) {

                        for (int next : bucket.get(p)) {

                            if (!visited[next]) {
                                visited[next] = true;
                                q.offer(next);
                            }
                        }

                        // Important optimization
                        bucket.remove(p);
                    }
                }
            }

            steps++;
        }

        return -1;
    }

    // Get unique prime factors
    private Set<Integer> getPrimeFactors(int x, int[] spf) {

        Set<Integer> set = new HashSet<>();

        while (x > 1) {
            int p = spf[x];
            set.add(p);

            while (x % p == 0) {
                x /= p;
            }
        }

        return set;
    }

    // Check prime
    private boolean isPrime(int x, int[] spf) {
        return x >= 2 && spf[x] == x;
    }
}