/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jclouds.opennebula.compute.ec2;

import java.net.URI;
import java.util.Properties;
import java.util.Set;

import org.jclouds.apis.ApiMetadata;

import org.jclouds.ec2.EC2Api;
import org.jclouds.ec2.EC2ApiMetadata;
import org.jclouds.ec2.compute.EC2ComputeServiceContext;
import org.jclouds.ec2.compute.config.EC2ResolveImagesModule;
import org.jclouds.ec2.compute.config.EC2ComputeServiceContextModule;
import org.jclouds.ec2.config.EC2HttpApiModule;
import org.jclouds.rest.internal.BaseHttpApiMetadata;

import static org.jclouds.Constants.PROPERTY_ISO3166_CODES;
import static org.jclouds.location.reference.LocationConstants.ISO3166_CODES;
import static org.jclouds.location.reference.LocationConstants.PROPERTY_REGION;
import static org.jclouds.location.reference.LocationConstants.PROPERTY_REGIONS;

import com.google.common.base.Joiner;
//import com.google.common.collect.ImmutableSet;

import com.google.auto.service.AutoService;
import com.google.common.collect.ImmutableSet;
import com.google.inject.Module;


@AutoService(ApiMetadata.class)
public final class OpenNebulaComputeEC2ApiMetadata extends BaseHttpApiMetadata<EC2Api> {
    
   @Override
   public Builder toBuilder() {
      return new Builder().fromApiMetadata(this);
   }

   public OpenNebulaComputeEC2ApiMetadata() {
      super(new Builder());
   }

   protected OpenNebulaComputeEC2ApiMetadata(Builder builder) {
      super(builder);
   }

   public static final String EU_WEST_1 = "eu-west-1";
   public static final String EU_CENTRAL_1 = "eu-central-1";
   public static final String US_STANDARD = "us-standard";
   public static final String US_EAST_1 = "us-east-1";
   public static final String US_WEST_1 = "us-west-1";
   public static final String US_WEST_2 = "us-west-2";
   public static final String SA_EAST_1 = "sa-east-1";
   public static final String AP_SOUTHEAST_2 = "ap-southeast-2";
   public static final String AP_SOUTHEAST_1 = "ap-southeast-1";
   public static final String AP_NORTHEAST_1 = "ap-northeast-1";

   
   public static final Set<String> DEFAULT_REGIONS = ImmutableSet.of(US_EAST_1, US_WEST_1, US_WEST_2, SA_EAST_1,
         EU_WEST_1, EU_CENTRAL_1, AP_SOUTHEAST_1, AP_SOUTHEAST_2, AP_NORTHEAST_1);
   
   public static Properties defaultProperties() {
      Properties properties = EC2ApiMetadata.defaultProperties();
      
      properties.setProperty(PROPERTY_REGIONS, Joiner.on(',').join(DEFAULT_REGIONS));
      properties.setProperty(PROPERTY_ISO3166_CODES, "US-VA,US-CA,US-OR,BR-SP,IE,DE-HE,SG,AU-NSW,JP-13");
      properties.setProperty(PROPERTY_REGION + "." + US_EAST_1 + "." + ISO3166_CODES, "US-VA");
      properties.setProperty(PROPERTY_REGION + "." + US_WEST_1 + "." + ISO3166_CODES, "US-CA");
      properties.setProperty(PROPERTY_REGION + "." + US_WEST_2 + "." + ISO3166_CODES, "US-OR");
      properties.setProperty(PROPERTY_REGION + "." + SA_EAST_1 + "." + ISO3166_CODES, "BR-SP");
      properties.setProperty(PROPERTY_REGION + "." + EU_WEST_1 + "." + ISO3166_CODES, "IE");
      properties.setProperty(PROPERTY_REGION + "." + EU_CENTRAL_1 + "." + ISO3166_CODES, "DE-HE");
      properties.setProperty(PROPERTY_REGION + "." + AP_SOUTHEAST_1 + "." + ISO3166_CODES, "SG");
      properties.setProperty(PROPERTY_REGION + "." + AP_SOUTHEAST_2 + "." + ISO3166_CODES, "AU-NSW");
      properties.setProperty(PROPERTY_REGION + "." + AP_NORTHEAST_1 + "." + ISO3166_CODES, "JP-13");
      
      return properties;
   }

   public static final class Builder extends BaseHttpApiMetadata.Builder<EC2Api, Builder> {
      public Builder() {
         id("opennebula-compute-ec2")
         .name("OpenNebula Compute EC2 API")
         .version("2016-01-13")
         .identityName("Access Key ID")
         .credentialName("Secret Access Key")
         .defaultEndpoint("http://localhost:4567/")
         .documentation(URI.create("http://docs.amazonwebservices.com/AWSEC2/latest/APIReference"))
         .defaultProperties(OpenNebulaComputeEC2ApiMetadata.defaultProperties())
         .view(EC2ComputeServiceContext.class)
         .defaultModules(ImmutableSet.<Class<? extends Module>>builder()
                                     .add(EC2HttpApiModule.class)
                                     .add(EC2ResolveImagesModule.class)
                                     .add(EC2ComputeServiceContextModule.class).build());
      }

      @Override
      public OpenNebulaComputeEC2ApiMetadata build() {
         return new OpenNebulaComputeEC2ApiMetadata(this);
      }

      @Override
      protected Builder self() {
         return this;
      }
   }
}
