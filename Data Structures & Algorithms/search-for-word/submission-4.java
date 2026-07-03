class Solution {
    // Space: O(L); L = word.length()
    // Time: O(row * col * 4^L)
    public boolean exist(char[][] board, String word) {
        int row = board.length;
        int col = board[0].length;

        // Scan the entire grid
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                // Skip this char           
                if (board[r][c] != word.charAt(0)) {
                    continue;
                }
                
                // If the start char matches, activate search
                if (search4D(board, word, r, c, 0)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean search4D(
        char[][] board, 
        String word,
        int r, 
        int c, 
        int index
    ) {
        // Success: found all chars in the word
        // i.e. We can only arrive at word.length if we stepped on every char
        if (index == word.length()) {
            return true;
        }

        // Failure 1: Out of grid boundaries
        if (isOutOfBound(board, r, c)) {
            return false;
        }

        // Failure 2: Char mismatch or already visited
        if (board[r][c] != word.charAt(index)) {
            return false;
        }

        // ===== Main searching steps =====
        // 1. Save the char on current grid
        char originalChar = board[r][c];

        // 2. Mark the current grid as visited
        board[r][c] = '*';

        // 3. Search in 4 directions
        boolean found = 
            search4D(board, word, r + 1, c, index + 1) ||
            search4D(board, word, r - 1, c, index + 1) ||
            search4D(board, word, r, c + 1, index + 1) ||
            search4D(board, word, r, c - 1, index + 1);

        // 4. Erase the visited mark and restore the original char
        board[r][c] = originalChar;

        return found;
    }

    private boolean isOutOfBound(char[][] board, int r, int c) {
        if (r < 0 || c < 0 || r >= board.length || c >= board[0].length) {
            return true;
        }
        return false;
    }
}
