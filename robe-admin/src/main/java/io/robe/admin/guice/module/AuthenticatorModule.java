package io.robe.admin.guice.module;

import com.codahale.metrics.MetricRegistry;
import com.google.common.cache.CacheBuilder;
import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provider;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.CachingAuthenticator;
import io.robe.admin.RobeServiceConfiguration;
import io.robe.admin.hibernate.dao.ServiceDao;
import io.robe.admin.hibernate.dao.UserDao;
import io.robe.auth.Credentials;
import io.robe.auth.TokenWrapper;
import io.robe.auth.tokenbased.BasicToken;
import io.robe.auth.tokenbased.TokenBasedAuthBundle;
import io.robe.auth.tokenbased.TokenBasedAuthenticator;
import io.robe.auth.tokenbased.configuration.TokenBasedAuthConfiguration;
import io.robe.hibernate.HibernateBundle;

import java.util.concurrent.TimeUnit;

/**
 * Default Guice bindings are done at this class.
 */
public class AuthenticatorModule<T extends RobeServiceConfiguration> extends AbstractModule {
	private final TokenBasedAuthBundle bundle;
	private final MetricRegistry metricRegistry;

	public AuthenticatorModule(TokenBasedAuthBundle<T> bundle, MetricRegistry metricRegistry) {
		this.bundle = bundle;
		this.metricRegistry = metricRegistry;
	}

	@Override
	protected void configure() {

		bind(Authenticator.class).toProvider(new Provider<Authenticator<String, Credentials>>() {
			@Inject
			HibernateBundle hibernateBundle;

			@Override
			public Authenticator get() {
				TokenWrapper.initialize(BasicToken.class, bundle.getConfiguration());
				TokenBasedAuthenticator tokenBasedAuthenticator =
						new TokenBasedAuthenticator(
								new UserDao(hibernateBundle.getSessionFactory()),
								new ServiceDao(hibernateBundle.getSessionFactory()));
				return new CachingAuthenticator(metricRegistry,
						tokenBasedAuthenticator,
						CacheBuilder.newBuilder().expireAfterAccess(bundle.getConfiguration().getMaxage(), TimeUnit.SECONDS).maximumSize(10000));
			}
		});
		bind(TokenBasedAuthConfiguration.class).toProvider(new Provider<TokenBasedAuthConfiguration>() {

			@Override
			public TokenBasedAuthConfiguration get() {
				return bundle.getConfiguration();
			}
		});

	}


}
