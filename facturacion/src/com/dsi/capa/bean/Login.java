package com.dsi.capa.bean;

import java.io.Serializable;

public class Login implements Serializable{

	/** */
	private static final long serialVersionUID = 1L;
	
	private String userName;
	
	private String password;
	
	private  int   Number;
	private  int   Number2=(int) Math.floor(Math.random()*(100-1+1)+1);
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String login() {
		String forward = null;
		
		if(userName.equalsIgnoreCase("test") 
				&& password.equalsIgnoreCase("test")) {
			forward = "exito";
			//Number=60;
		} else {
			forward = "fallo";
			//Number=0;
		}
		
		return forward;
	}

	public int getNumber() {
		return Number;
	}

	public void setNumber(int number) {
		Number = number;
	}
	
public String obtenerNumero(String msg)
{
	
	 msg="";
if (Number == Number2 )
{
	msg="Registro Correcto";
	}
else

	if(Number <Number2)
	{
		msg="Dato erroneo y debajo del rango";
	}
	else
		if(Number >Number2)
		{
			msg="Dato Errone y Por Encima del rango";
		}
	return msg;
	}
}
