package org.oszz.ox.core.filter;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.oszz.ox.common.utils.ClassUtils;
import org.oszz.ox.core.message.IMessage;
import org.oszz.ox.core.message.IMessageHandler;
import org.oszz.ox.core.message.MessageCodeMappingHolder;
import org.oszz.ox.core.message.MessageProcesserType;

/**
 * 过滤Get请求的数据
 * @author ZZ
 *
 */
public class DoGetDataFilter implements IFilter {
	
	private static final String CODE_KEY = "code";
	
	private boolean isDebug = false;//是否是debug
	
	/**
	 * 构建一个GET请求的过滤器<br>
	 * 注：只有debug模式，才会接受GET请求
	 */
	public DoGetDataFilter(){
	}
	public boolean isDebug() {
		return isDebug;
	}
	@Override
	public void setDebug(boolean isDebug) {
		this.isDebug = isDebug;
	}


	@Override
	public IMessage doInputFilter(HttpServletRequest request){
		IMessage message = null;
		if(isDebug){
			try {
				Map<String, String[]> paraMaps = request.getParameterMap();
				short code = Short.parseShort(paraMaps.get(CODE_KEY)[0]);
				
				Class<? extends IMessage> msgClass = MessageCodeMappingHolder.getInstance().getMessageClass(code);
				IMessageHandler msgHandler = MessageCodeMappingHolder.getInstance().getMessageHandler(code);
				MessageProcesserType messageProcesserType = MessageCodeMappingHolder.getInstance().getMessageProcesserType(code);
				message = ClassUtils.newInstance(msgClass);
				message.toProtobufMessage(toJson(paraMaps), message.getProtobufMessageClass());
				message.setMsgHandler(msgHandler);
				message.setMessageProcesserType(messageProcesserType);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return message;
	}
	
	public JSONObject toJson(Map<String, String[]> paraMaps) {
	    JSONObject json = new JSONObject();
	    for(Map.Entry<String, String[]> paraEntry : paraMaps.entrySet()){
	    	String key = paraEntry.getKey();
	    	String value = paraEntry.getValue()[0];
	    	json.put(key, value);
	    }
	    return json;
	}

}
