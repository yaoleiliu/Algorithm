class Solution {
    public int removeDuplicates(int[] nums) {
        if(nums.length==0 | nums.length==1){
            return nums.length;
        }
        
        int flag = 0;
        for(int i=1; i<nums.length; i++){
            if(nums[i]!=nums[flag]){
                nums[++flag] = nums[i];
            }
        }
        
        return flag+1;
    }
}
