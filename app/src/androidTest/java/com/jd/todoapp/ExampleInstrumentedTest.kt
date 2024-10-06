package com.jd.todoapp

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * 该类包含用于在Android设备上执行的仪态化测试。
 * 仪态化测试使用实际的Android设备和真实的系统环境来运行测试，可以验证应用在真实环境下的行为。
 * 这里的测试主要验证应用的上下文环境是否正确。
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
  /**
   * 验证应用的包名是否正确。
   * 通过比较InstrumentationRegistry提供的目标上下文中的包名，来确认当前测试运行的上下文是否为预期的应用上下文。
   */
  @Test
  fun useAppContext() {
    // 获取正在测试的应用上下文。
    val appContext = InstrumentationRegistry.getInstrumentation().targetContext
    // 断言获取的上下文中的包名是否与预期相符。
    assertEquals("com.jd.todoapp", appContext.packageName)
  }
}