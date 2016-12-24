/*
 * Copyright 2002-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.web.reactive.resource;

import java.io.IOException;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;

/**
 * An extension of {@link ByteArrayResource}
 * that a {@link ResourceTransformer} can use to represent an original
 * resource preserving all other information except the content.
 *
 * @author Rossen Stoyanchev
 * @since 5.0
 */
public class TransformedResource extends ByteArrayResource {

	private final String filename;

	private final long lastModified;


	public TransformedResource(Resource original, byte[] transformedContent) {
		super(transformedContent);
		this.filename = original.getFilename();
		try {
			this.lastModified = original.lastModified();
		}
		catch (IOException ex) {
			// should never happen
			throw new IllegalArgumentException(ex);
		}
	}


	@Override
	public String getFilename() {
		return this.filename;
	}

	@Override
	public long lastModified() {
		return this.lastModified;
	}

}