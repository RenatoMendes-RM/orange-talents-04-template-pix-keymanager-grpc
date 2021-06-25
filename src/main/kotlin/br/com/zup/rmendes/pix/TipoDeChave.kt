package br.com.zup.rmendes.pix


import io.micronaut.validation.validator.constraints.EmailValidator
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator

enum class TipoDeChave {
    CPF {
        override fun valida(chave: String?): Boolean {
            if (chave.isNullOrBlank()) {
                return false
            }

            if (!chave.matches("^[0-9]{11}$".toRegex())) {
                return false
            }

            return CPFValidator().run {
                initialize(null)
                isValid(chave, null)
            }
        }
    },
    CELULAR {
        override fun valida(chave: String?): Boolean {
            if (chave.isNullOrBlank()) {
                return false
            }
            return chave.matches("^\\+[1-9][0-9]\\d{1,14}\$".toRegex())
        }
    },
    EMAIL {
        override fun valida(chave: String?): Boolean {
            if (chave.isNullOrBlank()) {
                return false
            }
            return EmailValidator().run {
                initialize(null)
                isValid(chave, null)
            }
        }
    },
    ALEATORIA {
        override fun valida(chave: String?) = chave.isNullOrBlank()
                // se for aleatoria, não deve ser preenchida pelo solicitante.
    };

    // função abstrata é criada para usar polimorfismo, conforme acima,
    // na validação de acordo com o tipo de chave
    abstract fun valida(chave: String?): Boolean
}
