 
package com.alex.network.handler;

/**
 *  请求处理接口,所有的请求实现必须实现此接口
 *  
 */

import com.alex.network.eneity.CIMSession;
import com.alex.network.eneity.ReplyBody;
import com.alex.network.eneity.SentBody;
 
public    interface   CIMRequestHandler  {

    public abstract ReplyBody process(CIMSession ios,SentBody message);
}