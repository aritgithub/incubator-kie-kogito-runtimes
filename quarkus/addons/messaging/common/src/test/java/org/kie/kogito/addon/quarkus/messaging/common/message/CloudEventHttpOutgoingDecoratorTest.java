/*
 * Copyright 2021 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kie.kogito.addon.quarkus.messaging.common.message;

import javax.inject.Inject;

import org.eclipse.microprofile.reactive.messaging.Message;
import org.junit.jupiter.api.Test;

import io.quarkus.reactivemessaging.http.runtime.OutgoingHttpMetadata;
import io.quarkus.test.junit.QuarkusTest;

import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
class CloudEventHttpOutgoingDecoratorTest {

    @Inject
    MessageDecoratorProvider provider;

    @Test
    void verifyOutgoingHttpMetadataIsSet() {
        Message<String> message = Message.of("pepe");
        message = provider.decorate(message);
        assertThat(message.getMetadata(OutgoingHttpMetadata.class)).isNotEmpty();

        /*
         * It would be nice to check if the Content-Type header has the value "application/cloudevents+json".
         * But as far as we know, there's no way to test the actual headers, since OutgoingHttpMetadata#getHeaders is not public.
         * 
         * https://quarkusio.zulipchat.com/#narrow/stream/294206-smallrye/topic/OutgoingHttpMetadata.20has.20no.20public.20methods/near/274438268
         */
    }
}