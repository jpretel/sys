package dao;

import entity.Usuario;
import core.security.Encryption;

public class UsuarioMain extends Encryption{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Usuario usuario = new Usuario();
		UsuarioDAO udao = new UsuarioDAO();
		String usu, clave;
		//Encryption enc = new Encryption();
		
		usu = encrypt("carlos");
		clave = encrypt("123456");
		
		usuario.setEncUsu(usu);		
		usuario.setUsuario(decrypt(usu));
		usuario.setClave(decrypt(clave));
		usuario.setEncClave(clave);
		usuario.setId(udao.findAll().size()+1);
		
		udao.crear_editar(usuario);
		
		for(Usuario u : udao.findAll()){
			System.out.println("Usuario:  " + u.getUsuario() + "  Clave:  " + u.getClave());
			System.out.println("UsuarioEncr:  " + u.getEncUsu() + "  ClaveEncr:  " + u.getEncClave());
			System.out.println("-----------------------------------------------------");
		}

	}

}
