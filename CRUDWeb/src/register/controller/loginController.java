package register.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import register.model.Usuario;

@Controller
public class loginController {
	@RequestMapping("loginForm")
	public String loginForm() {
		return "formularioLogin";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/efetuaLogin")
	public String efetuaLogin(Usuario usuario, HttpSession session) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("cadastro");
		EntityManager manager = factory.createEntityManager();
		Query query = manager.createQuery("SELECT a FROM Usuario as a WHERE a.login = :login AND a.senha = :senha");
		query.setParameter("login", usuario.getLogin());
		query.setParameter("senha", usuario.getSenha());
		List<Usuario> user = query.getResultList();
		boolean userFound = false;
		
		if (user.isEmpty()) {
			userFound = false;
		} else {
			userFound = true;
		}
		
	if(userFound) {
		session.setAttribute("usuarioLogado", usuario);
		return "menu";
	}
		return "redirect:loginForm";
	}
	
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:loginForm";
	}
}
