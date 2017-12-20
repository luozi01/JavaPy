package syntaxtree;

import visitor.Visitor;

import java.util.ArrayList;

public class PrintStm extends Stm {

    public ArrayList<Exp> expList;

    public PrintStm(ArrayList<Exp> expList) {
        this.expList = expList;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
