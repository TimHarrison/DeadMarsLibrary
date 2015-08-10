package com.cecilectomy.dmge.Utility;

import javax.swing.JOptionPane;

public class ErrorDialog {
	
	public static void show(String message) {
		JOptionPane.showMessageDialog(null, "Error: " + message);
	}

}
