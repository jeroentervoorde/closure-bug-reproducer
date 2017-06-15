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
        //CompilationLevel.SIMPLE_OPTIMIZATIONS.setOptionsForCompilationLevel(options);
//        CompilationLevel.ADVANCED_OPTIMIZATIONS.setOptionsForCompilationLevel(options);
//        disableOpts(options);
        setFastOpt2(options);

        DiagnosticGroups groups = new DiagnosticGroups();

        options.setLanguageIn(CompilerOptions.LanguageMode.ECMASCRIPT5);
        options.setLanguageOut(CompilerOptions.LanguageMode.NO_TRANSPILE);

        options.setCheckTypes(false);
        options.setInferTypes(false);
        options.setTrustedStrings(true);
        options.setApplyInputSourceMaps(true);
        options.setCheckGlobalThisLevel(CheckLevel.OFF);
        options.setWarningsGuard(new ComposeWarningsGuard(new WarningsGuard() {
            public CheckLevel level(JSError error) {
                return CheckLevel.OFF;
            }
        }));

        for (int i =0; i < 100; i++) {

            long start = System.currentTimeMillis();

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

            System.out.println("Elapsed: " + (System.currentTimeMillis() - start));
            System.out.println(compiler.toSource().substring(0, 10));
            System.out.println("Source length: " + compiler.toSource().length());
        }

        //compiler.compile(extern1, code, options);
    }

    private static void disableOpts(CompilerOptions options) {
//        options.setClosurePass(false);
//        options.setRemoveDeadCode(false);
//        options.setOptimizeArgumentsArray(false);
//        options.setCollapseObjectLiterals(false);
    }

    private static void setFastOpt(CompilerOptions options) {
        options.setCheckSymbols(false);
        options.setCheckTypes(false);
        options.setClosurePass(false);
        options.setFoldConstants(true);
        options.setCoalesceVariableNames(true); //
        options.setDeadAssignmentElimination(false);
        options.setExtractPrototypeMemberDeclarations(true); ///
        options.setCollapseVariableDeclarations(true); ///
        options.setConvertToDottedProperties(true); //
        options.setLabelRenaming(true);
        options.setRemoveDeadCode(true);
        options.setOptimizeArgumentsArray(false);
        options.setCollapseObjectLiterals(false);

        options.setProtectHiddenSideEffects(false);
        options.setRemoveClosureAsserts(false);
        options.setRemoveAbstractMethods(false);
        options.setRemoveSuperMethods(false);

        options.setReserveRawExports(true);
        options.setRenamingPolicy(VariableRenamingPolicy.ALL, PropertyRenamingPolicy.ALL_UNQUOTED);
        options.setShadowVariables(false);
        options.setRemoveUnusedPrototypeProperties(true);
        options.setRemoveUnusedPrototypePropertiesInExterns(false);
        options.setRemoveUnusedClassProperties(true);

        options.setCollapseAnonymousFunctions(true); //
        options.setCollapseProperties(true); //
        options.setCheckGlobalThisLevel(CheckLevel.OFF);
        options.setRewriteFunctionExpressions(true); //
        options.setSmartNameRemoval(true);  ////
        options.setExtraSmartNameRemoval(false);

        options.setInlineConstantVars(false);
        options.setInlineFunctions(CompilerOptions.Reach.ALL);
        options.setAssumeClosuresOnlyCaptureReferences(false);
        options.setInlineVariables(CompilerOptions.Reach.ALL);

        options.setFlowSensitiveInlineVariables(true);
        options.setComputeFunctionSideEffects(true);
        options.setAssumeStrictThis(true);
        options.setRemoveUnusedVariables(CompilerOptions.Reach.ALL);
        options.setCrossModuleCodeMotion(false);
        options.setCrossModuleMethodMotion(false);
        options.setDevirtualizePrototypeMethods(true); //
        options.setOptimizeParameters(true);
        options.setOptimizeReturns(true);
        options.setOptimizeCalls(true);
    }

    private static void setFastOpt2(CompilerOptions options) {
        options.setCheckSymbols(false);
        options.setCheckTypes(false);
        options.setClosurePass(false);
        options.setFoldConstants(false);
        options.setCoalesceVariableNames(false); //
        options.setDeadAssignmentElimination(false);
        options.setExtractPrototypeMemberDeclarations(true); ///
        options.setCollapseVariableDeclarations(true); ///
        options.setConvertToDottedProperties(false); //
        options.setLabelRenaming(false);
        options.setRemoveDeadCode(false);
        options.setOptimizeArgumentsArray(false);
        options.setCollapseObjectLiterals(false);

        options.setProtectHiddenSideEffects(false);
        options.setRemoveClosureAsserts(false);
        options.setRemoveAbstractMethods(false);
        options.setRemoveSuperMethods(false);

        options.setReserveRawExports(true);
        options.setRenamingPolicy(VariableRenamingPolicy.ALL, PropertyRenamingPolicy.ALL_UNQUOTED);
        options.setShadowVariables(false);
        options.setRemoveUnusedPrototypeProperties(false);
        options.setRemoveUnusedPrototypePropertiesInExterns(false);
        options.setRemoveUnusedClassProperties(false);

        options.setCollapseAnonymousFunctions(false); //
        options.setCollapseProperties(false); //
        options.setCheckGlobalThisLevel(CheckLevel.OFF);
        options.setRewriteFunctionExpressions(false); //
        options.setSmartNameRemoval(true);  ////
        options.setExtraSmartNameRemoval(false);

        options.setInlineConstantVars(false);
        options.setInlineFunctions(CompilerOptions.Reach.NONE);
        options.setAssumeClosuresOnlyCaptureReferences(false);
        options.setInlineVariables(CompilerOptions.Reach.NONE);

        options.setFlowSensitiveInlineVariables(false);
        options.setComputeFunctionSideEffects(false);
        options.setAssumeStrictThis(false);
        options.setRemoveUnusedVariables(CompilerOptions.Reach.NONE);
        options.setCrossModuleCodeMotion(false);
        options.setCrossModuleMethodMotion(false);
        options.setDevirtualizePrototypeMethods(false); //
        options.setOptimizeParameters(false);
        options.setOptimizeReturns(false);
        options.setOptimizeCalls(false);
    }
}
