package br.rfdouro.demointerceptor.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.rfdouro.demointerceptor.annotations.VerificaAcesso;
import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {

 @Autowired
 HttpSession session;

 @GetMapping({ "/", "/index" })
 public String index() {
  return "index";
 }

 @GetMapping("/login")
 public String login() {
  return "login";
 }

 @PostMapping("login")
 public String login(@RequestParam String usuario) {
  if (usuario != null) {
   session.setAttribute("usuario", usuario);
   session.setAttribute("autorizacao", "admin");
   return "redirect:/index";
  }
  return "login";
 }

 @GetMapping("/logout")
 public String deslogar() {
  session.removeAttribute("usuario");
  return "index";
 }

 @GetMapping({ "/protegido" })
 @VerificaAcesso(permissao = { "admin" })
 public String protegido() {
  return "protegido";
 }

}
