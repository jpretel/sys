package vista.utils;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;
import java.util.Hashtable;

import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;

import vista.barras.BarraMaestro;
import vista.formularios.FrmCuentas;
import vista.formularios.FrmGrupos;

public class menus extends JMenuBar implements ActionListener{
	public String VectOpc[][]; // Vector de Opciones
	public String MetodoElegido = ""; //Nombre del Metodo a ejecutar
	public int LngOpc; //Longitud de Opciones
	public int LngVector;
	public JInternalFrame Formularios[];
	public JToolBar barra;
	public menus(JToolBar barra){
		this.barra = barra;
		InicializarForms();
	}
	
	public  menus(String VectOpc[][],String MetodoElegido){
		
		this.VectOpc = VectOpc;		
		this.LngVector = this.VectOpc.length;
		this.MetodoElegido = MetodoElegido;
		JMenu objMenu; 
		JMenuItem objItem;
		
		setLayout(new FlowLayout(FlowLayout.LEFT));
		
		Hashtable lista = new Hashtable();		
		for (int i = 0; i < LngVector ;i++){					
			//Si tiene Hijos debe ser un SubItem
			if(TieneHijos(this.VectOpc[i][0])){
				objMenu = new JMenu((this.VectOpc[i][0]));
				objMenu.setName(this.VectOpc[i][0]);
				objMenu.setText(this.VectOpc[i][1].replace('-',' ').trim());
				objMenu.setMnemonic(EncuentraMnemoico(this.VectOpc[i][1],'_'));
				lista.put(this.VectOpc[i][0], objMenu);
			}else{
				objItem = new JMenuItem((this.VectOpc[i][0]));
				objItem.setName(this.VectOpc[i][0]);
				objItem.setText(this.VectOpc[i][1].replace('-',' ').trim());
				char Nemonico = EncuentraMnemoico(this.VectOpc[i][1],'_');
				objItem.setMnemonic(Nemonico);			
				objItem.setAccelerator(KeyStroke.getKeyStroke( 
						 Nemonico, ActionEvent.ALT_MASK));
				objItem.setActionCommand(this.VectOpc[i][0]);
				objItem.addActionListener(this);
				lista.put(this.VectOpc[i][0], objItem);
			}
				
		}
		
		String idPapa;
		//Solo para comparar las clases
		JMenu ObjMenuTmp = new JMenu();
		for(int i = 0; i < this.LngVector ; i++){
			if(EsPrincipal(this.VectOpc[i][0])){
				if(lista.get(this.VectOpc[i][0]) instanceof JMenu){
					add((JMenu)lista.get(VectOpc[i][0]));
				}else{
					add((JMenuItem)lista.get(VectOpc[i][0]));
				}
			}else{
				idPapa = this.VectOpc[i][0].substring(0,this.VectOpc[i][0].length() -1);
				objMenu = (JMenu)lista.get(idPapa);				
				if(lista.get(this.VectOpc[i][0]).getClass() == ObjMenuTmp.getClass()){
					objMenu.add((JMenu)lista.get(VectOpc[i][0]));
				}else{
					
					objMenu.add((JMenuItem)lista.get(VectOpc[i][0]));
				}
			}
		}
		
	}	

	private void InicializarForms() {
		//formulario= FrmCuentas.class;		
		int index = 0;
		int nElementos = 1 ;
		FrmCuentas frmCuentas = new FrmCuentas((BarraMaestro) this.barra);
		nElementos = nElementos + 1;
		FrmGrupos frmGrupos = new FrmGrupos((BarraMaestro) this.barra);
		Formularios = new JInternalFrame[nElementos];		
		Formularios[index] = frmCuentas;
		index = index + 1;
		Formularios[index] = frmGrupos;		
	}
	
	public JInternalFrame getFormulario(int index){		
		return Formularios[index];
	}


	private boolean EsPrincipal(String item) {
		return (item.length() == 1);		
	}


	private char EncuentraMnemoico(String cad, char c) {
		int pos = cad.indexOf(c);
		if(pos == -1){
			return 0;
		}
		return cad.charAt(pos +1);	
		
	}


	private boolean TieneHijos(String item) {
		// TODO Auto-generated method stub
		int NVeces = 0;
		int lngItem = item.length();
		for(int i = 0; i<LngVector ;i++){
			if(this.VectOpc[i][0].length() >= lngItem){
				if(this.VectOpc[i][0].substring(0,lngItem).equals(item)){
					NVeces++;
					if (NVeces > 1){
						return true;
					}
				}
			} 
		}
		return (NVeces > 1);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		 try { 
			 /* Si el contexto en que se utiliza el objeto MiMenu es 
			 una ventana, dentro de la cual hay una barra de menús, 
			 extendiendo la barra MiMenu, el objeto que instancia 
			 la clase está en la tercera generación. Si este no es el 
			 caso, habrá que alterar la instrucción, referenciando 
			 el objeto padre en la generación correcta */ 
			 Object objPapa = getParent().getParent().getParent(); 
			 Class MiPapa = 
			 getParent().getParent().getParent().getClass(); 
			 /* Estableciendo que los parámetros del método de acciones 
			 en la clase que llama a MiMenu son de tipo String y 
			 pasando como argumento a dicho método la clave de la 
			 opción seleccionada */ 
			 Class[] TiposParametros = new Class[] {String.class}; 
			 Object[] argumentos = new Object[] {e.getActionCommand()}; 
			 /* Definiendo el método de acciones de la clase que llama 
			 a MiMenu y sus parámetros para luego invocarlo 
			 ocasionando su ejecución */ 
			 Method target = objPapa.getClass().getMethod( 
					 this.MetodoElegido, TiposParametros); 
			 Object param[] = { e.getActionCommand() }; 
			 target.invoke(objPapa,argumentos); 
			 
			 } catch ( Exception exp ) { 
			 exp.printStackTrace(); 
			 }
		
		
		
	}

}
