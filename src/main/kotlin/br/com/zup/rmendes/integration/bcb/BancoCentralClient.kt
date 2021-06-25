package br.com.zup.rmendes.integration.bcb

import io.micronaut.http.client.annotation.Client


// https://www.bcb.gov.br/content/estabilidadefinanceira/forumpireunioes/api-dict.html

@Client("\${bcb.pix.url}")
interface BancoCentralClient {


}
