package com.ifly.transporter.utils;

import java.util.Random;

public class CaptchaUtil{  
	
	public static String generateCaptcha(){
		Random rad=new Random();  
        String result  = rad.nextInt(1000000) +"";  
        if(result.length()!=6){  
            return generateCaptcha();  
        }  
		return result;
	}
}  