/*******************************************************************************
* Product of NIST/ITL Advanced Networking Technologies Division (ANTD).        *
*******************************************************************************/
package gov.nist.javax.sip.header;

/**
 * A generic extension header for the stack. 
 * The input text of the header gets recorded here.
 *
 * @version JAIN-SIP-1.1 $Revision: 1.2 $ $Date: 2004-01-22 13:26:29 $
 *
 * @author M. Ranganathan <mranga@nist.gov>  <br/>
 *
 * <a href="{@docRoot}/uncopyright.html">This code is in the public domain.</a>
 */
public class ExtensionHeaderImpl
	extends SIPHeader
	implements javax.sip.header.ExtensionHeader {

	protected String value;

	/**
	 * This was added to allow for automatic cloning of headers.
	 */
	public ExtensionHeaderImpl() {
	}

	public ExtensionHeaderImpl(String headerName) {
		super(headerName);
	}

	/**
	 * Set the name of the header.
	 * @param headerName is the name of the header to set.
	 */

	public void setName(String headerName) {
		this.headerName = headerName;
	}

	/**
	 * Set the value of the header.
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * Get the value of the extension header.
	 * @return the value of the extension header.
	 */
	public String getHeaderValue() {
		if (this.value != null) {
			return this.value;
		} else {
			String encodedHdr = null;
			try {
				// Bug fix submitted by Lamine Brahimi
				encodedHdr = this.encode();
			} catch (Exception ex) {
				return null;
			}
			StringBuffer buffer = new StringBuffer(encodedHdr);
			while (buffer.length() > 0 && buffer.charAt(0) != ':') {
				buffer.deleteCharAt(0);
			}
			buffer.deleteCharAt(0);
			this.value = buffer.toString().trim();
			return this.value;
		}
	}

	/**
	 * Return the canonical encoding of this header.
	 */
	public String encode() {
		return new StringBuffer(this.headerName)
			.append(COLON)
			.append(SP)
			.append(this.value)
			.append(NEWLINE)
			.toString();
	}

	/**
	 * Return just the body of this header encoded (leaving out the
	 * name and the CRLF at the end).
	 */
	public String encodeBody() {
		return this.getHeaderValue();
	}
}
/*
 * $Log: not supported by cvs2svn $
 */
