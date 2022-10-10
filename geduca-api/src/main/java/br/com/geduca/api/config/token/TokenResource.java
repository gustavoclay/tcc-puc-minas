package br.com.geduca.api.config.token;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Configuração para invalidar o refreshToken, Logout
 *
 * @author gustavoclay
 *
 */


@RestController
@RequestMapping("/token")
public class TokenResource {
	
	@Value("#{ @environment['geduca.enable-https'] }")
	private Boolean enableHttps;

	@DeleteMapping("/revoke")
	public void revoke(HttpServletRequest req, HttpServletResponse res) {
		Cookie cookie = new Cookie("refreshToken", null);
		cookie.setHttpOnly(true);
		cookie.setSecure(enableHttps);
		cookie.setPath(req.getContextPath() + "/oauth/token");
		cookie.setMaxAge(0);
		
		res.addCookie(cookie);
		res.setStatus(HttpStatus.NO_CONTENT.value());
	}
	
	@GetMapping("/autenticated")
	public ResponseEntity<?> autenticated() {
		return ResponseEntity.ok().build();
	}
}
