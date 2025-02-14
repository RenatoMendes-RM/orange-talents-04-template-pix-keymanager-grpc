package br.com.zup.rmendes.shared.grpc.handlers

import br.com.zup.rmendes.pix.ChavePixNaoEncontradaException
import br.com.zup.rmendes.shared.grpc.ExceptionHandler
import br.com.zup.rmendes.shared.grpc.ExceptionHandler.*
import io.grpc.Status
import javax.inject.Singleton

@Singleton
class ChavePixNaoEncontradaExceptionHandler : ExceptionHandler<ChavePixNaoEncontradaException> {
    override fun handle(e: ChavePixNaoEncontradaException): ExceptionHandler.StatusWithDetails {
        return ExceptionHandler.StatusWithDetails(
            Status.NOT_FOUND
                .withDescription(e.message)
                .withCause(e)
        )
    }

    override fun supports(e: Exception): Boolean {
        return e is ChavePixNaoEncontradaException
    }

}

class ChavePixNaoEncontradaException(message: String?) : RuntimeException(message)