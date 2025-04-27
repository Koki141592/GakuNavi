package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Student;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class StudentRepositoryImpl implements StudentRepository {
	
	private final JdbcTemplate jdbcTemplate;

	@Override
	public List<Student> selectByNameWildcard(String studentName) {
		
		//SQL文
		String sql = """
			    SELECT
			        st.student_id,
			        st.student_name,
			        st.school_name,
			        st.grade
			    FROM
			        m_student st
			        LEFT JOIN t_report tr ON st.student_id = tr.student_id
			    WHERE
			        st.student_name LIKE ?
			    GROUP BY
			        st.student_id,
			        st.student_name,
			        st.school_name,
			        st.grade
			    ORDER BY
			        st.student_id
			    """;
		
		String p = "%" + studentName + "%";
		
		//SQLで検索してプレースホルダに値を入れる
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, p);
		
		//リストの初期化を行う処理
		List<Student> result = new ArrayList<Student>();
		
		//データベースから取得した値を人数分だけ取得して格納する処理
		for(Map<String, Object> one : list) {
			
			Student student = new Student();
			
			student.setStudentId((int) one.get("student_id"));
			student.setStudentName((String) one.get("student_name"));
			student.setSchoolName((String) one.get("school_name")); 
			student.setGrade((int) one.get("grade"));
			
			result.add(student);
		}
		
		return result;
	}

}
