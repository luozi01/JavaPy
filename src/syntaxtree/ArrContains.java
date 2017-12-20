package syntaxtree;

import visitor.Visitor;

public class ArrContains extends Exp {

    public Exp e1, e2;

    public ArrContains(Exp e1, Exp e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
