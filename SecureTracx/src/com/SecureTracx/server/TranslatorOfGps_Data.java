package com.SecureTracx.server;

import java.nio.ByteBuffer;

import org.w3c.dom.CDATASection;

public class TranslatorOfGps_Data
{
	public byte[] data = new byte[36];
	public byte[] startbit = new byte[2];
	public byte packetLength;
	public byte protocolNumber;
	public byte[] date = new byte[3];
	public byte[] time = new byte[3];
	public byte[] latitude = new byte[4];
	public byte[] longitude = new byte[4];
	public byte speed;
	public byte[] courseStatus = new byte[2];
	public int i;
    public String str1=""; 
    String s = "";
	public TranslatorOfGps_Data() {
		
		/*String str = "";
		for(byte b: ClientTask.getMain_data())
		{
			str = str+String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
		}*/
		
		System.arraycopy(ClientTask.getMain_data(), 0, data, 0, 36);
		startbit[0] = data[0];
		startbit[1] = data[1];
		packetLength = data[2];
		protocolNumber = data[3];
		date[0] = data[4];
		date[1] = data[5];
		date[2] = data[6];
		time[0] = data[7];
		time[1] = data[8];
		time[2] = data[9];
		for(byte b : date){
		String str = ""+String.format("%02X", b);
		i = Integer.parseInt(str,16);
		str1 = str1+Integer.toString(i);
		}
		System.out.println("Date : "+str1);
		for(byte b : time)
		{
			String str = ""+String.format("%02X", b);
			int i = Integer.parseInt(str,16);
			s = s+Integer.toString(i);
		}
		System.out.println("Time is "+s);
	}
	
}
