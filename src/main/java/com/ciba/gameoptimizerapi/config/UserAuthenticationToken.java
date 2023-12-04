package com.ciba.gameoptimizerapi.config;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

public class UserAuthenticationToken extends AbstractAuthenticationToken {

    private final UserDetails userDetails;

    public UserAuthenticationToken(UserDetails userDetails) {
        super(userDetails.getAuthorities());
        this.userDetails = userDetails;
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() { return null; }

    public Object getPrincipal() { return this.userDetails; }

    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        Assert.isTrue(!isAuthenticated, "Token can't be trusted");
        super.setAuthenticated(false);
    }
}
