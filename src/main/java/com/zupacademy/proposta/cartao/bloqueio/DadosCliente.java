package com.zupacademy.proposta.cartao.bloqueio;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class DadosCliente {
    public String buscaUserAgent(HttpServletRequest request) {
        return request.getHeader("USER-AGENT");
    }

    public String buscaIpClient(HttpServletRequest request) {
        String ipClient = request.getHeader("X-FORWARDED-FOR");
        if (ipClient == null)
            ipClient = request.getRemoteAddr();

        return ipClient;
    }

}
