package com.ifly.transporter.conf;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 常量配置application.yml文件 对应属�?解析
 * @author zhangguan
 */
@Component
public class ConstantsConfig {
	
	
	@Value("${upload.filePath}")
	private String filePath;
	
	@Value("${repetition.collect}")
	private boolean collect;
	
	@Value("${repetition.filter}")
	private boolean filter;
	
	/**
	 * 打印�?��常量配置是否正确
	 */
	@PostConstruct
	public void PostConstruct(){
		System.out.println("####################application.yml config start####################");
		System.out.println("upload.filePath value:"+filePath);
		System.out.println("repetition.collect value:"+collect);
		System.out.println("repetition.filter value:"+filter);
		
		System.out.println("####################application.yml config end####################");
	}

	public String getFilePath() {
		return filePath;
	}

	public boolean isCollect() {
		return collect;
	}

	public boolean isFilter() {
		return filter;
	}
	
}
