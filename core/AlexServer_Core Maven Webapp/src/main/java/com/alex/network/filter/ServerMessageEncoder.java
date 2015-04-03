package com.alex.network.filter;



import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

import com.alex.network.eneity.CIMConstant;
import com.alex.network.handler.MainIOHandler;

/**
 * 服务端发送消息前编码，可在此加密消息
 *
 */
public class ServerMessageEncoder extends ProtocolEncoderAdapter {

	protected final Log logger = LogFactory.getLog(ServerMessageEncoder.class);
	@Override
	public void encode(IoSession iosession, Object message, ProtocolEncoderOutput out) throws Exception {
		//logger.info("encode:"+message.toString().getBytes("UTF-8"));
		IoBuffer buff = IoBuffer.allocate(320).setAutoExpand(true);
		buff.put(message.toString().getBytes("UTF-8"));
	    buff.put(CIMConstant.MESSAGE_SEPARATE);
	    buff.flip();
		out.write(buff);
	}
	
	 

}
