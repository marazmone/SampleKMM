package com.marazmone.samplekmm.data.datasource

import com.marazmone.samplekmm.data.model.entity.RocketLaunchEntity
import com.marazmone.samplekmm.data.model.response.RocketLaunchResponse
import com.marazmone.samplekmm.utils.insertOrUpdate
import io.realm.MutableRealm
import io.realm.Realm
import io.realm.notifications.InitialResults
import io.realm.notifications.UpdatedResults
import io.realm.query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class LaunchesCacheDataSourceImpl(
    private val realm: Realm
) : LaunchesCacheDataSource {

    override suspend fun getAll(): List<RocketLaunchEntity> =
        realm.query<RocketLaunchEntity>().find()

    override suspend fun save(launches: List<RocketLaunchEntity>) {
        realm.write {
            delete(query<RocketLaunchEntity>().find())
            launches.forEach { insertOrUpdate(it) }
        }
    }

    override suspend fun clear() {
        realm.write {
            val allRocketLaunchResponse = query<RocketLaunchEntity>().find()
            delete(allRocketLaunchResponse)
        }
    }

    override suspend fun observeAll(): Flow<List<RocketLaunchEntity>> =
        realm.query<RocketLaunchEntity>().find().asFlow().map { change ->
            when (change) {
                is InitialResults, is UpdatedResults -> change.list
            }
        }

    override suspend fun deleteById(id: Int) {
        realm.write {
            val launchEntity = query<RocketLaunchEntity>("id == $0", id).find()
            delete(launchEntity)
        }
    }
}