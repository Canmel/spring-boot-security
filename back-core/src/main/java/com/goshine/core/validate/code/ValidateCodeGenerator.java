/**
 * 
 */
package com.goshine.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author baily
 *
 */
public interface ValidateCodeGenerator {

	ValidateCode createImageCode(ServletWebRequest request);
}
