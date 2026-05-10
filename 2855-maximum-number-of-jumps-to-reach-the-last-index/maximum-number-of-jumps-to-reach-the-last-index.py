class Solution:
    def maximumJumps(self, nums, target):
        n = len(nums)

        # dp[i] = maximum jumps to reach index i
        dp = [-1] * n
        dp[0] = 0

        for i in range(n):
            if dp[i] == -1:
                continue

            for j in range(i + 1, n):
                diff = nums[j] - nums[i]

                if -target <= diff <= target:
                    dp[j] = max(dp[j], dp[i] + 1)

        return dp[-1]
        