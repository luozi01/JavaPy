package syntaxtree;

import visitor.Visitor;

import java.util.ArrayList;

public class ClassVarAssign extends Stm {

    public Exp e;
    public ArrayList<String> strList;

    public ClassVarAssign(ArrayList<String> strList, Exp e) {
        this.e = e;
        this.strList = strList;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
