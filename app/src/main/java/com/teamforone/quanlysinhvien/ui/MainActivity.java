package com.teamforone.quanlysinhvien.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app. AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.teamforone.quanlysinhvien.R;
import com.teamforone.quanlysinhvien.ui.QuanLySinhVien;

public class MainActivity extends AppCompatActivity {

    private CardView cardStudentManagement, cardAttendance, cardClassManagement;
    private CardView cardSubjectManagement, cardTeacherManagement, cardAccountManagement;
    private CardView cardStatistics, cardLogout;
    private TextView tvWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Khởi tạo views
        initViews();

        // Lấy thông tin người dùng (nếu có từ Intent hoặc SharedPreferences)
        String username = getIntent().getStringExtra("username");
        if (username != null && !username.isEmpty()) {
            tvWelcome.setText("Xin chào, " + username);
        }

        // Thiết lập sự kiện click cho các card
        setupClickListeners();
        getOnBackPressedDispatcher().addCallback(this,
                new OnBackPressedCallback(true) {
                    @Override
                    public void handleOnBackPressed() {
                        showExitDialog();
                    }
                });
    }

    private void initViews() {
        tvWelcome = findViewById(R. id.tvWelcome);
        cardStudentManagement = findViewById(R.id.cardStudentManagement);
        cardAttendance = findViewById(R.id. cardAttendance);
        cardClassManagement = findViewById(R. id.cardClassManagement);
        cardSubjectManagement = findViewById(R.id.cardSubjectManagement);
        cardTeacherManagement = findViewById(R. id.cardTeacherManagement);
        cardAccountManagement = findViewById(R.id.cardAccountManagement);
        cardStatistics = findViewById(R.id.cardStatistics);
        cardLogout = findViewById(R.id.cardLogout);
    }

    private void setupClickListeners() {
        // Quản lý sinh viên
        cardStudentManagement.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, QuanLySinhVien.class);
            startActivity(intent);
        });

//        // Điểm danh
//        cardAttendance. setOnClickListener(v -> {
//            Intent intent = new Intent(MainActivity.this, AttendanceActivity.class);
//            startActivity(intent);
//        });
//
//        // Quản lý lớp
//        cardClassManagement.setOnClickListener(v -> {
//            Intent intent = new Intent(MainActivity.this, ClassManagementActivity.class);
//            startActivity(intent);
//        });
//
//        // Quản lý môn học
//        cardSubjectManagement. setOnClickListener(v -> {
//            Intent intent = new Intent(MainActivity.this, SubjectManagementActivity.class);
//            startActivity(intent);
//        });
//
//        // Quản lý giảng viên
//        cardTeacherManagement.setOnClickListener(v -> {
//            Intent intent = new Intent(MainActivity.this, TeacherManagementActivity.class);
//            startActivity(intent);
//        });
//
//        // Quản lý tài khoản - phân quyền
//        cardAccountManagement.setOnClickListener(v -> {
//            Intent intent = new Intent(MainActivity.this, AccountManagementActivity.class);
//            startActivity(intent);
//        });
//
//        // Thống kê báo cáo
//        cardStatistics.setOnClickListener(v -> {
//            Intent intent = new Intent(MainActivity.this, StatisticsActivity.class);
//            startActivity(intent);
//        });

        // Đăng xuất
        cardLogout.setOnClickListener(v -> {
            // Hiển thị dialog xác nhận đăng xuất
            showLogoutDialog();
        });
    }

    private void showLogoutDialog() {
        new androidx.appcompat.app.AlertDialog.Builder(this)
                .setTitle("Đăng xuất")
                .setMessage("Bạn có chắc chắn muốn đăng xuất? ")
                .setPositiveButton("Có", (dialog, which) -> {
                    // Xóa thông tin đăng nhập (SharedPreferences)
                    // SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
                    // prefs.edit().clear().apply();

                    Toast.makeText(MainActivity.this, "Đã đăng xuất", Toast.LENGTH_SHORT).show();

//                    // Chuyển về màn hình đăng nhập
//                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent. FLAG_ACTIVITY_CLEAR_TASK);
//                    startActivity(intent);
//                    finish();
                })
                .setNegativeButton("Không", null)
                .show();
    }

    private void showExitDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Thoát ứng dụng")
                .setMessage("Bạn có muốn thoát ứng dụng?")
                .setPositiveButton("Có", (dialog, which) -> {
                    finishAffinity(); // Đóng toàn bộ Activity
                })
                .setNegativeButton("Không", null)
                .show();
    }
}