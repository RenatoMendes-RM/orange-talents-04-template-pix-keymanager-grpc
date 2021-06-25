package br.com.zup.rmendes.pix.registra

import br.com.zup.rmendes.RegistraChavePixRequest
import br.com.zup.rmendes.TipoDeChave.*
import br.com.zup.rmendes.TipoDeConta.*
import br.com.zup.rmendes.pix.TipoDeChave
import br.com.zup.rmendes.pix.TipoDeConta

fun RegistraChavePixRequest.toModel() : NovaChavePix {
    return NovaChavePix(
        clienteId = clienteId,
        tipo = when (tipoDeChave) {
            UNKNOWN_TIPO_CHAVE -> null
            else -> TipoDeChave.valueOf(tipoDeChave.name) // 1
        },
        chave = chave,
        tipoDeConta = when (tipoDeConta) {
            UNKNOWN_TIPO_CONTA -> null
            else -> TipoDeConta.valueOf(tipoDeConta.name) // 1
        }
    )

}