package org.pac4j.lagom.javadsl;

import com.lightbend.lagom.javadsl.api.transport.RequestHeader;
import org.pac4j.core.context.Cookie;
import org.pac4j.core.context.WebContext;
import org.pac4j.core.context.session.SessionStore;
import org.pac4j.core.exception.TechnicalException;
import play.core.cookie.encoding.ServerCookieDecoder;

import java.util.Collection;
import java.util.Map;

import static play.mvc.Http.HeaderNames.COOKIE;

import static java.lang.String.format;
import static java.util.Collections.emptySet;
import static java.util.stream.Collectors.toList;

/**
 * <p>Implementation web context of PAC4J for Lagom framework.</p>
 * <p>Context is immutable and the {@link SessionStore} is not supported.</p>
 *
 * @author Sergey Morgunov
 * @since 1.0.0
 */
public class LagomWebContext implements WebContext {

    private RequestHeader requestHeader;

    public LagomWebContext(RequestHeader requestHeader) {
        this.requestHeader = requestHeader;
    }

    @Override
    public SessionStore getSessionStore() {
        throw new TechnicalException("Operation not supported");
    }

    @Override
    public String getRequestParameter(String name) {
        throw new TechnicalException("Operation not supported");
    }

    @Override
    public Map<String, String[]> getRequestParameters() {
        throw new TechnicalException("Operation not supported");
    }

    @Override
    public Object getRequestAttribute(String name) {
        throw new TechnicalException("Operation not supported");
    }

    @Override
    public void setRequestAttribute(String name, Object value) {
        throw new TechnicalException("Operation not supported");
    }

    @Override
    public String getRequestHeader(String name) {
        return requestHeader.getHeader(name).orElseThrow(() -> new TechnicalException(format("Header %s not found", name)));
    }

    @Override
    public String getRequestMethod() {
        return requestHeader.method().name();
    }

    @Override
    public String getRemoteAddr() {
        throw new TechnicalException("Operation not supported");
    }

    @Override
    public void writeResponseContent(String content) {
        throw new TechnicalException("Operation not supported");
    }

    @Override
    public void setResponseStatus(int code) {
        throw new TechnicalException("Operation not supported");
    }

    @Override
    public void setResponseHeader(String name, String value) {
        // do nothing
    }

    @Override
    public void setResponseContentType(String content) {
        throw new TechnicalException("Operation not supported");
    }

    @Override
    public String getServerName() {
        throw new TechnicalException("Operation not supported");
    }

    @Override
    public int getServerPort() {
        throw new TechnicalException("Operation not supported");
    }

    @Override
    public String getScheme() {
        throw new TechnicalException("Operation not supported");
    }

    @Override
    public boolean isSecure() {
        return false;
    }

    @Override
    public String getFullRequestURL() {
        throw new TechnicalException("Operation not supported");
    }

    @Override
    public Collection<Cookie> getRequestCookies() {
        return requestHeader.getHeader(COOKIE)
                .map(ServerCookieDecoder.STRICT::decode)
                .orElse(emptySet())
                .stream()
                .map(cookie -> new Cookie(cookie.name(), cookie.value()))
                .collect(toList());
    }

    @Override
    public void addResponseCookie(Cookie cookie) {
        throw new TechnicalException("Operation not supported");
    }

    @Override
    public String getPath() {
        return requestHeader.uri().getPath();
    }
}
