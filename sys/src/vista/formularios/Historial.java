package vista.formularios;

import vista.Sys;
import dao.LogDAO;
import entity.Log;

public class Historial {
	
	static Log log = new Log();
	static LogDAO logDao = new LogDAO();
	
	public static void validar(String tipo, String historial, String nomFormulario){
		if(tipo.equals("Nuevo") || tipo.equals("Eliminar")){
			log.setIdlog(""+(logDao.findAll().size()+1));
			log.setUsuario(Sys.usuario.getIdusuario());
			log.setLog(historial);
			log.setTipo(tipo);
			log.setNomFormulario(nomFormulario);
			logDao.crear_editar(log);
		}		
	}
	
	public static void validar(String tipo, String logAnt, String historial, String nomFormulario){
		log.setIdlog(""+(logDao.findAll().size()+1));
		log.setUsuario(Sys.usuario.getIdusuario());
		log.setLog(logAnt + " POR " +historial);
		log.setTipo(tipo);
		log.setNomFormulario(nomFormulario);
		logDao.crear_editar(log);
	}
}
