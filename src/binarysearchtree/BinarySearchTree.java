package binarysearchtree;

public class BinarySearchTree {
    class TreeNode{
        String key;
        TreeNode left;
        TreeNode right;
    }
    private TreeNode rootNode;

    // TODO
    private TreeNode insertKey(TreeNode t, String x) {
        TreeNode newNode = new TreeNode();
        newNode.key = x;

        TreeNode findNode = t;
        if (findNode == null) {
            return newNode;
        } else {
            if(findNode.key.compareTo(newNode.key) > 0) {
                findNode.left = insertKey(findNode.left, x);
            } else if (findNode.key.compareTo(newNode.key) < 0) {
                findNode.right = insertKey(findNode.right, x);
            }
        }
        return findNode;
    }

    public void insert(String x) {
        rootNode = insertKey(rootNode, x);
    }

    public TreeNode find(String x) {
        TreeNode t = rootNode;
        int result;

        while (t != null) {
            if ((result = x.compareTo(t.key)) < 0) {
                t = t.left;
            } else if (result == 0) {
                return t;
            } else {
                t = t.right;
            }
        }

        return t;
    }

    private void printNode(TreeNode n) {
        if (n != null) {
            System.out.print("(");
            printNode(n.left);
            System.out.print(n.key);
            printNode(n.right);
            System.out.print(")");
        }
    }

    public void print() {
        printNode(rootNode);
        System.out.println();
    }

    public void delete(String x) {

    }
}
