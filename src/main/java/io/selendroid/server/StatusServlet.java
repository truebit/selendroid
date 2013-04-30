/*
 * Copyright 2013 selendroid committers.
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
package io.selendroid.server;

import org.json.JSONObject;
import org.webbitserver.HttpControl;
import org.webbitserver.HttpHandler;
import org.webbitserver.HttpRequest;
import org.webbitserver.HttpResponse;


public class StatusServlet implements HttpHandler {

  @Override
  public void handleHttpRequest(HttpRequest httpRequest, HttpResponse httpResponse,
      HttpControl httpControl) throws Exception {
    if (!"GET".equals(httpRequest.method())) {
      httpResponse.status(500);
      httpResponse.end();
      return;
    }

    JSONObject build = new JSONObject();
    // TODO do something better
    build.put("version", "0.4-SNAPSHOT");
    build.put("browserName", "selendroid");

    JSONObject os = new JSONObject();
    os.put("arch", "x86");
    os.put("name", "Android");
    os.put("version", "0815");

    JSONObject json = new JSONObject();
    json.put("build", build);
    json.put("os", os);
    httpResponse.header("Content-Type", "text/plain");
    httpResponse.content("{status: 0, value: " + json.toString() + "}");
    httpResponse.end();
  }
}
