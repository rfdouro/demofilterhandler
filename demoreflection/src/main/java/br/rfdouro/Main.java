package br.rfdouro;

import java.lang.reflect.Constructor;
import java.util.stream.Stream;

import br.rfdouro.util.CustomAnotacao;

public class Main {
 @SuppressWarnings({ "rawtypes", "unchecked" })
 public static void main(String[] args) throws Exception {
  Class c = Class.forName("br.rfdouro.models.Livro");
  // Lista os atributos da classe
  Stream.of(c.getDeclaredFields())
    .forEach(a -> System.out.println(
      "campo Nome: " + a.getName() + "\n" +
        "Tipo: " + a.getType() + "\n" +
        "************"));

  // lista os métodos da classe
  Stream.of(c.getDeclaredMethods())
    .forEach(m -> System.out.println(
      "metodo Nome: " + m.getName() + "\n" +
        "Tipo: " + m.getReturnType() + "\n" +
        "************"));

  // lista os métodos da classe anotados com a CustomAnotacao
  Stream.of(c.getDeclaredMethods())
    .forEach(m -> {
     if (m.isAnnotationPresent(CustomAnotacao.class))
      System.out.println(
        "@CustomAnotacao Nome: " + m.getName() + "\n" +
          "Tipo: " + m.getReturnType() + "\n" +
          "************");
    });

  Constructor constru = c.getConstructor(String.class);
  Object obj = constru.newInstance("A história do ROcK");
  System.out.println(obj);

 }
}