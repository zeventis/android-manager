package br.com.zeventis.producer.core.plataform

abstract class BaseMapper<T, K> {

    abstract fun transformFrom(s: K): T

    abstract fun transformTo(s: T): K

    fun transformFromList(source: List<K>): List<T> {
        return sour ce.map { src -> transformFrom(src) }
    }

    fun transformToList(source: List<T>): List<K> {
        return source.map { src -> transformTo(src) }
    }
}