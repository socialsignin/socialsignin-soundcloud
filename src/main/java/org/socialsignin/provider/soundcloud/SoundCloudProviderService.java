package org.socialsignin.provider.soundcloud;

import org.socialsignin.provider.AbstractProviderService;
import org.springframework.social.soundcloud.api.SoundCloud;
import org.springframework.social.soundcloud.api.impl.SoundCloudTemplate;
import org.springframework.stereotype.Service;

@Service
public class SoundCloudProviderService extends AbstractProviderService<SoundCloud> {

	@Override
	public Class<SoundCloud> getApiClass() {
		return SoundCloud.class;
	}

	@Override
	public SoundCloud getUnauthenticatedApi() {
		return new SoundCloudTemplate();
	}

}
