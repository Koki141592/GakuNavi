package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentListServiceImpl implements StudentListService {
	
	private final StudentRepository repository;

	@Override
	public List<Student> findByNameWildCard(String studentName) {
		
		//インフラ層で受け取った引数をそのままリストとして戻り値入れて返す処理
		List<Student> list = repository.selectByNameWildcard(studentName);
		
		return list;
	}

}
