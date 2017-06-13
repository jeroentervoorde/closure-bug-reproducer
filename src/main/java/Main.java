import com.google.javascript.jscomp.*;
import com.google.javascript.jscomp.Compiler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jeroen on 6/11/17.
 */
public class Main {

    public static void main(String[] args) {
        CompilerOptions options = new CompilerOptions();
        CompilationLevel.ADVANCED_OPTIMIZATIONS.setOptionsForCompilationLevel(options);

        DiagnosticGroups groups = new DiagnosticGroups();

        options.setCheckTypes(false);
        options.setInferTypes(false);
        options.setTrustedStrings(true);
        options.setApplyInputSourceMaps(true);
        options.setCheckGlobalThisLevel(CheckLevel.OFF);
//        options.setWarningsGuard(new ComposeWarningsGuard(new WarningsGuard() {
//            public CheckLevel level(JSError error) {
//                return CheckLevel.OFF;
//            }
//        }));

        SourceFile extern1 = SourceFile.fromFile("./externs1.js");
        SourceFile extern2 = SourceFile.fromFile("./externs2.js");
        List<SourceFile> externs = new ArrayList<SourceFile>();
        externs.add(extern1);
        externs.add(extern2);

        SourceFile code = SourceFile.fromFile("./code.js");

        JSModule module = new JSModule("Scala.js");
        module.add(new CompilerInput(code));
        List<JSModule> modules = new ArrayList<JSModule>();
        modules.add(module);


        Compiler compiler = new Compiler();
        compiler.compileModules(externs, modules, options);

        //compiler.compile(extern1, code, options);
    }
}
