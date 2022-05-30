package com.marazmone.samplekmm.data.datasource

import com.marazmone.samplekmm.data.model.entity.RocketLaunchEntity
import com.marazmone.samplekmm.data.model.response.RocketLaunchResponse
import com.marazmone.samplekmm.utils.insertOrUpdate
import io.realm.MutableRealm
import io.realm.Realm
import io.realm.query

internal class LaunchesCacheDataSourceImpl(
    private val realm: Realm
) : LaunchesCacheDataSource {

    override suspend fun getAll(): List<RocketLaunchEntity> {
        return realm.query<RocketLaunchEntity>().find()
    }

    override suspend fun save(launches: List<RocketLaunchEntity>) {
        realm.write {
            launches.forEach { insertOrUpdate(it) }
        }
    }

    override suspend fun clear() {
        realm.write {
            val allRocketLaunchResponse = realm.query<RocketLaunchEntity>().find()
            delete(allRocketLaunchResponse)
        }
    }
}