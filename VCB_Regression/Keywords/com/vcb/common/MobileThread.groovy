package com.vcb.common

import java.time.LocalTime
import java.time.format.DateTimeFormatter

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.vcb.mobile.internal.pages.base.BasePage
import com.vcb.mobile.internal.pages.base.CommonPage

import internal.GlobalVariable
import io.appium.java_client.AppiumDriver


public class MobileThread {

	// Thread stop check flag variable
	volatile boolean stopThread = false
	static volatile int delay = 0
	static Thread myThread = null
	static final int DELAY_LONG = 30000
	static final int DELAY_MEDIUM = 15000
	static final int DELAY_SHORT = 5000
	
	static CommonPage commonPage = BasePage.getPage(CommonPage)

	/**
	 * Starts a background thread to perform periodic actions using a provided AppiumDriver.
	 *
	 * This method checks if the thread (`myThread`) is already running. If not, it creates and starts a new thread.
	 * The thread executes periodic tasks, such as setting the mobile driver, switching to portrait mode, 
	 * and printing the current time every 30 seconds. The thread runs until the `stopThread` flag is set to `true`.
	 *
	 * @param driver The AppiumDriver instance to be used for mobile actions within the thread.
	 */
	def startThreadWaitingAction(AppiumDriver driver) {
		try {
			if (this.myThread == null || !this.myThread.isAlive()) {
				// Reset the stopThread flag to start the thread
				stopThread = false
				// Create and start the thread
				this.myThread = new Thread({
	                while (!stopThread) {
	                    LocalTime currentTime = LocalTime.now()
	                    println("Thread is working... " + currentTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")))
	
	                    // Simulate some work with a mobile element
	                    try {
	                        println "Delay ${delay}"
	                        if (delay) {
	                            Thread.sleep(delay)
	                            delay = 0
	                        }
	                        MobileDriverFactory.setDriver(driver)
	                        Mobile.switchToPortrait()
							Mobile.tapAtPosition(1, 1)
							
	                    } catch (ex) {
	                        println("Error Mobile Thread: " + ex.message)
	                    }
	                    Thread.sleep(DELAY_LONG) // Simulate a delay
	                }
					println "Thread has stopped."
				})

	            this.myThread.setDaemon(true) // Set thread as daemon
	            this.myThread.start() // Start the daemon thread
	            println "Daemon thread has started."
			} else {
				println "Thread is already running."
			}
		} catch (ex) {
			println ex.toString()
		}
	}

	/**
	 * Stops a running thread and waits for it to finish execution.
	 *
	 * This method checks if a thread (`myThread`) is alive, and if so, it sets a `stopThread` flag to `true` 
	 * to signal the thread to stop. It then waits for the thread to complete using the `join()` method. 
	 * If no thread is running, it logs an appropriate message.
	 * 
	 * Exception handling is included to catch and log any errors during the process.
	 */
	def stopThreadWaitingAction() {
		try {
			if (this.myThread != null && this.myThread.isAlive()) {
				// Stop the thread by setting the stopThread flag to true
				stopThread = true

				// Wait for the thread to finish
				this.myThread.join()
				println "Thread has stopped."
			} else {
				println "No running thread to stop."
			}
		} catch (ex) {
			println ex.toString()
		}
	}
	
	/**
	 * Sets a delay for the mobile thread to maintain session activity.
	 * @param waitTime The amount of time (in milliseconds) to set for the mobile thread delay. Default is `DELAY_MEDIUM`.
	 */
	static void setMobileThreadDelay(int waitTime = DELAY_MEDIUM) {
		if(GlobalVariable.G_isRunWebsiteAndMobile) {
			delay = waitTime
		}
	}
}
