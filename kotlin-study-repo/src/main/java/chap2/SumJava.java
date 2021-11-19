package chap2;

public class SumJava implements ExprJava {
    private ExprJava left;
    private ExprJava right;

    public SumJava(ExprJava left, ExprJava right) {
        this.left = left;
        this.right = right;
    }

    public ExprJava getLeft() {
        return left;
    }

    public ExprJava getRight() {
        return right;
    }
}
