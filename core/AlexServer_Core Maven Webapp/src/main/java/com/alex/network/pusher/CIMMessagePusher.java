 
package com.alex.network.pusher;
import com.alex.network.eneity.ImMessage;
/**
 * ��Ϣ����ʵ�ӿ�
 * 
 */
public interface CIMMessagePusher {

 
 
    /**
     * ���û�������Ϣ
     * @param msg
     */
	public void pushMessageToUser(ImMessage msg);

 
}
