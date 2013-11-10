package com.navinf.learntocode;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.*;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.SimpleJavaFileObject;
import javax.tools.ToolProvider;

import org.fife.ui.rtextarea.*;
import org.fife.ui.rsyntaxtextarea.*;

import com.navinf.learntocode.PlayerCode;

public class LearnToCode {
	@Deprecated
	private static volatile LearnToCode instance;

	public JFrame frame;
	public RSyntaxTextArea textArea = new RSyntaxTextArea(20, 60);

	private JLabel status = new JLabel("Ready");

	/**
	 * Launch the application.
	 * @throws InterruptedException 
	 * @throws ClassNotFoundException 
	 * @throws InvocationTargetException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, InterruptedException, InvocationTargetException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LearnToCode window = new LearnToCode();
					window.frame.setVisible(true);
					LearnToCode.instance=window;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		while(LearnToCode.instance==null);
		LearnToCode.instance.compileLoop();
	}
	
	public void compileLoop() throws InterruptedException, ClassNotFoundException, InvocationTargetException{
		JavaCompiler jc = ToolProvider.getSystemJavaCompiler();
		SwingUtilities.invokeAndWait(new Runnable(){public void run(){
		textArea.setText("public void main(){\n"+
				  "\t\n"+
				  "}");
		}});
		String last_code = null;
		boolean compiled = false;
		boolean ran = false;
		while(true){
			final String code = "import java.util.*;\n"+
								"import com.navinf.learntocode.*;\n"+
								"import static com.navinf.learntocode.GamePanel.*;\n"+
								"public class PlayerCodeImpl extends com.navinf.learntocode.PlayerCode{"+
								"public Mobile player = (Mobile)GamePanel.me.player;\n"+
								textArea.getText()+
								"}";
			if(!code.equals(last_code)){
				last_code=code;
				@SuppressWarnings("serial")
				ArrayList<SimpleJavaFileObject> java_files = new ArrayList<SimpleJavaFileObject>() {{
						add(new JavaSourceFromString("PlayerCodeImpl", code));
					}
				};
				List<String> compileOptions = Arrays.asList(new String[] {
						"-d", "bin" });
				CompilationTask task = null;
				try {
					task = jc.getTask(new PrintWriter("errors.log"), null,
							null, compileOptions, null, java_files);
				} catch (FileNotFoundException e1) {
					System.out.println(e1);
				}
				if (!task.call()) {
					System.err.println("[compile failed]");
					compiled=false;
				} else{
					System.err.println("[compile succeeded]");
					compiled=true;
					ran=false;
				}
				status.setText(GamePanel.el.last);
			}
			if (compiled&&!ran){
				Thread playerCodeExecutor = new Thread(){public void run(){
					try {
						PlayerCode playerCode = (PlayerCode) new ClassReLoader(
								ClassReLoader.class.getClassLoader()).loadClass(
										"PlayerCodeImpl").newInstance();
						playerCode.main();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}};
				playerCodeExecutor.start();
				playerCodeExecutor.join(3000);
				if(playerCodeExecutor.isAlive())
					playerCodeExecutor.stop();//XXX
				status.setText(GamePanel.el.last+":\t "+ (GamePanel.pl.last!=null?GamePanel.pl.last:""));
				ran=true;
			}
			Thread.sleep(500);
		}
	}

	/**
	 * Create the application.
	 */
	public LearnToCode() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
		textArea.setCodeFoldingEnabled(true);
		RTextScrollPane sp = new RTextScrollPane(textArea);
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(sp);
		panel.add(status, BorderLayout.SOUTH);
		frame.add(panel);
		
		frame.setTitle("Text Editor Demo");
		frame.pack();
		frame.setLocationRelativeTo(null);
	}

}
