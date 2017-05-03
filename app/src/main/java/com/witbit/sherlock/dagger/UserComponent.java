package com.witbit.sherlock.dagger;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by sherlock on 17/4/6.
 */

@Singleton
@Component(modules = {
//		UserModule.class, CarModule.class,
		PhoneModule.class
})
public interface UserComponent  {

	void justIn(DaggerDemoActivity activity);

	void hehehaxi(DaggerDemoActivity context);

	IPhoneModle getPhone();

}
