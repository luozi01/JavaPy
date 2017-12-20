package syntaxtree;

import visitor.Visitor;

public class ReturnStm extends Stm {

    public Exp e;

    public ReturnStm(Exp e) {
        this.e = e;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
