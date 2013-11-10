package com.navinf.learntocode;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.*;
import javax.tools.JavaCompiler;
import javax.tools.SimpleJavaFileObject;
import javax.tools.ToolProvider;

import org.fife.ui.rtextarea.*;
import org.fife.ui.rsyntaxtextarea.*;

public class LearnToCode {
	private static volatile LearnToCode instance;

	private JFrame frame;
	private RSyntaxTextArea textArea = new RSyntaxTextArea(20, 60);

	/**
	 * Launch the application.
	 */
	@SuppressWarnings("serial")
	public static void main(String[] args) {
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
			final String code = LearnToCode.instance.textArea.getText();
			ArrayList<SimpleJavaFileObject> java_files = new ArrayList<SimpleJavaFileObject>(){{
				add(new JavaSourceFromString("Main",code));
			}};
			jc.getTask(null, null, null, null, null, java_files);
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
