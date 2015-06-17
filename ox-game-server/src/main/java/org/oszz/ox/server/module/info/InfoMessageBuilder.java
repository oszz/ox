package org.oszz.ox.server.module.info;

import org.oszz.ox.server.module.info.msg.InfoProto;
import org.oszz.ox.server.module.info.msg.InfoProtoGCErrorPromptMessage;

/**
 * info消息的构建者
 * @author ZZ
 *
 */
public class InfoMessageBuilder {

	/**
	 * 构建一个错误提示的消息
	 * @author ZZ
	 * @return
	 */
	public static InfoProtoGCErrorPromptMessage buildGCErrorPrompt(String contents){
		InfoProto.GCErrorPrompt.Builder builder = InfoProto.GCErrorPrompt.newBuilder();
		builder.setContents(contents);
		InfoProto.GCErrorPrompt pmsg = builder.build();
		return new InfoProtoGCErrorPromptMessage(pmsg);
	}
}
