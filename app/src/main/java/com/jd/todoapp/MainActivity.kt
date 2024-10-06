package com.jd.todoapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController

// 定义MainActivity类，继承自AppCompatActivity
class MainActivity : AppCompatActivity() {
    // 重写onCreate方法
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 设置布局文件
        setContentView(R.layout.activity_main)

        // 设置ActionBar与NavController的关联
        setupActionBarWithNavController(findNavController(R.id.navHostFragment))
    }


  // 重写onSupportNavigateUp方法
  override fun onSupportNavigateUp(): Boolean {
    // 获取NavController
    val navController = findNavController(R.id.navHostFragment)
    // 返回NavController的navigateUp方法的结果，如果为false，则调用父类的onSupportNavigateUp方法
    return navController.navigateUp() || super.onSupportNavigateUp()
  }
}