package client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HelloClient {
	
	public String host = "127.0.0.1";
	public int port = 7878;
	public HelloClientInitializer initer=null;
	public Channel ch = null;
	
	public HelloClient createConn(String host, int port, int channelId){
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			initer = new HelloClientInitializer();
			initer.setChannelId(channelId);
			b.group(group)
			.channel(NioSocketChannel.class)
			.handler(initer);

			// 连接服务端
			ch = b.connect(host, port).sync().channel();
			
			// 控制台输入
/*			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			for (;;) {
				String line = in.readLine();
				if (line == null) {
					continue;
				}
				
//				向服务端发送在控制台输入的文本 并用"\r\n"结尾
//				之所以用\r\n结尾 是因为我们在handler中添加了 DelimiterBasedFrameDecoder 帧解码。
				ch.writeAndFlush(line + "\r\n");
			}*/
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} /*catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/ finally {
			// The connection is closed automatically on shutdown.
			group.shutdownGracefully();
		}
		
		return this;
	}

	/**
	 * @param args
	 * @throws InterruptedException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws InterruptedException, IOException {
		HelloClient l=new HelloClient();
				l.createConn("127.0.0.1", 7878, 1);
	}
}

