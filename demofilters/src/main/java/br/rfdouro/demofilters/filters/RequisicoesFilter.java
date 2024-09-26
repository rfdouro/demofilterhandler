package br.rfdouro.demofilters.filters;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import br.rfdouro.demofilters.DemofiltersApplication;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * aplica o filtro a todas as URLs da aplicação
 */
@WebFilter(urlPatterns = { "/protegido" })
public class RequisicoesFilter implements Filter {

  @Autowired
  HttpSession session;

  private static final Set<String> CAMINHOS_PERMITIDOS = Collections.unmodifiableSet(new HashSet<>(
        Arrays.asList("", "/login", "/logout", "/index")));

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    DemofiltersApplication.logger.info("filtro de requisicoes iniciado");
  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
      throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) servletRequest;
    HttpServletResponse response = (HttpServletResponse) servletResponse;

    String path = request.getRequestURI().substring(request.getContextPath().length()).replaceAll("[/]+$", "");

    DemofiltersApplication.logger.info("filtro de requisicoes doFilter em " + path);

    boolean logado = (session != null && session.getAttribute("usuario") != null);
    boolean caminhoPermitido = CAMINHOS_PERMITIDOS.contains(path);

    if (logado || caminhoPermitido)
      filterChain.doFilter(servletRequest, servletResponse);
    else
      response.sendRedirect(request.getContextPath() + "/login");
  }

  @Override
  public void destroy() {
    DemofiltersApplication.logger.info("filtro de requisicoes destruído");
  }

}
