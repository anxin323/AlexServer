 
package com.alex.network.pusher;
import com.alex.network.eneity.ImMessage;
/**
 * 消息发送实接口
 * 
 */
public interface CIMMessagePusher {

 
 
    /**
     * 向用户发送消息
     * @param msg
     */
	public void pushMessageToUser(ImMessage msg);

 
}
