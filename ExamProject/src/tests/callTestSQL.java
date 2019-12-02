package tests;

import java.sql.SQLException;

import tfr_l2project.CallSQL;

public class callTestSQL {
		
	public static void main(String[] args) {
		
		try {
			CallSQL.getStudentIdentity();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
//		try {
//			String req = callSQL.selectAllUserID();
//			
//			System.out.println(req);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		try {
//			String req = callSQL.selectNameStudL1Info();
//			
//			System.out.println(req);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		try {
//			String req = callSQL.roomDispo();
//			
//			System.out.println(req);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		try {
//			String req = callSQL.roomsDispoFixedSchedule();
//			
//			System.out.println(req);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
//		try {
//			String req = callSQL.roomsAndSchedule();
//			
//			System.out.println(req);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		
		
		// WIPed CLASS
//		try {
//			callSQL.setOneUserID("\"michel.carver@gmail.com\"");
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
//		try {
//			String req = CallSQL.update();
//			System.out.println(req);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
}
