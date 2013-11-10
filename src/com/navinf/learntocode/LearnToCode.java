package com.navinf.learntocode;

import java.awt.EventQueue;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
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
	private static volatile LearnToCode instance;

	public JFrame frame;
	public RSyntaxTextArea textArea = new RSyntaxTextArea(20, 60);

	/**
	 * Launch the application.
	 * @throws InterruptedException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, InterruptedException {
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
		LearnToCode.instance.textArea.setText("public void main(){\n"+
											  "\t\n"+
											  "}");
		compileLoop();
	}
	
	public static void compileLoop() throws InterruptedException, ClassNotFoundException{
		JavaCompiler jc = ToolProvider.getSystemJavaCompiler();
		while(true){
			final String code = "public class PlayerCodeImpl extends com.navinf.learntocode.PlayerCode{"+
								LearnToCode.instance.textArea.getText()+
								"}";
			@SuppressWarnings("serial")
			ArrayList<SimpleJavaFileObject> java_files = new ArrayList<SimpleJavaFileObject>(){{
				add(new JavaSourceFromString("PlayerCodeImpl",code));
			}};
			List<String> compileOptions = Arrays.asList(new String[]{"-d", "bin"}) ;
			CompilationTask task;
			try {
				task = jc.getTask(new PrintWriter("errors.log"), null, null, compileOptions, null, java_files);
			} catch (FileNotFoundException e1) {
				throw new AssertionError(e1);
			}
			if(!task.call()){
				System.err.println("[compile failed]");
				continue;
			}
			else
				System.out.println("[compile succeeded]");
			try {
				PlayerCode playerCode = (PlayerCode) new ClassReLoader(ClassReLoader.class.getClassLoader()).loadClass("PlayerCodeImpl").newInstance();
				playerCode.main();
			} catch (InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Thread.sleep(200);
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
		frame.add(sp);
		
		frame.setTitle("Text Editor Demo");
		frame.pack();
		frame.setLocationRelativeTo(null);
	}

}
