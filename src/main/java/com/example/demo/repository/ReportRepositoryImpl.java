package com.example.demo.repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Report;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ReportRepositoryImpl implements ReportRepository {
	
	private final JdbcTemplate jdbcTemplate;

	@Override
	public void add(Report report) {
		
		//データベースに登録するためのSQL文
		String sql = """
			    INSERT INTO t_report
			    (student_id, subject, field, class_date, rating, comment)
			    VALUES (?, ?, ?, ?, ?, ?)
			    """;
		
		//上記で記述したSQL文に取得した値を入れる
		jdbcTemplate.update(sql, report.getStudentId(),
				                 report.getSubject(),
				                 report.getField(),
				                 report.getDate(),
				                 report.getRating(),
				                 report.getComment()   );
	}

	@Override
	public List<Report> selectByStudentId(int studentId) {
		
		String sql = """
			    SELECT
			        report_id,
			        student_id,
			        subject,
			        field,
			        class_date,
			        rating,
			        comment
			    FROM
			        t_report
			    WHERE
			        student_id = ?
			    ORDER BY
			        class_date DESC,
			        report_id ASC
			    """;
		
		//SQLで検索して、プレースホルダに受け取ったstudentIdを入れる処理
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, studentId);
		
		List<Report> result = new ArrayList<Report>();
		
		for(Map<String, Object> one : list) {
			
			Report report = new Report();
			
			//各値を取得する処理
			report.setReportId((int)one.get("report_id"));
			report.setStudentId((int)one.get("student_id"));
			report.setSubject((String)one.get("subject"));
			report.setField((String)one.get("field"));
			report.setDate((Date)one.get("class_date"));
			report.setRating((int)one.get("rating"));
			report.setComment((String)one.get("comment"));
			
			result.add(report);
		}
		
		
		return result;
	}

	@Override
	public void update(Report report) {
		
		String sql = """
			    UPDATE t_report
			    SET
			      subject = ?,
			      field = ?,
			      class_date = ?,
			      rating = ?,
			      comment = ?
			    WHERE
			      report_id = ?
			    """;
		
		jdbcTemplate.update(sql,
				report.getSubject(),
				report.getField(),
				report.getDate(),
				report.getRating(),
				report.getComment(),
				report.getReportId()
				);
		
	}

	@Override
	public void delete(Report report) {
		
		String sql = """
				DELETE
				FROM
				    t_report
				WHERE
				    report_id = ?
				""";
				
		jdbcTemplate.update(sql, report.getReportId());
	}

}
