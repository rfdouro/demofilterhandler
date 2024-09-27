package br.rfdouro.models;

import br.rfdouro.util.CustomAnotacao;

public class Livro {
 private String titulo;

 public Livro() {
 }

 public Livro(String titulo) {
  this.titulo = titulo;
 }

 public String getTitulo() {
  return titulo;
 }

 public void setTitulo(String titulo) {
  this.titulo = titulo;
 }

 @Override
 @CustomAnotacao(valor = "método tostring")
 public String toString() {
  return "Livro [titulo=" + titulo + "]";
 }

}
