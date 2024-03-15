package com.example.burningwave.bw;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;

import org.burningwave.core.classes.ClassSourceGenerator;
import org.burningwave.core.classes.FunctionSourceGenerator;
import org.burningwave.core.classes.GenericSourceGenerator;
import org.burningwave.core.classes.TypeDeclarationSourceGenerator;
import org.burningwave.core.classes.UnitSourceGenerator;
import org.burningwave.core.classes.VariableSourceGenerator;

public class SourceGenerationTester {


    public static UnitSourceGenerator generate(String cn) {
        return UnitSourceGenerator.create(SourceGenerationTester.class.getPackage().getName())
                .addClass(
                        ClassSourceGenerator.create(TypeDeclarationSourceGenerator.create(cn))
                                .addField(
                                        VariableSourceGenerator.create(
                                                TypeDeclarationSourceGenerator.create(List.class)
                                                        .addGeneric(GenericSourceGenerator.create(String.class)),
                                                "words"
                                        )
                                )
                                .addConstructor(
                                        FunctionSourceGenerator.create().addParameter(
                                                VariableSourceGenerator.create(
                                                        TypeDeclarationSourceGenerator.create(String.class)
                                                                .setAsVarArgs(true),
                                                        "words"
                                                )
                                        ).addBodyCodeLine("this.words = Arrays.asList(words);").useType(Arrays.class)
                                )
                                .addMethod(
                                        FunctionSourceGenerator.create("print")
                                                .addModifier(Modifier.PUBLIC).setReturnType(void.class)
                                                .addBodyCodeLine(
                                                        "System.out.println(\"\\n\\t\" + String.join(\" \", words) + \"\\n\");"
                                                )
                                )
                                .addMethod(
                                        FunctionSourceGenerator.create("main")
                                                .addModifier(Modifier.PUBLIC | Modifier.STATIC)
                                                .setReturnType(void.class)
                                                .addParameter(VariableSourceGenerator.create(String[].class, "args"))
                                                .addBodyCodeLine("new "+cn +"(args).print();")
                                )
                );
    }


    public static void main(String args) {
        UnitSourceGenerator unitSG = SourceGenerationTester.generate(args);

        unitSG.storeToClassPath(System.getenv().get("PWD") + "/src/main/java/");
        //System.out.println("\nGenerated code:\n" + unitSG);

    }
}