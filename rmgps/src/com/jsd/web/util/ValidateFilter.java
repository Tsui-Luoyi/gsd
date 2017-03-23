/**
 *
 */
package com.jsd.web.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author cuilupeng
 *
 */
public class ValidateFilter implements Filter {

	public static boolean isContains(String container, String[] regx) {
		boolean result = false;

		for (int i = 0; i < regx.length; i++) {
			if ((container.indexOf(regx[i])) != -1) {
				return true;
			}
		}
		return result;
	}

	public FilterConfig config;

	public void setFilterConfig(FilterConfig config) {
		this.config = config;
	}

	public FilterConfig getFilterConfig() {
		return config;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest httpreq = (HttpServletRequest) request;
		HttpServletResponse httpres = (HttpServletResponse) response;

		String logonStrings = config.getInitParameter("logonStrings");
		String redirectPath = httpreq.getContextPath()
				+ config.getInitParameter("redirectPath");
		String disabletestfilter = config.getInitParameter("disabletestfilter");

		if (disabletestfilter.toUpperCase().equals("Y")) {
			String[] logonList = logonStrings.split(";");
			Object user = httpreq.getSession().getAttribute("USER");
			if (user == null) {
				if (ValidateFilter.isContains(httpreq.getRequestURI(), logonList)) {
					chain.doFilter(request, response);
					return;
				}
				if (httpreq.getRequestURI().indexOf("login.jsp") > 0) {
					chain.doFilter(request, response);
					return;
				}
				httpres.sendRedirect(redirectPath);
			} else {
				chain.doFilter(request, response);
			}
		}
	}

	@Override
	public void destroy() {
		this.config = null;
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.config = filterConfig;
	}
}
