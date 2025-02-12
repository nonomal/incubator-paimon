/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.paimon.flink.action.cdc;

import org.apache.paimon.types.DataType;

import org.apache.paimon.shade.jackson2.com.fasterxml.jackson.databind.JsonNode;

import java.io.Serializable;
import java.util.Map;

/**
 * A functional interface for converting CDC metadata.
 *
 * <p>This interface provides a mechanism to convert Change Data Capture (CDC) metadata from a given
 * {@link JsonNode} source. Implementations of this interface can be used to process and transform
 * metadata entries from CDC sources.
 */
public interface CdcMetadataConverter extends Serializable {

    Map<String, String> read(JsonNode payload);

    DataType getDataType();

    String getColumnName();
}
