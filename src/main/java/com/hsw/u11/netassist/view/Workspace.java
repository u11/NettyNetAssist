package com.hsw.u11.netassist.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.apache.log4j.Logger;
import org.omg.CORBA.PRIVATE_MEMBER;

import com.hsw.u11.netassist.netty.NettyClient;
import com.hsw.u11.netassist.util.PropertyUtils;

import resource.Resource;

public class Workspace extends JFrame implements ActionListener {
	private NettyClient client = null;
	private static final Logger log = Logger.getLogger("login");
	/**
	 * 
	 */
	private static final long serialVersionUID = -5079550980286163160L;
	// 定义组件
	JPanel jp1, jp2, jp3 = null;// 面板
	JButton jb1, jb2;// 按钮
	JTextField jtf;// 文本
	JTextArea jta;//
	// 构造函数

	public Workspace() {
		// 创建面板

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();

		// 创建按钮
		jb1 = new JButton("连接");
		jb2 = new JButton("发送");
		jb1.addActionListener(this);
		jb2.addActionListener(this);

		// 创建文本框
		jtf = new JTextField(10);
		jta = new JTextArea(10, 20);
		jtf.setText(PropertyUtils.read("host"));
		jtf.setToolTipText("请输入IP或者域名");

		// 设置布局管理
		this.setLayout(new GridLayout(3, 1));// 网格式布局

		// 加入各个组件
		jp1.add(jtf);

		jp2.add(jta);

		jp3.add(jb1);
		jp3.add(jb2);

		// 加入到JFrame
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);

		// 设置窗体
		this.setTitle("NettyNetAssist v1.0");// 窗体标签
		this.setSize(400, 300);// 窗体大小
		this.setIconImage(Resource.logo.getImage());
		this.setLocationRelativeTo(null);// 在屏幕中间显示(居中显示)
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 退出关闭JFrame
		this.setVisible(true);// 显示窗体

		// 锁定窗体
		this.setResizable(false);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "连接") {
			final String host = jtf.getText().trim();
			if (host.length() < 3) {
				JOptionPane.showMessageDialog(null, "请输入域名或者IP！", "提示消息", JOptionPane.WARNING_MESSAGE);
				return;
			}
			jb1.setEnabled(false);
			PropertyUtils.write("host", host);
			if (null != client)client.close();
			client = new NettyClient(21868, host);
			log.info("success");

		} else if (e.getActionCommand() == "发送") {
			if (null == client) {
				JOptionPane.showMessageDialog(null, "请先连接", "提示消息", JOptionPane.WARNING_MESSAGE);
				return;
			}
			client.write(jta.getText());
		}
	}
	

}