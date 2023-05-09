package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "flag")
@Data

public class Flag {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "image")
	private String image;
	
	@Column(name = "choice1")
	private String choice1;

	@Column(name = "choice2")
	private String choice2;
	
	@Column(name = "choice3")
	private String choice3;
	
	@Column(name = "choice4")
	private String choice4;
	
	@Column(name = "answer")
	private Integer answer;

	public Flag findById(int id2) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

}