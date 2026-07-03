class Solution {
    class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        String word = null;
    }

    public List<String> findWords(char[][] board, String[] words) {
        // Step 1: build trie
        TrieNode root = buildTrie(words);

        // List of words to be returned
        List<String> result = new ArrayList<>();

        int row = board.length;
        int col = board[0].length;

        // Scan every tile in board
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                search4D(board, r, c, root, result);
            }
        }

        return result;
    }

    // Build trie dictionary
    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();

        // Scan every word
        for (String w: words) {
            TrieNode node = root;

            // Scan every char in this word
            for (char c: w.toCharArray()) {
                // If current char is not in children map yet
                // Create a new path for it
                node.children.putIfAbsent(c, new TrieNode());

                // Move pointer down the path
                node = node.children.get(c);
            }

            // Stamp the word at the end
            node.word = w;
        }

        return root;
    }

    private void search4D(
        char[][] board, 
        int r, 
        int c, 
        TrieNode node,
        List<String> result
    ) {
        // Failure 1: Out of grid boundaries
        if (isOutOfBound(board, r, c)) {
            return;
        }

        // Get the current letter
        char letter = board[r][c];

        // Failure 2: No path exists in Trie
        TrieNode nextNode = node.children.get(letter);
        if (nextNode == null) {
            return;
        }

        // Success: Reached the end of a valid path in Trie
        if (nextNode.word != null) {
            result.add(nextNode.word);

            // Cross out from Trie to avoid duplicates
            nextNode.word = null;
        }

        // ===== Main searching steps =====
        // 1. Mark the current grid as visited
        board[r][c] = '*';

        // 3. Search in 4 directions
        search4D(board, r + 1, c, nextNode, result);
        search4D(board, r - 1, c, nextNode, result);
        search4D(board, r, c + 1, nextNode, result);
        search4D(board, r, c - 1, nextNode, result);

        // 4. Erase the visited mark and restore the original letter
        board[r][c] = letter;
    }

    private boolean isOutOfBound(char[][] board, int r, int c) {
        if (r < 0 || c < 0 || r >= board.length || c >= board[0].length) {
            return true;
        }
        return false;
    }
}
