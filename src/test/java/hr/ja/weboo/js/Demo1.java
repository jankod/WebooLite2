package hr.ja.weboo.js;

import hr.ja.weboo.ServerHandler;
import lombok.extern.slf4j.Slf4j;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;

@Slf4j
public class Demo1 {

    public static void main(String[] args) {
        Demo1 d = new Demo1();
        d.doSomething(new ServerHandler() {
            @Override
            public AjaxResult handle() {
                return new AjaxResult().call(new CustomJavaScript("""
                      console.log("dela")
                      """));
            }
        });


    }

    public void doSomething(ServerHandler lambda) {

        // Get the declaring class of the lambda function.
        Class<?> declaringClass = lambda.getClass().getDeclaringClass();
        log.debug("{}", declaringClass);
        // Check if the lambda function has the @Deprecated annotation.
        boolean isDeprecated = lambda.getClass().isAnnotationPresent(Deprecated.class);


        // Get the parameter types of the lambda function.
        TypeVariable<? extends Class<? extends ServerHandler>>[] parameterTypes = lambda.getClass().getTypeParameters();
        log.debug(" {} ", Arrays.toString(parameterTypes));

    }
}
