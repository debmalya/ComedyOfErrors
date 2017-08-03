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

import org.restlet.Client;
import org.restlet.Context;
import org.restlet.data.Protocol;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

/**
 * @author debmalyajash
 *
 */
public class DayActivityClientDelete {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws ResourceException 
	 */
	public static void main(String[] args) throws ResourceException, IOException {
		Client client = new Client(new Context(), Protocol.HTTP);
		client.getContext().getParameters().add("use ForwardedForHeader", "false");
		ClientResource dayActivityClient = new ClientResource("http://localhost:8112/api/v1/day/2017-07-26");
		dayActivityClient.setNext(client);
		dayActivityClient.delete().write(System.out);
		dayActivityClient.release();
	}

}
