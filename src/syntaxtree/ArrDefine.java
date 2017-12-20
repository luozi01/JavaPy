package syntaxtree;

import visitor.Visitor;

import java.util.ArrayList;

/**
 * Define array with values
 *
 * @author Mike_Casanova
 */
public class ArrDefine extends Exp {

    public ArrayList<Exp> list;

    public ArrDefine(ArrayList<Exp> el) {
        this.list = el;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
