package main.solution.search;

import java.util.*;

// important: implements algorithm
public class SwapNodesAlgo {

    private static int height = 1;

    public static class Node {
        int depth;
        int data;
        Node left;
        Node right;

        public Node(int depth, int data) {
            this.depth = depth;
            this.data = data;
        }
    }

    public static List<List<Integer>> swapNodes(List<List<Integer>> indexes, List<Integer> queries) {
        List<List<Integer>> results = new ArrayList<>();

        // Step 1: Build tree
        Map<Integer, List<Node>> depthToNodesMap = buildTree(indexes);
        Node root = depthToNodesMap.get(1).get(0);

        // Step 2: swap
        queries.forEach(query -> {
            int n = 1;
            while (n*query < height) {
                swap(n*query, depthToNodesMap);
                n++;
            }
            List<Integer> result = new ArrayList<>();
            traverseInOrder(root, result);
            results.add(result);
        });

        return results;
    }

    //Builds tree from given indices
    public static Map<Integer, List<Node>> buildTree(List<List<Integer>> indexes) {
        Node rootNode = new Node(1, 1); //root is always 1 for this problem
        Queue<Node> nodes = new LinkedList<>();
        HashMap<Integer, List<Node>> depthToNodes = new HashMap<>(); // map that uses depth as key and list of nodes as value, not required to build tree, use it as an extra for later use
        nodes.offer(rootNode);
        depthToNodes.put(1, Collections.singletonList(rootNode));
        Node current;

        for (int i = 2; i <= indexes.size(); i++) {
            depthToNodes.put(i, new ArrayList<>());
        }

        for (List<Integer> index : indexes) {
            current = nodes.poll();
            Node leftNode = index.get(0) == -1 ? null : new Node(current.depth + 1, index.get(0));
            Node rightNode = index.get(1) == -1 ? null : new Node(current.depth + 1, index.get(1));
            current.left = leftNode;
            current.right = rightNode;
            if (Objects.nonNull(leftNode)) {
                nodes.offer(leftNode);
                depthToNodes.get(current.depth + 1).add(leftNode);
            }
            if (Objects.nonNull(rightNode)) {
                nodes.offer(rightNode);
                depthToNodes.get(current.depth + 1).add(rightNode);
            }
            if (current.depth + 1 > height) {
                height = current.depth + 1;
            }
        }
        return depthToNodes;
    }

    public static void swap (int depth, Map<Integer, List<Node>> depthToNodesMap) {
        List<Node> nodesToSwap = depthToNodesMap.get(depth);
        nodesToSwap.forEach(node -> {
            Node temp = node.left;
            node.left = node.right;
            node.right = temp;
        });
    }

    // Adds result of inorder traversal to result list,
    public static void traverseInOrder (Node current, List<Integer> result) {
        if (Objects.isNull(current)) {
            return;
        }
        traverseInOrder(current.left, result);
        result.add(current.data);
        traverseInOrder(current.right, result);
    }
}
