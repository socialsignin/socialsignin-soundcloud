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
import org.socialsignin.provider.strategy.connectionrepository.ConnectionRepositoryStrategy;
import org.socialsignin.springsocial.security.SoundCloudConnectInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.connect.web.ConnectInterceptor;
import org.springframework.social.soundcloud.api.SoundCloud;
import org.springframework.social.soundcloud.api.impl.SoundCloudTemplate;
import org.springframework.social.soundcloud.connect.SoundCloudConnectionFactory;

/** 
* @author Michael Lavelle
*/
@Configuration
public class SoundCloudProviderConfig extends AbstractProviderConfig<SoundCloud> {

	@Autowired(required=false)
	private SoundCloudConnectInterceptor soundCloudConnectInterceptor;

	@Value("${soundcloud.consumerKey}")
	private String soundcloudConsumerKey;
	
	@Value("${soundcloud.redirectUri}")
	private String soundcloudRedirectUri;

	public String getSoundcloudConsumerKey() {
		return soundcloudConsumerKey;
	}

	public String getSoundcloudRedirectUri() {
		return soundcloudRedirectUri;
	}

	public String getSoundcloudConsumerSecret() {
		return soundcloudConsumerSecret;
	}

	@Value("${soundcloud.consumerSecret}")
	private String soundcloudConsumerSecret;
	
	public SoundCloudProviderConfig() {
		super();
	}
	
	public SoundCloudProviderConfig(String soundCloudConsumerKey) {
		this.soundcloudConsumerKey = soundCloudConsumerKey;
	}
	
	public SoundCloudProviderConfig(String soundCloudConsumerKey,
			SoundCloud authenticatedApi) {
		super(authenticatedApi);
		this.soundcloudConsumerKey = soundCloudConsumerKey;
	}
	
	public SoundCloudProviderConfig(String soundCloudConsumerKey,String sessionKey) {
		super(new SoundCloudTemplate(soundCloudConsumerKey,sessionKey));
		this.soundcloudConsumerKey = soundCloudConsumerKey;
	}
	
	public SoundCloudProviderConfig(String soundCloudConsumerKey,String soundCloudConsumerSecret,ConnectionRepository connectionRepository,
			ConnectionFactoryRegistry connectionFactoryRegistry) {
		super(connectionRepository, connectionFactoryRegistry);
		this.soundcloudConsumerKey = soundCloudConsumerKey;
		this.soundcloudConsumerSecret  = soundCloudConsumerSecret;
	}

	public SoundCloudProviderConfig(String soundCloudConsumerKey,String soundCloudConsumerSecret,ConnectionRepository connectionRepository,
			UsersConnectionRepository usersConnectionRepository,
			ConnectionFactoryRegistry connectionFactoryRegistry) {
		super(connectionRepository, usersConnectionRepository,
				connectionFactoryRegistry);
		this.soundcloudConsumerKey = soundCloudConsumerKey;
		this.soundcloudConsumerSecret  = soundCloudConsumerSecret;
	}
	
	public SoundCloudProviderConfig(String soundCloudConsumerKey,String soundCloudConsumerSecret,String userId,	UsersConnectionRepository usersConnectionRepository,
			ConnectionFactoryRegistry connectionFactoryRegistry) {
		super(userId,usersConnectionRepository,
				connectionFactoryRegistry);
		this.soundcloudConsumerKey = soundCloudConsumerKey;
		this.soundcloudConsumerSecret  = soundCloudConsumerSecret;
	}

	public void setSoundcloudConsumerKey(String soundcloudConsumerKey) {
		this.soundcloudConsumerKey = soundcloudConsumerKey;
	}


	public void setSoundcloudRedirectUri(String soundcloudRedirectUri) {
		this.soundcloudRedirectUri = soundcloudRedirectUri;
	}


	public void setSoundcloudConsumerSecret(String soundcloudConsumerSecret) {
		this.soundcloudConsumerSecret = soundcloudConsumerSecret;
	}


	@Override
	protected ConnectionFactory<SoundCloud> createConnectionFactory() {
		return new SoundCloudConnectionFactory(soundcloudConsumerKey,
				soundcloudConsumerSecret,soundcloudRedirectUri);
	}

	@Override
	protected ConnectInterceptor<SoundCloud> getConnectInterceptor() {
		return soundCloudConnectInterceptor;
	}

	@Override
	public Class<SoundCloud> getApiClass() {
		return SoundCloud.class;
	}

}
