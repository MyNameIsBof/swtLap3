package com.example.demo.service;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    // Lỗi: Dùng phương thức findAll() mà không kiểm tra null
    public List<Student> getAllStudents() {
        return studentRepository.findAll(); // Có thể trả về null nếu không có sinh viên nào
    }

    // Lỗi: Không bắt exception khi cập nhật sinh viên
    public Student updateStudent(Long id, Student student) {
        Student existingStudent = studentRepository.findById(id).orElseThrow();
        existingStudent.setFullName(student.getFullName());
        existingStudent.setEmail(student.getEmail());
        return studentRepository.save(existingStudent);  // Chưa kiểm tra lỗi có thể phát sinh trong quá trình lưu
    }

    // Lỗi: Xử lý xóa mà không kiểm tra xem sinh viên có tồn tại không
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);  // Không có xử lý khi không tìm thấy id
    }

    // Lỗi: Không kiểm tra đầu vào trước khi lưu mới sinh viên
    public Student addStudent(Student student) {
        return studentRepository.save(student);  // Không kiểm tra email trùng lặp hoặc validation
    }
}
