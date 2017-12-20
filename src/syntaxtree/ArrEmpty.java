package syntaxtree;

import visitor.Visitor;

public class ArrEmpty extends Exp {

    public Exp e;

    public ArrEmpty(Exp e) {
        this.e = e;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
