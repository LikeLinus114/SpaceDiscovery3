package com.example.spacediscovery.di.module

import com.example.spacediscovery.chat.ChatListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeChatListActivity(): ChatListActivity

}