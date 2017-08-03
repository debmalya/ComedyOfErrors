/**
 * Copyright 2015-2017 Debmalya Jash
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package Client;

import java.io.IOException;

import org.restlet.resource.ClientResource;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * @author debmalyajash
 *
 */
public class DayActivityClientGet {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		try {
			ClientResource dayActivityClient = new ClientResource("http://localhost:8112/api/v1/day/2017-07-20");
			dayActivityClient.get().write(System.out);
			dayActivityClient.release();

			dayActivityClient = new ClientResource("http://localhost:8112/api/v1/day/2017-07-13");
			dayActivityClient.get().write(System.out);
			dayActivityClient.release();

			dayActivityClient = new ClientResource("http://localhost:8112/api/v1/day");
			String dayList = dayActivityClient.get().getText();
			System.out.println(dayList);
			dayActivityClient.release();

			
		} finally {

		}
	}

}
