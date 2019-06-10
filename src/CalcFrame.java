package calc;
import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;


import javax.swing.JTextField;

import javax.swing.JButton;

import java.awt.Font;

public class CalcFrame extends JFrame{
	private JPanel jp;
	private JLabel jl;
	//四块显示屏区域
	private JTextField jt1,jt2,jt3,jt4;
	private JButton operate[];
	private String key[]={"MC","MR","MS","M+","M-",
			              "←", "C", "±", "√", "log",
			              "7", "8", "9", "×", "/",
			              "4", "5", "6", "+", "-",
			              "1", "2", "3", "=", ".",
			              "0", "e","π","x²", "x³",
			              "1/x","n!","sin","cos","tan",
			             "Exp", "mod","x^y","(",")"};
			             
	private String or;
	private CalcService calculator=null;
	public CalcFrame() {
		super("科学计算器");
		this.setLayout(null);
		this.setSize(400,560);
		this.setResizable(false);
		this.getContentPane().setBackground(new Color(217,228,241));
		
		// 设置四块显示屏
		
		jp = new JPanel();
		setContentPane(jp);
		jp.setLayout(null);
		jl = new JLabel("科学计算器");
		jl.setFont(new Font("宋体", Font.PLAIN, 25));
		jl.setBounds(80, 0, 240,50);
		jp.add(jl);
		jt1=new JTextField("");
		jt2=new JTextField("0");
		jt3=new JTextField("");
		jt4=new JTextField("");
		jt1.setEnabled(false);
		jt2.setEnabled(false);
		jt3.setEnabled(false);
		jt4.setEnabled(false);
		jt1.setBounds(119, 45, 260, 35);
		jt2.setBounds(59, 80, 320, 40);
		jt3.setBounds(19, 80, 40, 40);
		jt4.setBounds(19, 45, 100, 35);
		jt1.setHorizontalAlignment(JLabel.RIGHT);
		jt2.setHorizontalAlignment(JLabel.RIGHT);
		jt3.setHorizontalAlignment(JLabel.CENTER);
		jt4.setHorizontalAlignment(JLabel.RIGHT);
		jt1.setFont(new Font("宋体",Font.PLAIN,17));
		jt2.setFont(new Font("宋体",Font.BOLD,25));
		jt3.setFont(new Font("宋体",Font.PLAIN,15));
		jt4.setFont(new Font("宋体",Font.PLAIN,17));
		jt1.setDisabledTextColor(Color.BLACK);
		jt2.setDisabledTextColor(Color.BLACK);
		jt3.setDisabledTextColor(Color.BLACK);
		jt4.setDisabledTextColor(Color.BLACK);
		jt1.setBorder(new LineBorder(new Color(242,247,252)));
		jt2.setBorder(new LineBorder(new Color(242,247,252)));
		jt3.setBorder(new LineBorder(new Color(242,247,252)));
		jt4.setBorder(new LineBorder(new Color(242,247,252)));
		jt1.setBackground(new Color(242,247,252));
		jt2.setBackground(new Color(242,247,252));
		jt3.setBackground(new Color(242,247,252));
		jt4.setBackground(new Color(242,247,252));
		
		//添加按钮
		
		int i;
		operate=new JButton[41];
		
		for(i=0;i<key.length;i++)
		{
			operate[i]=new JButton(key[i]);
			operate[i].setMargin(new java.awt.Insets(0,0,0,0)); 
			operate[i].setBounds(i%5*(35+35)+28, 120+i/5*(29+20)+5, 60, 40);
			operate[i].setFont(new Font("宋体",Font.PLAIN,15));
			jp.add(operate[i]);
		}
		
		//添加鼠标监视器
		
		AL al=new AL();
		//keyAdapter kl=new keyAdapter();
		
		for(i=0;i<key.length;i++)
		{
			operate[i].addActionListener(al);
			//operate[i].addKeyListener(kl);
			operate[i].setBackground(new Color(234,240,247));
			operate[i].setForeground(new Color(30,57,91));
		}
		jp.add(jt1);
		jp.add(jt2);
		jp.add(jt3);
		jp.add(jt4);
		
		//jp.addKeyListener(kl);
		
		this.setVisible(true);
		calculator=new CalcService(this.jl,this.jt2,this.jt3,this.jt1,this.jt4);
	}/*
			 * 用ActionEvent对象的getActionCommand()方法
			 * 取得与引发事件对象相关的字符串
			 */
	class AL implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			
			or=e.getActionCommand();
			calculator.Input(or);
		}
	}
	

	
	public static void main(String[] args) {
		CalcFrame cal = new CalcFrame();
	
	}
		
}
