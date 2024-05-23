package ch02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerFile {

	public static void main(String[] args) {

		// 준비물
		// 1. 서버 소켓이 필요하다.
		// 2. 포트 번호가 필요하다. (0~65535까지 존재한다)
		// 2-1 잘 알려진 포트 번호 : 주로 시스템 레벨 -0 ~ 1023 까지 사용
		// 2-2 등록 가능한 포트 : 1024~ 49151까지
		// 2-3 동적 / 사설 포트 번호 - 그외 임시 사용을 위해 할당 된다.

		ServerSocket serverSocket = null;

		try {
			serverSocket = new ServerSocket(5001);
			System.out.println("server... start - port number : 5001 ");

			Socket socket = serverSocket.accept(); // while -->
			System.out.println(">>>client Attempt to connect <<< ");
			
			// 데이터를 전달 받기 위해서는 뭐가 필요하다 ? --> 스트림이 하다
			InputStream input = socket.getInputStream();
			// 문자 기반 스트림 -> 기능에 확장
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			
			//실제 데이터를 읽는 행위가 필요 하다.
			
			String message = reader.readLine();
			System.out.println("Send a client message "+message);
			
			socket.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(serverSocket != null) {
				try {
					serverSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
