package com.navinf.learntocode;

import java.awt.EventQueue;
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

	private JFrame frame;
	private RSyntaxTextArea textArea = new RSyntaxTextArea(20, 60);

	/**
	 * Launch the application.
	 * @throws InterruptedException 
	 * @throws ClassNotFoundException 
	 */
	@SuppressWarnings("serial")
	public static void main(String[] args) throws InterruptedException, ClassNotFoundException {
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
		
		JavaCompiler jc = ToolProvider.getSystemJavaCompiler();
		
		while(LearnToCode.instance==null);
		while(true){
			final String code = "public class PlayerCodeImpl extends com.navinf.learntocode.PlayerCode{"+
								LearnToCode.instance.textArea.getText()+
								"}";
			ArrayList<SimpleJavaFileObject> java_files = new ArrayList<SimpleJavaFileObject>(){{
				add(new JavaSourceFromString("PlayerCodeImpl",code));
			}};
			List<String> compileOptions = Arrays.asList(new String[]{"-d", "bin"}) ;
			CompilationTask task = jc.getTask(null, null, null, compileOptions, null, java_files);
			if(!task.call())
				System.err.println("compile failed");
			else
				System.out.println("compile succeeded");
			Thread.sleep(300);
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
