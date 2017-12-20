package syntaxtree;

import visitor.Visitor;

public class Var extends Stm {
    public Exp exp;
    public String id;
    public boolean isObject;

    public Var(Exp e, String id, boolean isObject) {
        this.exp = e;
        this.id = id;
        this.isObject = isObject;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
