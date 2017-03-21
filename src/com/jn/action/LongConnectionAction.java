package com.jn.action;

import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

public class LongConnectionAction {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NioSocketAcceptor socket = new NioSocketAcceptor();
		socket.setHandler(new ConnectionHandel());
		socket.getFilterChain().addLast("filter", new ProtocolCodecFilter(new TextLineCodecFactory()));
	}

}
