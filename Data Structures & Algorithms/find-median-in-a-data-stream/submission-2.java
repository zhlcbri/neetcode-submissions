class MedianFinder {
    // Max heap. e.g. [3, 2, 1]
    private PriorityQueue<Integer> leftHalf;

    // Min heap. e.g. [4, 5, 6]
    private PriorityQueue<Integer> rightHalf;

    // Space: O(N)
    public MedianFinder() {
        // PQ is min-heap by default in Java. Revert it.
        leftHalf = new PriorityQueue<>(Collections.reverseOrder());
        rightHalf = new PriorityQueue<>();
    }
    
    // Time: O(logN)
    public void addNum(int num) {
        // Step 1: Add to left half first
        leftHalf.offer(num);

        // Step 2: Move the largest from left to right
        rightHalf.offer(leftHalf.poll());

        // Step 3: Balance the sizes.
        // We want leftHalf.size() == rightHalf.size().
        // Left can have 1 more element than right at most.
        if (leftHalf.size() < rightHalf.size()) {
            leftHalf.offer(rightHalf.poll());
        }
    }
    
    // Time: O(1)
    public double findMedian() {
        // Case 1: Total number is odd - [1, 2, 3, 4, 5]
        // leftHalf = [3, 2, 1]
        // rightHalf = [4, 5]
        // median = 3
        if (leftHalf.size() > rightHalf.size()) {
            return (double) leftHalf.peek();
        }

        // Case 2: Total number is even - [1, 2, 3, 4]
        // leftHalf = [2, 1]
        // rightHalf = [3, 4]
        // median = (2 + 3) / 2 = 2.5
        return average(leftHalf.peek(), rightHalf.peek());
    }

    private double average(double a, double b) {
        return (a + b) / 2.0;
    }
}
