package com.witbit.sherlock.dagger;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by sherlock on 17/4/6.
 */
@Singleton
public class PhoneModle implements IPhoneModle {

	@Inject
	public PhoneModle(){
		name = "Nexus";
		size = 6;
	}

	private String name;
	private int size;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	@Override
	public String getPhoneName() {
		return name;
	}
}
