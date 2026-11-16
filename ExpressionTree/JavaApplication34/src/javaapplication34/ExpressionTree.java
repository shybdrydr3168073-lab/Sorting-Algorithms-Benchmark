
package javaapplication34;
import java.util.Stack;
public class ExpressionTree {
public static Node buildTree(String postfix) {

    Stack<Node> stack = new Stack<>();

    for (int i = 0; i < postfix.length(); i++) {

        String s = postfix.substring(i, i + 1);
        Node n = new Node(s);

        if (s.equals("+") || s.equals("-") ||
            s.equals("*") || s.equals("/")) {

            n.right = stack.pop();
            n.left = stack.pop();
        }

        stack.push(n);
    }

    return stack.pop();
}

public static int evaluate(Node root) {

    if (root.left == null && root.right == null)
        return Integer.parseInt(root.value);

    int left = evaluate(root.left);
    int right = evaluate(root.right);

    if (root.value.equals("+"))
        return left + right;

    if (root.value.equals("-"))
        return left - right;

    if (root.value.equals("*"))
        return left * right;

    return left / right;
}

public static void printInfix(Node root) {

    if (root == null)
        return;

    if (root.left != null)
        System.out.print("(");

    printInfix(root.left);

    System.out.print(root.value);

    printInfix(root.right);

    if (root.right != null)
        System.out.print(")");
}
}


