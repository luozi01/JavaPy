package syntaxtree;

import visitor.Visitor;

public class ClassVarExp extends Exp {

    public Exp className;
    public String varName;

    public ClassVarExp(Exp className, String varName) {
        this.className = className;
        this.varName = varName;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
