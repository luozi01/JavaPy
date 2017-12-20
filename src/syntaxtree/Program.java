package syntaxtree;

import visitor.Visitor;

import java.util.ArrayList;

public class Program extends ASTNode {
    public ArrayList<Class> classList;
    public ArrayList<Function> funcList;
    public ArrayList<Stm> stmList;

    public Program(ArrayList<Function> funcList, ArrayList<Stm> stmList, ArrayList<Class> classList) {
        this.funcList = funcList;
        this.stmList = stmList;
        this.classList = classList;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
