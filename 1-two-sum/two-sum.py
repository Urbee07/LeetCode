class Solution(object):
    def twoSum(self, nums, target):
        res=[]
        for i in range(len(nums)):
            for j in range(i+1,len(nums)):
                if((nums[i]+nums[j])==target):
                    res.append(i)
                    res.append(j)
        return res

            
        