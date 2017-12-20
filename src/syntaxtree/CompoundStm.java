package syntaxtree;

import visitor.Visitor;

import java.util.ArrayList;

public class CompoundStm extends Stm {

    public ArrayList<Stm> list;

    public CompoundStm(ArrayList<Stm> list) {
        this.list = list;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
