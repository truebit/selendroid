/*
 * Copyright 2012-2014 eBay Software Foundation and selendroid committers.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package io.selendroid.server.model;

import android.os.Environment;
import android.os.Build;
import io.selendroid.server.common.model.ExternalStorageFile;
import io.selendroid.server.util.SelendroidLogger;

import java.io.File;

/**
 * Provides access to device's external storage.
 */
public class ExternalStorage {
  private static final String MULTIUSER_SDCARD_ROOT_DIR = "/storage/emulated/0";
  private static final String SDCARD_ROOT_DIR = "/storage/emulated/legacy";

  /**
   * Returns the external storage root.
   */
  public static File getExternalStorageDir() {
    String externalStoragePath = Environment.getExternalStorageDirectory().getAbsolutePath();
    if (externalStoragePath.contains(MULTIUSER_SDCARD_ROOT_DIR) && Build.VERSION.SDK_INT < 23) {
      String replacedPath = externalStoragePath.replace(MULTIUSER_SDCARD_ROOT_DIR, SDCARD_ROOT_DIR);
      String msg = String.format("converted external storage directory from %s to %s ", externalStoragePath, replacedPath);
      SelendroidLogger.info(msg);
      return new File(replacedPath);
    }
    return new File(externalStoragePath);
  }

  public static File getExtensionDex() {
    return new File(getExternalStorageDir(), "extension.dex");
  }

  public static File getCrashLog() {
    return new File(getExternalStorageDir(), ExternalStorageFile.APP_CRASH_LOG.toString());
  }
}