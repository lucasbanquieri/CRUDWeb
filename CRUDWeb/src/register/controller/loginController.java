package register.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import register.dao.UsuarioDAO;
import register.model.Usuario;

@Controller
public class loginController {
	@RequestMapping("loginForm")
	public String loginForm() {
		return "formularioLogin";
	}
	
	@RequestMapping("/efetuaLogin")
	public String efetuaLogin(Usuario usuario, HttpSession session) {
	if(new UsuarioDAO().existeUsuario(usuario)) {
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
