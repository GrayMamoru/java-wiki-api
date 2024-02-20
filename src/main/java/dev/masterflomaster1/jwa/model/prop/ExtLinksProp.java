package dev.masterflomaster1.jwa.model.prop;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * Returns all external URLs (not interwikis) from the given pages.
 *
 * @see <a href="https://www.mediawiki.org/wiki/API:Extlinks">API:Extlinks</a>
 */
public class ExtLinksProp extends AbstractProp {

    private int elLimit;
    private String elContinue;
    private ElProtocol elProtocol;
    private String elQuery;

    private ExtLinksProp() {
        url = "&prop=extlinks";
    }

    public int getElLimit() {
        return elLimit;
    }

    public String getElContinue() {
        return elContinue;
    }

    public ElProtocol getElProtocol() {
        return elProtocol;
    }

    public String getElQuery() {
        return elQuery;
    }

    public static class Builder {

        private final ExtLinksProp extLinksProp = new ExtLinksProp();

        /**
         * How many links to return.
         * @param elLimit The value must be between 1 and 500.
         * @return {@code Builder}
         */
        public Builder elLimit(int elLimit) {
            extLinksProp.elLimit = elLimit;
            extLinksProp.url += "&ellimit=" + elLimit;
            return this;
        }

        /**
         * When more results are available, use this to continue.
         * More detailed information on how to continue queries can be found on
         * <a href="https://www.mediawiki.org/wiki/API:Continue">mediawiki.org</a>.
         * @return {@code Builder}
         */
        public Builder elContinue(String elContinue) {
            extLinksProp.elContinue = elContinue;
            extLinksProp.url += "&elcontinue=" + URLEncoder.encode(elContinue, StandardCharsets.UTF_8);
            return this;
        }

        /**
         * Protocol of the URL. If empty and {@code elquery} is set, the protocol is {@code http} and {@code https}.
         * Leave both this and {@code elquery} empty to list all external links.
         * @return {@code Builder}
         */
        public Builder elProtocol(ElProtocol elProtocol) {
            extLinksProp.elProtocol = elProtocol;
            extLinksProp.url += "&elprotocol=" + elProtocol.value;
            return this;
        }

        /**
         * Search string without protocol. Useful for checking whether a certain page contains a certain external url.
         * @return {@code Builder}
         */
        public Builder elQuery(String elQuery) {
            extLinksProp.elQuery = elQuery;
            extLinksProp.url += "&elquery=" + elQuery;
            return this;
        }

        public ExtLinksProp build() {
            return extLinksProp;
        }

    }

    public enum ElProtocol {
        BITCOIN ("bitcoin"),
        FTP ("ftp"),
        FTPS ("ftps"),
        GEO ("geo"),
        GIT ("git"),
        GOPHER ("gopher"),
        HTTP ("http"),
        HTTPS ("https"),
        IRC ("irc"),
        IRCS ("ircs"),
        MAGNET ("magnet"),
        MAILTO ("mailto"),
        MATRIX ("matrix"),
        MMS ("mms"),
        NEWS ("news"),
        NNTP ("nntp"),
        REDIS ("redis"),
        SFTP ("sftp"),
        SIP ("sip"),
        SIPS ("sips"),
        SMS ("sms"),
        SSH ("ssh"),
        SVN ("svn"),
        TEL ("tel"),
        TELNET ("telnet"),
        URN ("urn"),
        WORLDWIND ("worldwind"),
        XMPP ("xmpp");

        private final String value;

        ElProtocol(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

}
