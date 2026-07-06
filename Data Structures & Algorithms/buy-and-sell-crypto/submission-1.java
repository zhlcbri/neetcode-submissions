class Solution {
    // Time: O(n)
    // Space: O(1)
    public int maxProfit(int[] prices) {
        // Min price seen so far
        int minPrice = Integer.MAX_VALUE;

        // Max profit seen so far
        int maxProfit = 0;

        for (int currentPrice: prices) {
            // Step 1: Check if today's price is the new lowest
            if (currentPrice < minPrice) {
                minPrice = currentPrice;
            }

            // Step 2: Calculate profit if we sell today
            int currentProfit = currentPrice - minPrice;

            // Step 3: Check if today's profit is the new highest
            if (currentProfit > maxProfit) {
                maxProfit = currentProfit;
            }
        }

        return maxProfit;
    }
}
