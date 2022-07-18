package com.vertyce.nfe;

import br.com.swconsultoria.certificado.Certificado;
import br.com.swconsultoria.certificado.exception.CertificadoException;
import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.enuns.AmbienteEnum;
import br.com.swconsultoria.nfe.dom.enuns.EstadosEnum;

public class ConfiguradorNfe {

    /**
     * Cria o objeto com as configurações para emissão de uma nota fiscal.
     * @param certificado objeto do certificado digital (arquivo ou cartão);
     * @return Configurações de NF-e.
     * @throws CertificadoException
     */
    public ConfiguracoesNfe criarConfiguracoesNfe(Certificado certificado) throws CertificadoException {
        return ConfiguracoesNfe.criarConfiguracoes(
                EstadosEnum.AL,
                AmbienteEnum.HOMOLOGACAO,
                certificado,
                "/home/vertyce/IdeaProjects/EmissorNFeSN/schemas");
    }
}
