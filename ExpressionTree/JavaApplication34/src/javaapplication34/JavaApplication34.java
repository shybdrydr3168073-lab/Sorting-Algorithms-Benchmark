
package javaapplication34;




public class JavaApplication34 {

public static void main(String[] args) {

    String postfix = "53+82-*";

    Node root = ExpressionTree.buildTree(postfix);

    ExpressionTree.printInfix(root);

    System.out.println("\nResult: " + ExpressionTree.evaluate(root));
}
}
