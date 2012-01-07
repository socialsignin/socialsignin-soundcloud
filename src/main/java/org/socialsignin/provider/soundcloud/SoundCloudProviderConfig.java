/*
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.socialsignin.provider.soundcloud;

import org.socialsignin.provider.AbstractProviderConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.web.ConnectInterceptor;
import org.springframework.social.soundcloud.api.SoundCloud;
import org.springframework.social.soundcloud.connect.SoundCloudConnectionFactory;

/** 
* @author Michael Lavelle
*/
@Configuration
public class SoundCloudProviderConfig extends AbstractProviderConfig<SoundCloud> {

	@Autowired
	private SoundCloudConnectInterceptor soundCloudConnectInterceptor;

	@Value("${soundcloud.consumerKey}")
	private String soundcloudConsumerKey;
	
	@Value("${soundcloud.redirectUri}")
	private String soundcloudRedirectUri;

	@Value("${soundcloud.consumerSecret}")
	private String soundcloudConsumerSecret;

	@Override
	protected ConnectionFactory<SoundCloud> createConnectionFactory() {
		return new SoundCloudConnectionFactory(soundcloudConsumerKey,
				soundcloudConsumerSecret,soundcloudRedirectUri);
	}

	@Override
	protected ConnectInterceptor<SoundCloud> getConnectInterceptor() {
		return soundCloudConnectInterceptor;
	}

}
