package com.witbit.sherlock.dagger;

import dagger.Module;
import dagger.Provides;

/**
 * Created by sherlock on 17/4/6.
 */
@Module
public class UserModule {

//	@Provides UserModle privideSherlockUser()
//	{
//		UserModle sherlock = new UserModle();
//		sherlock.userName= "sherlock";
//		sherlock.age = 26;
//		return sherlock;
//	}

	@Provides UserModle privideGirlFriend()
	{
		UserModle girlFriend = new UserModle();
		girlFriend.userName="lover";
		girlFriend.age=24;
		return girlFriend;
	}

}
