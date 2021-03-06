/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to You under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License. You may obtain a
 * copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.telosys.gwtp.base.client.util.validation;

import com.google.gwt.core.shared.GWT;
import com.telosys.gwtp.base.shared.dto.player.PlayerDto;
import com.telosys.gwtp.base.shared.dto.team.TeamDto;

import de.knightsoftnet.validators.client.AbstractGwtReflectGetterFactory;
import de.knightsoftnet.validators.client.GwtReflectGetterInterface;
import de.knightsoftnet.validators.client.GwtValidation;

public class ReflectGetterFactory extends AbstractGwtReflectGetterFactory {

	@GwtValidation(value = { PlayerDto.class, TeamDto.class })
	public interface GwtGetReflector extends GwtReflectGetterInterface {
	}

	@Override
	public GwtReflectGetterInterface createGwtReflectGetter() {
		return GWT.create(GwtGetReflector.class);
	}
}
