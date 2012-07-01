package org.socialsignin.provider.soundcloud;

import org.socialsignin.provider.AbstractProviderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.soundcloud.api.SoundCloud;
import org.springframework.social.soundcloud.api.impl.SoundCloudTemplate;
import org.springframework.stereotype.Service;

@Service
public class SoundCloudProviderService extends AbstractProviderService<SoundCloud,SoundCloudProviderConfig> {


	public SoundCloudProviderService() {
		super();
	}

	public SoundCloudProviderService(SoundCloudProviderConfig providerConfig) {
		super(providerConfig);
	}

	@Override
	public SoundCloud getUnauthenticatedApi() {
		return new SoundCloudTemplate(providerConfig.getSoundcloudConsumerKey());
	}

}
