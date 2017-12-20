package visitor;

import syntaxtree.*;
import syntaxtree.Class;

public interface Visitor {

    void visit(Param n);

    void visit(Program n);

    void visit(Class n);

    void visit(ClassExp n);

    void visit(ClassVarExp n);

    void visit(ClassFuncExp n);

    void visit(Function n);

    void visit(CompoundStm n);

    void visit(ProcedureExp n);

    /*******************Basic****************/
    void visit(If n);

    void visit(Not n);

    void visit(Null n);

    void visit(True n);

    void visit(False n);

    void visit(Equal n);

    void visit(IdExp n);

    void visit(OpExp n);

    void visit(NumExp n);

    void visit(NegInt n);

    void visit(LogExp n);

    void visit(Compare n);

    void visit(ReadExp n);

    void visit(PrintStm n);

    void visit(ReturnStm n);

    void visit(AssignStm n);

    void visit(Var n);

    /*******************Array****************/
    void visit(ArrGet n);

    void visit(ArrAdd n);

    void visit(ArrSet n);

    void visit(ArrEmpty n);

    void visit(ArrDefine n);

    void visit(ArrInsert n);

    void visit(ArrLength n);

    void visit(ArrIndexOf n);

    void visit(ArrContains n);

    void visit(ArrRemoveFirst n);

    /*******************Loop****************/
    void visit(WhileExp n);

    void visit(ForLoopExp n);

    /*******************Dictionary****************/
    void visit(Dict n);

    void visit(DictGet n);

    void visit(DictPut n);

    void visit(DictKeys n);

}
