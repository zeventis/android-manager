package br.com.zeventis.managerapp.core.plataform

/**
 * Based on https://medium.com/@lupajz/you-either-love-it-or-you-havent-used-it-yet-a55f9b866dbe
 */
sealed class Either<out E, out V> {
    data class Left<out E>(val error: E) : Either<E, Nothing>()
    data class Right<out V>(val value: V) : Either<Nothing, V>()

    private fun <V> right(value: V): Either<Nothing, V> = Right(value)
    private fun <E> left(value: E): Either<E, Nothing> = Left(value)

    fun <V> either(action: () -> V): Either<Exception, V> =
        try {
            right(action())
        } catch (e: Exception) {
            left(e)
        }
}

inline infix fun <E, V, V2> Either<E, V>.map(f: (V) -> V2): Either<E, V2> = when (this) {
    is Either.Left -> this
    is Either.Right -> Either.Right(f(this.value))
}

inline fun <E, V, A> Either<E, V>.fold(e: (E) -> A, v: (V) -> A): A = when (this) {
    is Either.Left -> e(this.error)
    is Either.Right -> v(this.value)
}