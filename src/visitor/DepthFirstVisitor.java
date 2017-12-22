package visitor;

import syntaxtree.*;
import syntaxtree.Class;

public class DepthFirstVisitor implements Visitor {

    /**********************Structure*********************/

    @Override
    public void visit(Program n) {
        for (Class c : n.classList)
            c.accept(this);
        for (Function f : n.funcList)
            f.accept(this);
        for (Stm s : n.stmList)
            s.accept(this);
    }

    @Override
    public void visit(Class n) {
        for (Class c : n.cl)
            c.accept(this);
        for (Var v : n.vl)
            v.accept(this);
        for (Function f : n.fl)
            f.accept(this);
    }

    @Override
    public void visit(ClassExp n) {
        for (Exp e : n.el) {
            e.accept(this);
        }
    }

    /**
     * String varName
     * Exp className
     */
    @Override
    public void visit(ClassVarExp n) {
        n.className.accept(this);
    }


    /**
     * Exp classExp, funcExp
     */
    @Override
    public void visit(ClassFuncExp n) {
        n.classExp.accept(this);
        n.funcExp.accept(this);
    }

    @Override
    public void visit(ClassVarAssign n) {
        n.e1.accept(this);
        n.e2.accept(this);
    }


    @Override
    public void visit(Function n) {
        for (Param p : n.pl)
            p.accept(this);
        for (Stm s : n.sl)
            s.accept(this);
    }

    @Override
    public void visit(Var n) {
        n.accept(this);
    }

    @Override
    public void visit(Param n) {
        n.accept(this);
    }

    @Override
    public void visit(ProcedureExp n) {
        for (Exp e : n.expList)
            e.accept(this);
    }

    /**
     * ArrayList<Stm></Stm> list
     */
    @Override
    public void visit(CompoundStm n) {
        for (Stm s : n.list)
            s.accept(this);
    }

    /**
     * Exp e
     */
    @Override
    public void visit(ReturnStm n) {
        n.e.accept(this);
    }

    /**
     * Exp exp
     */
    @Override
    public void visit(AssignStm n) {
        n.exp.accept(this);
    }

    /**
     * ArrayList<Exp></Exp> list;
     */
    @Override
    public void visit(PrintStm n) {
        for (Exp e : n.expList)
            e.accept(this);
    }

    /**
     * ArrayList<Exp></Exp> expList;
     * ArrayList<Stm></Stm> stmList;
     */
    @Override
    public void visit(If n) {
        for (Exp e : n.expList)
            e.accept(this);
        for (Stm s : n.stmList)
            s.accept(this);
    }

    /**
     * String id
     */
    @Override
    public void visit(IdExp exp) {
    }

    /**
     * int num
     */
    @Override
    public void visit(NumExp exp) {
    }

    /**
     * Exp e1;
     * String op;
     * Exp e2;
     */
    @Override
    public void visit(OpExp n) {
        n.e1.accept(this);
        n.e2.accept(this);
    }

    /**
     * Exp e1,e2
     * String id
     */
    @Override
    public void visit(Compare n) {
        n.e1.accept(this);
        n.e2.accept(this);
    }

    @Override
    public void visit(Null n) {
    }

    @Override
    public void visit(True n) {
    }

    @Override
    public void visit(False n) {
    }

    /**
     * Exp exp
     */
    @Override
    public void visit(Not n) {
        n.exp.accept(this);
    }

    /**
     * Exp e1,e2
     * String id
     */
    @Override
    public void visit(Equal n) {
        n.e1.accept(this);
        n.e2.accept(this);
    }

    /**
     * Exp e1,e2
     * String id
     */
    @Override
    public void visit(LogExp n) {
        n.e1.accept(this);
        n.e2.accept(this);
    }

    @Override
    public void visit(NegInt n) {
        n.exp.accept(this);
    }

    @Override
    public void visit(ReadExp n) {
    }

    /**
     * ArrayList<Exp></Exp> list
     */
    @Override
    public void visit(ArrDefine n) {
        for (Exp e : n.list)
            e.accept(this);
    }

    /**
     * String id
     * Exp e
     */
    @Override
    public void visit(ArrAdd n) {
        n.e.accept(this);
    }

    /**
     * Exp e1,e2
     */
    @Override
    public void visit(ArrContains n) {
        n.e1.accept(this);
        n.e2.accept(this);
    }

    /**
     * Exp e1,e2
     */
    @Override
    public void visit(ArrIndexOf n) {
        n.e1.accept(this);
        n.e2.accept(this);
    }

    /**
     * Exp e1,e2
     */
    @Override
    public void visit(ArrInsert n) {
        n.e1.accept(this);
        n.e2.accept(this);
    }

    /**
     * Exp e1,e2
     */
    @Override
    public void visit(ArrGet n) {
        n.e1.accept(this);
        n.e2.accept(this);
    }

    /**
     * Exp e
     */
    @Override
    public void visit(ArrLength n) {
        n.e.accept(this);
    }

    /**
     * Exp e1,e2
     */
    @Override
    public void visit(ArrSet n) {
        n.e1.accept(this);
        n.e2.accept(this);
    }

    /**
     * Exp e
     */
    @Override
    public void visit(ArrRemoveFirst n) {
        n.e.accept(this);
    }

    /**
     * Exp e
     */
    @Override
    public void visit(ArrEmpty n) {
        n.e.accept(this);
    }

    /*****************Dictionary***********************/
    @Override
    public void visit(Dict n) {
    }

    /**
     * Exp e1,e2
     */
    @Override
    public void visit(DictGet n) {
        n.e1.accept(this);
        n.e2.accept(this);
    }

    /**
     * Exp e
     */
    @Override
    public void visit(DictKeys n) {
        n.e.accept(this);
    }

    /**
     * Exp e1,e2,e3
     */
    @Override
    public void visit(DictPut n) {
        n.e1.accept(this);
        n.e2.accept(this);
    }

    /**
     * ArrayList<Exp></Exp> expList
     * String var
     * Stm body
     * Exp e
     */
    @Override
    public void visit(ForLoopExp n) {
        for (Exp e : n.expList) {
            e.accept(this);
        }
        n.e.accept(this);
        n.body.accept(this);
    }

    /**
     * Exp bool
     * Stm body
     */
    @Override
    public void visit(WhileExp n) {
        n.bool.accept(this);
        n.body.accept(this);
    }
}
