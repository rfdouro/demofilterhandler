package br.rfdouro.demointerceptor.handlers;

import java.lang.reflect.Method;

import org.springframework.stereotype.Controller;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import br.rfdouro.demointerceptor.DemointerceptorApplication;
import br.rfdouro.demointerceptor.annotations.VerificaAcesso;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RequestInterceptor implements HandlerInterceptor {
 @Override
 public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
  DemointerceptorApplication.logger.info("lógica de pré processamento");
  HandlerMethod hm = (HandlerMethod) handler;
  Method method = hm.getMethod();
  boolean verificado = true;
  if (method.getDeclaringClass().isAnnotationPresent(Controller.class)) {
   if (method.isAnnotationPresent(VerificaAcesso.class)) {
    verificado = false;

    String[] permissao = method.getAnnotation(VerificaAcesso.class).permissao();

    for (String verifica : permissao) {
     if (verifica.equals(request.getSession().getAttribute("autorizacao"))) {
      verificado = true;
      break;
     }
    }
   }
  }
  if (!verificado) {
   request.getRequestDispatcher("/login").forward(request, response);
  }
  return true;
 }

 @Override
 public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
   ModelAndView modelAndView) throws Exception {
  DemointerceptorApplication.logger.info("lógica de pós processamento");
 }

 @Override
 public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
   throws Exception {
  DemointerceptorApplication.logger.info("após processar a lógica");
 }
}
