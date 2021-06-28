package br.com.zup.rmendes.pix.registra

import br.com.zup.rmendes.KeymanagerRegistraGrpcServiceGrpc
import br.com.zup.rmendes.RegistraChavePixRequest
import br.com.zup.rmendes.RegistraChavePixResponse
import br.com.zup.rmendes.shared.grpc.ErrorHandler
import io.grpc.stub.StreamObserver
import javax.inject.Inject
import javax.inject.Singleton

@ErrorHandler
@Singleton
class RegistraChaveEndpoint(@Inject private val service: NovaChavePixService,)
    : KeymanagerRegistraGrpcServiceGrpc.KeymanagerRegistraGrpcServiceImplBase() {
    override fun registra(
        request: RegistraChavePixRequest?,
        responseObserver: StreamObserver<RegistraChavePixResponse>?
    ) {
        val novaChave = request!!.toModel()
        val chaveCriada = service.registra(novaChave)

        responseObserver?.onNext(RegistraChavePixResponse.newBuilder()
            .setClienteId(chaveCriada.clienteId.toString())
            .setPixId(chaveCriada.id.toString())
            .build())
        responseObserver?.onCompleted()
    }

}