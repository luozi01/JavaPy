import syntaxtree.Program;
import visitor.MyLangInterpreter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class JavPyInterpret {
    @SuppressWarnings("static-access")
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Given " + args.length + " args");
            System.err.println("Usage: java Interpret <MyLang source filename>");
            System.exit(1);
        }
        try {
            InputStream is = new FileInputStream(new File(args[0]));
            Program parseTree = new JavPy(is).Program();
            parseTree.accept(new MyLangInterpreter());
        } catch (ParseException e) {
            System.out.println("Parser Error : \n" + e.toString());
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + args[0]);
            System.exit(1);
        }
    }
}
