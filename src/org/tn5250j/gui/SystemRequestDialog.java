/**
 * $Id$
 *
 * Title: tn5250J
 * Copyright:   Copyright (c) 2001,2009
 * Company:
 * @author: master_jaf
 *
 * Description:
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this software; see the file COPYING.  If not, write to
 * the Free Software Foundation, Inc., 59 Temple Place, Suite 330,
 * Boston, MA 02111-1307 USA
 *
 */
package org.tn5250j.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Small dialog asking the user to enter a value for doing a system request.
 *
 * @author master_jaf
 */
public class SystemRequestDialog {

	private final static String[] OPTIONS = new String[] { "SysReq", "Cancel" };

	private final Component parent;

	private JDialog dialog;
	private JOptionPane pane;
	private JTextField text;


	/**
	 * @param parent
	 */
	public SystemRequestDialog(Component parent) {
		super();
		this.parent = parent;
		initLayout();
	}

	private void initLayout() {
		JPanel srp = new JPanel();
		srp.setLayout(new BorderLayout());
		JLabel jl = new JLabel("Enter alternate job");
		text = new JTextField();
		srp.add(jl, BorderLayout.NORTH);
		srp.add(text, BorderLayout.CENTER);
		Object[] message = new Object[1];
		message[0] = srp;

		pane = new JOptionPane(message, // the dialog message array
				JOptionPane.QUESTION_MESSAGE, // message type
				JOptionPane.DEFAULT_OPTION, // option type
				null, // optional icon, use null to use the default icon
				OPTIONS, // options string array, will be made into buttons
				OPTIONS[0]);

		dialog = pane.createDialog(parent, "System Request");

		// add the listener that will set the focus to the desired option
		dialog.addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) {
				text.requestFocus();
			}
		});

	}

	/**
	 * Shows the dialog and returns the given input
	 * or null if the operation was canceled.
	 *
	 * @return
	 */
	public String show() {
		String result = null;
		dialog.setVisible(true);
		if (OPTIONS[0].equals(pane.getValue())) {
			result = text.getText();
		}
		return result;
	}

}
