package org.oszz.ox.server.module.info.msg;

import org.oszz.ox.core.message.AbstractMessage;
import org.oszz.ox.server.base.message.MessageCode;

import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.Message;

/**
 * 错误提示信息<br>
 * Auto Generator, Don't Modify .
 * @author ZZ
 *
 */
public class InfoProtoGCErrorPromptMessage extends AbstractMessage {

	public InfoProtoGCErrorPromptMessage() {
		
	}

	public InfoProtoGCErrorPromptMessage(Message protoMsg) {
		super(protoMsg);
	}

	@Override
	public short getCode() {
		return MessageCode.INFO_PROTO_G_C_ERROR_PROMPT;
	}

	@Override
	public Class<? extends GeneratedMessage> getProtobufMessageClass() {
		return org.oszz.ox.server.module.info.msg.InfoProto.GCErrorPrompt.class;
	}

}
