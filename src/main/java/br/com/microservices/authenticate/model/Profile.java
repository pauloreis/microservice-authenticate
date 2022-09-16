package br.com.microservices.authenticate.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Builder
public class Profile implements GrantedAuthority {

    private Long id;
    private String name;

    @Override
    public String getAuthority() {
        return this.name;
    }
}
