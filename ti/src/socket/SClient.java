package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class SClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Socket socket = new Socket("localhost",10086);
			
			OutputStream out = socket.getOutputStream();
			PrintWriter pw = new PrintWriter(out);
			pw.write("name pw");
			pw.flush();
			socket.shutdownOutput();
			
			InputStream in = socket.getInputStream();
			BufferedReader buffer = new BufferedReader(new InputStreamReader(in));
			String info = null;
			while((info = buffer.readLine())!=null){
				System.out.println("server say:"+info);
				info = buffer.readLine();
			}
			buffer.close();
			in.close();
			pw.close();
			out.close();
			socket.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
