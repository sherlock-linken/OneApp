// IMyAidlInterface.aidl
package com.sherlock.twoapp;
import com.sherlock.twoapp.Friend;
// Declare any non-default types here with import statements

interface IMyAidlInterface {
    void showLog(String log);
    String getName();
    List<Friend> getFriends();
}
