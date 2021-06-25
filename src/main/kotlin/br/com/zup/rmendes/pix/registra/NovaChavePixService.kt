package br.com.zup.rmendes.pix.registra

import br.com.zup.rmendes.integration.itau.ContasDeClientesNoItauClient
import br.com.zup.rmendes.pix.ChavePix
import br.com.zup.rmendes.pix.ChavePixExistenteException
import br.com.zup.rmendes.pix.ChavePixRepository
import io.micronaut.validation.Validated
import org.slf4j.LoggerFactory
import javax.inject.Inject
import javax.inject.Singleton
import javax.transaction.Transactional
import javax.validation.Valid

// @Inject val bcbClient: BancoCentralClient,

@Validated
@Singleton
class NovaChavePixService(
    @Inject val repository: ChavePixRepository,
    @Inject val itauClient: ContasDeClientesNoItauClient,
) {

    private val LOGGER = LoggerFactory.getLogger(this::class.java)

    @Transactional
    fun registra(@Valid novaChave: NovaChavePix): ChavePix {

        // 1. verificar se chave existe no sistema.
        if (repository.existsByChave(novaChave.chave))
            throw ChavePixExistenteException("Chave Pix '${novaChave.chave}' foi cadastrada anteriormente")

        // 2. busca dados da conta do cliente no ERP do ITAU
        val response = itauClient.buscaContaPorTipo(novaChave.clienteId!!, novaChave.tipoDeConta!!.name)
        val conta = response.body()?.toModel() ?: throw IllegalStateException("Cliente não encontrado no Itau")

        // 3. grava no Banco de Dados
        val chave = novaChave.toModel(conta)
        repository.save(chave)

        //// **** TODO: ....***

        return chave

    }
}
