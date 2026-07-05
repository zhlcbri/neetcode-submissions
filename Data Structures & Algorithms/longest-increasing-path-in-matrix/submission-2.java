class Solution {
    // 4 directions: up, down, left, right
    private static final int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    // e.g. memo[0][0] = 3
    // Means starting from matrix[0][0], the longest path has length 3.
    private int[][] memo;
    
    private int rows; // Num of rows
    private int cols; // Num of columns

    // Time: O(row * col * 4) - each cell is only explored once
    // Space: O(row * col * 2)
    // memo space, and worst call stack of going from upper left to lower right cell
    public int longestIncreasingPath(int[][] matrix) {
        // Edge case: empty matrix
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        // Init variables
        rows = matrix.length;
        cols = matrix[0].length;
        memo = new int[rows][cols]; // All 0s by default
        
        int longestPath = 0;

        // Loop through every cell and run DFS
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                // Start exploring this cell
                int currentPath = dfs(matrix, r, c);

                // Keep track of the longest path
                longestPath = Math.max(longestPath, currentPath);
            }
        }

        return longestPath;
    }

    // Does the actual pathfinding from matrix[r][c]
    private int dfs(int[][] matrix, int r, int c) {
        // Check memo first
        if (memo[r][c] > 0) {
            return memo[r][c];
        }

        // A path includes current cell itself
        int maxStepsFromHere = 1;

        // Try walking in all 4 directions
        for (int[] dir: DIRS) {
            int nextR = r + dir[0];
            int nextC = c + dir[1];

            // Make sure this is an increasing path within bound
            if (!isOutOfBound(nextR, nextC) 
                && isIncreasingPath(matrix, r, c, nextR, nextC)) {
                    // Here going up, down, left, or right
                    int stepsFromHere = 1 + dfs(matrix, nextR, nextC);
                    // The longest path must be found from one direction
                    maxStepsFromHere = Math.max(maxStepsFromHere, stepsFromHere);
            }
        }

        // Save the best result in memo and return it
        memo[r][c] = maxStepsFromHere;
        return memo[r][c];
    }

    private boolean isOutOfBound(int row, int col) {
        return row < 0 || col < 0 || row >= rows || col >= cols;
    }

    private boolean isIncreasingPath(int[][] matrix, int r, int c, int nextR, int nextC) {
        return matrix[nextR][nextC] > matrix[r][c];
    }
}
