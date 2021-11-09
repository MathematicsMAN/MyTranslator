package ru.android.mytranslator.viewmodel.rx

import io.reactivex.Scheduler

interface ISchedulerProvider {

    val ui: Scheduler

    val io: Scheduler
}