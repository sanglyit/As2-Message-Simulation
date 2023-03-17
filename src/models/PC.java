package models;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class PC {
	private int id;
	private String name;
	private LinkedList<Segment> sendQueue;
	private LinkedList<Segment> receiveQueue;

	public PC(int id, String name) {
		this.id = id;
		this.name = name;
		this.sendQueue = new LinkedList<Segment>();
		this.receiveQueue = new LinkedList<Segment>();
	}

	public void displaySendQueue() {
		System.out.println("\n =================  Send Queue Segment ==================\n");

		for (Segment segment : sendQueue) {
			int id = segment.getSegmentId();
			int PC_from_ID = segment.getPC_from_ID();
			int PC_to_ID = segment.getPC_to_ID();
			int port = segment.getPort();
			String message = segment.getMessage();

			System.out.printf("ID: %d, From PC %d to PC %d   |   Port: %d     |  Message:  %s\n", id, PC_from_ID, PC_to_ID, port, message);
		}
	}
	
	
	public void displayReceiveQueue() {
		System.out.println("\n =================  Receive Queue Segment ==================\n");

		for (Segment segment : receiveQueue) {
			int id = segment.getSegmentId();
			int PC_from_ID = segment.getPC_from_ID();
			int PC_to_ID = segment.getPC_to_ID();
			int port = segment.getPort();
			String message = segment.getMessage();

			System.out.printf("ID: %d    |   From PC %d to PC %d   |   Port: %d     |  Message:  %s\n", id, PC_from_ID, PC_to_ID, port, message);
		}
	}

	public void sendMessage(PC thatPC, String message) {
		if (message.isEmpty())
			System.out.println("There is no message");
		else {
			message = message.trim();
			// 5 kí tự => cắt chuỗi
			// ngược lại => in chuỗi cuối cùng ra < 5 kí tự
			int segmentId = 1;
			int len = message.length();
			for (int i = 0; i < len; i += 5) {
				if (i + 5 < len) {
					sendQueue.add(new Segment(segmentId, this.id, thatPC.id, message.substring(i, i + 5)));
				} else {
					sendQueue.add(new Segment(segmentId, this.id, thatPC.id, message.substring(i)));
				}
				segmentId++;
			}

			// sau khi cắt chuỗi => gửi từng segment qua máy B

			for (int i = 0; i < sendQueue.size(); i++) {
				thatPC.receiveQueue.add(sendQueue.get(i));
			}
			this.sendQueue.clear();
		}

	}

	public void receiveMessage() {
		if (receiveQueue.isEmpty())
			System.out.println("There is no message");
		else {
			Map<Integer, StringBuilder> map = new HashMap<>();

			for (Segment segment : receiveQueue) {
				int PC_from_ID = segment.getPC_from_ID();
				String message = segment.getMessage();

				StringBuilder sb = map.get(PC_from_ID);
				
				// ======================================================================
				// null => Chuỗi mới => bỏ vô Map
				
				
				// ===================================================================
				// nếu có r 
				// Step 1: trả về chuỗi trước
				// Step 2: lấy chuỗi đc trả về rồi gán chuỗi tiếp theo có cùng id vào.
				
				
				if (sb == null) {
					sb = new StringBuilder();
					map.put(PC_from_ID, sb);
				}
				sb.append(message);
			}

			System.out.printf("\n ================= Messages of PC %d ==================\n", this.id);

			// Entry là một interface con của Map. Vì vậy, chúng ta được truy cập nó bằng tên Map.Entry.
			//Nó cung cấp các phương pháp để truy xuất các key và value.
			for (Map.Entry<Integer, StringBuilder> entry : map.entrySet()) {
				int PC_from_ID = entry.getKey();
				String message = entry.getValue().toString();

				System.out.printf("From PC %d |  Message: %s\n", PC_from_ID, message);

			}
		}
	}
}
