package com.marazmone.samplekmm.data.mapper.base

interface Mapper<SOURCE, RESULT> {

    fun map(source: SOURCE): RESULT

    fun list(source: List<SOURCE>) = source.map { map(it) }
}