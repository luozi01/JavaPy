package syntaxtree;

import visitor.Visitor;

public class Compare extends Exp {
    public String op;
    public Exp e1, e2;

    public Compare(String id, Exp e1, Exp e2) {
        this.op = id;
        this.e1 = e1;
        this.e2 = e2;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
