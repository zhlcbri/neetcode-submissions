class Solution {
    public void rotate(int[] nums, int k) {
        // Step 0: Optimize k
        int len = nums.length;
        k = k % len;

        // Step 1: Reverse the entire array
        reverse(nums, 0, len - 1);
        
        // Step 2: Reverse the first k elements
        reverse(nums, k, len - 1);
        reverse(nums, 0, k - 1);

        // Step 3: Reverse the remaining elements
        
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            // Swap numbers
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;

            // Move pointers towards the middle
            start++;
            end--;
        }
    }
}
