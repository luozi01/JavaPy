package syntaxtree;

import visitor.Visitor;

public class LogExp extends Exp {

    public String op;
    public Exp e1, e2;

    public LogExp(String operator, Exp exp1, Exp exp2) {
        this.op = operator;
        this.e1 = exp1;
        this.e2 = exp2;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
