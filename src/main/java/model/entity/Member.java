package model.entity;

import java.io.Serializable;

public class Member implements Serializable
{
		private int id;
		private String uid;
		private String password;
		private String name;
		private String phone;
		private String email;
		private int year;
		private String address;
		public Member() {}
		public Member(String uid, String password, String name, String phone, String email, int year, String address) {
			super();			
			this.uid=uid;
			this.password = password;
			this.name = name;
			this.phone = phone;
			this.email = email;
			this.year = year;
			this.address = address;
		}
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		
		public String getUid() {
			return uid;
		}
		public void setUid(String uid) {
			this.uid = uid;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public int getYear() {
			return year;
		}
		public void setYear(int year) {
			this.year = year;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
}

