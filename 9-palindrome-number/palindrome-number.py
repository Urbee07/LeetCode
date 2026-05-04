class Solution(object):
    def isPalindrome(self, x):
        """
        :type x: int
        :rtype: bool
        """
        temp=x
        res=0
        if x>=0:
            while x!=0:
                res = (res*10)+ x%10
                x//=10
        
            if res==temp:
                return True
            else:
                return False
        else:
            return False

        