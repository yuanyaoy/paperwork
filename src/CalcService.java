package calc;

import java.text.DecimalFormat;
import java.util.Stack;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class CalcService {
	
	public int keynum=0;
	public int brackets =0;

    public double memory=0;
	public double accumulate=0;
	public double oprator_number =0;
	
	public boolean actionable=false;
	public boolean point =false;
	
	public String Str="";
	
	private double op1;	
	private double op2;
	
	private JLabel jLabel ;
	private JTextField Jt1,Jt2,Jt3,Jt4;
	
	Stack<Double> stack1=null;
	Stack<Operator> stack2=null;
	
	private enum Operator {OpNone,OpLeft,OpAdd,OpSub,OpFang,OpMod,OpMul,OpDiv,OpExp,OpRight};
	public Operator calcuoperator=Operator.OpNone;
	
	//构造函数
	public CalcService(JLabel jl,JTextField jt2,JTextField jt3,JTextField jt1,JTextField jt4)
	{
		jLabel=jl;
		Jt1=jt2;
		Jt2=jt3;
		Jt3=jt1;
		Jt4=jt4;
		Jt1.setText("0");
		Jt2.setText("");
		Jt3.setText("");
		Jt4.setText("");
		calcuoperator=Operator.OpNone;
		jLabel.setText("欢迎使用科学计算器");
		stack1=new Stack<Double>();
		stack2=new Stack<Operator>();
		stack1.clear();
		stack2.clear();
		System.out.println("科学计算器已正常运行");
	}
	
	
	
	public void Input(String str)
	{   //加减乘除模基本操作
		if(str.equals("+"))
		{
			actionable=false;
			calcuoperator=Operator.OpAdd;
			Jt3.setText(Jt3.getText()+"+");		
			run();
			
			return;
		}
		if(str.equals("-"))
		{
			actionable=false;
			calcuoperator=Operator.OpSub;		
			Jt3.setText(Jt3.getText()+"-");
			run();
			
			return;
		}
		if(str.equals("×"))
		{
			actionable=false;
			calcuoperator=Operator.OpMul;	
			Jt3.setText(Jt3.getText()+"×");		
			run();
			
			return;
		}
		if(str.equals("/")){
			actionable=false;
			calcuoperator=Operator.OpDiv;		
			Jt3.setText(Jt3.getText()+"/");
			run();
			return;
		}
		if(str.equals("mod")) {
			actionable=false;
			calcuoperator=Operator.OpMod;
			run();
			Jt3.setText(Jt3.getText()+"mod");
			return;
		}
		//M存储加减操作
		if(str.equals("MS"))
	    {
	    	Jt4.setText("M");
			memory = Double.valueOf(Jt1.getText());
			return;
	    }
	    if(str.equals("MC"))
	    {
	    	Jt4.setText("");
			memory = 0;
			return;
		}
	    	
	    if(str.equals("M+"))
	    {
			memory+=Double.valueOf(Jt1.getText());
			return;
		}
	    if(str.equals("M-"))
	    {
			memory-=Double.valueOf(Jt1.getText());
			return;
		}
	    if(str.equals("MR"))
	    {
	    	Jt1.setText(String.valueOf(memory));
			return;
		}
	    //小数点、平方、立方等操作
		if(str.equals("."))
		{
			if(point ) {
			point =true;
			actionable=true;
				
			return;
			}
			else {
			point =true;
			actionable=true;
			Jt1.setText(Jt1.getText()+str);
			Jt3.setText(Jt3.getText()+str);
				
			return;
			}
			
		}
		if(str.equals("Exp")) {
			actionable=false;
			calcuoperator=Operator.OpExp;
			Jt3.setText(Jt3.getText()+"Exp");
			run();
			return;
		}
		if(str.equals("x^y")) {
			actionable=false;
			calcuoperator=Operator.OpFang;
			Jt3.setText(Jt3.getText()+"x^y");
			run();
			return;
		}
		
		if(str.equals("π")) {
			oprator_number =Math.PI;
			actionable=true;
			Jt3.setText(Jt3.getText()+"π");
			display2();
			return;
		}
		//三角函数
		if(str.equals("sin"))
		{
			oprator_number =oprator_number /180*Math.PI;
			oprator_number =Math.sin(oprator_number );
			accumulate=oprator_number ;
			actionable=false;
			display1();
			Jt4.setText(Jt1.getText());
			Jt3.setText("");
			
			return;
		}
		if(str.equals("cos"))
		{
			oprator_number =oprator_number /180*Math.PI;
			oprator_number =Math.cos(oprator_number );
			accumulate=oprator_number ;
			actionable=false;
			display1();	
			Jt4.setText(Jt1.getText());
			Jt3.setText("");
			return;
			
		}
		if(str.equals("tan"))
		{
		    oprator_number =oprator_number /180*Math.PI;
		    oprator_number =Math.tan(oprator_number );
		    accumulate=oprator_number ;
		    actionable=false;
		    display1();
		    Jt4.setText(Jt1.getText());
			Jt3.setText("");
		    return;
		}
		if(str.equals("log"))
		{
			oprator_number =Math.log(oprator_number );
			 
			display1();	
			Jt4.setText(Jt1.getText());
			Jt3.setText("");
			return;
		}
		if(str.equals("x²")) 
		{
			actionable=false;
			oprator_number =oprator_number *oprator_number ;
			accumulate=oprator_number ;
			display1();
			Jt4.setText(Jt1.getText());
			Jt3.setText("");
			return;
		}
		if(str.equals("x³"))
		{
			actionable=false;
			oprator_number =oprator_number *oprator_number *oprator_number ;;
			accumulate=oprator_number ;
			display1();
			Jt4.setText(Jt1.getText());
			Jt3.setText("");
			return;
		}
		if(str.equals("1/x"))
		{
			actionable=false;
			if(oprator_number ==0)
			{
			jLabel.setText("   除数不能为零！");
			}else
			oprator_number =1/oprator_number ;
			accumulate=oprator_number ;
			display1();		
			Jt4.setText(Jt1.getText());
			Jt3.setText("");
			return;
		}
		if(str.equals("√"))
		{
			actionable=false;
			oprator_number =Math.sqrt(oprator_number );
			accumulate=oprator_number ;
			display1();
			Jt4.setText(Jt1.getText());
			Jt3.setText("");
			return;
		}
		if(str.equals("n!"))
		{
			if(isIntegerForDouble(oprator_number )) {
				int n = (int) oprator_number ;
				int num=1;
				
				if(n>100)
				jLabel.setText("   超过运算范围！");
				else if(n<1)
				oprator_number =1;
				else {
				for(int i=1;i<=n;i++)
					num=num*i;
					oprator_number =num;
				}
				
				display1();
				Jt4.setText(Jt1.getText());
				Jt3.setText("");
				return;
			}
		}	
		if(str.equals("±"))
		{
			actionable=false;
			oprator_number =accumulate=(-1)*oprator_number ;
			display1();
			Jt3.setText(Jt1.getText());
			return;
		}
	    if(str.equals("e")) 
		{
			oprator_number =Math.E;
			actionable=true;
			display2();
			Jt3.setText(Jt3.getText()+"e");
			return;
		}
	    //等号
	    if(str.equals("="))
		{
			actionable=false;
			calcuoperator=Operator.OpNone;
			run();
			Jt4.setText(Jt1.getText());
			Jt3.setText("");
			return;
		}
	    //清除
	    if(str.equals("C"))
		{	
		op1=0;
		op2=0;
		accumulate=0;
		brackets =0;
		oprator_number =0;
		Str="";
		Jt1.setText("0");
		Jt2.setText("");
		Jt3.setText("");
		Jt4.setText("");
		point =false;
		actionable=false;
		stack1.clear();
		stack2.clear();
		calcuoperator=Operator.OpNone;
		jLabel.setText("欢迎使用科学计算器");
		return;
		}
	    //后退
		if(str.equals("←"))
		{
			String stra = Jt1.getText();
			
			
			if(Double.parseDouble(stra) > 0){
			if(stra.length() > 1){
			if((stra.charAt(stra.length()-1))=='.')
			point =true;
			oprator_number =Double.valueOf(stra.substring(0, stra.length()-1));
			Jt1.setText(stra.substring(0, stra.length()-1));
			}
			else{
			Jt1.setText("0");
	        oprator_number  = 0;
			}
			Jt3.setText(Jt3.getText().substring(0,Jt3.getText().length()-1));
			return;
			}else{
			if(stra.length() > 2){
			if((stra.charAt(stra.length()-1))=='.')
			point =true;
			Jt1.setText(stra.substring(0,stra.length() - 1));
			oprator_number =Double.valueOf(stra.substring(0, stra.length()-1));
			}else{
			Jt1.setText("0");
			oprator_number  = 0;
			}
			Jt3.setText(Jt3.getText().substring(0,Jt3.getText().length()-1));
			return;
			}
		}
		//左括号
	    if(str.equals("("))
		{
			brackets ++;
			Jt2.setText("( "+String.valueOf(brackets ));
			calcuoperator=Operator.OpLeft;
			actionable=false;
			Jt3.setText(Jt3.getText()+"(");
			run();
			return;
		}
	    //右括号
		if(str.equals(")"))
		{
			    if(brackets !=0)
			    {
				calcuoperator=Operator.OpRight;
				actionable=false;
				brackets --;
				if(brackets !=0)
					Jt2.setText("( "+String.valueOf(brackets ));
				else
					Jt2.setText("");
				stack1.push(oprator_number );
				Operator Op;
				while(stack2.peek()!=Operator.OpLeft)
				{
					Op=stack2.pop();
					calcu(Op);
				}
				oprator_number =accumulate=stack1.peek();
				display1();
				stack2.pop();
				stack1.pop();
				Jt3.setText(Jt3.getText()+")");
				return;
			    }
			    return;
		}
		//取数字
		if(!actionable)
		{
			oprator_number =0;
			point =false;
			keynum=0;
		}
		if(point)
		{
			keynum++;
			oprator_number =oprator_number +Integer.parseInt(str)/Math.pow(10,keynum);
			Jt3.setText(Jt3.getText()+str);
		}
		
		else {
			
			oprator_number =oprator_number *10+Integer.parseInt(str);
			Jt3.setText(Jt3.getText()+str);
		
			
		}
			
		actionable=true;
		display1();
	}
	//浮点数与整数确认
	public static boolean isIntegerForDouble(double doub) {  
		    double dou = 1e-10;    
		    if(doub <1) {
		    return false;
		    }
		    return doub-Math.floor(doub) < dou;  
		}
    //双操作符
	private int double_op()
	{
		if(stack1.empty())	return 0;
		op1=stack1.pop();
		if(stack1.empty())	return 0;
		op2=stack1.pop();
		return 1;
		
	}


 
	//显示1
	public void display1()
	{
		double lVal=(actionable) ? oprator_number :accumulate;
		
		DecimalFormat df = new DecimalFormat("#.####");
		String temp = df.format(lVal);
		
		Str=temp;
		if(Str.equals("Infinity"))
		{
			jLabel.setText("   超过运算范围！");
			Str="0";
		}
		Jt1.setText(Str);
		oprator_number =Double.valueOf(Str);
	}
	//显示2
	public void display2()
	{
		double lVal=(actionable) ? oprator_number :accumulate;
		
		Str=String.valueOf(lVal);

		if(Str.equals("Infinity"))
		{
			jLabel.setText("   超过运算范围！");
			Str="0";
		}
		Jt1.setText(Str);
		oprator_number =Double.valueOf(Str);

	}
	//运行函数
	public void run()
	{
		Operator Go;
		if(stack2.empty()||(calcuoperator==Operator.OpLeft)
		||calcuoperator.ordinal()>stack2.peek().ordinal())
		{
			if(calcuoperator==Operator.OpLeft)
			stack2.push(calcuoperator);
			else
			{
			stack2.push(calcuoperator);
			stack1.push(oprator_number );
			}
		}else
		{
		stack1.push(oprator_number );
		while(!stack2.empty() && calcuoperator.ordinal()<=stack2.peek().ordinal())
			{
			Go=stack2.pop();
			calcu(Go);
			}
			stack2.push(calcuoperator);
		}
		if(!stack1.empty())
		{
		oprator_number =accumulate=stack1.peek();
		display1();
		}
		if(calcuoperator==Operator.OpNone)
		{
		stack2.clear();
		stack1.clear();
		}
	}
	//计算函数
	public void calcu(Operator Opp)
	{
		int result;
		result=double_op();
		if(result==1)
		{
			
			switch(Opp)
			{
				case OpAdd:	stack1.push(op1+op2);break;
				case OpMul:stack1.push(op1*op2);break;
				case OpFang:stack1.push(Math.pow(op2,op1));break;
				case OpMod:stack1.push(op2%op1);break;
				case OpSub:stack1.push(op2-op1);break;
				case OpDiv:
					if(op1==0)
					jLabel.setText("   除数不能为零！");
					else
					stack1.push(op2/op1);
					break;
				case OpExp:
					stack1.push(op2*Math.pow(10, op1));
					break;
			default:
				break;
			}
		}else
		{
		stack1.clear();
		stack2.clear();
		}
		actionable=false;
	}

	

}
