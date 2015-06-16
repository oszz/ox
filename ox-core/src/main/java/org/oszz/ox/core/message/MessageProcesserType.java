package org.oszz.ox.core.message;

/**
 * 消息处理器类型
 * @author ZZ
 *
 */
public enum MessageProcesserType {

	/**
	 * 异步的处理器
	 */
	ASYN("ASYN"),
	/**
	 * 场景的处理器
	 */
    SCENE("SCENE"),
    /**
     * 世界的处理器
     */
    WORLD("WORLD"),
    
    ;
    
    private String value;
    
    private MessageProcesserType(String value){
    	this.value = value;
    }
    
    public String valueOf(){
    	return this.value;
    }
}
