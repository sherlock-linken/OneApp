package com.witbit.sherlock.dagger;

import dagger.Module;
import dagger.Provides;

/**
 * Created by sherlock on 17/4/6.
 */

@Module
public class CarModule {

	@Provides
	CarModel getMeCar() {
		CarModel car = new CarModel();
		car.seats = 5;
		car.whells = 4;
		return car;
	}

}
