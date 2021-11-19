package chap2;

public class EvalUtil {
    public int eval(ExprJava e) {
        if (e instanceof NumJava) {
            return ((NumJava) e).getValue();
        }
        if (e instanceof SumJava) {
            return eval(((SumJava) e).getRight()) + eval(((SumJava) e).getLeft());
        }
        throw new IllegalArgumentException("unknown expression");
    }

    public static void main(String[] args) {
        NumJava num1 = new NumJava(1);

        System.out.println(new EvalUtil().eval(num1));
    }
}
