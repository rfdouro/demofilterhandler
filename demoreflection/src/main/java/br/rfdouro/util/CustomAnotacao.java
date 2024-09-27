package br.rfdouro.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// onde pode ser usada
@Target({ ElementType.METHOD, ElementType.FIELD })
// define a disponibilidade da anotação
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomAnotacao {
 // atributos da anotação
 String valor() default "";
 String[] valores() default {};
}
