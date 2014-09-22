package dao;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import entity.GrupoUsuario;
import entity.GrupoUsuarioPrivilegio;
import entity.SysFormulario;
import entity.SysGrupo;
import entity.SysModulo;
import entity.SysOpcion;
import entity.Usuario;

public class SysModuloDAO extends AbstractDAO<SysModulo> {

	public SysModuloDAO() {
		super(SysModulo.class);
	}
	
	public List<SysModulo> getModulos(GrupoUsuario grupo){
		
		List<SysFormulario> formularios = new GrupoUsuarioPrivilegioDAO().getFormularioPorGrupoUsuario(grupo);
		
		System.out.println("Formularios: " + formularios);
		
		List<SysGrupo> opciones = new SysOpcionDAO().getPorFormularios(formularios);
		
		System.out.println("Grupos: " + opciones);
		
		//List<SysGrupo> grupos = new SysGrupoDAO().getPorOpciones(opciones);
		
		//System.out.println("Grupos: " + grupos);
		
		return null;
	}
}
