/**
 *  Copyright 2016-2018 the original author or authors.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.antheminc.oss.nimbus.domain.model.state.extension;

import com.antheminc.oss.nimbus.context.BeanResolverStrategy;
import com.antheminc.oss.nimbus.domain.defn.extension.ValuesConditional;
import com.antheminc.oss.nimbus.domain.model.state.EntityState.Param;
import com.antheminc.oss.nimbus.domain.model.state.event.StateEventHandlers.OnStateLoadHandler;

/**
 * 
 * <p>Abstract Conditional State Event handler for updating <tt>Values</tt> annotated fields based
 * on conditional logic defined via configuration during the OnStateLoad event.</p>
 * 
 * @author Tony Lopez
 * @see com.antheminc.oss.nimbus.domain.defn.extension.ValuesConditional
 * @see com.antheminc.oss.nimbus.domain.model.state.extension.AbstractValuesConditionalStateEventHandler
 *
 */
public class ValuesConditionalOnStateLoadEventHandler extends AbstractValuesConditionalStateEventHandler 
	implements OnStateLoadHandler<ValuesConditional> {

	public ValuesConditionalOnStateLoadEventHandler(BeanResolverStrategy beanResolver) {
		super(beanResolver);
	}

	/*
	 * (non-Javadoc)
	 * @see com.anthem.oss.nimbus.core.domain.model.state.event.StateEventHandlers.OnStateLoadHandler#handle(java.lang.annotation.Annotation, com.anthem.oss.nimbus.core.domain.model.state.EntityState.Param)
	 */
	@Override
	public void handle(ValuesConditional configuredAnnotation, Param<?> param) {
		this.handleInternal(configuredAnnotation, param);
	}

}
