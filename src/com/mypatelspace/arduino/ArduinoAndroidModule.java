/**
 * This file was auto-generated by the Titanium Module SDK helper for Android
 * Appcelerator Titanium Mobile
 * Copyright (c) 2009-2013 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Apache Public License
 * Please see the LICENSE included with this distribution for details.
 *
 */
package com.mypatelspace.arduino;

import java.io.IOException;

import org.appcelerator.kroll.KrollModule;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.kroll.common.Log;
import org.appcelerator.titanium.TiApplication;
import org.shokai.firmata.ArduinoFirmata;
import org.shokai.firmata.ArduinoFirmataEventHandler;

import android.app.Activity;
import android.os.Handler;

@Kroll.module(name = "ArduinoAndroid", id = "com.mypatelspace.arduino")
public class ArduinoAndroidModule extends KrollModule {

	// Standard Debugging variables
	private static final String TAG = "ArduinoAndroidModule";

	// You can define constants with @Kroll.constant, for example:
	// @Kroll.constant public static final String EXTERNAL_NAME = value;
	private ArduinoFirmata arduino;
	private Handler handler;

	public ArduinoAndroidModule() {
		super();
	}

	@Kroll.onAppCreate
	public static void onAppCreate(TiApplication app) {
		Log.d(TAG, "inside onAppCreate");
		// put module init code that needs to run when the application is
		// created
	}

	@Kroll.method
	public void init() {
		Log.v(TAG, "Firmata Lib Version : " + ArduinoFirmata.VERSION);
		TiApplication appContext = TiApplication.getInstance();
		final Activity activity = appContext.getCurrentActivity();
		arduino = new ArduinoFirmata(activity);
		// ArduinoFirmataEventHandler aHandler =
		arduino.setEventHandler(new ArduinoFirmataEventHandler() {
			public void onError(String errorMessage) {
				Log.e(TAG, errorMessage);
			}

			public void onClose() {
				Log.v(TAG, "arduino closed");
				activity.finish();
			}
		});
	}

	// Methods
	@Kroll.method
	public void connect() {
		Log.d(TAG, "connect called");
		this.handler = new Handler();
		TiApplication appContext = TiApplication.getInstance();
		final Activity activity = appContext.getCurrentActivity();
		try {
			arduino.connect();
			Log.v(TAG, "Board Version : " + arduino.getBoardVersion());
			// arduino.pinMode(7, ArduinoFirmata.INPUT);

		} catch (IOException e) {
			e.printStackTrace();
			// not sure if we really need this
			activity.finish();
		} catch (InterruptedException e) {
			e.printStackTrace();
			// not sure if we really need this
			activity.finish();
		}
	}

	@Kroll.method
	public boolean isOpen() {
		return arduino != null;
	}

	@Kroll.method
	public boolean close() {
		return arduino.close();
	}

	@Kroll.method
	public void reset() {
		arduino.reset();
	}

	@Kroll.method
	public boolean digitalRead(int pin) {
		return arduino.digitalRead(pin);
	}

	@Kroll.method
	public int analogRead(int pin) {
		return arduino.analogRead(pin);
	}

	@Kroll.method
	public void pinMode(int pin, byte mode) {
		arduino.pinMode(pin, mode);
	}

	@Kroll.method
	public void digitalWrite(int pin, boolean value) {
		arduino.digitalWrite(pin, value);
	}

	@Kroll.method
	public void analogWrite(int pin, int value) {
		arduino.analogWrite(pin, value);
	}

	@Kroll.method
	public void servoWrite(int pin, int angle) {
		arduino.servoWrite(pin, angle);
	}
}
