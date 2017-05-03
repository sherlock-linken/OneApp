package com.witbit.sherlock.dagger;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by sherlock on 17/4/6.
 */
@Module
public class PhoneModule {

	@Singleton
	@Provides
	public IPhoneModle giveMePhone(PhoneModle phone)
	{
		return phone;
	}

}
