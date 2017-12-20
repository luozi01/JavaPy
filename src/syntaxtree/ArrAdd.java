package syntaxtree;

import visitor.Visitor;

public class ArrAdd extends Stm {

    public String id;
    public Exp e;

    public ArrAdd(String id, Exp e) {
        this.id = id;
        this.e = e;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
