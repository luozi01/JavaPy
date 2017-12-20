package syntaxtree;

import visitor.Visitor;

public class Equal extends Exp {
    public Exp e1, e2;
    public String id;

    public Equal(String id, Exp e1, Exp e2) {
        this.e1 = e1;
        this.e2 = e2;
        this.id = id;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}