package com.example.burningwave.bw;

import static org.burningwave.core.assembler.StaticComponentContainer.ClassLoaders;
import static org.burningwave.core.assembler.StaticComponentContainer.Methods;

import org.burningwave.core.assembler.ComponentContainer;
import org.burningwave.core.classes.JavaMemoryCompiler;
import org.burningwave.core.classes.JavaMemoryCompiler.Compilation;
import org.burningwave.core.concurrent.QueuedTaskExecutor.ProducerTask;


public class SourceCompilationTester {


    public static void main(String args) throws ClassNotFoundException, InterruptedException {
        ComponentContainer componentContainer = ComponentContainer.getInstance();
        JavaMemoryCompiler javaMemoryCompiler = componentContainer.getJavaMemoryCompiler();
        ProducerTask<Compilation.Result> compilationTask = javaMemoryCompiler.compile(
                Compilation.Config.forUnitSourceGenerator(
                                SourceGenerationTester.generate(args)
                        )
                        .storeCompiledClassesTo(
                                System.getenv().get("PWD") + "/target/classes/"
                        )
        );

        Compilation.Result compilattionResult = compilationTask.join();

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        ClassLoaders.addClassPaths(classLoader, compilattionResult.getDependencies());
        //System.out.println(compilattionResult.getDependencies());

        ClassLoaders.addClassPath(classLoader, compilattionResult.getClassPath().getAbsolutePath());
        //System.out.println("PATH: "+ compilattionResult.getClassPath().getAbsolutePath());
        //Thread.sleep(5000);
        //System.out.println("continue");
        //Class<?> cls = classLoader.loadClass(System.getenv().get("PWD")
        //       + "/src/main/java/com/example/burningwave/bw/"+args);
        Class<?> c = Class.forName("com.example.burningwave.bw."+args);
        Methods.invokeStaticDirect(c, "main", new Object[] {new String[] {"Hello", "world!"}});
    }

}