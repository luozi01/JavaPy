package syntaxtree;

import visitor.Visitor;

public class ClassFuncExp extends Exp {

    public Exp classExp;
    public Exp funcExp;

    public ClassFuncExp(Exp classExp, Exp funcExp) {
        super();
        this.classExp = classExp;
        this.funcExp = funcExp;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
