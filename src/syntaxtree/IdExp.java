package syntaxtree;

import visitor.Visitor;

public class IdExp extends Exp {

    public String id;

    public IdExp(String id) {
        this.id = id;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
