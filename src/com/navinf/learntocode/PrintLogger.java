package com.navinf.learntocode;
import java.io.OutputStream;
import java.io.PrintStream;


public class PrintLogger extends PrintStream{

	public PrintLogger(OutputStream out) {
		super(out);
	}
	
	public String last;
	
	public void println(String s) {
		last=s;
		super.println(s);
	}

}
