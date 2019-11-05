package tech.codewithabhi.hamonclassroom

import android.app.Application
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import tech.codewithabhi.hamonclassroom.data.network.DataAPI
import tech.codewithabhi.hamonclassroom.data.network.NetworkConnectionInterceptor
import tech.codewithabhi.hamonclassroom.data.repositories.HomeRepository
import tech.codewithabhi.hamonclassroom.ui.home.student.StudentViewModelFactory
import tech.codewithabhi.hamonclassroom.ui.home.subject.SubjectViewModelFactory

class HamonClassroom : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@HamonClassroom))

        bind() from singleton { NetworkConnectionInterceptor(instance()) }

        bind() from singleton { DataAPI(instance()) }

        bind() from singleton { HomeRepository(instance()) }

        bind() from provider { StudentViewModelFactory(instance()) }
        bind() from provider { SubjectViewModelFactory(instance()) }
    }

}