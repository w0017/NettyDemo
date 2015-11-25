package client;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class HelloClientHandler extends SimpleChannelInboundHandler<String> {
	private int channelId=0;
	public int active=0;
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		
		System.out.println("Server say : " + msg);
	}
	
	

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("Client active ");
		active=1;
		super.channelActive(ctx);
	}



	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("Client close ");
		active=0;
		super.channelInactive(ctx);
	}



	public int getChannelId() {
		return this.channelId;
	}



	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}
}
