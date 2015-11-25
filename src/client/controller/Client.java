package client.controller;

import java.util.ArrayList;
import java.util.List;
import client.bean.IpAddress;
import io.netty.channel.Channel;
import client.HelloClient;

public class Client {
	ArrayList<IpAddress> ipList = new ArrayList<IpAddress>(8);
	int curCount = 0;

	public void init() {
		for (int i = 0; i < 8; i++) {
			IpAddress ip = new IpAddress();
			ip.cli = new HelloClient();
			// ip.cli.createConn(host, port, channelId);
			ipList.add(ip);
		}
	}

	public void query(float x, float y){
		int tempCount=0;
		curCount++;
		if(curCount>=8000000){
			curCount=0;
		}
		
		while(true){
			IpAddress ip=ipList.get(curCount%8);
			if(ip.cli!=null&&ip.cli.initer!=null&&ip.cli.ch!=null&&ip.cli.initer.cHandle!=null) {
				send();
				break;
			} else {
				curCount++;
				if(curCount>=8000000){
					curCount=0;
				}
				tempCount++;
				if(tempCount>=800000) {
					senderr();
					break;
				}
			}
		}
		
	}

	private void send() {
		;
	}
	
	private void senderr() {
		;
	}
}
